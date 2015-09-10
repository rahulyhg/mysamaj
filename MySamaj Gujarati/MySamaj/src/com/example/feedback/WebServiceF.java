package com.example.feedback;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

public class WebServiceF {

	private static String NAMESPACE = "http://tempuri.org/";
	// Webservice URL - It is asmx file location hosted in the server in case of
	// .Net
	// Change the IP address to your machine IP address
	private static String URL = "http://ws.mysamaj.co.in/WebSOB.asmx";// "http://192.168.0.126/MyWebServices/WebService1.asmx";
	// SOAP Action URI again http://tempuri.org
	private static String SOAP_ACTION = "http://tempuri.org/getImageData";
	private static String resTxt = "";

	public static String invokeHelloWorldWS(String webMethName,
			String fullname, String mobileNo, String emailID, String feedback) {

		// Create request
		SoapObject request = new SoapObject(NAMESPACE, webMethName);

		PropertyInfo fullproInfo = new PropertyInfo();
		PropertyInfo mobileproInfo = new PropertyInfo();
		PropertyInfo emailproInfo = new PropertyInfo();
		PropertyInfo feedbakProInfo = new PropertyInfo();

		fullproInfo.setName("fullname");
		fullproInfo.setValue(fullname);
		fullproInfo.setType(String.class);
		request.addProperty(fullproInfo);

		mobileproInfo.setName("mobileNo");
		mobileproInfo.setValue(mobileNo);
		mobileproInfo.setType(String.class);
		request.addProperty(mobileproInfo);

		emailproInfo.setName("emailid");
		emailproInfo.setValue(emailID);
		emailproInfo.setType(String.class);
		request.addProperty(emailproInfo);

		feedbakProInfo.setName("feedback");
		feedbakProInfo.setValue(feedback);
		feedbakProInfo.setType(String.class);
		request.addProperty(feedbakProInfo);

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
