package dev.cooley.consolebanking.models;

import dev.cooley.consolebanking.ds.ArrayList;

public class Users {
	private int id;
	private String username;
	private String password;
	private String name;
	private ArrayList<Accounts> accounts;
	
	public Users(String username, String password, String name) {
		this.username = username;
		this.password = password;
		this.name = name;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<Accounts> getAccounts() {
		return accounts;
	}

	public void setAccounts(ArrayList<Accounts> accounts) {
		this.accounts = accounts;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
