package com.example.deletedetails;

import java.nio.channels.ByteChannel;

import org.apache.http.util.ByteArrayBuffer;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.AndroidHttpTransport;

public class WebServiceDeleteDetails {
	private static String NAMESPACE = "http://tempuri.org/";
	private static String URL = "http://ws.mysamaj.co.in/WebSOB.asmx";//"http://192.168.0.126/mysamaj/WebSOB.asmx"; // 
	// SOAP Action URI again http://tempuri.org
	private static String SOAP_ACTION = "http://tempuri.org/";
	private static String WEB_METHOD = "m_sptbPersonDetailMaster_UpdateDeleteRecordByID";
	private static String resTxt = "";

	public static String invokeDeleteDetailsWS(String deletepersonId,
			String intstatus, String deleteReason, String webMethod) {

		// Create request
		String loginStatus = null;
		// Create request
		SoapObject request = new SoapObject(NAMESPACE, webMethod);
		// Property which holds input parameters
		PropertyInfo deletepersonIdPI = new PropertyInfo();
		PropertyInfo intStatusPI = new PropertyInfo();
		PropertyInfo deleteReasonPI = new PropertyInfo();

		deletepersonIdPI.setName("intPersonID");
		deletepersonIdPI.setValue(deletepersonId);
		deletepersonIdPI.setType(String.class);
		request.addProperty(deletepersonIdPI);

		intStatusPI.setName("intstatus");
		intStatusPI.setValue(intstatus);
		intStatusPI.setType(String.class);
		request.addProperty(intStatusPI);

		deleteReasonPI.setName("strReason");
		deleteReasonPI.setValue(deleteReason);
		deleteReasonPI.setType(String.class);
		request.addProperty(deleteReasonPI);

		// Create envelope
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		// Set envelope as dotNet
		envelope.dotNet = true;

		// Set output SOAP object
		envelope.setOutputSoapObject(request);
		// Create HTTP call object
		AndroidHttpTransport androidHttpTransport = new AndroidHttpTransport(
				URL);
		// HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
		try {
			// Invoke web service
			androidHttpTransport.call(SOAP_ACTION + webMethod, envelope);
			androidHttpTransport.debug = true;
			// Get the response
			// SoapPrimitive response = (SoapPrimitive) ((SoapObject) envelope
			// .getResponse()).getAttribute(0);
			SoapPrimitive response = (SoapPrimitive) envelope.getResponse();

			// SoapObject response = (SoapObject) envelope.getResponse();
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

	public static String invokeDeleteDetailsSetFamilyHead(
			String deletePersonId, String intstatus, String deleteReason,
			String newFamilyPersonId, String webMethod) {

		// Create request
		String loginStatus = null;
		// Create request
		SoapObject request = new SoapObject(NAMESPACE, webMethod);
		// Property which holds input parameters
		PropertyInfo deletePersonIdPI = new PropertyInfo();
		PropertyInfo intStatusPI = new PropertyInfo();
		PropertyInfo deleteReasonPI = new PropertyInfo();
		PropertyInfo newFamilyPersonIdPI = new PropertyInfo();

		deletePersonIdPI.setName("intPersonID");
		deletePersonIdPI.setValue(deletePersonId);
		deletePersonIdPI.setType(String.class);
		request.addProperty(deletePersonIdPI);

		intStatusPI.setName("intstatus");
		intStatusPI.setValue(intstatus);
		intStatusPI.setType(String.class);
		request.addProperty(intStatusPI);

		deleteReasonPI.setName("strReason");
		deleteReasonPI.setValue(deleteReason);
		deleteReasonPI.setType(String.class);
		request.addProperty(deleteReasonPI);

		newFamilyPersonIdPI.setName("newFamilyHeadId");
		newFamilyPersonIdPI.setValue(newFamilyPersonId);
		newFamilyPersonIdPI.setType(String.class);
		request.addProperty(newFamilyPersonIdPI);

		// Create envelope
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		// Set envelope as dotNet
		envelope.dotNet = true;

		// Set output SOAP object
		envelope.setOutputSoapObject(request);
		// Create HTTP call object
		AndroidHttpTransport androidHttpTransport = new AndroidHttpTransport(
				URL);
		// HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
		try {
			// Invoke web service
			androidHttpTransport.call(SOAP_ACTION + webMethod, envelope);
			androidHttpTransport.debug = true;
			// Get the response
			// SoapPrimitive response = (SoapPrimitive) ((SoapObject) envelope
			// .getResponse()).getAttribute(0);
			SoapPrimitive response = (SoapPrimitive) envelope.getResponse();

			// SoapObject response = (SoapObject) envelope.getResponse();
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
