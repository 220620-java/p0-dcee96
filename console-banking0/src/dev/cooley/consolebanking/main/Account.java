package dev.cooley.consolebanking.main;

public class Account {
	private int id;
	private String name;
	private double balance;
	
	Account (int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	} 
	
	public double getBalance() {
		return balance;
	}
	
	public void depositBalance(double amount) {	
		this.balance += amount;
	}
	
	public boolean withdrawBalance(double amount) {
		if (this.balance < amount) {
			return false;
		} else {
			this.balance -= amount;
			return true;
		}
	}
	
	
}
