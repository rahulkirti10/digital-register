package com.docsapi.entities;

import java.util.List;

public class AllDataList {

	private List<String> data;
	
	private String user_id;
	private String document_name;
	private List<String> width;
	private List<String> height;

	private List<String> column_name;
	
	private List<String> column_type;
	private int rows_num;
	
	private int cols_num;
	
	private String serialno;
	
	public String getSerialno() {
		return serialno;
	}

	public void setSerialno(String serialno) {
		this.serialno = serialno;
	}

	public List<String> getColumn_type() {
		return column_type;
	}

	public void setColumn_type(List<String> column_type) {
		this.column_type = column_type;
	}

	public List<String> getColumn_name() {
		return column_name;
	}

	public void setColumn_name(List<String> column_name) {
		this.column_name = column_name;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public int getCols_num() {
		return cols_num;
	}

	public void setCols_num(int cols_num) {
		this.cols_num = cols_num;
	}

	public int getRows_num() {
		return rows_num;
	}

	public void setRows_num(int rows_num) {
		this.rows_num = rows_num;
	}


	public String getDocument_name() {
		return document_name;
	}

	public void setDocument_name(String document_name) {
		this.document_name = document_name;
	}

	public List<String> getWidth() {
		return width;
	}

	public void setWidth(List<String> width) {
		this.width = width;
	}

	public List<String> getHeight() {
		return height;
	}

	public void setHeight(List<String> height) {
		this.height = height;
	}

	public List<String> getData() {
		return data;
	}

	public void setData(List<String> data) {
		this.data = data;
	}

	

	public AllDataList(List<String> data, String user_id, String document_name, List<String> width, List<String> height,
			List<String> column_name, List<String> column_type, String serialno, int rows_num, int cols_num) {
		super();
		this.data = data;
		this.user_id = user_id;
		this.document_name = document_name;
		this.width = width;
		this.height = height;
		this.column_name = column_name;
		this.column_type = column_type;
		this.serialno = serialno;
		this.rows_num = rows_num;
		this.cols_num = cols_num;
	}

	@Override
	public String toString() {
		return "AllDataList [data=" + data + ", user_id=" + user_id + ", document_name=" + document_name + ", width="
				+ width + ", height=" + height + ", column_name=" + column_name + ", column_type=" + column_type
				+ ", serialno=" + serialno + ", rows_num=" + rows_num + ", cols_num=" + cols_num + "]";
	}

	public AllDataList() {
		super();
		// TODO Auto-generated constructor stub
	}
}
