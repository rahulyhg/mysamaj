package com.example.updatedetails;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.AndroidHttpTransport;

public class WebServiceUpdateDetails {
	private static String NAMESPACE = "http://tempuri.org/";
	private static String URL = "http://192.168.0.126/mysamaj/WebSOB.asmx";//"http://ws.mysamaj.co.in/WebSOB.asmx";// 
																		 //
	// SOAP Action URI again http://tempuri.org
	private static String SOAP_ACTION = "http://tempuri.org/";
	private static String WEB_METHOD = "m_sptbPersonalDetailMaster_Update";
	private static String resTxt = "";

	public static String invokeUpdateDetailsWS(String personId, String surName,
			String name, String fatherName, String genderId, String mobileNo1,
			String mobileNo2, String jobDetails, String emailId, String fbUrl,
			String dob, String relationId, String mosalId,
			String maritalStatusId, String jobTYpeId, String educationId,
			String strEducationEN, String shakhId, String wardId,
			String mosalOther, String updatedById, String age, String webMethod) {

		// Create request
		String loginStatus = null;
		// Create request
		SoapObject request = new SoapObject(NAMESPACE, webMethod);
		// Property which holds input parameters
		PropertyInfo unamePI = new PropertyInfo();
		PropertyInfo surNamePI = new PropertyInfo();
		PropertyInfo namePI = new PropertyInfo();
		PropertyInfo fatherNamePI = new PropertyInfo();
		PropertyInfo genderIdPI = new PropertyInfo();
		PropertyInfo mobileNo1PI = new PropertyInfo();
		PropertyInfo mobileNo2PI = new PropertyInfo();
		PropertyInfo jobDetailsPI = new PropertyInfo();
		PropertyInfo emailIdPI = new PropertyInfo();
		PropertyInfo fbUrlPI = new PropertyInfo();
		PropertyInfo dobPI = new PropertyInfo();
		PropertyInfo relationIdPI = new PropertyInfo();
		PropertyInfo mosalIdPI = new PropertyInfo();
		PropertyInfo maritalStatusIdPI = new PropertyInfo();
		PropertyInfo jobTypePI = new PropertyInfo();
		PropertyInfo educationIdPI = new PropertyInfo();
		PropertyInfo educationDetailsENPI = new PropertyInfo();
		PropertyInfo shakhID = new PropertyInfo();
		PropertyInfo wardIdPI = new PropertyInfo();
		PropertyInfo mosalOtherPI = new PropertyInfo();
		PropertyInfo updatedByIdPI = new PropertyInfo();
		PropertyInfo agePI = new PropertyInfo();

		unamePI.setName("personId");
		unamePI.setValue(personId);
		unamePI.setType(String.class);
		request.addProperty(unamePI);

		surNamePI.setName("surName");
		surNamePI.setValue(surName);
		surNamePI.setType(String.class);
		request.addProperty(surNamePI);

		namePI.setName("name");
		namePI.setValue(name);
		namePI.setType(String.class);
		request.addProperty(namePI);

		fatherNamePI.setName("fatherName");
		fatherNamePI.setValue(fatherName);
		fatherNamePI.setType(String.class);
		request.addProperty(fatherNamePI);

		genderIdPI.setName("genderId");
		genderIdPI.setValue(genderId);
		genderIdPI.setType(Integer.class);
		request.addProperty(genderIdPI);

		mobileNo1PI.setName("mobile1");
		mobileNo1PI.setValue(mobileNo1);
		mobileNo1PI.setType(String.class);
		request.addProperty(mobileNo1PI);

		mobileNo2PI.setName("mobile2");
		mobileNo2PI.setValue(mobileNo2);
		mobileNo2PI.setType(String.class);
		request.addProperty(mobileNo2PI);

		jobDetailsPI.setName("jobDetails");
		jobDetailsPI.setValue(jobDetails);
		jobDetailsPI.setType(String.class);
		request.addProperty(jobDetailsPI);

		emailIdPI.setName("emailId");
		emailIdPI.setValue(emailId);
		emailIdPI.setType(String.class);
		request.addProperty(emailIdPI);

		fbUrlPI.setName("fbUrl");
		fbUrlPI.setValue(fbUrl);
		fbUrlPI.setType(String.class);
		request.addProperty(fbUrlPI);

		dobPI.setName("dob");
		dobPI.setValue(dob);
		dobPI.setType(String.class);
		request.addProperty(dobPI);

		relationIdPI.setName("relationId");
		relationIdPI.setValue(relationId);
		relationIdPI.setType(String.class);
		request.addProperty(relationIdPI);

		mosalIdPI.setName("mosalId");
		mosalIdPI.setValue(mosalId);
		mosalIdPI.setType(String.class);
		request.addProperty(mosalIdPI);

		maritalStatusIdPI.setName("maritalStatusId");
		maritalStatusIdPI.setValue(maritalStatusId);
		maritalStatusIdPI.setType(String.class);
		request.addProperty(maritalStatusIdPI);

		jobTypePI.setName("jobTypeId");
		jobTypePI.setValue(jobTYpeId);
		jobTypePI.setType(String.class);
		request.addProperty(jobTypePI);

		educationIdPI.setName("educationId");
		educationIdPI.setValue(educationId);
		educationIdPI.setType(String.class);
		request.addProperty(educationIdPI);

		educationDetailsENPI.setName("educationDetailsEN");
		educationDetailsENPI.setValue(strEducationEN);
		educationDetailsENPI.setType(String.class);
		request.addProperty(educationDetailsENPI);

		shakhID.setName("shakhId");
		shakhID.setValue(shakhId);
		shakhID.setType(String.class);
		request.addProperty(shakhID);

		wardIdPI.setName("wardId");
		wardIdPI.setValue(wardId);
		wardIdPI.setType(String.class);
		request.addProperty(wardIdPI);

		mosalOtherPI.setName("MosalOther");
		mosalOtherPI.setValue(mosalOther);
		mosalOtherPI.setType(String.class);
		request.addProperty(mosalOtherPI);

		updatedByIdPI.setName("updatedById");
		updatedByIdPI.setValue(updatedById);
		updatedByIdPI.setType(String.class);
		request.addProperty(updatedByIdPI);

		agePI.setName("age");
		agePI.setValue(age);
		agePI.setType(String.class);
		request.addProperty(agePI);

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

	public static String invokeUpdateImagesWS(String personId, String shakhId,
			String IntFamilyNo, String imageInByte, String webMethod) {
		SoapObject request = new SoapObject(NAMESPACE, webMethod);
		// Property which holds input parameters
		PropertyInfo imagePI = new PropertyInfo();
		imagePI.setName("StrFileImage");
		imagePI.setValue(imageInByte);
		imagePI.setType(String.class);
		request.addProperty(imagePI);

		PropertyInfo shakhIdPI = new PropertyInfo();
		shakhIdPI.setName("StrFileImage");
		shakhIdPI.setValue(imageInByte);
		shakhIdPI.setType(String.class);
		request.addProperty(shakhIdPI);

		PropertyInfo IntFamilyNoPI = new PropertyInfo();
		IntFamilyNoPI.setName("StrFileImage");
		IntFamilyNoPI.setValue(imageInByte);
		IntFamilyNoPI.setType(String.class);
		request.addProperty(IntFamilyNoPI);

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
