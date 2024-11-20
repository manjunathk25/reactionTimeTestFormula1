package com.f1.dto;

public class LeaderBoard {
	private int pid;
	private String pname;
	private double reaction_time;
	private int position;
	

	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public double getReaction_time() {
		return reaction_time;
	}
	public void setReaction_time(double reaction_time) {
		this.reaction_time = reaction_time;
	}
	
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
	}
	@Override
	public String toString() {
		return "LeaderBoard [pid=" + pid + ", pname=" + pname + ", reaction_time=" + reaction_time + ", position=" + position
				+ "]";
	}
	
}
