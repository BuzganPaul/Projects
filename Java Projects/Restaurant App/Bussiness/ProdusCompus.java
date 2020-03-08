package Bussiness;

import java.util.ArrayList;

public class ProdusCompus implements OperatiiIteme{
	
	private String nume;
	private int pret;
	private int cantitate;
	
	public void setNume(String nume) {
		this.nume = nume;
	}

	public void setPret(int pret) {
		this.pret = pret;
	}

	public void setCantitate(int cantitate) {
		this.cantitate = cantitate;
	}

	public ProdusCompus(String nume, int pret, int cantitate) {
		this.nume = nume;
		this.pret = pret;
		this.cantitate = cantitate;
	}
	
	public ArrayList<ProdusSimplu> ingrediente = new ArrayList <ProdusSimplu>(); 
	
	public void addIngredient(ProdusSimplu produs)
	{
		ingrediente.add(produs);
		int aux=0;
		for(ProdusSimplu aux1 : ingrediente)
		{
			aux=aux+aux1.getPret();
		}
		this.pret=aux + (aux/5);
	}
	
	public void removeIngredient(ProdusSimplu produs)
	{
		ingrediente.remove(produs);
		int aux=0;
		for(ProdusSimplu aux1 : ingrediente)
		{
			aux=aux+aux1.getPret();
		}
		this.pret=aux + (aux/5);
	}

	public String printItem() {
		String aux = nume + " " + pret + " lei " + cantitate + " grame \n    (Ingrediente: ";
		
		for(ProdusSimplu aux1 : ingrediente)
		{
			aux=aux+aux1.getNume() + " ";
		}
		
		aux=aux + ") \n";
		return aux;
		
	}

	public int calculeazaPret() {
		
		return pret;
		
	}

	public String getNume() {
		return this.nume;
	}

	public int getPret() {
		return pret;
	}

	public int getCantitate() {
		return cantitate;
	}

	public String printIngrediente() {
		String auxi = "";
		
		for(ProdusSimplu auxiliar : ingrediente)
		{
			auxi=auxi+auxiliar.getNume() + ", ";
		}
		return auxi.substring(0, auxi.length()-2);

	}

	public String printFile() {
		String aux = "Compus: Nume: " + nume.replaceAll(" ", "_") + " Pret: " + pret + " Gramaj: " + cantitate + " ";
		
		for(ProdusSimplu aux1 : ingrediente)
		{
			aux=aux+"Nume: "+aux1.getNume().replaceAll(" ", "_") + " ";
			aux=aux+"Pret: "+aux1.getPret() + " ";
			aux=aux+"Gramaj: "+aux1.getCantitate() + " ";
		}
		
		return aux;
	}

}
