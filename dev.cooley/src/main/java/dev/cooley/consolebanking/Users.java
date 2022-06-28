package dev.cooley.consolebanking;

public class Users {
	private String username;
	private String password;
	private int accountIds;
	private Accounts[] accounts = new Accounts[10];
	
	public void createAccount(String name, double balance) {
		if (this.accountIds < accounts.length) {
			Accounts newAccount = new Accounts(this.accountIds, name, balance);
			accounts[accountIds] =  newAccount;
			this.accountIds += 1;
		} else {
			System.out.println("You reached the limit of accounts per user!");
		}
	}
	
	
}
