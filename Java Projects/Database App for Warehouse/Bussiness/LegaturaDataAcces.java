package Bussiness;

import DataAcces.AccessDB;

import javax.swing.*;
import java.util.List;

public class LegaturaDataAcces {

    public static Object[][] contentTable(String table) {
        return AccessDB.getContent(table);
    }
    
    public static int returnId(String table, int a) {
        Object[][] auxiliar=  AccessDB.getContent(table);
        int nr= (int)auxiliar[a][0];
        return nr;
    }
    
    public static String returnNume(String table, int a) {
        Object[][] auxiliar=  AccessDB.getContent(table);
        String nr= (String)auxiliar[a][1];
        return nr;
    }
    
    public static int returnPret(String table, int a) {
        Object[][] auxiliar=  AccessDB.getContent(table);
        int nr= (int)auxiliar[a][4];
        return nr;
    }
    
    public static int returnIdD(String table, int a) {
        Object[][] auxiliar=  AccessDB.getContent(table);
        int nr= (int)auxiliar[a][3];
        return nr;
    }
    
    public static int returnStoc(String table, int a) {
        Object[][] auxiliar=  AccessDB.getContent(table);
        int nr= (int)auxiliar[a][2];
        return nr;
    }
    
    public static String returnAdresa(String table, int a) {
        Object[][] auxiliar=  AccessDB.getContent(table);
        String nr= (String)auxiliar[a][2];
        return nr;
    }
    
    public static String[] toStringOptions(String table) {
    	Object[][] aux = AccessDB.getContent(table);
    	String[] auxstring = new String[aux.length];
    	
    	for(int i=0; i < aux.length; i++)
    	{
    		auxstring[i]=(i+1)+"  --  ";
    		for(int j=0; j < aux[i].length; j++)
    		{
    			if(table == "client") {
    				if(j != aux[i].length - 1)
    				{
    					auxstring[i]=auxstring[i] + Tabele.columnNamesClient[j] + ": "+ aux[i][j] + ",  ";
    				}
    				else 
    				{
    					auxstring[i]=auxstring[i] + Tabele.columnNamesClient[j] + ": "+ aux[i][j] + ";";
    				}
    			}
    			
    			if(table == "produs") {
    				if(j != aux[i].length - 1)
    				{
    					auxstring[i]=auxstring[i] + Tabele.columnNamesProdus[j] + ": "+ aux[i][j] + ",  ";
    				}
    				else 
    				{
    					auxstring[i]=auxstring[i] + Tabele.columnNamesProdus[j] + ": "+ aux[i][j] + ";";
    				}
    			}
    			
    			if(table == "comanda") {
    				if(j != aux[i].length - 1)
    				{
    					auxstring[i]=auxstring[i] + Tabele.columnNamesComanda[j] + ": "+ aux[i][j] + ",  ";
    				}
    				else 
    				{
    					auxstring[i]=auxstring[i] + Tabele.columnNamesComanda[j] + ": "+ aux[i][j] + ";";
    				}
    			}
    			
    			if(table == "distribuitor") {
    				if(j != aux[i].length - 1)
    				{
    					auxstring[i]=auxstring[i] + Tabele.columnNamesDistribuitor[j] + ": "+ aux[i][j] + ",  ";
    				}
    				else 
    				{
    					auxstring[i]=auxstring[i] + Tabele.columnNamesDistribuitor[j] + ": "+ aux[i][j] + ";";
    				}
    			}
    		}
    	}
    	
		return auxstring;
    }
    
}