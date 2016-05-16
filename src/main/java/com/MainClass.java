package com; /**
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


import com.soap.Impl.SOAPEnvelope;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPConstants;
import javax.xml.soap.SOAPMessage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.net.URL;
import java.nio.charset.Charset;

/**
 * Main class to test the SOAP Envelope Generation
 */
public class MainClass {

    public static void main(String[] args) throws Exception {

        String wsdlUrl = "http://localhost:9763/services/HelloService?wsdl";
        String message = SOAPEnvelope.generateSOAPEnvelope();
        System.out.println("REQUEST  ---->  " +message);
        System.out.println();
        String response = sendSoapRequest(message, wsdlUrl);
        System.out.println("RESPONSE  ---->  " +response);
    }

    private static String sendSoapRequest(String soapMessage, String wsdlUrl) throws Exception {
        URL url = new URL(wsdlUrl);

        // Create the connection
        SOAPConnectionFactory scf = SOAPConnectionFactory.newInstance();
        SOAPConnection conn = scf.createConnection();


        // Create message
        MessageFactory mf = null;
        MimeHeaders hd = new MimeHeaders();

        SOAPMessage msg = mf.newInstance(SOAPConstants.SOAP_1_1_PROTOCOL).createMessage(hd, new ByteArrayInputStream(soapMessage.getBytes(Charset.forName("UTF-8"))));

        // Save message
        msg.saveChanges();

        msg.writeTo(new ByteArrayOutputStream());

        // Send
        SOAPMessage response = conn.call(msg, url);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        response.writeTo(out);
        String responseMessage = new String(out.toByteArray());

        // Close connection
        conn.close();

        return responseMessage;
    }

}
