package com.example.model;

import java.io.Serializable;

public class PersonDetailsItem implements Serializable {
	public int id;
	private String personId, intFamilyNo, intIsFamilyHead, strSurnameEN,
			strNameEN, strFatherNameEN, intAge, intGender, intAddressID,
			intRelationID, intMaritalStatus, intShakhID, intWardID, intMosalEN,
			strMosalOtherEN, strEducationEN, intSem, intJobEN, strJobDetailEN,
			strMobile, strMobile2, strFbLink, dtBirthDate, strEmailid,
			strProfileImage, intEducationEN, intVillageID;

	public PersonDetailsItem(int id, String name, String phoneNo, String email) {
		// TODO Auto-generated constructor stub
		this.id = id;
		this.strNameEN = name;
		this.strEmailid = email;
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

	public PersonDetailsItem(String strNameEN2, String strMobile3,
			String strEmailid2) {
		// TODO Auto-generated constructor stub
		this.strNameEN = strNameEN2;
		this.strMobile = strMobile3;
		this.strEmailid = strEmailid2;
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

	public String getStrMosalOtherEN() {
		return strMosalOtherEN;
	}

	public void setStrMosalOtherEN(String strMosalOtherEN) {
		this.strMosalOtherEN = strMosalOtherEN;
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
				+ "intMosalEN	" + intMosalEN + "strMosalOtherEN"
				+ strMosalOtherEN + "strEducationEN" + strEducationEN
				+ "intSem" + intSem + "intJobEN" + intJobEN + "strJobDetailEN"
				+ strJobDetailEN + "strMobile" + strMobile + "strMobile2"
				+ strMobile2 + "strFbLink" + strFbLink + "dtBirthDate"
				+ dtBirthDate + "strEmailid" + strEmailid + "strProfileImage"
				+ strProfileImage + "intVillageID=" + intVillageID + "]";

	}
}
