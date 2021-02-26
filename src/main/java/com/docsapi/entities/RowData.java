package com.docsapi.entities;

import java.util.List;

public class RowData {

	private String doc_id;
	private String current_row_number;
	private String rows_num;
	private List<String> data;
	public List<String> getData() {
		return data;
	}
	public void setData(List<String> data) {
		this.data = data;
	}
	public String getCols_num() {
		return cols_num;
	}
	public void setCols_num(String cols_num) {
		this.cols_num = cols_num;
	}
	private String cols_num;
	
	public String getRows_num() {
		return rows_num;
	}
	public void setRows_num(String rows_num) {
		this.rows_num = rows_num;
	}
	public String getDoc_id() {
		return doc_id;
	}
	public void setDoc_id(String doc_id) {
		this.doc_id = doc_id;
	}
	public String getCurrent_row_number() {
		return current_row_number;
	}
	public void setCurrent_row_number(String current_row_number) {
		this.current_row_number = current_row_number;
	}
	
	public RowData(String doc_id, String current_row_number, String rows_num) {
		super();
		this.doc_id = doc_id;
		this.current_row_number = current_row_number;
		this.rows_num = rows_num;
	}
	@Override
	public String toString() {
		return "RowData [doc_id=" + doc_id + ", current_row_number=" + current_row_number + ", rows_num=" + rows_num
				+ "]";
	}
	public RowData() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
