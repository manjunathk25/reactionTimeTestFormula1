package com.f1.app;

import com.f1.dao.GamesDAO;
import com.f1.dao.GamesDAOImp;
import com.f1.dto.Games;
import com.f1.dto.Player;

public class BestScore {
	@SuppressWarnings("null")
	public static void getBestScore(Player p) {
		GamesDAO gdao = new GamesDAOImp();
		
		Games g = gdao.getGames(p);
		if(g == null) {
			System.out.println("You have not played any game");
		}
		else {
			System.out.println("Your Best Score: "+g.getBest()+"s");
		}
	}

}
