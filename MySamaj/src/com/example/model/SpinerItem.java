package com.example.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class SpinerItem implements Serializable {

	public String spinerId;
	public String spinerName;

	public SpinerItem(String spinerId, String spinerName) {
		super();
		this.spinerId = spinerId;
		this.spinerName = spinerName;
	}

	public SpinerItem() {
		super();
	}

	public String getSpinerId() {
		return spinerId;
	}

	public void setSpinerId(String spinerId) {
		this.spinerId = spinerId;
	}

	public String getSpinerName() {
		return spinerName;
	}

	public void setSpinerName(String spinerName) {
		this.spinerName = spinerName;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "[spinerName=" + spinerName + "spinerId=" + spinerId + "]";
	}

}
