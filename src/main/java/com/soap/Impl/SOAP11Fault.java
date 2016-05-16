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
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilder;

public class SOAP11Fault {

    // ------- SOAP 1.1 ----------

    /**
     * Creates the SOAP Fault Model for SOAP 1.1
     *
     * @param doc
     * @param docBuilder
     * @param namespaceURI
     * @return SOAP Fault Model element for SOAP 1.1
     * @throws Exception
     */
    public static Element createSOAP11FaultModel(Document doc, DocumentBuilder docBuilder, String namespaceURI) throws Exception {

        Element faultElement = doc.createElementNS(namespaceURI, Constants.SOAP_FAULT);
        faultElement.setPrefix(Constants.SOAP_NAMESPACE_PREFIX);

        Element faultCodeElement = createSOAP11FaultCode(doc);
        faultElement.appendChild(faultCodeElement);

        Element faultStringElement = createSOAP11FaultString(doc);
        faultElement.appendChild(faultStringElement);

        Element faultDetilsElement = createSOAP11FaultDetails(doc, docBuilder);
        faultElement.appendChild(faultDetilsElement);

        return faultElement;

    }

    /**
     * Creates the SOAP Fault Code for SOAP 1.1
     *
     * @param doc
     * @return SOAP Fault Code element for SOAP 1.1
     */
    public static Element createSOAP11FaultCode(Document doc) {
        Element faultCodeElement = doc.createElement(SOAP11Constants.SOAP_FAULT_CODE);
        faultCodeElement.appendChild(doc.createTextNode("env:Server"));
        return faultCodeElement;
    }

    /**
     * Creates the SOAP Fault String/Cause for SOAP 1.1
     *
     * @param doc
     * @return SOAP Fault String element for SOAP 1.1
     */
    public static Element createSOAP11FaultString(Document doc) {
        Element faultStringElement = doc.createElement(SOAP11Constants.SOAP_FAULT_STRING);
        faultStringElement.appendChild(doc.createTextNode("Processing error"));
        return faultStringElement;
    }

    /**
     * Creates the SOAP Fault Detail for SOAP 1.1
     *
     * @param doc
     * @return SOAP Fault Detail element for SOAP 1.1
     */
    public static Element createSOAP11FaultDetails(Document doc, DocumentBuilder docBuilder) throws Exception {
        Element faultDetailsElement = doc.createElement(SOAP11Constants.SOAP_FAULT_DETAIL);

        //Add a user defined fault detail node
        String faultDetails = SOAPFault.getFaultDetailsFromUser();
        Node fragmentNode = SOAPFault.addFaultDetail(faultDetails, docBuilder, doc);
        faultDetailsElement.appendChild(fragmentNode);

        return faultDetailsElement;
    }

}
