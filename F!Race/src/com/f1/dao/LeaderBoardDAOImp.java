package com.f1.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.f1.connectors.Connectionfactory;
import com.f1.dto.LeaderBoard;
import com.f1.dto.Player;

public class LeaderBoardDAOImp implements LeaderBoardDAO {
	private Connection con;
    public LeaderBoardDAOImp() {
		this.con = Connectionfactory.requestConnection();
	}

	@Override
	public boolean insertLeaderBoard() {
		// TODO Auto-generated method stub
		PreparedStatement psDel = null;
		PreparedStatement psInsert = null;
		PreparedStatement psSel = null;
		ResultSet rs = null;
		int res1 = 0;
		int res2 = 0;
		
		String query1 = "DELETE FROM LEADERBOARD";
		String query2 = "SELECT G.PLAYERID, P.PNAME, G.REACTION_TIMES, DENSE_RANK() OVER(ORDER BY G.REACTION_TIMES) 'POSITION' FROM GAMES G INNER JOIN PLAYERS P ON P.PID = G.PLAYERID ORDER BY G.REACTION_TIMES LIMIT 20";
		String query3 = "INSERT INTO LEADERBOARD(PID, PNAME, REACTION_TIME, POSITION)  VALUES (?, ?, ?, ?)";
		try {
			con.setAutoCommit(false);
			psDel = con.prepareStatement(query1);
			res1 = psDel.executeUpdate();
			if(res1 >= 0) {
				con.commit();
				con.setAutoCommit(false);
				psSel = con.prepareStatement(query2);
				rs = psSel.executeQuery();
				psInsert = con.prepareStatement(query3);
				while(rs.next()) {
					psInsert.setInt(1, rs.getInt(1));
					psInsert.setString(2, rs.getString(2));
					psInsert.setDouble(3, rs.getDouble(3));
					psInsert.setInt(4, rs.getInt(4));
					res2 = psInsert.executeUpdate();
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(res1 >= 0 && res2 > 0) {
			try {
				con.commit();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return true;
		}
		else {	
			try {
				con.rollback();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    return false;
		}
	}

	@Override
	public List<LeaderBoard> getLeaderBoard() {
		// TODO Auto-generated method stub
		PreparedStatement ps = null;
		ResultSet rs = null;
		LeaderBoard lb = null;
		List<LeaderBoard> leaderboard = new ArrayList<LeaderBoard>();
		
		String query = "SELECT * FROM LEADERBOARD";
		try {
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();

			while(rs.next()) {
				lb = new LeaderBoard();
				lb.setPid(rs.getInt(1));
				lb.setPname(rs.getString(2));
				lb.setReaction_time(rs.getDouble(3));
				lb.setPosition(rs.getInt(4));
				leaderboard.add(lb);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(leaderboard != null) {
			return leaderboard;
		}
		else {
		    return null;
		}
	}

}
