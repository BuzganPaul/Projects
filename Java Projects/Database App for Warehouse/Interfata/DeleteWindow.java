package Interfata;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import Bussiness.DBOperations;
import Bussiness.LegaturaDataAcces;
import Bussiness.Tabele;
import Model.Client;
import Model.Comanda;
import Model.Distribuitor;
import Model.Produs;

public class DeleteWindow extends JFrame{
    public JPanel topPanel;
    public JPanel btnPanel;
    public JScrollPane scrollPane;

    public DeleteWindow(final String comp){
        setTitle("Delete Window");
        setSize(600,150);
        setBackground(Color.gray);
        setLayout(new BorderLayout());
        
        topPanel = new JPanel();
        btnPanel = new JPanel();
        
        add(topPanel, BorderLayout.CENTER);
        add(btnPanel, BorderLayout.SOUTH);

        topPanel.setLayout(new BorderLayout());
        
        JLabel l1_2 = new JLabel(comp);
        
        String[] choices2 = null;
        
        if(comp == "Clienti:")
        {
        	 choices2 =  LegaturaDataAcces.toStringOptions("client");
        }
        if(comp == "Produse:")
        {
        	 choices2 =  LegaturaDataAcces.toStringOptions("produs");
        }
        if(comp == "Comenzi:")
        {
        	 choices2 =  LegaturaDataAcces.toStringOptions("comanda");
        }
        if(comp == "Distribuitori:")
        {
        	 choices2 =  LegaturaDataAcces.toStringOptions("distribuitor");
        }
		


	    final JComboBox<String> cb1_2 = new JComboBox<String>(choices2);

	    cb1_2.setVisible(true);
		
        
        topPanel.add(l1_2,BorderLayout.NORTH);
        topPanel.add(cb1_2,BorderLayout.CENTER);
        
        
        JButton exButton = new JButton("DELETE");
        
        exButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent startDelete) {
            	if(comp == "Clienti:") {
            		int aux = cb1_2.getSelectedIndex();
        			int auxf = LegaturaDataAcces.returnId("client", aux);
        			final Client auxClient = new Client(auxf);
        			DBOperations.deleteItem(auxClient);
        			System.out.println("Clientul cu ID: "+ auxf + " a fost sters din Baza de Date specifica.");
            	}
            	
            	if(comp == "Distribuitori:") {
            		int aux = cb1_2.getSelectedIndex();
        			int auxf = LegaturaDataAcces.returnId("distribuitor", aux);
                	final Distribuitor auxDistribuitor = new Distribuitor(auxf);
                	DBOperations.deleteItem(auxDistribuitor);
                	System.out.println("Distribuitorul cu ID: "+ auxf + " a fost sters din Baza de Date specifica.");
                	}
            	
            	if(comp == "Comenzi:") {
            		int aux = cb1_2.getSelectedIndex();
            		int auxf = LegaturaDataAcces.returnId("comanda", aux);
            		final Comanda auxComanda = new Comanda(auxf);
                	DBOperations.deleteItem(auxComanda);
                	System.out.println("Comanda cu ID: "+ auxf + " a fost stearsa din Baza de Date specifica.");
            	}
        	
        		
        		if(comp == "Produse:") {
        			int aux = cb1_2.getSelectedIndex();
        			int auxf = LegaturaDataAcces.returnId("produs", aux);
        			final Produs auxProdus = new Produs(auxf);
        			DBOperations.deleteItem(auxProdus);
        			System.out.println("Produsul cu ID: "+ auxf + " a fost modificat in Baza de Date specifica.");
        		}
    	}
        });


        btnPanel.add(exButton);

    }
}