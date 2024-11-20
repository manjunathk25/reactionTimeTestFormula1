package com.f1.dao;

import java.util.List;

import com.f1.dto.LeaderBoard;

public interface LeaderBoardDAO {
	public boolean insertLeaderBoard();
	public List<LeaderBoard> getLeaderBoard();

}
