package com.example.loginpanel;

import java.util.Hashtable;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

public class Category implements KvmSerializable {

	public String intUserID;
	public String strMobile;
	public String strPassword;

	public String getIntUserID() {
		return intUserID;
	}

	public void setIntUserID(String intUserID) {
		this.intUserID = intUserID;
	}

	public String getStrMobile() {
		return strMobile;
	}

	public void setStrMobile(String strMobile) {
		this.strMobile = strMobile;
	}

	public String getStrPassword() {
		return strPassword;
	}

	public void setStrPassword(String strPassword) {
		this.strPassword = strPassword;
	}

	public Category() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Category(String intUserID, String strMobile, String strPassword) {
		super();
		this.intUserID = intUserID;
		this.strMobile = strMobile;
		this.strPassword = strPassword;
	}

	@Override
	public Object getProperty(int arg0) {
		// TODO Auto-generated method stub

		switch (arg0) {
		case 0:
			return intUserID;
		case 1:
			return strMobile;
		case 2:
			return strPassword;
		}

		return null;
	}

	@Override
	public int getPropertyCount() {
		// TODO Auto-generated method stub

		return 3;
	}

	@Override
	public void getPropertyInfo(int index, Hashtable arg1, PropertyInfo info) {
		// TODO Auto-generated method stub
		switch (index) {
		case 0:
			info.type = PropertyInfo.INTEGER_CLASS;
			info.name = "intUserID";
			break;
		case 1:
			info.type = PropertyInfo.STRING_CLASS;
			info.name = "strMobile";
			break;
		case 2:
			info.type = PropertyInfo.STRING_CLASS;
			info.name = "strPassword";
			break;
		default:
			break;
		}
	}

	@Override
	public void setProperty(int index, Object value) {
		// TODO Auto-generated method stub
		switch (index) {
		case 0:
			intUserID = value.toString();//Integer.parseInt(value.toString());
			break;
		case 1:
			strMobile = value.toString();
			break;
		case 2:
			strPassword = value.toString();
			break;
		default:
			break;
		}
	}

	
}
