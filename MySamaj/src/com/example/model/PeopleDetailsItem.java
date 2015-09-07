package com.example.model;

import java.io.Serializable;

public class PeopleDetailsItem implements Serializable {

	private String intIsFamilyHead;
	private String personId;
	private String intFamilyNo;
	private String NameEN;
	private String AGE;
	private String Gender;
	private String AddressEN;
	private String RelationEN;
	private String MaritalStatusEN;
	private String ShakhEN;
	private String MosalEN;
	private String strEducationEN;
	private String JobDetailEN;
	private String Mobile;
	private String Mobile2;
	private String strEmialID;
	private String member;

	public String getIntIsFamilyHead() {
		return intIsFamilyHead;
	}

	public void setIntIsFamilyHead(String intIsFamilyHead) {
		this.intIsFamilyHead = intIsFamilyHead;
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

	public String getNameEN() {
		return NameEN;
	}

	public void setNameEN(String nameEN) {
		NameEN = nameEN;
	}

	public String getAGE() {
		return AGE;
	}

	public void setAGE(String aGE) {
		AGE = aGE;
	}

	public String getGender() {
		return Gender;
	}

	public void setGender(String gender) {
		Gender = gender;
	}

	public String getAddressEN() {
		return AddressEN;
	}

	public void setAddressEN(String addressEN) {
		AddressEN = addressEN;
	}

	public String getRelationEN() {
		return RelationEN;
	}

	public void setRelationEN(String relationEN) {
		RelationEN = relationEN;
	}

	public String getMaritalStatusEN() {
		return MaritalStatusEN;
	}

	public void setMaritalStatusEN(String maritalStatusEN) {
		MaritalStatusEN = maritalStatusEN;
	}

	public String getShakhEN() {
		return ShakhEN;
	}

	public void setShakhEN(String shakhEN) {
		ShakhEN = shakhEN;
	}

	public String getMosalEN() {
		return MosalEN;
	}

	public void setMosalEN(String mosalEN) {
		MosalEN = mosalEN;
	}

	public String getStrEducationEN() {
		return strEducationEN;
	}

	public void setStrEducationEN(String strEducationEN) {
		this.strEducationEN = strEducationEN;
	}

	public String getJobDetailEN() {
		return JobDetailEN;
	}

	public void setJobDetailEN(String jobDetailEN) {
		JobDetailEN = jobDetailEN;
	}

	public String getMobile() {
		return Mobile;
	}

	public void setMobile(String mobile) {
		Mobile = mobile;
	}

	public String getMobile2() {
		return Mobile2;
	}

	public void setMobile2(String mobile2) {
		Mobile2 = mobile2;
	}

	public String getStrEmialID() {
		return strEmialID;
	}

	public void setStrEmialID(String strEmialID) {
		this.strEmialID = strEmialID;
	}

	public String getMember() {
		return member;
	}

	public void setMember(String member) {
		this.member = member;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return " [ personId=" + personId + "intFamilyNo=" + intFamilyNo
				+ "intIsFamilyHead=" + intIsFamilyHead + "NameEN=" + NameEN
				+ "AGE=" + AGE + "Gender=" + Gender + "AddressEN=" + AddressEN
				+ "RelationEN=" + RelationEN + "MaritalStatusEN"
				+ MaritalStatusEN + "ShakhEN" + ShakhEN + "MosalEN	" + MosalEN
				+ "strEducationEN" + strEducationEN + "JobDetailEN"
				+ JobDetailEN + "Mobile" + Mobile + "Mobile2" + Mobile2
				+ "strEmialID" + strEmialID + "]";

	}
}
