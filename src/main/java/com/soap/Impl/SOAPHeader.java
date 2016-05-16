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
package com.soap.Impl;

import com.soap.Constants.Constants;
import com.soap.Constants.SOAP11Constants;
import com.soap.Constants.SOAP12Constants;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import java.io.StringReader;

/**
 * Creates the SOAP Headers of the SOAP request
 */
public class SOAPHeader {
    /**
     * Creates the SOAP Header element according to the SOAP version
     *
     * @param doc
     * @param docBuilder
     * @return SOAP Header element
     * @throws Exception
     */
    public static Element createSOAPHeader(Document doc, DocumentBuilder docBuilder) throws Exception {
        String soapVersion = SOAPVersion.soapVersion;
        String namespaceURI = null;
        String specifiedEndpoint = null;
        if (soapVersion.equals(Constants.SOAP11_VERSION)) {
            namespaceURI = SOAP11Constants.SOAP_ENVELOPE_NAMESPACE_URI;
            specifiedEndpoint = SOAP11Constants.ACTOR;
        } else {
            namespaceURI = SOAP12Constants.SOAP_ENVELOPE_NAMESPACE_URI;
            specifiedEndpoint = SOAP12Constants.SOAP_ROLE;
        }
        Element headerElement = doc.createElementNS(namespaceURI, Constants.HEADER);
        headerElement.setPrefix(Constants.SOAP_NAMESPACE_PREFIX);

       /* //Must Understand attribute
        Attr mustUnderstandAttr = createMustUnderstandAttr(doc, namespaceURI);
        headerElement.setAttributeNode(mustUnderstandAttr);

        // Specific Endpoint attribute actor/role
        Attr specifiedEndpointAttr = createSpecifiedEndpointAttr(doc, namespaceURI, specifiedEndpoint);
        headerElement.setAttributeNode(specifiedEndpointAttr);*/

        //Attaching the header block the params given by the user to the SOAP Header
        String headerBlock = getHeaderBlock();
        if (headerBlock == null || headerBlock.isEmpty()) {
        } else {
            Node fragmentNode = addHeaderBlock(headerBlock, docBuilder, doc);
            headerElement.appendChild(fragmentNode);
        }

        return headerElement;

    }

    /**
     * Creates the mustUnderstand attribute which indicates whether a header entry is mandatory or optional
     *
     * @param doc
     * @param namespaceURI
     * @return mustUnderstand attribute
     */
    public static Attr createMustUnderstandAttr(Document doc, String namespaceURI) {
        Attr mustUnderstandAttr = doc.createAttributeNS(namespaceURI, Constants.MUST_UNDERSTAND);
        mustUnderstandAttr.setValue("1");
        mustUnderstandAttr.setPrefix(Constants.SOAP_NAMESPACE_PREFIX);
        return mustUnderstandAttr;
    }

    /**
     * Creates the actor/role attribute which is used to address the Header element to a specific endpoint
     *
     * @param doc
     * @param namespaceURI
     * @param specifiedEndpoint
     * @return actor/role attribute
     */
    public static Attr createSpecifiedEndpointAttr(Document doc, String namespaceURI, String specifiedEndpoint) {
        Attr specifiedEndpointAttr = doc.createAttributeNS(namespaceURI, specifiedEndpoint);
        specifiedEndpointAttr.setValue("");
        specifiedEndpointAttr.setPrefix(Constants.SOAP_NAMESPACE_PREFIX);
        return specifiedEndpointAttr;
    }

    /**
     * Attaching the header block with params given by the user to the SOAP Header
     *
     * @param headers
     * @param docBuilder
     * @param doc
     * @return node element i.e. the header block converted into an xml element by the DOM element
     * @throws Exception
     */
    public static Node addHeaderBlock(String headers, DocumentBuilder docBuilder, Document doc) throws Exception {
        Node fragmentNode = docBuilder.parse(new InputSource(new StringReader(headers)))
                .getDocumentElement();
        fragmentNode = doc.importNode(fragmentNode, true);

        return fragmentNode;

    }

    /**
     * Gets the header block from the user
     *
     * @return header block
     */
    public static String getHeaderBlock() {
        //Sample header block is for SOAP 1.1. This is specified by the user
        String headers = "<m:Trans xmlns:m=\"http://www.w3schools.com/transaction/\" soapenv:actor=\"http://www.w3schools.com/appml/\">234 </m:Trans>";
        return headers;
    }

}
