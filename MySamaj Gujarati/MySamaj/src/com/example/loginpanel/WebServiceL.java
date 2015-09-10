package com.example.loginpanel;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.AndroidHttpTransport;

public class WebServiceL {
	// Namespace of the Webservice - can be found in WSDL
	private static String NAMESPACE = "http://tempuri.org/";
	// Webservice URL - WSDL File location
	private static String URL ="http://ws.mysamaj.co.in/WebSOB.asmx";//"http://192.168.0.126/mysamaj/WebSOB.asmx";// //
	// private static String URL =
	// "http://192.168.0.126/loginService/Service.asmx";

	// SOAP Action URI again Namespace + Web method name
	private static String SOAP_ACTION = "http://tempuri.org/m_sptbPersonalDetailMaster_LoginCheck";

	// private static String SOAP_ACTION =
	// "http://tempuri.org/AuthenticateUser";
	private static String WEB_METHOD = "m_sptbPersonalDetailMaster_LoginCheck";

	// private static String WEB_METHOD= "AuthenticateUser";

	public static String invokeLoginWS(String userName, String passWord) {
		// boolean loginStatus = false;

		String loginStatus = null;
		// Create request
		SoapObject request = new SoapObject(NAMESPACE, WEB_METHOD);
		// Property which holds input parameters
		PropertyInfo unamePI = new PropertyInfo();
		PropertyInfo passPI = new PropertyInfo();
		
		// Set Username
		unamePI.setName("userName");
		// Set Value
		unamePI.setValue(userName);
		// Set dataType
		unamePI.setType(String.class);
		// Add the property to request object
		request.addProperty(unamePI);
		// Set Password
		passPI.setName("passWord");
		// Set dataType
		passPI.setValue(passWord);
		// Set dataType
		passPI.setType(String.class);
		// Add the property to request object
		request.addProperty(passPI);
		// Create envelope
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		// Set output SOAP object
		envelope.setOutputSoapObject(request);
		envelope.dotNet = true;

		// Create HTTP call object
		AndroidHttpTransport androidHttpTransport = new AndroidHttpTransport(
				URL);

		try {
			// Invoke web service
			androidHttpTransport.call(SOAP_ACTION, envelope);
			androidHttpTransport.debug = true;
			// Get the response
			//SoapPrimitive sp= (SoapPrimitive)envelope.getResponse();
			
			SoapPrimitive response = (SoapPrimitive) ((SoapObject) envelope.bodyIn)
				.getProperty(0);

			//SoapObject obj = (SoapObject) envelope.bodyIn;
			// Assign it to boolean variable variable
			// loginStatus = Boo lean.parseBoolean(response.toString());
			loginStatus = response.toString();

		} catch (Exception e) {
			// Assign Error Status true in static variable 'errored'
			CheckLoginActivity.errored = true;
			loginStatus = e.toString();
			e.printStackTrace();

		}
		// Return booleam to calling object
		return loginStatus;
	}
}
