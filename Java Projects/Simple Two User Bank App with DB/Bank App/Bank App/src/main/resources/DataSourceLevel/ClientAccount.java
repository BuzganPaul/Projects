package DataSourceLevel;

public class ClientAccount {
	
	private int idclient;
	private String accountType;
	private int money;
	private String creationDate;
	private int idAccount;
	
	public int getIdclient() {
		return idclient;
	}
	public void setIdclient(int idclient) {
		this.idclient = idclient;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
	}
	public String getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}
	public int getIdAccount() {
		return idAccount;
	}
	public void setIdAccount(int idAccount) {
		this.idAccount = idAccount;
	}
	
	public ClientAccount(int idclient, String accountType, int money, String creationDate, int idAccount) {
		super();
		this.idclient = idclient;
		this.accountType = accountType;
		this.money = money;
		this.creationDate = creationDate;
		this.idAccount = idAccount;
	}
	@Override
	public String toString() {
		return "ClientAccount [idclient=" + idclient + ", accountType=" + accountType + ", money=" + money
				+ ", creationDate=" + creationDate + ", idAccount=" + idAccount + "]";
	}
	
	

}
