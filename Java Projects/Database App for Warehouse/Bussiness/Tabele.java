package Bussiness;
import DataAcces.*;
import java.awt.Component;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Model.*;

public class Tabele {
	
	public final static String[] columnNamesClient = {"ID Client",
            "Nume",
            "Adresa Livrare",
            "Email"};
	
	public final static String[] columnNamesProdus = {"ID Produs",
            "Nume",
            "Stoc",
            "ID Distribuitor",
            "Pret/unitate"};
	
	public final static String[] columnNamesComanda = {"ID Comanda",
            "ID Client",
            "ID Produs",
            "Numar unitati",
            "Pret total"};
	
	public final static String[] columnNamesDistribuitor = {"ID Distribuitor",
            "Nume",
            "Adresa"};

	
	
	public static JTable generareClient()
	{
		JTable tableClient = new JTable(LegaturaDataAcces.contentTable("client"), columnNamesClient);
		return tableClient;
	}
	
	
	public static JTable generareProdus()
	{
		JTable tableProdus = new JTable(LegaturaDataAcces.contentTable("produs"), columnNamesProdus);
		return tableProdus;
	}
	
	public static JTable generareComanda()
	{
		JTable tableComanda = new JTable(LegaturaDataAcces.contentTable("comanda"), columnNamesComanda);
		return tableComanda;
	}
	
	public static JTable generareDistribuitor()
	{
		JTable tableDistribuitor = new JTable(LegaturaDataAcces.contentTable("distribuitor"), columnNamesDistribuitor);
		return tableDistribuitor;
	}

}
