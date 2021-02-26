package com.docsapi.entities;

import java.util.List;

public class GetAllDocument {

	private List<Document> Document;

	public List<Document> getDocument() {
		return Document;
	}

	public void setDocument(List<Document> document) {
		Document = document;
	}

	public GetAllDocument() {
		super();
		// TODO Auto-generated constructor stub
	}

	public GetAllDocument(List<com.docsapi.entities.Document> document) {
		super();
		Document = document;
	}

	@Override
	public String toString() {
		return "GetAllDocument [Document=" + Document + "]";
	}

	

	
}
