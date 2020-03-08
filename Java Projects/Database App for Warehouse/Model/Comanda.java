package Model;

public class Comanda {
	
	 	private int idcomanda;
	    private int idclient;
	    private int idprodus;
	    private int nrproduse;
	    private int prettotal;


	    public Comanda(int a, int b, int c, int d, int e) {
	        this.idcomanda = a;
	        this.idclient = b;
	        this.idprodus = c;
	        this.nrproduse = d;
	        this.prettotal = e;
	    }
	    
	    public Comanda(int a) {
	        this.idcomanda = a;
	    }


		public int getIdcomanda() {
			return idcomanda;
		}


		public void setIdcomanda(int idcomanda) {
			this.idcomanda = idcomanda;
		}


		public int getIdclient() {
			return idclient;
		}


		public void setIdclient(int idclient) {
			this.idclient = idclient;
		}


		public int getIdprodus() {
			return idprodus;
		}


		public void setIdprodus(int idprodus) {
			this.idprodus = idprodus;
		}


		public int getNrproduse() {
			return nrproduse;
		}


		public void setNrproduse(int nrproduse) {
			this.nrproduse = nrproduse;
		}


		public int getPrettotal() {
			return prettotal;
		}


		public void setPrettotal(int prettotal) {
			this.prettotal = prettotal;
		}


}
