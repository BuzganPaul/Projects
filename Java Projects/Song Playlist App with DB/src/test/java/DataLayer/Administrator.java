package DataLayer;

public class Administrator {
	
	
	private int adminID;
	private String usernameAdmin;
	private String passwordAdmin;
	
	public Administrator() {
	}
	
	
	public Administrator(String usernameAdmin, String passwordAdmin) {
		this.usernameAdmin = usernameAdmin;
		this.passwordAdmin = passwordAdmin;
	}


	public int getAdminID() {
		return adminID;
	}



	public String getUsernameAdmin() {
		return usernameAdmin;
	}


	public void setUsernameAdmin(String usernameAdmin) {
		this.usernameAdmin = usernameAdmin;
	}


	public String getPasswordAdmin() {
		return passwordAdmin;
	}


	public void setPasswordAdmin(String passwordAdmin) {
		this.passwordAdmin = passwordAdmin;
	}
	
	
	

}
