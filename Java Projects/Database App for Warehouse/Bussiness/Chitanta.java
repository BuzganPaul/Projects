package Bussiness;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

public class Chitanta {
	
	private static String textChitanta = "Chitanta numarul: ";
	private static String numeChitanta = "chitanta";
	private static int nrc;
	
	private static int getRandomNumber()
	{
		Random r= new Random();
		return r.nextInt((10000 - 1000) +1) + 1000;
	}
	
	
	
	public static void scrieChitanta( int client, int prod, int nr, int pret) {
	try {
	nrc=getRandomNumber();
	numeChitanta=numeChitanta + nrc +".txt";
	textChitanta=textChitanta + nrc + "\n";
	
	PrintWriter writerinText = new PrintWriter(numeChitanta, "UTF-8");
	writerinText.println(textChitanta);
	writerinText.println("A fost inregistrata comanda pentru:  " + LegaturaDataAcces.returnNume("produs", prod) + "  X " + nr + "\n");
	writerinText.println("Pret total: " + pret + " lei \n");
	writerinText.println("Nume destinatar:  " + LegaturaDataAcces.returnNume("client", client) + "  \n");
	writerinText.println("Adresa destinatar:  " + LegaturaDataAcces.returnAdresa("client", client) + "  \n");
	writerinText.println("Multumim ca ati comandat de la noi!  ");
	writerinText.close();
	} catch (IOException e) {
		  System.out.println("Eroare screiere chitanta.");
		}

}
}
