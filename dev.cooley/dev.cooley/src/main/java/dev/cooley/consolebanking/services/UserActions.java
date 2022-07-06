package dev.cooley.consolebanking.services;

import dev.cooley.consolebanking.data.AccountDAO;
import dev.cooley.consolebanking.data.UserDAO;
import dev.cooley.consolebanking.ds.ArrayList;
import dev.cooley.consolebanking.exceptions.UsernameAlreadyExist;
import dev.cooley.consolebanking.models.Accounts;
import dev.cooley.consolebanking.models.Users;

public class UserActions<U> implements UserServices<U> {

	private UserDAO<?> userDao = new UserDAO<Object>();
	private AccountDAO<?> accDao = new AccountDAO<Object>();
	
	
	public double getBalance(Users user) {
		Accounts account = accDao.getByID(user);
		return account.getBalance();
	}
	
	@Override
	public Users signUp(Users user) throws UsernameAlreadyExist {
		
		Users newUser = user;
		userDao.create(newUser);
		return newUser;
	}

	@Override
	public Users logIn(String username, String password) {
		
		Users person = (Users) userDao.getUsername(username);
		if (person != null && (password!=null && password.equals(person.getPassword()))) {
			accDao.getByID(person);
			return (Users) person;
		}
		return person;
	}

	@Override
	public Accounts createAccount(Accounts account) {
		Accounts newAccount = account;
		accDao.create(newAccount);
		return newAccount;
	}

	@Override
	public double withdraw(double amount, U account) {
		double balance = ((Accounts) account).getBalance();
		if (amount > 0 && amount <= balance) {
			double newBalance = balance - amount;
			((Accounts) account).setBalance(newBalance);
			accDao.update((Accounts) account);
		return newBalance;
		} else {
			System.out.println("There aren't enough funds to complete the transaction.");
			return 0;
		}
	}

	public double deposit(double amount, Accounts account) {
		if (amount > 0) {
			double newBalance = ((Accounts) account).getBalance() + amount;
			((Accounts) account).setBalance(newBalance);
			accDao.update((Accounts) account);
			return newBalance;
		} else {
			System.out.println("You can't deposit a negative amount.");
		}
		return 0;
	}

	@Override
	public void transfer(double amount, U account1, U account2) {
		double balance1 = ((Accounts) account1).getBalance();
		if (amount > 0 && amount <= balance1) {
			double newBalance1 = balance1 - amount;
			((Accounts) account1).setBalance(newBalance1);
			accDao.update((Accounts) account1);
			
			double newBalance2 = ((Accounts) account2).getBalance() + amount;
			((Accounts) account2).setBalance(newBalance2);
			accDao.update((Accounts) account2);
		} else {
			System.out.println("There aren't enough funds to complete the transaction.");
		}
	}

	@Override
	public ArrayList<Accounts> allAccounts(Users user) {
		
		ArrayList<Accounts> userAccounts = null;
		userAccounts = (ArrayList<Accounts>) accDao.allAccounts(user);
		
		user.setAccounts(userAccounts);
		
		return userAccounts;
	}

	@Override
	public double deposit(double amount, U user) {
		// TODO Auto-generated method stub
		return 0;
	}
}
