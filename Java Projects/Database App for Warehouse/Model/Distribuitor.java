package Model;


public class Distribuitor {

	    private int iddistribuitor;
	    private String nume;
	    private String adresadistribuitor;



	    public Distribuitor(int a, String b, String c) {
	        this.iddistribuitor = a;
	        this.nume = b;
	        this.adresadistribuitor = c;
	    }
	    
	    public Distribuitor(int a) {
	        this.iddistribuitor = a;
	    }


		public int getIddistribuitor() {
			return iddistribuitor;
		}



		public void setIddistribuitor(int iddistribuitor) {
			this.iddistribuitor = iddistribuitor;
		}



		public String getNume() {
			return nume;
		}



		public void setNume(String nume) {
			this.nume = nume;
		}



		public String getAdresadistribuitor() {
			return adresadistribuitor;
		}



		public void setAdresadistribuitor(String adresadistribuitor) {
			this.adresadistribuitor = adresadistribuitor;
		}
}
