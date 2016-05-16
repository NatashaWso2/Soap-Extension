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
 * constants used by both SOAP 1.1 and SOAP 1.2 when creating the SOAP Envelope
 */
public interface Constants {


    public static final String SOAP_NAMESPACE_PREFIX = "soapenv";

    public static final String SOAP_ENVELOPE = "Envelope";

    public static final String HEADER = "Header";

    public static final String BODY = "Body";

    public static final String SOAP_FAULT = "Fault";

    public static final String ENCODING_STYLE = "encodingStyle";

    public static final String MUST_UNDERSTAND = "mustUnderstand";

    public static final String SOAP11_VERSION = "soap11";

    public static final String SOAP12_VERSION = "soap12";


}
