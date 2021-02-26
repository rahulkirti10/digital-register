package com.docsapi.entities;

import java.util.List;

public class AddMoreThanOneColumn {

	private List<String> column_type;
	private String column_nums;
	private String row_nums;
	private List<String> column_names;
	private Document document;
	public List<String> getColumn_type() {
		return column_type;
	}
	public void setColumn_type(List<String> column_type) {
		this.column_type = column_type;
	}
	public String getColumn_nums() {
		return column_nums;
	}
	public void setColumn_nums(String column_nums) {
		this.column_nums = column_nums;
	}
	public String getRow_nums() {
		return row_nums;
	}
	public void setRow_nums(String row_nums) {
		this.row_nums = row_nums;
	}
	public List<String> getColumn_names() {
		return column_names;
	}
	public void setColumn_names(List<String> column_names) {
		this.column_names = column_names;
	}
	public Document getDocument() {
		return document;
	}
	public void setDocument(Document document) {
		this.document = document;
	}
	public AddMoreThanOneColumn(List<String> column_type, String column_nums, String row_nums,
			List<String> column_names, Document document) {
		super();
		this.column_type = column_type;
		this.column_nums = column_nums;
		this.row_nums = row_nums;
		this.column_names = column_names;
		this.document = document;
	}
	public AddMoreThanOneColumn() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
