package com.f1.dto;

import java.util.List;

public class Games {
	private int playerId;
	private List<Double> reactionTimes;
	private double best;

	public int getPlayerId() {
		return playerId;
	}
	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}
	public List<Double> getReactionTimes() {
		return reactionTimes;
	}
	public void setReactionTimes(List<Double> reactionTimes) {
		this.reactionTimes = reactionTimes;
	}
	public double getBest() {
		return best;
	}
	public void setBest(double best) {
		this.best = best;
	}
	
	@Override
	public String toString() {
		return "Games [playerId=" + playerId + ", reactionTimes=" + reactionTimes + ", best=" + best + "]";
	}
	

}
