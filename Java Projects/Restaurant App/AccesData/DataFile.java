package AccesData;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import Bussiness.Comanda;
import Bussiness.ItemMeniu;
import Bussiness.OperatiiIteme;
import Bussiness.ProdusCompus;
import Bussiness.ProdusSimplu;
import Bussiness.Restaurant;

public class DataFile {
	
	public static void salveazaMeniu()
			throws IOException {
			    String file = "MeniuSalvat.txt";
			    Scanner scanner = new Scanner(new File(file));
			    String word = "";
			    scanner.useDelimiter(" ");
			    
			    System.out.println(scanner.next());
			    System.out.println(scanner.next());
			    word=scanner.next();
			    
			    while(!word.equals("SFARSIT!"))
			    {
			    	System.out.println(word);
			    	if(word.equals("Simplu:"))
			    	{
			    		System.out.println("PRODUS: SIMPLU");
			    		word = scanner.next();
			    		String numeaux = scanner.next();
			    		
			    		word = scanner.next();
			    		int pretaux = Integer.parseInt(scanner.next());
			    		
			    		word = scanner.next();
			    		int gramajaux = Integer.parseInt(scanner.next());
			    		

			    		ProdusSimplu aux = new ProdusSimplu(numeaux, pretaux, gramajaux);
			    		System.out.println(aux.printItem());
			    		Restaurant.meniu.addItem(aux);
			    	}
			    	
			    	if(word.equals("Compus:"))
			    	{
			    		System.out.println("PROD COMPUS");
			    		scanner.next();
			    		String numeaux = scanner.next();
			    		scanner.next();
			    		int pretaux = Integer.parseInt(scanner.next());
			    		scanner.next();
			    		int gramajaux = Integer.parseInt(scanner.next());

			    		ProdusCompus auxCompus = new ProdusCompus(numeaux, pretaux, gramajaux);
			    		word=scanner.next();
			    		
			    		while(!word.equals("Compus:") && !word.equals("SFARSIT!") && !word.equals("Simplu:"))
			    		{
				    		String numeaux2 = scanner.next();
				    		
				    		word=scanner.next();
				    		int pretaux2 = Integer.parseInt(scanner.next());
				    		
				    		word=scanner.next();
				    		int gramajaux2 = Integer.parseInt(scanner.next());

				    		ProdusSimplu aux2 = new ProdusSimplu(numeaux2, pretaux2, gramajaux2);
				    		System.out.println(aux2.printItem());
				    		auxCompus.addIngredient(aux2);
				    		word=scanner.next();
			    		}
			    		System.out.println(auxCompus.printItem());
			    		Restaurant.meniu.addItem(auxCompus);

			    	
			    	}
			    	if(!word.equals("Compus:") && !word.equals("SFARSIT!") && !word.equals("Simplu:")) {
			    	word=scanner.next();
			    	}
			    	
			    	
			    }
			    System.out.println(word);
			    scanner.close();
			}
	
	
	public static void saveData() {
		try {
			
		String numeSave="MeniuSalvat.txt";
		String dataSave="Meniu Restaurant ";
		
		PrintWriter writerinText = new PrintWriter(numeSave, "UTF-8");

		
		for(OperatiiIteme aux : Restaurant.meniu.iteme)
		{
			dataSave=dataSave+aux.printFile();
		}
		
		dataSave=dataSave+"SFARSIT! ";
		
		writerinText.println(dataSave);
		writerinText.close();
		} catch (IOException e) {
			  System.out.println("Eroare screiere date salvate.");
			}

	}

}
