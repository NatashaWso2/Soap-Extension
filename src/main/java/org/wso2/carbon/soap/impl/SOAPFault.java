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
    public static Element createSOAPFault(Document doc, DocumentBuilder docBuilder) throws Exception {
        String soapVersion = SOAPVersion.soapVersion;
        String namespaceURI = null;
        Element faultElement = null;
        if (soapVersion.equals(Constants.SOAP11_VERSION)) {
            namespaceURI = SOAP11Constants.SOAP_ENVELOPE_NAMESPACE_URI;
            faultElement = SOAP11Fault.createSOAP11FaultModel(doc, docBuilder, namespaceURI);
        } else {
            namespaceURI = SOAP12Constants.SOAP_ENVELOPE_NAMESPACE_URI;
            faultElement = SOAP12Fault.createSOAP12FaultModel(doc, docBuilder, namespaceURI);
        }

        return faultElement;
    }
/*

    // ------- SOAP 1.1 ----------

    */
/**
     * Creates the SOAP Fault Model for SOAP 1.1
     *
     * @param doc
     * @param docBuilder
     * @param namespaceURI
     * @return SOAP Fault Model element for SOAP 1.1
     * @throws Exception
     *//*

    public static Element createSOAP11FaultModel(Document doc, DocumentBuilder docBuilder, String namespaceURI) throws Exception {

        Element faultElement = doc.createElementNS(namespaceURI, constants.SOAP_FAULT);
        faultElement.setPrefix(constants.SOAP_NAMESPACE_PREFIX);

        Element faultCodeElement = createSOAP11FaultCode(doc);
        faultElement.appendChild(faultCodeElement);

        Element faultStringElement = createSOAP11FaultString(doc);
        faultElement.appendChild(faultStringElement);

        Element faultDetilsElement = createSOAP11FaultDetails(doc, docBuilder);
        faultElement.appendChild(faultDetilsElement);

        return faultElement;

    }

    */
/**
     * Creates the SOAP Fault Code for SOAP 1.1
     *
     * @param doc
     * @return SOAP Fault Code element for SOAP 1.1
     *//*

    public static Element createSOAP11FaultCode(Document doc) {
        Element faultCodeElement = doc.createElement(SOAP11Constants.SOAP_FAULT_CODE);
        faultCodeElement.appendChild(doc.createTextNode("env:Server"));
        return faultCodeElement;
    }

    */
/**
     * Creates the SOAP Fault String/Cause for SOAP 1.1
     *
     * @param doc
     * @return SOAP Fault String element for SOAP 1.1
     *//*

    public static Element createSOAP11FaultString(Document doc) {
        Element faultStringElement = doc.createElement(SOAP11Constants.SOAP_FAULT_STRING);
        faultStringElement.appendChild(doc.createTextNode("Processing error"));
        return faultStringElement;
    }

    */
/**
     * Creates the SOAP Fault Detail for SOAP 1.1
     *
     * @param doc
     * @return SOAP Fault Detail element for SOAP 1.1
     *//*

    public static Element createSOAP11FaultDetails(Document doc, DocumentBuilder docBuilder) throws Exception {
        Element faultDetailsElement = doc.createElement(SOAP11Constants.SOAP_FAULT_DETAIL);

        //Add a user defined fault detail node
        String faultDetails = getFaultDetailsFromUser();
        Node fragmentNode = addFaultDetail(faultDetails, docBuilder, doc);
        faultDetailsElement.appendChild(fragmentNode);

        return faultDetailsElement;
    }
*/

    // ------- SOAP 1.2 ----------

    /**
     * Creates the SOAP Fault Model for SOAP 1.2
     *
     * @param doc
     * @param docBuilder
     * @param namespaceURI
     * @return SOAP Fault Model element for SOAP 1.2
     * @throws Exception
     */
    /*public static Element createSOAP12FaultModel(Document doc, DocumentBuilder docBuilder, String namespaceURI) throws Exception {

        Element faultElement = doc.createElementNS(namespaceURI, constants.SOAP_FAULT);
        faultElement.setPrefix(constants.SOAP_NAMESPACE_PREFIX);

        Element faultCodeElement = createSOAP12FaultCode(doc, namespaceURI);
        faultElement.appendChild(faultCodeElement);

        Element faultReasonElement = createSOAP12FaultReason(doc, namespaceURI);
        faultElement.appendChild(faultReasonElement);

        Element faultDetailsElement = createSOAP12FaultDetails(doc, docBuilder, namespaceURI);
        faultElement.appendChild(faultDetailsElement);

        return faultElement;

    }

    *//**
     * Creates the SOAP Fault Code element for SOAP 1.2
     *
     * @param doc
     * @param namespaceURI
     * @return SOAP Fault Code element for SOAP 1.2
     *//*
    public static Element createSOAP12FaultCode(Document doc, String namespaceURI) {
        Element faultCodeElement = doc.createElementNS(namespaceURI, SOAP12Constants.SOAP_FAULT_CODE);
        faultCodeElement.setPrefix(constants.SOAP_NAMESPACE_PREFIX);

        //Set Value to the Fault Code
        Element faultCodeValueElement = createSOAP12FaultCodeValue(doc, namespaceURI);
        faultCodeElement.appendChild(faultCodeValueElement);

        //Set Fault Subcode
        Element faultSubCodeElement = createSOAP12FaultSubCode(doc, namespaceURI);
        faultCodeElement.appendChild(faultSubCodeElement);

        return faultCodeElement;
    }

    *//**
     * Creates the SOAP Fault Code Value for SOAP 1.2
     *
     * @param doc
     * @param namespaceURI
     * @return SOAP Fault Code Value for SOAP 1.2
     *//*
    public static Element createSOAP12FaultCodeValue(Document doc, String namespaceURI) {
        Element faultCodeValueElement = doc.createElementNS(namespaceURI, SOAP12Constants.SOAP_FAULT_VALUE);
        faultCodeValueElement.setPrefix(constants.SOAP_NAMESPACE_PREFIX);
        faultCodeValueElement.appendChild(doc.createTextNode("env:Sender"));
        return faultCodeValueElement;
    }

    *//**
     * Creates the SOAP Fault SubCode element for SOAP 1.2
     *
     * @param doc
     * @param namespaceURI
     * @return SOAP Fault SubCode element for SOAP 1.2
     *//*
    public static Element createSOAP12FaultSubCode(Document doc, String namespaceURI) {
        Element faultSubCodeElement = doc.createElementNS(namespaceURI, SOAP12Constants.SOAP_FAULT_SUBCODE);
        faultSubCodeElement.setPrefix(constants.SOAP_NAMESPACE_PREFIX);

        //Set Value to SubCode
        Element faultSubCodeValueElement = createSOAP12FaultSubCodeValue(doc, namespaceURI);
        faultSubCodeElement.appendChild(faultSubCodeValueElement);


        return faultSubCodeElement;
    }

    *//**
     * Creates the SOAP Fault SubCode Value element for SOAP 1.2
     *
     * @param doc
     * @param namespaceURI
     * @return SOAP Fault SubCode Value element for SOAP 1.2
     *//*
    public static Element createSOAP12FaultSubCodeValue(Document doc, String namespaceURI) {
        Element faultSubCodeValueElement = doc.createElementNS(namespaceURI, SOAP12Constants.SOAP_FAULT_VALUE);
        faultSubCodeValueElement.setPrefix(constants.SOAP_NAMESPACE_PREFIX);
        faultSubCodeValueElement.appendChild(doc.createTextNode("mycode:SomeError"));
        return faultSubCodeValueElement;
    }

    *//**
     * Creates the SOAP Fault Reason element for SOAP 1.2
     *
     * @param doc
     * @param namespaceURI
     * @return SOAP Fault Reason element for SOAP 1.2
     *//*
    public static Element createSOAP12FaultReason(Document doc, String namespaceURI) {
        Element faultCodeElement = doc.createElementNS(namespaceURI, SOAP12Constants.SOAP_FAULT_REASON);
        faultCodeElement.setPrefix(constants.SOAP_NAMESPACE_PREFIX);

        //Set Value to the Fault Reason
        Element faultCodeValueElement = createSOAP12FaultReasonText(doc, namespaceURI);
        faultCodeElement.appendChild(faultCodeValueElement);

        return faultCodeElement;
    }

    *//**
     * Creates the SOAP Fault Reason Text element for SOAP 1.2
     *
     * @param doc
     * @param namespaceURI
     * @return SOAP Fault Reason Text element for SOAP 1.2
     *//*
    public static Element createSOAP12FaultReasonText(Document doc, String namespaceURI) {
        Element faultSubCodeValueElement = doc.createElementNS(namespaceURI, SOAP12Constants.SOAP_FAULT_TEXT);
        faultSubCodeValueElement.setPrefix(constants.SOAP_NAMESPACE_PREFIX);
        faultSubCodeValueElement.appendChild(doc.createTextNode("Processing error"));

        //Setting attribute to the Text element
        Attr textAttr = setAttrSOAP12FaultResonText(doc);
        faultSubCodeValueElement.setAttributeNode(textAttr);

        return faultSubCodeValueElement;
    }

    *//**
     * Set attribute to fault reason text
     *
     * @param doc
     * @return fault reason text attribute
     *//*
    public static Attr setAttrSOAP12FaultResonText(Document doc) {

        Attr textAttr = doc.createAttribute(SOAP12Constants.SOAP_FAULT_TEXT_LANG);
        // textAttr.setPrefix(SOAP12Constants.SOAP_FAULT_TEXT_LANG_PREFIX);
        textAttr.setValue(SOAP12Constants.SOAP_FAULT_TEXT_LANG_NAMESPACE);
        return textAttr;
    }

    *//**
     * Creates the SOAP Fault Detail element for SOAP 1.2
     *
     * @param doc
     * @param namespaceURI
     * @return SOAP Fault Detail element for SOAP 1.2
     *//*
    public static Element createSOAP12FaultDetails(Document doc, DocumentBuilder docBuilder, String namespaceURI) throws Exception {
        Element faultDetailsElement = doc.createElementNS(namespaceURI, SOAP12Constants.SOAP_FAULT_DETAIL);
        faultDetailsElement.setPrefix(constants.SOAP_NAMESPACE_PREFIX);

        //Add a user defined fault detail node
        String faultDetails = getFaultDetailsFromUser();
        Node fragmentNode = addFaultDetail(faultDetails, docBuilder, doc);
        faultDetailsElement.appendChild(fragmentNode);

        return faultDetailsElement;
    }
*/
    /**
     * Attaching the fault detail part given by the user
     *
     * @param faultDetails
     * @param docBuilder
     * @param doc
     * @return node element i.e. the fault details converted into an xml element by the DOM element
     * @throws Exception
     */
    public static Node addFaultDetail(String faultDetails, DocumentBuilder docBuilder, Document doc) throws Exception {
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
    public static String getFaultDetailsFromUser() {
        String faultDetails = " <e:myFaultDetails xmlns:e=\"http://myexample.org/faults\" ><e:message>Invalid credit card details</e:message> <e:errorcode>999</e:errorcode></e:myFaultDetails>";
        return faultDetails;
    }


}
