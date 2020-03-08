package Interfata;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import Bussiness.DBOperations;
import Bussiness.LegaturaDataAcces;
import Model.Client;
import Model.Distribuitor;
import Model.Produs;

public class EditWindow extends JFrame{
	public JPanel titlePanel;
    public JPanel topPanel;
    public JPanel btnPanel;
    public JScrollPane scrollPane;

    public EditWindow(final String comp){
        setTitle("Edit Window");
        setSize(550,300);
        setBackground(Color.gray);
        setLayout(new BorderLayout());
        
        
        titlePanel = new JPanel();
        topPanel = new JPanel();
        btnPanel = new JPanel();
        
        add(titlePanel, BorderLayout.NORTH);
        add(topPanel, BorderLayout.CENTER);
        add(btnPanel, BorderLayout.SOUTH);

        final JTextField tf2 = new JTextField(" ");
        final JTextField tf3 = new JTextField(" ");
        final JTextField tf4 = new JTextField(" ");
        
        String[] choices0 =  LegaturaDataAcces.toStringOptions("client");;
        String[] choices1 =  LegaturaDataAcces.toStringOptions("produs");;
        String[] choices2 =  LegaturaDataAcces.toStringOptions("comanda");
        String[] choices3 =  LegaturaDataAcces.toStringOptions("distribuitor");
        final JComboBox<String> cb0 = new JComboBox<String>(choices0);
        final JComboBox<String> cb1 = new JComboBox<String>(choices1);
        final JComboBox<String> cb2 = new JComboBox<String>(choices2);
        final JComboBox<String> cb3 = new JComboBox<String>(choices3);
             
        if(comp == "Clienti:")
        {
        	JLabel l1_2 = new JLabel("CLIENT: ");
            titlePanel.add(l1_2);
            
            JLabel a0 = new JLabel("Client intitial: ");

    	    cb0.setVisible(true);

        	JLabel a2 = new JLabel("Nume: ");
        	JLabel a3 = new JLabel("Adresa: ");
        	JLabel a4 = new JLabel("Email: ");
       		
       		tf2.setText("nume... ");
       		tf2.setPreferredSize(new Dimension(450, 20));
       		
       		tf3.setText("adresa... ");
       		tf3.setPreferredSize(new Dimension(450, 20));
       		
       		tf4.setText("email... ");
       		tf4.setPreferredSize(new Dimension(450, 20));
       		
       		topPanel.add(a0,BorderLayout.CENTER);
        	topPanel.add(cb0,BorderLayout.CENTER);
        	
        	topPanel.add(a2,BorderLayout.CENTER);
        	topPanel.add(tf2,BorderLayout.CENTER);
        	
        	topPanel.add(a3,BorderLayout.CENTER);
        	topPanel.add(tf3,BorderLayout.CENTER);
        	
        	topPanel.add(a4,BorderLayout.CENTER);
        	topPanel.add(tf4,BorderLayout.CENTER);
        	
        }
        
        if(comp == "Comenzi:")
        {
        	
        	JLabel l1_2 = new JLabel("COMANDA: ");
            titlePanel.add(l1_2);
            
            JLabel a0 = new JLabel("Comanda intitial: ");

    	    cb0.setVisible(true); 
        	
        	JLabel a1 = new JLabel("Unitati: ");
        	JLabel a2 = new JLabel("Client: ");
        	JLabel a3 = new JLabel("Produs: ");
        	
        	
        	tf2.setText("numar produse... ");
       		tf2.setPreferredSize(new Dimension(450, 20));

    	    cb1.setVisible(true);

    	    cb2.setVisible(true);
    	    
    	    topPanel.add(a0,BorderLayout.CENTER);
        	topPanel.add(cb3,BorderLayout.CENTER);
       		
        	
        	topPanel.add(a2,BorderLayout.CENTER);
        	topPanel.add(cb0,BorderLayout.CENTER);
        	
        	topPanel.add(a3,BorderLayout.CENTER);
        	topPanel.add(cb2,BorderLayout.CENTER);
        	
        	topPanel.add(a1,BorderLayout.CENTER);
        	topPanel.add(tf2,BorderLayout.CENTER);
        	
        }
        
        if(comp == "Produse:")
        {
        	JLabel l1_2 = new JLabel("PRODUS: ");
            titlePanel.add(l1_2);
            
            JLabel a0 = new JLabel("Produs intitial: ");

    	    cb1.setVisible(true);

        	JLabel a2 = new JLabel("Nume: ");
        	JLabel a3 = new JLabel("Stoc:    ");
        	JLabel a4 = new JLabel("Pret: ");
        	JLabel a5 = new JLabel("Distribuitori: ");
        	
       		tf2.setText("nume... ");
       		tf2.setPreferredSize(new Dimension(450, 20));
       		
       		tf3.setText("stoc... ");
       		tf3.setPreferredSize(new Dimension(450, 20));
       		
       		tf4.setText("pret... ");
       		tf4.setPreferredSize(new Dimension(450, 20));

    	    cb3.setVisible(true);
    	    
    	    topPanel.add(a0,BorderLayout.CENTER);
        	topPanel.add(cb1,BorderLayout.CENTER);

        	
        	topPanel.add(a2,BorderLayout.CENTER);
        	topPanel.add(tf2,BorderLayout.CENTER);
        	
        	topPanel.add(a3,BorderLayout.CENTER);
        	topPanel.add(tf3,BorderLayout.CENTER);
        	
        	topPanel.add(a4,BorderLayout.CENTER);
        	topPanel.add(tf4,BorderLayout.CENTER);
        	
        	topPanel.add(a5,BorderLayout.CENTER);
        	topPanel.add(cb3,BorderLayout.CENTER);
        }
        
        if(comp == "Distribuitori:")
        {
        	JLabel l1_2 = new JLabel("DISTRIBUITOR: ");
            titlePanel.add(l1_2);
            
            JLabel a0 = new JLabel("Distribuitor intitial: ");

    	    cb3.setVisible(true);

        	JLabel a2 = new JLabel("Nume: ");
        	JLabel a3 = new JLabel("Adresa: ");
       		
       		tf2.setText("nume... ");
       		tf2.setPreferredSize(new Dimension(450, 20));
       		
       		tf3.setText("adresa... ");
       		tf3.setPreferredSize(new Dimension(450, 20));
       		
       		
       		topPanel.add(a0,BorderLayout.CENTER);
        	topPanel.add(cb3,BorderLayout.CENTER);
        	
        	topPanel.add(a2,BorderLayout.CENTER);
        	topPanel.add(tf2,BorderLayout.CENTER);
        	
        	topPanel.add(a3,BorderLayout.CENTER);
        	topPanel.add(tf3,BorderLayout.CENTER);
        }
        

        
        
        JButton EDITButton = new JButton("EDIT");
        
        EDITButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent startEdit) {
            	if(comp == "Clienti:") {
            		int aux = cb0.getSelectedIndex();
        			int auxf = LegaturaDataAcces.returnId("client", aux);
        			final Client auxClient = new Client(auxf, tf2.getText(), tf3.getText(), tf4.getText());
        			DBOperations.editItem(auxClient);
        			System.out.println("Clientul cu ID: "+ auxf + " a fost modificat in Baza de Date specifica.");
            	}
            	
            	if(comp == "Distribuitori:") {
            		int aux = cb3.getSelectedIndex();
        			int auxf = LegaturaDataAcces.returnId("distribuitor", aux);
                	final Distribuitor auxDistribuitor = new Distribuitor(auxf, tf2.getText(), tf3.getText());
                	DBOperations.editItem(auxDistribuitor);
                	System.out.println("Distribuitorul cu ID: "+ auxf + " a fost modificat in Baza de Date specifica.");
                	}
            	
        		
        		if(comp == "Produse:") {
        			int aux = cb1.getSelectedIndex();
        			int auxf = LegaturaDataAcces.returnId("produs", aux);
        			int aux2 = cb3.getSelectedIndex();
        			int auxf2 = LegaturaDataAcces.returnId("distribuitor", aux2);
        			final Produs auxProdus = new Produs(auxf, tf2.getText(), Integer.parseInt(tf3.getText()), auxf2, Integer.parseInt(tf4.getText()));
        			DBOperations.editItem(auxProdus);
        			System.out.println("Produsul cu ID: "+ auxf + " a fost modificat in Baza de Date specifica.");
        		}
    	}
        });


        btnPanel.add(EDITButton);

    }
}