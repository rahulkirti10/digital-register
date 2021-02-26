package com.docsapi.entities;

public class ColumnFormula {
	
	
	private String col_id;
	private String formula;
	public String getCol_id() {
		return col_id;
	}
	public void setCol_id(String col_id) {
		this.col_id = col_id;
	}
	public String getFormula() {
		return formula;
	}
	public void setFormula(String formula) {
		this.formula = formula;
	}
	@Override
	public String toString() {
		return "ColumnFormula [col_id=" + col_id + ", formula=" + formula + "]";
	}
	public ColumnFormula(String col_id, String formula) {
		super();
		this.col_id = col_id;
		this.formula = formula;
	}
	
	

}
