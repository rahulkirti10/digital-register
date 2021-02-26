package com.docsapi.entities;

import java.util.List;

public class GetDataByColumn {

	private List<ColumnDetails> data;

	public List<ColumnDetails> getData() {
		return data;
	}

	public void setData(List<ColumnDetails> data) {
		this.data = data;
	}

	public GetDataByColumn(List<ColumnDetails> data) {
		super();
		this.data = data;
	}

	public GetDataByColumn() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "GetDocsData [data=" + data + "]";
	}
	
	
	

	
	
	
}
