package Bussiness;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import AccesData.DataFile;


public class Restaurant {
	
	static public ItemMeniu meniu =  new ItemMeniu();
	static public HashMap<Comanda, ItemMeniu> Comenzi = new  HashMap<Comanda, ItemMeniu>();
	static public ArrayList<Comanda> mese= new ArrayList <Comanda>();
	

	
	
	public static void bringData() {
	try {
		DataFile.salveazaMeniu();
	} catch (IOException e) {
		e.printStackTrace();
	}
	}
	
	public static void saveallData() {
		DataFile.saveData();
		}
}
