package Bussiness;

public class ProdusSimplu implements OperatiiIteme{
	
	private String nume;
	private int pret;
	private int cantitate;
	
	
	public ProdusSimplu(String nume, int pret, int cantitate) {
		this.pret = pret;
		this.nume = nume;
		this.cantitate = cantitate;
	}

	public String printItem() {
		String aux = nume + " " + pret + " lei " + cantitate + " grame \n";
		return aux;
		
	}

	public int calculeazaPret() {
		return this.pret;
		
	}

	public String getNume() {
		return nume;
	}

	public void setNume(String nume) {
		this.nume = nume;
	}

	public int getPret() {
		return pret;
	}

	public void setPret(int pret) {
		this.pret = pret;
	}

	public int getCantitate() {
		return cantitate;
	}

	public void setCantitate(int cantitate) {
		this.cantitate = cantitate;
	}

	public String printIngrediente() {
		return "-";
	}

	public String printFile() {
		
			String aux = "Simplu: Nume: " + nume.replaceAll(" ", "_") + " Pret: " + pret + " Gramaj: " + cantitate + " ";
			return aux;

	}

}
