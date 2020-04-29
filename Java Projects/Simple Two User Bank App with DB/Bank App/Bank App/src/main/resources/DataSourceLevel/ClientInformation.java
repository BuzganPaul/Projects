package DataSourceLevel;

public class ClientInformation {
	private int idclient;
	private String nameClient;
	private long idNumber;
	private String address;
	
	
	public int getIdclient() {
		return idclient;
	}
	public void setIdclient(int idclient) {
		this.idclient = idclient;
	}
	public String getNameClient() {
		return nameClient;
	}
	public void setNameClient(String nameClient) {
		this.nameClient = nameClient;
	}
	public long getIdNumber() {
		return idNumber;
	}
	public void setIdNumber(long idNumber) {
		this.idNumber = idNumber;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	public ClientInformation(int idclient, String nameClient, long idNumber, String address) {
		super();
		this.idclient = idclient;
		this.nameClient = nameClient;
		this.idNumber = idNumber;
		this.address = address;
	}
	@Override
	public String toString() {
		return "ClientInformation [idclient=" + idclient + ", nameClient=" + nameClient + ", idNumber=" + idNumber
				+ ", address=" + address + "]";
	}
	
	
	

}
