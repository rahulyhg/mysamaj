package com.example.updatedetails;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.AndroidHttpTransport;

public class WebServiceSpiner {
	private static String NAMESPACE = "http://tempuri.org/";
	// Webservice URL - It is asmx file location hosted in the server in case of
	// .Net
	// Change the IP address to your machine IP address
	private static String URL = "http://ws.mysamaj.co.in/WebSOB.asmx"; //"http://192.168.0.126/mysamaj/WebSOB.asmx";
	// SOAP Action URI again http://tempuri.org
	private static String SOAP_ACTION = "http://tempuri.org/";
	private static String resTxt = "";

	public static String invokeHelloWorldWS(String webMethod) {

		// Create request
		SoapObject request = new SoapObject(NAMESPACE, webMethod);

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
		try {
			// Invoke web service
			androidHttpTransport.call(SOAP_ACTION, envelope);
			androidHttpTransport.debug = true;

			SoapPrimitive response = (SoapPrimitive) envelope.getResponse();

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

	public static String invokeGenderMaster(String webMethod) {

		SoapObject request = new SoapObject(NAMESPACE, webMethod);

		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		envelope.dotNet = true;
		envelope.setOutputSoapObject(request);
		AndroidHttpTransport androidHttpTransport = new AndroidHttpTransport(
				URL);
		try {
			androidHttpTransport.call(SOAP_ACTION + webMethod, envelope);
			androidHttpTransport.debug = true;
			SoapPrimitive response = (SoapPrimitive) envelope.getResponse();
			resTxt = response.toString();
		} catch (Exception e) {
			e.printStackTrace();
			resTxt = e.toString();
		}
		return resTxt;
	}

	public static String invokeRelationMaster(String webMethod) {
		SoapObject request = new SoapObject(NAMESPACE, webMethod);

		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		envelope.dotNet = true;
		envelope.setOutputSoapObject(request);
		AndroidHttpTransport androidHttpTransport = new AndroidHttpTransport(
				URL);
		try {
			androidHttpTransport.call(SOAP_ACTION + webMethod, envelope);
			androidHttpTransport.debug = true;
			SoapPrimitive response = (SoapPrimitive) envelope.getResponse();
			resTxt = response.toString();
		} catch (Exception e) {
			e.printStackTrace();
			resTxt = e.toString();
		}
		return resTxt;
	}

	public static String invokeMaritalStatusMaster(String webMethod) {

		SoapObject request = new SoapObject(NAMESPACE, webMethod);

		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		envelope.dotNet = true;
		envelope.setOutputSoapObject(request);
		AndroidHttpTransport androidHttpTransport = new AndroidHttpTransport(
				URL);
		try {
			androidHttpTransport.call(SOAP_ACTION + webMethod, envelope);
			androidHttpTransport.debug = true;
			SoapPrimitive response = (SoapPrimitive) envelope.getResponse();
			resTxt = response.toString();
		} catch (Exception e) {
			e.printStackTrace();
			resTxt = e.toString();
		}
		return resTxt;
	}

	public static String invokeEducationMaster(String webMethod) {

		SoapObject request = new SoapObject(NAMESPACE, webMethod);

		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		envelope.dotNet = true;
		envelope.setOutputSoapObject(request);
		AndroidHttpTransport androidHttpTransport = new AndroidHttpTransport(
				URL);
		try {
			androidHttpTransport.call(SOAP_ACTION + webMethod, envelope);
			androidHttpTransport.debug = true;
			SoapPrimitive response = (SoapPrimitive) envelope.getResponse();
			resTxt = response.toString();
		} catch (Exception e) {
			e.printStackTrace();
			resTxt = e.toString();
		}
		return resTxt;
	}

	public static String invokeShakhVillageMaster(String webMethod) {

		SoapObject request = new SoapObject(NAMESPACE, webMethod);

		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		envelope.dotNet = true;
		envelope.setOutputSoapObject(request);
		AndroidHttpTransport androidHttpTransport = new AndroidHttpTransport(
				URL);
		try {
			androidHttpTransport.call(SOAP_ACTION + webMethod, envelope);
			androidHttpTransport.debug = true;
			SoapPrimitive response = (SoapPrimitive) envelope.getResponse();
			resTxt = response.toString();
		} catch (Exception e) {
			e.printStackTrace();
			resTxt = e.toString();
		}
		return resTxt;
	}

	public static String invokeJobMaster(String webMethod) {

		SoapObject request = new SoapObject(NAMESPACE, webMethod);

		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		envelope.dotNet = true;
		envelope.setOutputSoapObject(request);
		AndroidHttpTransport androidHttpTransport = new AndroidHttpTransport(
				URL);
		try {
			androidHttpTransport.call(SOAP_ACTION + webMethod, envelope);
			androidHttpTransport.debug = true;
			SoapPrimitive response = (SoapPrimitive) envelope.getResponse();
			resTxt = response.toString();
		} catch (Exception e) {
			e.printStackTrace();
			resTxt = e.toString();
		}
		return resTxt;
	}

	public static String invokeWardMasterMaster(String webMethod) {

		SoapObject request = new SoapObject(NAMESPACE, webMethod);

		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		envelope.dotNet = true;
		envelope.setOutputSoapObject(request);
		AndroidHttpTransport androidHttpTransport = new AndroidHttpTransport(
				URL);
		try {
			androidHttpTransport.call(SOAP_ACTION + webMethod, envelope);
			androidHttpTransport.debug = true;
			SoapPrimitive response = (SoapPrimitive) envelope.getResponse();
			resTxt = response.toString();
		} catch (Exception e) {
			e.printStackTrace();
			resTxt = e.toString();
		}
		return resTxt;
	}
}
