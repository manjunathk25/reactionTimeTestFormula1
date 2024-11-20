package com.f1.dao;

import java.util.List;

import com.f1.dto.Games;
import com.f1.dto.Player;

public interface GamesDAO {
	public boolean insertGames(Games g);
	public boolean updateBestGames(Player p, Games g);
	public List<Games> getGames();
	public Games getGames(Player p);
}
