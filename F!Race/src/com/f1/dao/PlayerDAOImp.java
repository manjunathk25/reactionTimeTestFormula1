package com.f1.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.f1.connectors.Connectionfactory;
import com.f1.dto.Player;

public class PlayerDAOImp implements PlayerDAO {
	
	private Connection con;
    public PlayerDAOImp() {
		this.con = Connectionfactory.requestConnection();
	}

	@Override
	public boolean insertPlayer(Player p) {
		// TODO Auto-generated method stub
		PreparedStatement ps = null;
		int res = 0;
		String query = "INSERT INTO PLAYERS(PNAME, MAILID, PASSWORD) VALUES(?, ?, ?)";
		try {
			con.setAutoCommit(false);
			ps = con.prepareStatement(query);
			ps.setString(1, p.getPname());
			ps.setString(2, p.getMail());
			ps.setString(3, p.getPassword());
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
	public boolean deletePlayer(Player p) {
		// TODO Auto-generated method stub
		PreparedStatement ps = null;
		int res = 0;
		String query = "DELETE FROM PLAYERS WHERE PID = ?";
		try {
			con.setAutoCommit(false);
			ps = con.prepareStatement(query);
			ps.setInt(1, p.getPid());
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
	public Player getPlayer(String mail, String pass) {
		// TODO Auto-generated method stub
		PreparedStatement ps = null;
		ResultSet rs = null;
		Player p = null;
		String query = "SELECT * FROM PLAYERS WHERE MAILID = ? AND PASSWORD = ?";
		try {
			ps = con.prepareStatement(query);
			ps.setString(1, mail);
			ps.setString(2, pass);
			rs = ps.executeQuery();
			if(rs.next()) {
				p = new Player();
				p.setPid(rs.getInt(1));
				p.setPname(rs.getString(2));
				p.setMail(mail);
				p.setPassword(pass);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return p;
	}

}
