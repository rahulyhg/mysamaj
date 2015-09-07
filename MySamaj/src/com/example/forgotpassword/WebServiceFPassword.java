package com.example.forgotpassword;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.AndroidHttpTransport;

public class WebServiceFPassword {

	private static String NAMESPACE = "http://tempuri.org/";
	// Webservice URL - WSDL File location
	private static String URL = "http://ws.mysamaj.co.in/WebSOB.asmx";//"http://192.168.0.126/mysamaj/WebSOB.asmx";// //
	// private static String URL =
	// "http://192.168.0.126/loginService/Service.asmx";

	// SOAP Action URI again Namespace + Web method name
	private static String SOAP_ACTION = "http://tempuri.org/";

	// private static String SOAP_ACTION =
	// "http://tempuri.org/AuthenticateUser";
	//private static String WEB_METHOD = "m_sptbPersonalDetailMaster_LoginCheck";

	// private static String WEB_METHOD= "AuthenticateUser";
	public static String invokeWebMethod(String username, String WebMethod) {
		// boolean loginStatus = false;

		String loginStatus = null;
		// Create request
		SoapObject request = new SoapObject(NAMESPACE, WebMethod);
		// Property which holds input parameters
		PropertyInfo passPI = new PropertyInfo();

		// Set Password
		passPI.setName("username");
		// Set dataType
		passPI.setValue(username);
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
			androidHttpTransport.call(SOAP_ACTION + WebMethod, envelope);
			androidHttpTransport.debug = true;
			// Get the response
			// SoapPrimitive sp= (SoapPrimitive)envelope.getResponse();

			SoapPrimitive response = (SoapPrimitive) ((SoapObject) envelope.bodyIn)
					.getProperty(0);

			// SoapObject obj = (SoapObject) envelope.bodyIn;
			// Assign it to boolean variable variable
			// loginStatus = Boo lean.parseBoolean(response.toString());
			loginStatus = response.toString();

		} catch (Exception e) {
			// Assign Error Status true in static variable 'errored'
			loginStatus = e.toString();
			e.printStackTrace();

		}
		// Return booleam to calling object
		return loginStatus;
	}

	public static String invokeGeneratePassword(String personID,
			String password, String WebMethod) {
		// boolean loginStatus = false;

		String loginStatus = null;
		// Create request
		SoapObject request = new SoapObject(NAMESPACE, WebMethod);
		// Property which holds input parameters
		PropertyInfo userNamePI = new PropertyInfo();
		PropertyInfo passPI = new PropertyInfo();

		userNamePI.setName("personId");
		userNamePI.setValue(personID);
		userNamePI.setType(String.class);
		request.addProperty(userNamePI);

		passPI.setName("password");
		passPI.setValue(password);
		passPI.setType(String.class);
		request.addProperty(passPI);

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
			androidHttpTransport.call(SOAP_ACTION + WebMethod, envelope);
			androidHttpTransport.debug = true;
			// Get the response
			// SoapPrimitive sp= (SoapPrimitive)envelope.getResponse();

			SoapPrimitive response = (SoapPrimitive) ((SoapObject) envelope.bodyIn)
					.getProperty(0);

			// SoapObject obj = (SoapObject) envelope.bodyIn;
			// Assign it to boolean variable variable
			// loginStatus = Boo lean.parseBoolean(response.toString());
			loginStatus = response.toString();

		} catch (Exception e) {
			// Assign Error Status true in static variable 'errored'
			loginStatus = e.toString();
			e.printStackTrace();

		}
		// Return booleam to calling object
		return loginStatus;
	}
}
