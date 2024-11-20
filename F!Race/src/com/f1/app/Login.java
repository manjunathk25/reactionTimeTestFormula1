package com.f1.app;

import java.util.Scanner;

import com.f1.dao.PlayerDAO;
import com.f1.dao.PlayerDAOImp;
import com.f1.dto.Player;

public class Login {
	public static void login() {
		Scanner sc = new Scanner(System.in);
		PlayerDAO pdao = new PlayerDAOImp();
		System.out.println("Enter your mail");
		String mail = sc.next();
		System.out.println("Enter your password");
		String pass = sc.next();
		Player p = pdao.getPlayer(mail, pass);
		if(p != null) {
			System.out.println("Login Successfull");
			System.out.println("Welcome Player: "+p.getPname());
			Options.options(p);
		}
		else {
			System.out.println("Login Failed");
		}
	}

}
