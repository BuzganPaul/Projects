package DataLayer;

public class Regular {
	
	
	private int regularID;
	private String usernameReg;
	private String passwordReg;
	
	public Regular() {
	}
	
	public Regular(String usernameReg, String passwordReg) {
		this.usernameReg = usernameReg;
		this.passwordReg = passwordReg;
	}

	public int getRegularID() {
		return regularID;
	}

	public String getUsernameReg() {
		return usernameReg;
	}

	public void setUsernameReg(String usernameReg) {
		this.usernameReg = usernameReg;
	}

	public String getPasswordReg() {
		return passwordReg;
	}

	public void setPasswordReg(String passwordReg) {
		this.passwordReg = passwordReg;
	}
	
	
	
	

}
