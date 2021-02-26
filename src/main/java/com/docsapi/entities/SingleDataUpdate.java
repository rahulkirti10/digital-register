package com.docsapi.entities;

import java.util.List;

public class SingleDataUpdate {

	private List<String> data;
	private String id;
	private String cols_nums;
	private String rows_nums;
	private List<String> height;
	private List<String> width;
	private String serialno;
	
	public String getSerialno() {
		return serialno;
	}
	public void setSerialno(String serialno) {
		this.serialno = serialno;
	}
	public List<String> getData() {
		return data;
	}
	public void setData(List<String> data) {
		this.data = data;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCols_nums() {
		return cols_nums;
	}
	public void setCols_nums(String cols_nums) {
		this.cols_nums = cols_nums;
	}
	public String getRows_nums() {
		return rows_nums;
	}
	public void setRows_nums(String rows_nums) {
		this.rows_nums = rows_nums;
	}
	public List<String> getHeight() {
		return height;
	}
	public void setHeight(List<String> height) {
		this.height = height;
	}
	public List<String> getWidth() {
		return width;
	}
	public void setWidth(List<String> width) {
		this.width = width;
	}
	public SingleDataUpdate(List<String> data, String id, String cols_nums, String rows_nums, List<String> height,
			List<String> width, String serialno) {
		super();
		this.data = data;
		this.id = id;
		this.cols_nums = cols_nums;
		this.rows_nums = rows_nums;
		this.height = height;
		this.width = width;
		this.serialno = serialno;
	}
	@Override
	public String toString() {
		return "SingleDataUpdate [data=" + data + ", id=" + id + ", cols_nums=" + cols_nums + ", rows_nums=" + rows_nums
				+ ", height=" + height + ", width=" + width + ", serialno=" + serialno + "]";
	}
	
	
	
}
