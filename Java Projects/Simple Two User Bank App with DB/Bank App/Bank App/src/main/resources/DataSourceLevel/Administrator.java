package DataSourceLevel;

public class Administrator {
	private int idAdministrator;
	private String user;
	private String password;
	
	public int getIdAdministrator() {
		return idAdministrator;
	}
	public void setIdAdministrator(int idAdministrator) {
		this.idAdministrator = idAdministrator;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	public Administrator(int idAdministrator, String user, String password) {
		super();
		this.idAdministrator = idAdministrator;
		this.user = user;
		this.password = password;
	}
	@Override
	public String toString() {
		return "Administrator [idAdministrator=" + idAdministrator + ", user=" + user + ", password=" + password + "]";
	}
	
	

}
