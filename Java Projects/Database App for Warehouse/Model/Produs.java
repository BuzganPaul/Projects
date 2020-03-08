package Model;


public class Produs {
	    private int idprodus;
	    private String nume;
	    private int nrstoc;
	    private int iddistribuitor;
	    private int pret;

	    public Produs(int a, String b, int c, int d, int e) {
	        this.idprodus = a;
	        this.nume = b;
	        this.nrstoc = c;
	        this.iddistribuitor = d;
	        this.pret = e;
	    }
	    
	    public Produs(int a) {
	        this.idprodus = a;
	    }

		public int getIdprodus() {
			return idprodus;
		}

		public void setIdprodus(int idprodus) {
			this.idprodus = idprodus;
		}

		public String getNume() {
			return nume;
		}

		public void setNume(String nume) {
			this.nume = nume;
		}

		public int getNrstoc() {
			return nrstoc;
		}

		public void setNrstoc(int nrstoc) {
			this.nrstoc = nrstoc;
		}

		public int getIddistribuitor() {
			return iddistribuitor;
		}

		public void setIddistribuitor(int distribuitorid) {
			this.iddistribuitor = distribuitorid;
		}

		public int getPret() {
			return pret;
		}

		public void setPret(int pret) {
			this.pret = pret;
		}


	}