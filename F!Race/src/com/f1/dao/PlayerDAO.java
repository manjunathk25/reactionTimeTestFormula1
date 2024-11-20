package com.f1.dao;


import com.f1.dto.Player;

public interface PlayerDAO {
	public boolean insertPlayer(Player p);
	public Player getPlayer(String mail, String pass);
	public boolean deletePlayer(Player p);
}
