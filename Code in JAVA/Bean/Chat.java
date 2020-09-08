package chat.bean;

import java.sql.Timestamp;

public class Chat {
	 private String id;
	 private String message;
	 private String userName;
	 private String forUser;
	 private Timestamp createdTime;
	 private String seenBy;
	 
	public String getForUser() {
		return forUser;
	}
	public void setForUser(String forUser) {
		this.forUser = forUser;
	}
	public Timestamp getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Timestamp createdTime) {
		this.createdTime = createdTime;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getSeenBy() {
		return seenBy;
	}
	public void setSeenBy(String seenBy) {
		this.seenBy = seenBy;
	}
	 
}
