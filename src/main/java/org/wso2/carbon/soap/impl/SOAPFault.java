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

import javax.xml.parsers.DocumentBuilder;
import java.io.StringReader;

/**
 * Creates the SOAP Fault Element with its components
 */
public class SOAPFault {
    /**
     * Creates the SOAP Fault Element
     *
     * @param doc
     * @param docBuilder
     * @return SOAP Fault element
     * @throws Exception
     */
    public Element createSOAPFault(Document doc, DocumentBuilder docBuilder) throws Exception {
        String soapVersion = SOAPVersion.soapVersion;
        String namespaceURI = null;
        Element faultElement = null;
        if (soapVersion.equals(Constants.SOAP11_VERSION)) {
            namespaceURI = SOAP11Constants.SOAP_ENVELOPE_NAMESPACE_URI;
            SOAP11Fault soap11Fault = new SOAP11Fault();
            faultElement = soap11Fault.createSOAP11FaultModel(doc, docBuilder, namespaceURI);
        } else {
            namespaceURI = SOAP12Constants.SOAP_ENVELOPE_NAMESPACE_URI;
            SOAP12Fault soap12Fault = new SOAP12Fault();
            faultElement = soap12Fault.createSOAP12FaultModel(doc, docBuilder, namespaceURI);
        }

        return faultElement;
    }

    /**
     * Attaching the fault detail part given by the user
     *
     * @param faultDetails
     * @param docBuilder
     * @param doc
     * @return node element i.e. the fault details converted into an xml element by the DOM element
     * @throws Exception
     */
    public Node addFaultDetail(String faultDetails, DocumentBuilder docBuilder, Document doc) throws Exception {
        Node fragmentNode = docBuilder.parse(new InputSource(new StringReader(faultDetails)))
                .getDocumentElement();
        fragmentNode = doc.importNode(fragmentNode, true);

        return fragmentNode;

    }

    /**
     * Get the fault detail string from the user
     *
     * @return fault detail string
     */
    public String getFaultDetailsFromUser() {
        String faultDetails = " <e:myFaultDetails xmlns:e=\"http://myexample.org/faults\" ><e:message>Invalid credit card details</e:message> <e:errorcode>999</e:errorcode></e:myFaultDetails>";
        return faultDetails;
    }


}
