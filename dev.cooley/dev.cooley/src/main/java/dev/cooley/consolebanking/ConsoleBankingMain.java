package dev.cooley.consolebanking;

import java.util.Scanner;

import dev.cooley.consolebanking.data.AccountDAO;
import dev.cooley.consolebanking.ds.ArrayList;
import dev.cooley.consolebanking.exceptions.UsernameAlreadyExist;
import dev.cooley.consolebanking.models.Accounts;
import dev.cooley.consolebanking.models.Users;
import dev.cooley.consolebanking.services.UserActions;

public class ConsoleBankingMain {
	private static Scanner scanner = new Scanner(System.in);
	private static UserActions<?> userActions = new UserActions<>();
	
	
	public static void main(String[] args) {
		boolean usingConsoleBanking = true;
		
		Users user = null;
		Accounts account = null;
		while (usingConsoleBanking) {
			if (user == null) {
				System.out.println("***** Welcome to ConsoleBanking *****\n"
										+ "\nHow would you like to procced? -\n" 
										+ "Log in: 1\n" 
										+ "Sign up: 2\n" 
										+ "Quit: q\n");
				
				String response = scanner.nextLine();
				
				switch (response) {
				case "1":
					user = logIn();
					break;
					
				case "2":
					signUp();
					break;
					
				case "q":
					usingConsoleBanking = false;
					System.out.print("Come back soon!");
				}
			}
			
			if (user != null) {
				System.out.println("What would you like to do today? -\n" 
									+ "View account balance: 1\n" 
									+ "Make a Deposit: 2\n" 
									+ "Make a Withdraw: 3\n"
									+ "Transfer funds: 4\n"
									+ "Create new Account: 5\n" 
									+ "Quit: q\n");
				
				String response = scanner.nextLine();
				
				switch(response) {
				case "1":
					account.setUser_id(user.getId());
					viewBalance(account);
					break;
					
				case "2":
					account = new Accounts();
					System.out.println("How much would you like to deposit?");
					double amount = scanner.nextDouble();
					
					deposit(amount, account);
					break;
					
				case "3":
					System.out.println("How much would you like to withdraw?");
					double amount1 = scanner.nextDouble();
					
					withdraw(amount1, account);
					break;
					
				case "4":
					transfer();
					break;
					
				case "5":
					account = createAccount(user);
					break;
					
				case "q":
					usingConsoleBanking = false;
					System.out.println("You have been Logged off.");
					user = null;
				}
			}
		}
		
		scanner.close();
	}
	
	private static Users logIn() {
		boolean logginIn = true;
		
		while (logginIn) {
			System.out.println("Enter Username:\n");
			String username = scanner.nextLine();
			
			System.out.println("Enter Password:\n");
			String password = scanner.nextLine();
			
			Users user = userActions.logIn(username, password);
			
			if (user == null) {
				System.out.println("Creditials that were provided couldn't be found... ");
				System.out.println("Would you like to retry? - y/n\n");
				String response = scanner.nextLine();
				
				if (!("y".equals(response))) {
					logginIn = false; 
				}
			} else {
				return user;
			}
		}
		return null;
	}
	
	private static void signUp() {
		boolean signingUp = true;
		
		while (signingUp) {
			System.out.println("Create a Username:\n");
			String username = scanner.nextLine();			
			
			System.out.println("Create a Password:\n");
			String password = scanner.nextLine();
			
			System.out.println("Enter you name: \n");
			String name = scanner.nextLine();
			
			System.out.println("Enter (y) to comfirm\n" 
								+ "Enter (n) to retry");
			
			String response = scanner.nextLine();
			
			switch (response) {
			case "y":
				Users user = new Users(username, password, name);
				
				try {
					
					userActions.signUp(user);
					signingUp = false;
					System.out.println("You can now log in!");
					
				} catch (UsernameAlreadyExist e){
					
					System.out.println("Looks like that username already Exist \ntry another one!");
					
				}
				
				break;
				
			case "n":
				
				System.out.println("There is nothing like a fresh start!");
				
				break;
				
			default:
				
				System.out.println("Back to the Login!");
				signingUp = false;
			}
		}
	}
	
	private static Accounts createAccount(Users user) {
		boolean creatingAcc = true;
		
		while (creatingAcc) {
			System.out.println("Enter (save) for Savings Account:\n" 
								+ "Enter (check) for Checkings Account:\n");
			String accType = scanner.nextLine();
			
			System.out.println("How much would you like to use for your inital deposit? - \n");
			double initDeposit = scanner.nextDouble();
			
			System.out.println("Type (y) to complete the setup\n" 
								+ "Type (n) to redo the setup process\n" 
								+ "Type anything else to return home.\n");
			
			String response = scanner.next();
			switch (response) {
			
			case "y":
				
				Accounts account = new Accounts(accType, initDeposit, user.getId());
				userActions.createAccount(account);
				user.addAccount(account);
				System.out.println("The account is ready to use!");
				creatingAcc = false;
				return account;
				
				
			case "n":
				System.out.println("Going back to the account creation section.......");
				break;
			
			default:
				creatingAcc = false;
				System.out.println();
			}
		}
		return null;
	}
	
	private static void deposit(double amount, Accounts acc) {
		double newBalance = userActions.deposit(amount, acc);
		acc.setBalance(newBalance);
	}
	
	private static void withdraw(double amount, Accounts acc) {
		double newBalance = userActions.deposit(amount, acc);
		acc.setBalance(newBalance);
	}
	
	private static void transfer() {
		System.out.println("Coming Soon....");
	}
	
	private static void viewBalance(Accounts acc) {
		double balance = acc.getBalance();
		System.out.println(acc.getName() + ": $" + balance);
	}
}
