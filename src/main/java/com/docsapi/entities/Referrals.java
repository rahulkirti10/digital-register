package com.docsapi.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Referrals {

	public Referrals() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Id
	@GeneratedValue(
		    strategy= GenerationType.AUTO,
		    generator="native"
		)
		@GenericGenerator(
		    name = "native",
		    strategy = "native"
		)
	private long id;
	public Referrals(long id, String referFrom, String referTo) {
		super();
		this.id = id;
		this.referFrom = referFrom;
		this.referTo = referTo;
	}
	@Override
	public String toString() {
		return "Referrals [id=" + id + ", referFrom=" + referFrom + ", referTo=" + referTo + "]";
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getReferFrom() {
		return referFrom;
	}
	public void setReferFrom(String referFrom) {
		this.referFrom = referFrom;
	}
	public String getReferTo() {
		return referTo;
	}
	public void setReferTo(String referTo) {
		this.referTo = referTo;
	}
	private String referFrom;
	private String referTo;
	
}
