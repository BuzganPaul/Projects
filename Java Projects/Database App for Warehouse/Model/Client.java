package Model;


public class Client {
	private int idclient;
    private String nume;
    private String adresalivrare;
    private String email;


    public Client(int a, String b, String c, String d) {
        this.idclient = a;
        this.nume = b;
        this.adresalivrare = c;
        this.email = d;
    }
    
    public Client(int a) {
        this.idclient = a;
    }


	public int getIdclient() {
		return idclient;
	}


	public void setIdclient(int idclient) {
		this.idclient = idclient;
	}


	public String getNume() {
		return nume;
	}


	public void setNume(String nume) {
		this.nume = nume;
	}


	public String getAdresalivrare() {
		return adresalivrare;
	}


	public void setAdresalivrare(String adresalivrare) {
		this.adresalivrare = adresalivrare;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}
    
}
