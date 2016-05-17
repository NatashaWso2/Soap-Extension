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
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.StringWriter;

/**
 * Creates the SOAP Envelope i.e. the SOAP request payload
 */
public class SOAPEnvelope {
    /**
     * Generates the SOAP Envelope
     *
     * @return SOAP Envelope/request and its components as a string to be passed as the input payload request
     */
    public String generateSOAPEnvelope() throws SOAPException {
        String soapRequest = null;
        try {
            DocumentBuilder docBuilder = createDocumentBuilder();
            Document doc = docBuilder.newDocument();

            // SOAP Envelope
            Element rootElement = createSOAPEnvelope(doc);
            doc.appendChild(rootElement);

            // Header Element
            SOAPHeader soapHeader = new SOAPHeader();
            Element headerElement = soapHeader.createSOAPHeader(doc, docBuilder);
            rootElement.appendChild(headerElement);

            // Body Element
            SOAPBody soapBody = new SOAPBody();
            Element bodyElement = soapBody.createSOAPBody(doc, docBuilder);
            rootElement.appendChild(bodyElement);

            //Creating the SOAP Envelope
            Transformer serializer = TransformerFactory.newInstance().newTransformer();
            StringWriter stw = new StringWriter();
            serializer.transform(new DOMSource(doc), new StreamResult(stw));
            soapRequest = stw.toString();

        } catch (TransformerException tfe) {
            throw new SOAPException("Exceptional condition that occured during the transformation process.",tfe);
        }
        return soapRequest;
    }

    /**
     * Creates the documentBuilder object used to create the xml document i.e. the SOAP Envelope
     *
     * @return documentBuilder
     * @throws Exception
     */
    public DocumentBuilder createDocumentBuilder() throws SOAPException{
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = null;
        try {
            docBuilder = docFactory.newDocumentBuilder();
        } catch (ParserConfigurationException pce) {
            throw new SOAPException("Configuration Error",pce);
        }
        docFactory.setNamespaceAware(true);

        return docBuilder;
    }

    /**
     * Creating the SOAP Envelope element according to the SOAP versions
     *
     * @param doc
     * @return SOAP Envelope element
     * @throws Exception
     */
    public Element createSOAPEnvelope(Document doc) throws SOAPException {
        SOAPVersion version = new SOAPVersion();
        String soapVersion = version.getSOAPVersion();
        String namespaceURI = null;
        String encodingNSURI = null;
        if (soapVersion.equals(Constants.SOAP11_VERSION)) {
            namespaceURI = SOAP11Constants.SOAP_ENVELOPE_NAMESPACE_URI;
            encodingNSURI = SOAP11Constants.SOAP_ENCODING_NAMESPACE_URI;
        } else {
            namespaceURI = SOAP12Constants.SOAP_ENVELOPE_NAMESPACE_URI;
            encodingNSURI = SOAP12Constants.SOAP_ENCODING_NAMESPACE_URI;
        }
        Element rootElement = doc.createElementNS(namespaceURI,
                Constants.SOAP_ENVELOPE);
        rootElement.setPrefix(Constants.SOAP_NAMESPACE_PREFIX);

        Attr encodingStyleAttr = doc.createAttributeNS(namespaceURI, Constants.ENCODING_STYLE);
        encodingStyleAttr.setValue(encodingNSURI);
        encodingStyleAttr.setPrefix(Constants.SOAP_NAMESPACE_PREFIX);
        rootElement.setAttributeNode(encodingStyleAttr);

        return rootElement;
    }


}

