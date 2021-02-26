package com.docsapi.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class ColumnDetails {
	@Id
	@GeneratedValue(
		    strategy= GenerationType.AUTO,
		    generator="native"
		)
		@GenericGenerator(
		    name = "native",
		    strategy = "native"
		)
	private long id;
	private String total;
	
	private String column_names;
	
	@JsonBackReference
	@ManyToOne
	private Document document;
	
	private String formula;
	private String column_type;
	private String column_nums;
	private String row_nums;
	

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
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

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getColumn_names() {
		return column_names;
	}

	public void setColumn_names(String column_names) {
		this.column_names = column_names;
	}

	public Document getDocument() {
		return document;
	}

	public void setDocument(Document document) {
		this.document = document;
	}

	public String getColumn_type() {
		return column_type;
	}

	public void setColumn_type(String column_type) {
		this.column_type = column_type;
	}


	
	public String getFormula() {
		return formula;
	}

	public void setFormula(String formula) {
		this.formula = formula;
	}

	
	@Override
	public String toString() {
		return "ColumnDetails [id=" + id + ", total=" + total + ", column_names=" + column_names + ", document="
				+ document + ", formula=" + formula + ", column_type=" + column_type + ", column_nums=" + column_nums
				+ ", row_nums=" + row_nums + "]";
	}

	public ColumnDetails(long id, String total, String column_names, Document document, String formula,
			String column_type, String column_nums, String row_nums) {
		super();
		this.id = id;
		this.total = total;
		this.column_names = column_names;
		this.document = document;
		this.formula = formula;
		this.column_type = column_type;
		this.column_nums = column_nums;
		this.row_nums = row_nums;
	}

	public ColumnDetails() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
}
