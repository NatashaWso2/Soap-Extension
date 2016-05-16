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
package org.wso2.carbon.soap.constants;

/**
 * constants specific to SOAP 1.2
 */
public interface SOAP12Constants extends Constants {

    public static final String SOAP_ENVELOPE_NAMESPACE_URI = "http://www.w3.org/2003/05/soap-envelope";

    public static final String SOAP_ENCODING_NAMESPACE_URI = "http://www.w3.org/2003/05/soap-encoding";

    public static final String SOAP12_CONTENT_TYPE = "application/soap+xml";

    public static final String SOAP_ROLE = "role";

    //SOAP Fault Codes

    /**
     * <env:Code>
     * <env:Value>env:Sender</env:Value>
     * <env:Subcode>
     * <env:Value>mycode:SomeError</env:Value>
     * </env:Subcode>
     * </env:Code>
     */

    public static final String SOAP_FAULT_CODE = "Code";
    public static final String SOAP_FAULT_SUBCODE = "Subcode";
    public static final String SOAP_FAULT_VALUE = "Value";

    // SOAP Fault Reason
    /**
     * <env:Reason>
     * <env:Text xml:lang="en-US">Processing error</env:Text>
     * </env:Reason>
     */
    public static final String SOAP_FAULT_REASON = "Reason";
    public static final String SOAP_FAULT_TEXT = "Text";
    public static final String SOAP_FAULT_TEXT_LANG = "lang";
    public static final String SOAP_FAULT_TEXT_LANG_PREFIX = "xml";
    public static final String SOAP_FAULT_TEXT_LANG_NAMESPACE = "en-US";

    // SOAP Fault Detail
    /**
     * <env:Detail>
     * <e:myFaultDetails xmlns:e="http://myexample.org/faults" >
     * <e:message>Invalid credit card details</e:message>
     * <e:errorcode>999</e:errorcode>
     * </e:myFaultDetails>
     * </env:Detail>
     */
    public static final String SOAP_FAULT_DETAIL = "Detail";

    // SOAP Fault Role

    public static final String SOAP_FAULT_ROLE = "Role";
}
