package Bussiness;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Comanda{
	
	private int nrmasa;
	private String datacomanda;
	
	
	public Comanda(int idcomanda) {
		this.setIdcomanda(idcomanda);
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		this.setDatacomanda(dateFormat.format(date));
	}
	
	@Override

	public int hashCode() {
        return nrmasa;
    }
	
	public static void scrieChitanta(Comanda com, ItemMeniu prod) {
		try {
			
		String chitanta="Masa nr: " + com.getIdcomanda() + " Data: " + com.getDatacomanda() + ".txt";
		chitanta=chitanta.replaceAll(" ", "_");
		chitanta=chitanta.replaceAll(":", "_");
		chitanta=chitanta.replaceAll("/", "_");
		
		PrintWriter writerinText = new PrintWriter(chitanta, "UTF-8");
		writerinText.println("Data:" + com.getDatacomanda());
		writerinText.println("Masa:" + com.getIdcomanda());
		writerinText.println("Produse comanda:  ");
		
		for(OperatiiIteme aux : prod.iteme)
		{
			writerinText.println(aux.getNume());
		}

		writerinText.println("Pret total: " + prod.calculeazaPret() + " lei \n");
		writerinText.println("Multumim ca ati comandat de la noi!  ");
		writerinText.close();
		} catch (IOException e) {
			e.printStackTrace();
			  System.out.println("Eroare screiere chitanta.");
			}

	}


	public int getIdcomanda() {
		return nrmasa;
	}


	public void setIdcomanda(int idcomanda) {
		this.nrmasa = idcomanda;
	}

	public String getDatacomanda() {
		return datacomanda;
	}

	public void setDatacomanda(String datacomanda) {
		this.datacomanda = datacomanda;
	}

	

}
