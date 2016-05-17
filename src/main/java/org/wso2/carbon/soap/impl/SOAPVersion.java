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

import javax.wsdl.Definition;
import javax.wsdl.Service;
import javax.wsdl.WSDLException;
import javax.wsdl.extensions.soap.SOAPAddress;
import javax.xml.namespace.QName;

/**
 * Get the SOAP version of the request to be created when the Service, Port Name and Operation is given
 */
public class SOAPVersion {

    static String soapVersion;

    /**
     * Returns the soap version of the request
     *
     * @return SOAP version i.e. soap11 or soap12
     * @throws Exception
     */
    public String getSOAPVersion() throws SOAPException{
        Definition def = null;
        try {
            javax.wsdl.xml.WSDLReader wsdlReader11 = javax.wsdl.factory.WSDLFactory.newInstance().newWSDLReader();
            def = wsdlReader11.readWSDL("http://localhost:9763/services/HelloService?wsdl");
        } catch (WSDLException e) {
            throw new SOAPException("Exception when reading the WSDL",e);
        }
        //("/home/natasha/Documents/workspace/SoapHandler/src/main/resources/HelloService.wsdl");
        String serviceName = "HelloService";
        String ns = def.getTargetNamespace();
        String port = "HelloPort";

        QName serviceQName = new QName(ns, serviceName);
        Service service = def.getService(serviceQName);

        SOAPAddress address = (SOAPAddress) service.getPort(port).getExtensibilityElements().get(0);
        String soapVersionNS = address.getElementType().getNamespaceURI();
        if (soapVersionNS.equals(SOAP11Constants.SOAP11_VERSION_NAMESPACE)) {
            soapVersion = Constants.SOAP11_VERSION;
        } else {
            soapVersion = Constants.SOAP12_VERSION;
        }
        return soapVersion;
    }
}
