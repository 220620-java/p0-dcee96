package dev.cooley.consolebanking.services;

public interface UserServices<T> {
	
	/**
	 * Allows users to create new Accounts(not bank accounts) in the bank. It will check to make
	 * sure that the username selected isn't already taken.
	 * 
	 * @param name Name of the new user
	 * @param username Username that will be attached to the account.
	 * @param password User's password.
	 * @return This will return an User object with the users creds attached.
	 */
	public T signUp(String name, String username, String password);
	
	/**
	 * This allows returning users to log in with their existing info.
	 * Will validate that the username exist and that the password is right.
	 * 
	 * @param username of the account 
	 * @param password of the account
	 * @return returns the Users account as a User object with all its info.
	 */
	public T logIn(String username, String password);
	
	/**
	 * Allows users to create new accounts.
	 * 
	 * @param name The name of the account ie (savings, checking, etc).
	 * @param balance The initial balance of the account.
	 * @param user The user the account will be connected to.
	 * @return
	 */
	public T createAccount(String name, double balance, int user);
	
	/**
	 * 
	 * @param account
	 * @return
	 */
	public T[] transactionHistory(T account);
	
	/**
	 * 
	 * @param amount
	 * @param user
	 * @param account
	 * @return
	 */
	public double withdraw(double amount, T user, T account);
	
	/**
	 * 
	 * @param amount
	 * @param user
	 * @param account
	 * @return
	 */
	public double deposit(double amount, T user, T account);
	
	/**
	 * 
	 * @param amount
	 * @param user1
	 * @param account1
	 * @param user2
	 * @param account2
	 */
	public void transfer(double amount, T user1, T account1, T user2, T account2);
}
