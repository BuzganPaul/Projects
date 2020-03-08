package Interfata;
import Bussiness.*;


import java.awt.*;
import java.awt.event.*;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.swing.*;

public class OspatarGUI extends JFrame{
    public static JPanel topPanel;
    public JPanel btnPanel;
    public static JScrollPane scrollPanetabel;
    public static JTable  tb1 = new JTable();

    public OspatarGUI( String nume){
        setTitle(nume);
        setSize(600,600);
        setBackground(Color.gray);
        setLayout(new BorderLayout());
        
        topPanel = new JPanel();
        btnPanel = new JPanel();
        
        add(topPanel, BorderLayout.CENTER);
        add(btnPanel, BorderLayout.SOUTH);

        
        topPanel.setLayout(new BorderLayout());
        
        String[] columnNames = {"Masa Nr.",
                "Data/Ora",
                "Iteme"};
        
        Object[][] data = new Object[Restaurant.mese.size()][3];
        int a=0;
        
        final Set< Map.Entry< Comanda,ItemMeniu> > vedereComenzi = Restaurant.Comenzi.entrySet();    
        
        for (Entry<Comanda, ItemMeniu> me:vedereComenzi) 
        { 
        	String aux1 = "";
        	data[a][0]=me.getKey().getIdcomanda();
        	data[a][1]=me.getKey().getDatacomanda();
        	for(OperatiiIteme aux : me.getValue().iteme)
        	{
        		aux1=aux1 + aux.getNume().replaceAll("_", " ") + "  ";
        	}
        	data[a][2]=aux1;
        	a++;
        } 
        
        tb1=new JTable(data, columnNames);
        scrollPanetabel = new JScrollPane(tb1);
        topPanel.add(scrollPanetabel,BorderLayout.CENTER);
        
        String[] choices2 = new String[a];
        
        for(int i=0; i<a; i++)
        {
        	choices2[i]="Masa: " +data[i][0]+";";
        	
        }
		
        JLabel t1 = new JLabel("Selecteaza comanda de printat: ");

	    final JComboBox<String> cb1_2 = new JComboBox<String>(choices2);

	    cb1_2.setVisible(true);
        
	    topPanel.add(t1,BorderLayout.SOUTH);
    	topPanel.add(cb1_2,BorderLayout.SOUTH);
        
        JButton addButton = new JButton("ADAUGA COMANDA");
        JButton chitantaButton = new JButton("EMITE FACTURA");

        
        
        addButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent add) {
            	AddComanda ad = new AddComanda();
                ad.setVisible(true);
            }

        });
        

        
        
        chitantaButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent delete) {
            	String auxi = (String) cb1_2.getSelectedItem();
            	auxi = auxi.substring(auxi.indexOf(" ") + 1);
            	auxi = auxi.substring(0, auxi.indexOf(";"));
            	int nr = Integer.parseInt(auxi);
            	System.out.println(nr); 
            	Comanda auxCom=null;
            	ItemMeniu auxMenu = null;
            	
            	for (Entry<Comanda, ItemMeniu> me:vedereComenzi) 
                { 
            		if(me.getKey().getIdcomanda() == nr)
            		{
            			auxCom = me.getKey();
            			auxMenu = me.getValue();
            		}
                } 
            	
            	Comanda.scrieChitanta(auxCom, auxMenu);
            }

        });


        btnPanel.add(addButton);
        btnPanel.add(chitantaButton);
        }		
}