package kr.ac.mju.model.dto;

import kr.ac.mju.model.Gangjwa;

public class GangjwaTableDto extends Gangjwa {
	private String profid;
	private String profname;
	private String score;
	
	public String getProfid() {
		return profid;
	}
	public void setProfid(String profid) {
		this.profid = profid;
	}
	public String getProfname() {
		return profname;
	}
	public void setProfname(String profname) {
		this.profname = profname;
	}
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	
}
