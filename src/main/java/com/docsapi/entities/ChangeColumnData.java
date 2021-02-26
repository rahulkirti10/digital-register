package com.docsapi.entities;

import java.util.List;

public class ChangeColumnData {

	private List<String> data;
	private long doc_id;
	private int current_col_number;
	public List<String> getData() {
		return data;
	}
	public void setData(List<String> data) {
		this.data = data;
	}
	public long getDoc_id() {
		return doc_id;
	}
	public void setDoc_id(long doc_id) {
		this.doc_id = doc_id;
	}
	public int getCurrent_col_number() {
		return current_col_number;
	}
	public void setCurrent_col_number(int current_col_number) {
		this.current_col_number = current_col_number;
	}
	public ChangeColumnData() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ChangeColumnData(List<String> data, long doc_id, int current_col_number) {
		super();
		this.data = data;
		this.doc_id = doc_id;
		this.current_col_number = current_col_number;
	}
	
	
}
