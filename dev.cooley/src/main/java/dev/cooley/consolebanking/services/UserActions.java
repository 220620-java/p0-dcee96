package dev.cooley.consolebanking.services;

import dev.cooley.consolebanking.data.AccountDAO;
import dev.cooley.consolebanking.data.TransactionDAO;
import dev.cooley.consolebanking.data.UserDAO;
import dev.cooley.consolebanking.models.Accounts;
import dev.cooley.consolebanking.models.Users;
@SuppressWarnings("unchecked")
public class UserActions<U> implements UserServices<U> {

	private UserDAO userDao = new UserDAO();
	private AccountDAO accDao = new AccountDAO();
	private TransactionDAO tranDao = new TransactionDAO();
	
	@Override
	public U signUp(String username, String password, String name) {
		
		if (userDao.findUsername(username)==false) {
			Users user = new Users(username, password, name);
			userDao.create(user);
			return (U) user;
		}
		return null;
	}

	@Override
	public U logIn(String username, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public U createAccount(String name, double balance, int userId) {
		Accounts account = new Accounts(name, balance, userId);
		accDao.create(account);
		return (U) account;
	}

	@Override
	public U[] transactionHistory(U account) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double withdraw(double amount, U user, U account) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double deposit(double amount, U user, U account) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void transfer(double amount, U user1, U account1, U user2, U account2) {
		// TODO Auto-generated method stub
		
	}

}
