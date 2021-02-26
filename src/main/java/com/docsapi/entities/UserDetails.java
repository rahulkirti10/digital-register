package com.docsapi.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class UserDetails {
	
	@Id
	private String user_id;
	
	private String phone_no;
	private String b_name;
	private String b_add1;
	private String b_add2;
	private String pin;
	private String city_state_country;
	private String document_count;
	private String branding;
	private String b_details;
	
	
	public UserDetails(String user_id, String phone_no, String b_name, String b_add1, String b_add2, String pin,
			String city_state_country, String document_count, List<Document> doc_list) {
		super();
		this.user_id = user_id;
		this.phone_no = phone_no;
		this.b_name = b_name;
		this.b_add1 = b_add1;
		this.b_add2 = b_add2;
		this.pin = pin;
		this.city_state_country = city_state_country;
		this.document_count = document_count;
		this.doc_list = doc_list;
	}

	public String getDocument_count() {
		return document_count;
	}

	public void setDocument_count(String document_count) {
		this.document_count = document_count;
	}

	@JsonManagedReference
	@OneToMany(mappedBy = "details",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	private List<Document> doc_list;

	public String getBranding() {
		return branding;
	}

	public void setBranding(String branding) {
		this.branding = branding;
	}

	public String getB_details() {
		return b_details;
	}

	public void setB_details(String b_details) {
		this.b_details = b_details;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	

	

	public UserDetails(String user_id, String phone_no, String b_name, String b_add1, String b_add2, String pin,
			String city_state_country, String document_count, String branding, String b_details,
			List<Document> doc_list) {
		super();
		this.user_id = user_id;
		this.phone_no = phone_no;
		this.b_name = b_name;
		this.b_add1 = b_add1;
		this.b_add2 = b_add2;
		this.pin = pin;
		this.city_state_country = city_state_country;
		this.document_count = document_count;
		this.branding = branding;
		this.b_details = b_details;
		this.doc_list = doc_list;
	}

	public String getPhone_no() {
		return phone_no;
	}

	public void setPhone_no(String phone_no) {
		this.phone_no = phone_no;
	}

	public String getB_name() {
		return b_name;
	}

	public void setB_name(String b_name) {
		this.b_name = b_name;
	}

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

	public List<Document> getDoc_list() {
		return doc_list;
	}

	public void setDoc_list(List<Document> doc_list) {
		this.doc_list = doc_list;
	}

	public UserDetails() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "UserDetails [user_id=" + user_id + ", phone_no=" + phone_no + ", b_name=" + b_name + ", b_add1="
				+ b_add1 + ", b_add2=" + b_add2 + ", pin=" + pin + ", city_state_country=" + city_state_country
				+ ", document_count=" + document_count + ", branding=" + branding + ", b_details=" + b_details
				+ ", doc_list=" + doc_list + "]";
	}

	
	
	
}
