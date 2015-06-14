package kr.ac.mju.model;

public class UserInfo {
	private String username;
	private String password;
	private String userRealName;
	private String userCode;
	
	
	public UserInfo() {
	}

	public UserInfo(String username, String password, String userRealName,
			String userCode) {
		this.username = username;
		this.password = password;
		this.userRealName = userRealName;
		this.userCode = userCode;
	}
	public UserInfo(String username, String userRealName) {
		this.username = username;
		this.userRealName = userRealName;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUserRealName() {
		return userRealName;
	}
	public void setUserRealName(String userRealName) {
		this.userRealName = userRealName;
	}
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
}
