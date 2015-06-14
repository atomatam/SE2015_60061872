package kr.ac.mju.model;

public class GangjwaScore {
	private String username;
	private String gangjwaID;
	private String score;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getGangjwaID() {
		return gangjwaID;
	}
	public void setGangjwaID(String gangjwaID) {
		this.gangjwaID = gangjwaID;
	}
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	public GangjwaScore(String username, String gangjwaID, String score) {
		this.username = username;
		this.gangjwaID = gangjwaID;
		this.score = score;
	}
	public GangjwaScore() {
	}
	
}
