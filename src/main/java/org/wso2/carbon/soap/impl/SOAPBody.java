/**
 *  Copyright (c) 2015 WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.wso2.carbon.soap.impl;

import org.wso2.carbon.soap.constants.Constants;
import org.wso2.carbon.soap.constants.SOAP11Constants;
import org.wso2.carbon.soap.constants.SOAP12Constants;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.swing.*;
import javax.xml.parsers.DocumentBuilder;
import java.io.IOException;
import java.io.StringReader;

/**
 * Creates the SOAP body of the SOAP Envelope
 */
public class SOAPBody {
    /**
     * Create the SOAP body of the SOAP Envelope according to the SOAP version
     *
     * @param doc
     * @param docBuilder
     * @return SOAPBody Element
     * @throws Exception
     */
    public Element createSOAPBody(Document doc, DocumentBuilder docBuilder) throws SOAPException {
        String soapVersion = SOAPVersion.soapVersion;
        String namespaceURI = null;
        if (soapVersion.equals(Constants.SOAP11_VERSION)) {
            namespaceURI = SOAP11Constants.SOAP_ENVELOPE_NAMESPACE_URI;
        } else {
            namespaceURI = SOAP12Constants.SOAP_ENVELOPE_NAMESPACE_URI;
        }
        //Creates the main SOAP Body Element
        Element bodyElement = doc.createElementNS(namespaceURI, Constants.BODY);
        bodyElement.setPrefix(Constants.SOAP_NAMESPACE_PREFIX);

        //Attaching the payload request part with params given by the user to the SOAP body
        String requestPayload = getRequestPayloadFromUser("");
        if (requestPayload == null || requestPayload.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please give the payload request part!!", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            Node fragmentNode = addRequestBody(requestPayload, docBuilder, doc);
            bodyElement.appendChild(fragmentNode);
        }

        // Fault Element inside the Body element
        /*SOAPFault soapFault = new SOAPFault();
        Element faultElement = soapFault.createSOAPFault(doc, docBuilder);
        bodyElement.appendChild(faultElement);*/

        return bodyElement;

    }

    /**
     * Attaching the payload request part with params given by the user to the SOAP body
     *
     * @param payload    payload request part given by the user
     * @param docBuilder
     * @param doc
     * @return node element i.e. the string request converted into an xml element by the DOM element
     * @throws Exception
     */
    public Node addRequestBody(String payload, DocumentBuilder docBuilder, Document doc) throws SOAPException {
        Node fragmentNode = null;
        try {
            fragmentNode = docBuilder.parse(new InputSource(new StringReader(payload)))
                    .getDocumentElement();
        }  catch (SAXException e) {
            throw new SOAPException("Error with the XML Parser", e);
        } catch (IOException e) {
            throw new SOAPException("An I/O operation has been failed or interrupted", e);
        }
        fragmentNode = doc.importNode(fragmentNode, true);

        return fragmentNode;

    }

    /**
     * Gets the payload request part with params from the user
     *
     * @return payload request part with params
     */
    public String getRequestPayloadFromUser(String payload) {
        payload = "<ns1:hello xmlns:ns1='http://ode/bpel/unit-test.wsdl'><TestPart>Mellow</TestPart></ns1:hello>";
        return payload;
    }
}
