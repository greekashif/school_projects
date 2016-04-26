package com.tcs.ilp.bean;

public class AccountBean {

	private int id;
	private String name;
	private String type;
	private float balance;
	private float interest;
	
	public AccountBean() {
		id = 0;
		name = null;
		type = null;
		balance = 0.0f;
		interest = 0.0f;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public float getBalance() {
		return balance;
	}

	public void setBalance(float balance) {
		this.balance = balance;
	}

	public float getInterest() {
		return interest;
	}

	public void setInterest(float interest) {
		this.interest = interest;
	}
	
	
	
}
