package dev.cooley.consolebanking;

public class Accounts {
	private double balance;
	private int id;
	private String name;
	
	Accounts (int id, String name, double balance) {
		this.id = id;
		this.name = name;
		this.balance = balance;
	}
	
	public void deposit(double amount) {
		if (0 < amount) { this.balance += amount; }
		else { System.out.println("You can't deposit a negative amount!"); }
	}

	public void withdraw(double amount) {
		
		if (this.getBalance() > amount) { 
			this.balance -= amount;
		} else { 
			System.out.println("Insufficent funds!");
		}
	}
	
	public double getBalance() {
		return balance;
	}
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	
	
}
