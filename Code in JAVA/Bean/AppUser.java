package chat.bean;

public class AppUser {
	 private String id;
	 private String userName;
	 private String password;
	 private String role;
	 private String status;
	 
	 public boolean isAdmin() {
		 return "admin".equalsIgnoreCase(role);
	 }
	 public boolean isMute() {
		 return "mute".equalsIgnoreCase(status);
	 }
	 
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	 
}
