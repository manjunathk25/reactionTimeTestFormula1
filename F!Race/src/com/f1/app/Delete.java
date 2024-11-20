package com.f1.app;

import java.util.Scanner;

import com.f1.dao.PlayerDAO;
import com.f1.dao.PlayerDAOImp;
import com.f1.dto.Player;

public class Delete {
	public static void delete() {
		Player p = new Player();
		PlayerDAO pdao = new PlayerDAOImp();
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter your mail");
		String mail = sc.next();
		p.setMail(mail);
		System.out.println("Enter your password");
        String pass = sc.next();
		p.setPassword(pass);
		p = pdao.getPlayer(mail, pass);
		
		if(p == null) {
			System.out.println("No account found");
		}
		else {
		boolean res = pdao.deletePlayer(p);
		if (res) {
			System.out.println("Account deleted Successfully");
		}
		else {
			System.out.println("failed to delete");
		}
		}
		
	}
}
