package dev.cooley.consolebanking.models;

public class Accounts {
	private double balance;
	private int id;
	private String name;
	private int userId;
	
	public Accounts () {
		this.id = 0;
		this.name = "";
		this.balance = 0.0;
		this.userId = 0;
	}
	
	public Accounts (String name, double balance, int user_id) {
		this.name = name;
		this.balance = balance;
		this.userId = user_id;
	}

	public double getBalance() {
		return this.balance;
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
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getUser_id() {
		return this.userId;
	}

	public void setUser_id(int user_id) {
		this.userId = user_id;
	}
	
	public String toString() {
		String accInfo = "Account: " + this.name +
						 "\nBalance: " + this.balance +
						 "\nID: " + this.id;
		return accInfo;
	}
}
