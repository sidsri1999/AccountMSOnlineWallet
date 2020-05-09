package com.example.demo.dto;

public class TransactionDto {

	String recieverAccountId;
	double amount;
	public TransactionDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public TransactionDto(String recieverAccountId, double amount) {
		super();
		this.recieverAccountId = recieverAccountId;
		this.amount = amount;
	}
	public String getRecieverAccountId() {
		return recieverAccountId;
	}
	public void setRecieverAccountId(String recieverAccountId) {
		this.recieverAccountId = recieverAccountId;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	@Override
	public String toString() {
		return "TransactionDto [recieverAccountId=" + recieverAccountId + ", amount=" + amount + "]";
	}
}
