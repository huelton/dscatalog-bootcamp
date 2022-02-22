package com.devsuperior.dscatalog.exercicio;

import java.io.Serializable;

public class Financing implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Double totalAmount;
	private Double income;
	private Integer months;
	
	public Financing() {
	}

	public Financing(Double totalAmount, Double income, Integer months) {
		if((totalAmount * 0.8) / months > income / 2.0) {
			throw new IllegalArgumentException("A parcela não pode ser maior que a metade da renda.");
		}
		this.totalAmount = totalAmount;
		this.income = income;
		this.months = months;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		if((totalAmount * 0.8) / months > income / 2.0) {
			throw new IllegalArgumentException("A parcela não pode ser maior que a metade da renda.");
		}
		this.totalAmount = totalAmount;
	}

	public Double getIncome() {
		return income;
	}

	public void setIncome(Double income) {
		if((totalAmount * 0.8) / months > income / 2.0) {
			throw new IllegalArgumentException("A parcela não pode ser maior que a metade da renda.");
		}
		this.income = income;
	}

	public Integer getMonths() {
		return months;
	}

	public void setMonths(Integer months) {
		if((totalAmount * 0.8) / months > income / 2.0) {
			throw new IllegalArgumentException("A parcela não pode ser maior que a metade da renda.");
		}
		this.months = months;
	}
	
	public Double entry() {
		return totalAmount * 0.2;
	}
	
	public Double quota() {		
		return (totalAmount - entry()) / months;
	}

}
