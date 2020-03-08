package Interfata;
import java.awt.*;
import Model.*;
import java.awt.event.*;
import javax.swing.*;

import Bussiness.Chitanta;
import Bussiness.DBOperations;
import Bussiness.LegaturaDataAcces;
import Bussiness.Tabele;


public class AddWindow extends JFrame{
	public JPanel titlePanel;
    public JPanel topPanel;
    public JPanel btnPanel;
    public JScrollPane scrollPane;

    public AddWindow(final String comp){
        setTitle("Add Window");
        setSize(550,350);
        setBackground(Color.gray);
        setLayout(new BorderLayout());
        
        
        titlePanel = new JPanel();
        topPanel = new JPanel();
        btnPanel = new JPanel();
        
        add(titlePanel, BorderLayout.NORTH);
        add(topPanel, BorderLayout.CENTER);
        add(btnPanel, BorderLayout.SOUTH);
        
        String[] choices1 =  null;
        String[] choices2 =  null;
        String[] choices3 =  null;
        
        final JTextField tf0 = new JTextField(" ");
        final JTextField tf1 = new JTextField(" ");
        final JTextField tf2 = new JTextField(" ");
        final JTextField tf3 = new JTextField(" ");
        final JTextField tf4 = new JTextField(" ");
        
        choices1 =  LegaturaDataAcces.toStringOptions("distribuitor");
        choices2 =  LegaturaDataAcces.toStringOptions("client");
         choices3 =  LegaturaDataAcces.toStringOptions("produs");
        
        final JComboBox<String> cb1_2 = new JComboBox<String>(choices1);
        final JComboBox<String> cm2 = new JComboBox<String>(choices2);
        final JComboBox<String> cm3 = new JComboBox<String>(choices3);
        
        if(comp == "Clienti:")
        {
        	JLabel l1_2 = new JLabel("CLIENT: ");
            titlePanel.add(l1_2);
        	
        	JLabel a1 = new JLabel("ID Client: ");
        	JLabel a2 = new JLabel("Nume: ");
        	JLabel a3 = new JLabel("Adresa: ");
        	JLabel a4 = new JLabel("Email: ");
        	
        	
        	 tf1.setText("ID... ");
       		tf1.setPreferredSize(new Dimension(450, 20));
       		
       		 tf2.setText("nume... ");
       		tf2.setPreferredSize(new Dimension(450, 20));
       		
       		tf3.setText("adresa... ");
       		tf3.setPreferredSize(new Dimension(450, 20));
       		
       		tf4.setText("email... ");
       		tf4.setPreferredSize(new Dimension(450, 20));
        	
        	topPanel.add(a1,BorderLayout.CENTER);
        	topPanel.add(tf1,BorderLayout.CENTER);
        	
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
        	
            JLabel a0 = new JLabel("ID:                ");
        	JLabel a1 = new JLabel("Unitati:           ");
        	JLabel a2 = new JLabel("Client: ");
        	JLabel a3 = new JLabel("Produs: ");
        	
        	
        	tf0.setText("ID... ");
       		tf0.setPreferredSize(new Dimension(450, 20));
        	
        	tf1.setText("numar produse... ");
       		tf1.setPreferredSize(new Dimension(450, 20));

    	    cm2.setVisible(true);
    	    cm3.setVisible(true);
       		
    	    
        	topPanel.add(a2,BorderLayout.CENTER);
        	topPanel.add(cm2,BorderLayout.CENTER);
        	
        	topPanel.add(a3,BorderLayout.CENTER);
        	topPanel.add(cm3,BorderLayout.CENTER);
        	
        	topPanel.add(a0,BorderLayout.CENTER);
        	topPanel.add(tf0,BorderLayout.CENTER);
        	
        	topPanel.add(a1,BorderLayout.CENTER);
        	topPanel.add(tf1,BorderLayout.CENTER);
        	
        }
        
        if(comp == "Produse:")
        {
        	JLabel l1_2 = new JLabel("PRODUS: ");
            titlePanel.add(l1_2);
        	
        	JLabel a1 = new JLabel("ID Produs: ");
        	JLabel a2 = new JLabel("Nume: ");
        	JLabel a3 = new JLabel("Stoc:    ");
        	JLabel a4 = new JLabel("Pret: ");
        	JLabel a5 = new JLabel("Distribuitori: ");
        	
        	
        	tf1.setText("ID... ");
       		tf1.setPreferredSize(new Dimension(450, 20));
       		
       		tf2.setText("nume... ");
       		tf2.setPreferredSize(new Dimension(450, 20));
       		
       		tf3.setText("stoc... ");
       		tf3.setPreferredSize(new Dimension(450, 20));
       		
       		tf4.setText("pret... ");
       		tf4.setPreferredSize(new Dimension(450, 20));

    	    cb1_2.setVisible(true);
        	
        	topPanel.add(a1,BorderLayout.CENTER);
        	topPanel.add(tf1,BorderLayout.CENTER);
        	
        	topPanel.add(a2,BorderLayout.CENTER);
        	topPanel.add(tf2,BorderLayout.CENTER);
        	
        	topPanel.add(a3,BorderLayout.CENTER);
        	topPanel.add(tf3,BorderLayout.CENTER);
        	
        	topPanel.add(a4,BorderLayout.CENTER);
        	topPanel.add(tf4,BorderLayout.CENTER);
        	
        	topPanel.add(a5,BorderLayout.CENTER);
        	topPanel.add(cb1_2,BorderLayout.CENTER);
        }
        
        if(comp == "Distribuitori:")
        {
        	JLabel l1_2 = new JLabel("DISTRIBUITOR: ");
            titlePanel.add(l1_2);
        	
        	JLabel a1 = new JLabel("ID Distrib: ");
        	JLabel a2 = new JLabel("Nume: ");
        	JLabel a3 = new JLabel("Adresa: ");
        	
        	
        	tf1.setText("ID... ");
       		tf1.setPreferredSize(new Dimension(450, 20));
       		
       		tf2.setText("nume... ");
       		tf2.setPreferredSize(new Dimension(450, 20));
       		
       		tf3.setText("adresa... ");
       		tf3.setPreferredSize(new Dimension(450, 20));

        	
        	topPanel.add(a1,BorderLayout.CENTER);
        	topPanel.add(tf1,BorderLayout.CENTER);
        	
        	topPanel.add(a2,BorderLayout.CENTER);
        	topPanel.add(tf2,BorderLayout.CENTER);
        	
        	topPanel.add(a3,BorderLayout.CENTER);
        	topPanel.add(tf3,BorderLayout.CENTER);
        }
        

        
        
        JButton ADDButton = new JButton("ADD");
        
        ADDButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent startAdd) {
            	if(comp == "Clienti:") {
            	final Client auxClient = new Client(Integer.parseInt(tf1.getText()), tf2.getText(), tf3.getText(), tf4.getText());
            	DBOperations.addItem(auxClient);
            	System.out.println(tf2.getText() + " a fost adaugat ca si client in Baza de Date specifica.");
            	}
            	
            	if(comp == "Distribuitori:") {
                	final Distribuitor auxDistribuitor = new Distribuitor(Integer.parseInt(tf1.getText()), tf2.getText(), tf3.getText());
                	DBOperations.addItem(auxDistribuitor);
                	System.out.println(tf2.getText() + " a fost adaugat ca distribuitor in Baza de Date specifica.");
                	}
            	
            	if(comp == "Comenzi:") {
            		int aux = cm2.getSelectedIndex();
            		int aux2 = cm3.getSelectedIndex();
            		int auxf = LegaturaDataAcces.returnId("client", aux);
            		int auxf2 = LegaturaDataAcces.returnId("produs", aux2);

            		if (LegaturaDataAcces.returnStoc("produs", aux2) < Integer.parseInt(tf1.getText())) {
                        JOptionPane.showMessageDialog(null, "Nu avem atatea produse in stoc! Micsorati numarul de produse sau asteptati refacerea stocului.");
            		}
            		else
            		{
            		Produs deModP = new Produs(LegaturaDataAcces.returnId("produs", aux2), LegaturaDataAcces.returnNume("produs", aux2), (LegaturaDataAcces.returnStoc("produs", aux2) - Integer.parseInt(tf1.getText())), LegaturaDataAcces.returnIdD("produs", aux2), LegaturaDataAcces.returnPret("produs", aux2));
            		DBOperations.editItem(deModP);
            		
            		Comanda auxComanda = new Comanda(Integer.parseInt(tf0.getText()), auxf, auxf2, Integer.parseInt(tf1.getText()), Integer.parseInt(tf1.getText()) * LegaturaDataAcces.returnPret("produs", aux2));
            		DBOperations.addItem(auxComanda);
            		Chitanta.scrieChitanta( aux, aux2, Integer.parseInt(tf1.getText()), Integer.parseInt(tf1.getText()) * LegaturaDataAcces.returnPret("produs", aux2));
            		System.out.println(tf0.getText() + " a fost adaugata ca ca si comanda in Baza de Date specifica.");
            		}
            	}
        	
        		
        		if(comp == "Produse:") {
        			int aux = cb1_2.getSelectedIndex();
        			int auxf = LegaturaDataAcces.returnId("distribuitor", aux);
        			final Produs auxProdus = new Produs(Integer.parseInt(tf1.getText()), tf2.getText(), Integer.parseInt(tf3.getText()), auxf, Integer.parseInt(tf4.getText()));
        			DBOperations.addItem(auxProdus);
        			System.out.println(tf2.getText() + " a fost adaugat ca produs in Baza de Date specifica.");
        		}
    	
    	}
            
            

        });


        btnPanel.add(ADDButton);

    }
}