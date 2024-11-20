package com.f1.app;

import java.util.Scanner;

import com.f1.dao.PlayerDAO;
import com.f1.dao.PlayerDAOImp;
import com.f1.dto.Player;

public class SignUp {
	public static void signUp() {
		Scanner sc = new Scanner(System.in);
		Player p = new Player();
		PlayerDAO pdao = new PlayerDAOImp();
		System.out.println("Enter your Name");
		p.setPname(sc.nextLine());
		
		System.out.println("Enter your Mail");
		p.setMail(sc.next());
		
		System.out.println("Enter your Password");
		String pass = sc.next();
		
		System.out.println("Confirm Password");
		String confirm = sc.next();
		
		if(pass.equals(confirm)) {
			p.setPassword(pass);
			boolean res = pdao.insertPlayer(p);
			System.out.println("SignedUp Successfully");
			Player p1 = pdao.getPlayer(p.getMail(), pass);
			System.out.println("Your PlayerId is: Player#"+p1.getPid());
		}
		else {
			System.out.println("Failed to SignUp");
		}
	}
}
