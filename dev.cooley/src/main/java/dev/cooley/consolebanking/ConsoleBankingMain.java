package dev.cooley.consolebanking;

import java.util.Scanner;

import dev.cooley.consolebanking.data.AccountDAO;
import dev.cooley.consolebanking.data.UserDAO;
import dev.cooley.consolebanking.ds.ArrayList;
import dev.cooley.consolebanking.models.Accounts;
import dev.cooley.consolebanking.models.Users;
import dev.cooley.consolebanking.services.UserActions;

public class ConsoleBankingMain {
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		UserActions userA = new UserActions();
		
		System.out.println("******Welcome to the Console Bank*****");
		System.out.println("Enter q to exit the app.");
		System.out.println("Have you banked with us before (y/n)");
		String response = scanner.next();
		
		switch (response) {
			case "n": 
				System.out.println("What is your name?");
				String name = scanner.next();
				
				System.out.println("Enter a username.");
				String username = scanner.next();
				
				System.out.println("Enter a password.");
				String password = scanner.next();
				
				Users userObject = (Users) userA.signUp(username, password, name);
				
				System.out.println("Now we need to set up a new account for you.\n"
						+ "What would you like to call this account?");
				String acc_name = scanner.next();
				
				System.out.println("How much would you like to deposit into this account?");
				double amount = scanner.nextDouble();
				
				Accounts accObject = (Accounts) userA.createAccount(acc_name, amount, userObject.getId());
				break;
			case "y":
				System.out.println("Enter your username.");
				String usernameL = scanner.next();
				System.out.println("Enter your password.");
				String passwordL = scanner.next();
				userA.logIn(usernameL, passwordL);
				break;
		}
		scanner.close();	
	}	
}

