package com.f1.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.f1.connectors.Connectionfactory;
import com.f1.dto.Games;
import com.f1.dto.Player;


public class GamesDAOImp implements GamesDAO {
	private Connection con;
    public GamesDAOImp() {
		this.con = Connectionfactory.requestConnection();
	}

	@Override
	public boolean insertGames(Games g) {
		// TODO Auto-generated method stub
		PreparedStatement ps = null;
		int res = 0;
		List<Double> reactionTimes = g.getReactionTimes();
		String query = "INSERT INTO GAMES VALUES(?, ?, ?)";
		try {
			con.setAutoCommit(false);
			ps = con.prepareStatement(query);
			ps.setInt(1, g.getPlayerId());
			for(double reactionTime : reactionTimes) {
			ps.setDouble(2, reactionTime);
			}
			ps.setDouble(3, g.getBest());
			res = ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(res > 0) {
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

	@SuppressWarnings("unused")
	@Override
	public List<Games> getGames() {
		// TODO Auto-generated method stub
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Games> games = new ArrayList<Games>();
		List<Double> reactionTimes = null;
		Games g = null;
		String query = "SELECT * FROM GAMES";
		
		try {
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while(rs.next()) {
				g = new Games();
				g.setPlayerId(rs.getInt(1));
				reactionTimes = new ArrayList<Double>();
				reactionTimes.add(rs.getDouble(2));
				g.setReactionTimes(reactionTimes);
				g.setBest(rs.getDouble(3));
				games.add(g);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (games == null) {
			return null;
		}
		else {
		    return games;
		}
	}

	@Override
	public boolean updateBestGames(Player p, Games g) {
		// TODO Auto-generated method stub
		PreparedStatement ps = null;
		ResultSet rs = null;
		int res = 0;
		String query1 = "SELECT MIN(BEST) FROM GAMES WHERE PLAYERID = ?";
		String query2 = "UPDATE GAMES SET BEST = ? WHERE PLAYERID = ?";
		try {
			con.setAutoCommit(false);
			ps = con.prepareStatement(query1);
			ps.setInt(1, g.getPlayerId());
			rs = ps.executeQuery();
			if(rs.next()) {
				con.commit();
				g.setBest(rs.getDouble(1));
			}
			con.setAutoCommit(false);
			ps = con.prepareStatement(query2);
			ps.setDouble(1, g.getBest());
			ps.setInt(2, p.getPid());
			res = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(res > 0) {
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
	public Games getGames(Player p) {
		// TODO Auto-generated method stub
		PreparedStatement ps = null;
		ResultSet rs = null;
		Games g = null;
        List<Double> reaction_times = new ArrayList<Double>();
		String query = "SELECT * FROM GAMES WHERE PLAYERID = ? LIMIT 1";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, p.getPid());
			rs = ps.executeQuery();
			if(rs.next()) {
				g = new Games();
				g.setPlayerId(rs.getInt(1));
				double reaction_time = rs.getDouble(2);
				reaction_times.add(reaction_time);
				g.setReactionTimes(reaction_times);
				g.setBest(rs.getDouble(3));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(g == null) {
			return null;
		}
		else {
			return g;
		}
	
	}
}
