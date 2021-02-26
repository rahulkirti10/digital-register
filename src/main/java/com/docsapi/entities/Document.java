package com.docsapi.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Component
public class Document {

	@Id
	@GeneratedValue(
		    strategy= GenerationType.AUTO,
		    generator="native"
		)
		@GenericGenerator(
		    name = "native",
		    strategy = "native"
		)
	private long doc_id;
	
	private String document_name;
	
	private String update_document;

	public String getUpdate_document() {
		return update_document;
	}

	public void setUpdate_document(String update_document) {
		this.update_document = update_document;
	}

	@Override
	public String toString() {
		return "Document [doc_id=" + doc_id + ", document_name=" + document_name + ", data=" + data + ", details="
				+ details + "]";
	}

	@JsonManagedReference
	@OneToMany(mappedBy = "docs",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	private List<SavedData> data;
	
	@JsonBackReference
	@ManyToOne
	private UserDetails details;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "document",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private List<ColumnDetails> columnDetails;

	public List<ColumnDetails> getColumnDetails() {
		return columnDetails;
	}

	public void setColumnDetails(List<ColumnDetails> columnDetails) {
		this.columnDetails = columnDetails;
	}

	

	public Document(long doc_id, String document_name, String update_document, List<SavedData> data,
			UserDetails details, List<ColumnDetails> columnDetails) {
		super();
		this.doc_id = doc_id;
		this.document_name = document_name;
		this.update_document = update_document;
		this.data = data;
		this.details = details;
		this.columnDetails = columnDetails;
	}

	public String getDocument_name() {
		return document_name;
	}

	public void setDocument_name(String document_name) {
		this.document_name = document_name;
	}

	public UserDetails getDetails() {
		return details;
	}

	public void setDetails(UserDetails details) {
		this.details = details;
	}

	public long getDoc_id() {
		return doc_id;
	}

	public void setDoc_id(long doc_id) {
		this.doc_id = doc_id;
	}

	public List<SavedData> getData() {
		return data;
	}

	public void setData(List<SavedData> data) {
		this.data = data;
	}

	public Document() {
		super();
		// TODO Auto-generated constructor stub
	}


	
	
	
}
