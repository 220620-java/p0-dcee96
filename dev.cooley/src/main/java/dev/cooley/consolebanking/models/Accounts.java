package dev.cooley.consolebanking.models;

import dev.cooley.consolebanking.ds.ArrayList;

public class Accounts {
	private double balance;
	private int id;
	private String name;
	private ArrayList<Transactions> transactions;
	private int userId;
	
	public Accounts (String name, double balance, int userId) {
		this.setId(id);
		this.setName(name);
		this.setBalance(balance);
		this.userId = userId;
		this.transactions = new ArrayList<>(10);
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
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

	public ArrayList<Transactions> getTransactions() {
		return transactions;
	}

	public void setTransactions(ArrayList<Transactions> transactions) {
		this.transactions = transactions;
	}

	public int getUser_id() {
		return userId;
	}

	public void setUser_id(int user_id) {
		this.userId = user_id;
	}
	
	
}
