package com.docsapi.entities;

import java.util.List;

public class CalculationDetails {

	private String saving_column;
	private List<String> pos_column;
	private List<String> operators;
	private List<String> numbers;
	private Long doc_id;
	
	public String getSaving_column() {
		return saving_column;
	}
	public void setSaving_column(String saving_column) {
		this.saving_column = saving_column;
	}
	public Long getDoc_id() {
		return doc_id;
	}
	public void setDoc_id(Long doc_id) {
		this.doc_id = doc_id;
	}
	public List<String> getPos_column() {
		return pos_column;
	}
	public void setPos_column(List<String> pos_column) {
		this.pos_column = pos_column;
	}
	public List<String> getOperators() {
		return operators;
	}
	public void setOperators(List<String> operators) {
		this.operators = operators;
	}
	public List<String> getNumbers() {
		return numbers;
	}
	public void setNumbers(List<String> numbers) {
		this.numbers = numbers;
	}
	
	
	public CalculationDetails(String saving_column, List<String> pos_column, List<String> operators,
			List<String> numbers, Long doc_id) {
		super();
		this.saving_column = saving_column;
		this.pos_column = pos_column;
		this.operators = operators;
		this.numbers = numbers;
		this.doc_id = doc_id;
	}
	public CalculationDetails() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
