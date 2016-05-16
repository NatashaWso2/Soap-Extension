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

import java.util.Properties;

/**
 * Defines the transport binding rules specific to each soap version
 */
public class HeaderProperties {
    /**
     * Sets the transport binding rules specific to each soap version
     *
     * @return properties which contains transport binding rules
     * @throws Exception
     */
    public static Properties setHeaderProperties() throws Exception {
        Properties properties = new Properties();
        String soapVersion = SOAPVersion.getSOAPVersion();
        //
        if (soapVersion.equals(Constants.SOAP11_VERSION)) {
            properties.setProperty("Content-Type", SOAP11Constants.SOAP11_CONTENT_TYPE);
            properties.setProperty("Content-Length", "");
            properties.setProperty("SOAPAction", ""); //mandatory
        } else {
            properties.setProperty("Content-Type", SOAP12Constants.SOAP12_CONTENT_TYPE);
            properties.setProperty("Content-Length", "");
            /*properties.setProperty("SOAPAction", ""); //optional*/
        }
        return properties;

    }

}
