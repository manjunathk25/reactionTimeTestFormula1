package com.f1.app;

import java.util.Scanner;

import com.f1.dto.Games;
import com.f1.dto.Player;

public class Options {
	public static void options(Player p) {
		Scanner sc = new Scanner(System.in);
		Games g = new Games();
		g.setPlayerId(p.getPid());
		int choice = 0;
		do {
			System.out.println("1.Start Game");
			System.out.println("2.LeaderBoard");
			System.out.println("3.Best Score");
			System.out.println("4.End Game");
			choice = sc.nextInt();
			
			switch(choice) {
			case 1:
				GameArea.playGame(p, g);
				break;
				
			case 2:
				 Leaderboard.leaderBoard();
				 break;
				 
			case 3:
				BestScore.getBestScore(p);
				break;
				 
			case 4:
				System.out.println("Game ended");
				break;
				
			default:
				System.out.println("Invalid Option");
			}
		}while(choice != 4);
	}
}
