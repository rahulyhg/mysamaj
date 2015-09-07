package com.example.mysamajmain;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

public class WebService {

	private static String NAMESPACE = "http://tempuri.org/";
	// Webservice URL - It is asmx file location hosted in the server in case of
	// .Net
	// Change the IP address to your machine IP address
	private static String URL = "http://ws.mysamaj.co.in/WebSOB.asmx";//"http://192.168.0.126/MyWebServices/WebService1.asmx";
	// SOAP Action URI again http://tempuri.org
	private static String SOAP_ACTION = "http://tempuri.org/getImageData";
	private static String WEB_METHOD = "m_sptbClientLogin_GetUserByUserMobile";
	private static String resTxt = "";

	public static String invokeHelloWorldWS(String webMethName) {

		// Create request
		SoapObject request = new SoapObject(NAMESPACE, WEB_METHOD);
		// Create envelope
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		// Set envelope as dotNet
		envelope.dotNet = true;

		// Set output SOAP object
		envelope.setOutputSoapObject(request);
		// Create HTTP call object
		// AndroidHttpTransport androidHttpTransport = new
		// AndroidHttpTransport(URL);
		HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
		try {
			// Invoke web service
			androidHttpTransport.call(SOAP_ACTION, envelope);
			androidHttpTransport.debug = true;
			// Get the response
			SoapPrimitive response = (SoapPrimitive) envelope.getResponse();

			// SoapObject obj = (SoapObject) envelope.getResponse();
			// Assign it to resTxt variable static variable

			// resTxt = response.toString();

			resTxt = response.toString();

		} catch (Exception e) {
			// Print error
			e.printStackTrace();
			// Assign error message to resTxt
			resTxt = e.toString();
		}
		// Return resTxt to calling object
		return resTxt;
	}

}
