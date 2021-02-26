package com.docsapi.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;


@Entity
public class Election {

private long id;

private String fname;
private String sname;
	
@Id
@GeneratedValue(
	    strategy= GenerationType.AUTO,
	    generator="native"
	)
	@GenericGenerator(
	    name = "native",
	    strategy = "native"
	)
	public long getId() {
	return id;
}


public void setId(long id) {
	this.id = id;
}


public String getFname() {
	return fname;
}


public void setFname(String fname) {
	this.fname = fname;
}


public String getSname() {
	return sname;
}


public void setSname(String sname) {
	this.sname = sname;
}


	public Election() {
		// TODO Auto-generated constructor stub
	
	
	}

}
