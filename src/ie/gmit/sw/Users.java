package ie.gmit.sw;

public class Users {
	//variables
	String username;
	String password;
	String loginID;
	//constructor
	public Users(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	//getters and setters
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
	public String getLoginID() {
		return loginID;
	}
	public void setLoginID(String loginID) {
		this.loginID = loginID;
	}
	
	
	
	
}