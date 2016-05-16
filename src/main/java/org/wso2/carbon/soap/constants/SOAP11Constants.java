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
 * constants specific to SOAP 1.1
 */
public interface SOAP11Constants extends Constants {

    public static final String SOAP_ENVELOPE_NAMESPACE_URI = "http://schemas.xmlsoap.org/soap/envelope/";

    public static final String SOAP_ENCODING_NAMESPACE_URI = "http://schemas.xmlsoap.org/soap/encoding/";

    public static final String SOAP11_CONTENT_TYPE = "text/xml";

    public static final String ACTOR = "actor";

    public static final String SOAP11_VERSION_NAMESPACE = "http://schemas.xmlsoap.org/wsdl/soap/";

    //Fault Codes

    public static final String SOAP_FAULT_CODE = "faultcode";

    public static final String SOAP_FAULT_STRING = "faultstring";

    public static final String SOAP_FAULT_DETAIL = "detail";

    public static final String SOAP_FAULT_ACTOR = "faultactor";

}
