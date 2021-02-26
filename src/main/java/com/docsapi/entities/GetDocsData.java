package com.docsapi.entities;

import java.util.List;

public class GetDocsData {

	private List<SavedData> data;

	public List<SavedData> getData() {
		return data;
	}

	public void setData(List<SavedData> data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "GetDocsData [data=" + data + "]";
	}

	public GetDocsData() {
		super();
		// TODO Auto-generated constructor stub
	}

	public GetDocsData(List<SavedData> data) {
		super();
		this.data = data;
	}
	
	
}
