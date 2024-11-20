package com.f1.app;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.f1.dao.LeaderBoardDAO;
import com.f1.dao.LeaderBoardDAOImp;
import com.f1.dto.LeaderBoard;

public class Leaderboard {
	public static void leaderBoard() {
		LeaderBoardDAO ldao = new LeaderBoardDAOImp();
		boolean res = ldao.insertLeaderBoard();
		if(res) {
			List<LeaderBoard> leaderboard = new ArrayList<LeaderBoard>();
			leaderboard = ldao.getLeaderBoard();
			Iterator<LeaderBoard> itr = leaderboard.iterator();
			while(itr.hasNext()) {
				LeaderBoard lb = itr.next();
			    System.out.println("PlayerId: " +lb.getPid()+","+"PlayerName: "+lb.getPname()+","+"Reaction Time: "+lb.getReaction_time()+"s"+","+"Position: "+lb.getPosition());
			}
		}
		else {
			System.out.println("LeaderBoard not found");
		}
     }
}
