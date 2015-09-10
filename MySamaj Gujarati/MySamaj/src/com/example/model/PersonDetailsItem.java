package com.example.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class PersonDetailsItem implements Serializable {
	public int id;
	private String personId, intFamilyNo, intIsFamilyHead, strSurnameEN,
			strNameEN, strFatherNameEN, intAge, intGender, intAddressID,
			intRelationID, intMaritalStatus, intShakhID, intWardID, intMosalEN,
			strMosalOtherGJ, strEducationEN, intSem, intJobEN, strJobDetailEN,
			strMobile, strMobile2, strFbLink, dtBirthDate, strEmailid,
			strProfileImage, intEducationEN, intVillageID, strSurnameGJ,
			strNameGJ, strFatherNameGJ, strJobDetailGJ;

	public PersonDetailsItem(int id, String name, String phoneNo, String email) {
		// TODO Auto-generated constructor stub
		this.id = id;
		this.strNameEN = name;
		this.strEmailid = email;
	}

	public PersonDetailsItem(String intFamilyNo, String intIsFamilyHead,
			String strSurnameEN, String strNameEN, String strFatherNameEN,
			String strSurnameGJ, String strNameGJ, String strFatherNameGJ,
			String strJobDetailGJ, String intAge, String intGender,
			String intAddressID, String intRelationID, String intMaritalStatus,
			String intShakhID, String intWardID, String intMosalEN,
			String strMosalOtherGJ, String strEducationEN,
			String intEducationEN, String intSem, String intJobEN,
			String strJobDetailEN, String strMobile, String strMobile2,
			String strFbLink, String dtBirthDate, String strEmailid,
			String strProfileImage, String intVillageID) {
		super();
		this.intFamilyNo = intFamilyNo;
		this.intIsFamilyHead = intIsFamilyHead;
		this.strSurnameEN = strSurnameEN;
		this.strNameEN = strNameEN;
		this.strFatherNameEN = strFatherNameEN;
		this.intAge = intAge;
		this.intGender = intGender;
		this.intAddressID = intAddressID;
		this.intRelationID = intRelationID;
		this.intMaritalStatus = intMaritalStatus;
		this.intShakhID = intShakhID;
		this.intWardID = intWardID;
		this.intMosalEN = intMosalEN;
		this.strMosalOtherGJ = strMosalOtherGJ;
		this.strEducationEN = strEducationEN;
		this.intSem = intSem;
		this.intJobEN = intJobEN;
		this.strJobDetailEN = strJobDetailEN;
		this.strMobile = strMobile;
		this.strMobile2 = strMobile2;
		this.strFbLink = strFbLink;
		this.dtBirthDate = dtBirthDate;
		this.strEmailid = strEmailid;
		this.strProfileImage = strProfileImage;
		this.intEducationEN = intEducationEN;
		this.intVillageID = intVillageID;
		this.strSurnameGJ = strSurnameGJ;
		this.strNameGJ = strNameGJ;
		this.strFatherNameGJ = strFatherNameGJ;
		this.strJobDetailGJ = strJobDetailGJ;
	}

	public String getStrJobDetailGJ() {
		return strJobDetailGJ;
	}

	public void setStrJobDetailGJ(String strJobDetailGJ) {
		this.strJobDetailGJ = strJobDetailGJ;
	}

	public String getStrSurnameGJ() {
		return strSurnameGJ;
	}

	public void setStrSurnameGJ(String strSurnameGJ) {
		this.strSurnameGJ = strSurnameGJ;
	}

	public String getStrNameGJ() {
		return strNameGJ;
	}

	public void setStrNameGJ(String strNameGJ) {
		this.strNameGJ = strNameGJ;
	}

	public String getStrFatherNameGJ() {
		return strFatherNameGJ;
	}

	public void setStrFatherNameGJ(String strFatherNameGJ) {
		this.strFatherNameGJ = strFatherNameGJ;
	}

	public String getIntVillageID() {
		return intVillageID;
	}

	public void setIntVillageID(String intVillageID) {
		this.intVillageID = intVillageID;
	}

	public String getIntEducationEN() {
		return intEducationEN;
	}

	public void setIntEducationEN(String intEducationEN) {
		this.intEducationEN = intEducationEN;
	}

	public PersonDetailsItem() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPersonId() {
		return personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}

	public String getIntFamilyNo() {
		return intFamilyNo;
	}

	public void setIntFamilyNo(String intFamilyNo) {
		this.intFamilyNo = intFamilyNo;
	}

	public String getIntIsFamilyHead() {
		return intIsFamilyHead;
	}

	public void setIntIsFamilyHead(String intIsFamilyHead) {
		this.intIsFamilyHead = intIsFamilyHead;
	}

	public String getStrSurnameEN() {
		return strSurnameEN;
	}

	public void setStrSurnameEN(String strSurnameEN) {
		this.strSurnameEN = strSurnameEN;
	}

	public String getStrNameEN() {
		return strNameEN;
	}

	public void setStrNameEN(String strNameEN) {
		this.strNameEN = strNameEN;
	}

	public String getStrFatherNameEN() {
		return strFatherNameEN;
	}

	public void setStrFatherNameEN(String strFatherNameEN) {
		this.strFatherNameEN = strFatherNameEN;
	}

	public String getIntAge() {
		return intAge;
	}

	public void setIntAge(String intAge) {
		this.intAge = intAge;
	}

	public String getIntGender() {
		return intGender;
	}

	public void setIntGender(String intGender) {
		this.intGender = intGender;
	}

	public String getIntAddressID() {
		return intAddressID;
	}

	public void setIntAddressID(String intAddressID) {
		this.intAddressID = intAddressID;
	}

	public String getIntRelationID() {
		return intRelationID;
	}

	public void setIntRelationID(String intRelationID) {
		this.intRelationID = intRelationID;
	}

	public String getIntMaritalStatus() {
		return intMaritalStatus;
	}

	public void setIntMaritalStatus(String intMaritalStatus) {
		this.intMaritalStatus = intMaritalStatus;
	}

	public String getIntShakhID() {
		return intShakhID;
	}

	public void setIntShakhID(String intShakhID) {
		this.intShakhID = intShakhID;
	}

	public String getIntWardID() {
		return intWardID;
	}

	public void setIntWardID(String intWardID) {
		this.intWardID = intWardID;
	}

	public String getIntMosalEN() {
		return intMosalEN;
	}

	public void setIntMosalEN(String intMosalEN) {
		this.intMosalEN = intMosalEN;
	}

	public String getstrMosalOtherGJ() {
		return strMosalOtherGJ;
	}

	public void setstrMosalOtherGJ(String strMosalOtherGJ) {
		this.strMosalOtherGJ = strMosalOtherGJ;
	}

	public String getStrEducationEN() {
		return strEducationEN;
	}

	public void setStrEducationEN(String strEducationEN) {
		this.strEducationEN = strEducationEN;
	}

	public String getIntSem() {
		return intSem;
	}

	public void setIntSem(String intSem) {
		this.intSem = intSem;
	}

	public String getIntJobEN() {
		return intJobEN;
	}

	public void setIntJobEN(String intJobEN) {
		this.intJobEN = intJobEN;
	}

	public String getStrJobDetailEN() {
		return strJobDetailEN;
	}

	public void setStrJobDetailEN(String strJobDetailEN) {
		this.strJobDetailEN = strJobDetailEN;
	}

	public String getStrMobile() {
		return strMobile;
	}

	public void setStrMobile(String strMobile) {
		this.strMobile = strMobile;
	}

	public String getStrMobile2() {
		return strMobile2;
	}

	public void setStrMobile2(String strMobile2) {
		this.strMobile2 = strMobile2;
	}

	public String getStrFbLink() {
		return strFbLink;
	}

	public void setStrFbLink(String strFbLink) {
		this.strFbLink = strFbLink;
	}

	public String getDtBirthDate() {
		return dtBirthDate;
	}

	public void setDtBirthDate(String dtBirthDate) {
		this.dtBirthDate = dtBirthDate;
	}

	public String getStrEmailid() {
		return strEmailid;
	}

	public void setStrEmailid(String strEmailid) {
		this.strEmailid = strEmailid;
	}

	public String getStrProfileImage() {
		return strProfileImage;
	}

	public void setStrProfileImage(String strProfileImage) {
		this.strProfileImage = strProfileImage;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return " [ intPersonID=" + personId + "intFamilyNo=" + intFamilyNo
				+ "intIsFamilyHead=" + intIsFamilyHead + "strSurnameEN="
				+ strSurnameEN + "strNameEN=" + strNameEN + "strFatherNameEN="
				+ strFatherNameEN + "intAge=" + intAge + "intGender="
				+ intGender + "intAddressID=" + intAddressID + "intRelationID="
				+ intRelationID + "intMaritalStatus" + intMaritalStatus
				+ "intShakhID" + intShakhID + "intWardID" + intWardID
				+ "intMosalEN	" + intMosalEN + "strMosalOtherGJ"
				+ strMosalOtherGJ + "strEducationEN" + strEducationEN
				+ "intSem" + intSem + "intJobEN" + intJobEN + "strJobDetailEN"
				+ strJobDetailEN + "strMobile" + strMobile + "strMobile2"
				+ strMobile2 + "strFbLink" + strFbLink + "dtBirthDate"
				+ dtBirthDate + "strEmailid" + strEmailid + "strProfileImage"
				+ strProfileImage + "intVillageID=" + intVillageID + "]";

	}
}
