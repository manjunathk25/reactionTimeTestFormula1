package com.f1.app;

import java.util.Scanner;

import com.f1.dto.Player;

public class Welcome {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Player p = new Player();
		int choice = 0;
		do {
			System.out.println("1.Player Login");
			System.out.println("2.Player SignUp");
			System.out.println("3.Exit Game Application");
			System.out.println("4.Delete your Account");
			choice = sc.nextInt();
			
			switch(choice) {
			case 1:
				Login.login();
				break;
			case 2:
				SignUp.signUp();
				break;
			case 3:
				System.out.println("Exiting the game application");
				break;
			case 4:
				Delete.delete();
			default:
				System.out.println("Invalid Selection");
		   }
			
	   }while (choice != 3);
	}
}
