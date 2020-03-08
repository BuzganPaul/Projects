package Bussiness;

import java.util.ArrayList;

public class ItemMeniu implements OperatiiIteme{
	
	public ArrayList<OperatiiIteme> iteme= new ArrayList <OperatiiIteme>(); 
	
	
	public void addItem(OperatiiIteme produs)
	{
		iteme.add(produs);
	}
	
	public void removeItem(int produs)
	{
		iteme.remove(produs);
	}

	public String printItem() {
		
		String aux="Iteme: \n";
		for(OperatiiIteme aux1 : iteme)
		{
			aux=aux+aux1.getNume() + " \n";
		}
		return aux;
	}

	public int calculeazaPret() {
		int aux=0;
		for(OperatiiIteme aux1 : iteme)
		{
			aux=aux+aux1.getPret();
		}
		return aux;
		
	}

	public String getNume() {
		// TODO Auto-generated method stub
		return null;
	}

	public int getPret() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getCantitate() {
		// TODO Auto-generated method stub
		return 0;
	}

	public String printIngrediente() {
	
		return "-";
	}

	public String printFile() {
		// TODO Auto-generated method stub
		return null;
	}

}
