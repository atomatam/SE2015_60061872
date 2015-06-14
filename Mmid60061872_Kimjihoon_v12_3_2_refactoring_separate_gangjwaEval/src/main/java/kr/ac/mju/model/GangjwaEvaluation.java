package kr.ac.mju.model;

public class GangjwaEvaluation {
	private String userName;
	private String gangjwaID;
	private String gangjwaEval;
	private String isRead;
	private String date;
	
	public GangjwaEvaluation(String userName, String gangjwaID,
			String gangjwaEval, String isRead, String date) {
		this.userName = userName;
		this.gangjwaID = gangjwaID;
		this.gangjwaEval = gangjwaEval;
		this.isRead = isRead;
		this.date = date;
	}

	public GangjwaEvaluation() {
	}

	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getGangjwaID() {
		return gangjwaID;
	}
	public void setGangjwaID(String gangjwaID) {
		this.gangjwaID = gangjwaID;
	}
	public String getGangjwaEval() {
		return gangjwaEval;
	}
	public void setGangjwaEval(String gangjwaEval) {
		this.gangjwaEval = gangjwaEval;
	}
	public String getIsRead() {
		return isRead;
	}
	public void setIsRead(String isRead) {
		this.isRead = isRead;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	
}	
