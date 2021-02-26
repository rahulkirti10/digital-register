package com.docsapi.entities;

import org.springframework.web.bind.annotation.RequestBody;

public class UserAddressDetails {

	private String b_add1;
	private String b_add2;
	private String pin;
	private String city_state_country;
	public String getB_add1() {
		return b_add1;
	}
	public void setB_add1(String b_add1) {
		this.b_add1 = b_add1;
	}
	public String getB_add2() {
		return b_add2;
	}
	public void setB_add2(String b_add2) {
		this.b_add2 = b_add2;
	}
	public String getPin() {
		return pin;
	}
	public void setPin(String pin) {
		this.pin = pin;
	}
	public String getCity_state_country() {
		return city_state_country;
	}
	public void setCity_state_country(String city_state_country) {
		this.city_state_country = city_state_country;
	}
	public UserAddressDetails(String b_add1, String b_add2, String pin, String city_state_country) {
		super();
		this.b_add1 = b_add1;
		this.b_add2 = b_add2;
		this.pin = pin;
		this.city_state_country = city_state_country;
	}
	public UserAddressDetails() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}

