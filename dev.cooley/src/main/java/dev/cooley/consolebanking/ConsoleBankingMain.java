package dev.cooley.consolebanking;

import java.util.Scanner;

public class ConsoleBankingMain {
	
	public static void main(String[] args) {
		Scanner fetch = new Scanner(System.in);
		greeting();
		String reply = fetch.nextLine();
		if (reply.startsWith("y")) {
			System.out.println("Enter Username - ");
		} else if (reply.startsWith("n")) {
			System.out.println("*****Create New account*****");
			System.out.println("Enter Username - ");
		} else {
			System.out.println("Please enter y or n");
		} 
		fetch.close();
	}
	
	private static void greeting() {
		System.out.println("*****Welcome to ConsoleBanking*****");
		System.out.println("Have you used ConsoleBanking before (y/n) - ");
		
	}

}
