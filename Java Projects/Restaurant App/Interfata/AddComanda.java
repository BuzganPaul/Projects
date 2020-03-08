package Interfata;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import Bussiness.*;


public class AddComanda extends JFrame{
	public JPanel titlePanel;
    public JPanel topPanel;
    public JPanel btnPanel;
    public JScrollPane scrollPane;

    public AddComanda(){
        setTitle("Add Comanda Window");
        setSize(550,600);
        setBackground(Color.gray);
        setLayout(new BorderLayout());
        
        
        titlePanel = new JPanel();
        topPanel = new JPanel();
        btnPanel = new JPanel();
        
        add(titlePanel, BorderLayout.NORTH);
        add(topPanel, BorderLayout.CENTER);
        add(btnPanel, BorderLayout.SOUTH);

        final JTextField tf0 = new JTextField(" ");
        final JTextField tf1 = new JTextField(" ");
        
        final ItemMeniu meniuAux =  new ItemMeniu();
        final Comanda comandaAux = new Comanda(-1);
        
        String[] choices2 = new String[Restaurant.meniu.iteme.size()];
        int a=0;
        
        for(OperatiiIteme auxi : Restaurant.meniu.iteme)
        {
        	choices2[a]=""+ a+1 +". ";
        	choices2[a]=auxi.getNume().replace("_", " ");
        	choices2[a]=choices2[a] + " " + auxi.getPret();
        	choices2[a]=choices2[a] + " " + auxi.getCantitate();
        	choices2[a]=choices2[a] + " " + auxi.printIngrediente() + ";";
        	a++;
        	
        }
		


	    final JComboBox<String> cb1_2 = new JComboBox<String>(choices2);

	    cb1_2.setVisible(true);

        

        	JLabel l1_2 = new JLabel("ADAUGA Comanda: ");
        	JLabel a2 = new JLabel("Item de pus in comanda: ");
        	
            titlePanel.add(l1_2);
        	
            JLabel a0 = new JLabel("Iteme comandate: ");
            JLabel a1 = new JLabel("Numarul mesei: ");
        	
        	tf0.setText("produse... ");
       		tf0.setPreferredSize(new Dimension(450, 100));
       		
        	 tf1.setText("nr masa.... ");
       		tf1.setPreferredSize(new Dimension(450, 20));


        	
        	topPanel.add(a1,BorderLayout.CENTER);
        	topPanel.add(tf1,BorderLayout.CENTER);

        
        	topPanel.add(a0,BorderLayout.CENTER);
        	topPanel.add(tf0,BorderLayout.CENTER);
        	
        	topPanel.add(a2,BorderLayout.CENTER);
        	topPanel.add(cb1_2,BorderLayout.CENTER);
        
        	
        
        JButton ADDButton = new JButton("Adauga");
        
        ADDButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent startAdd) {
            	comandaAux.setIdcomanda(Integer.parseInt(tf1.getText()));
            	Restaurant.Comenzi.put(comandaAux, meniuAux);
            	Restaurant.mese.add(comandaAux);
            	
            	Subject subject = new Subject();

                new Bucatar(subject);	
                subject.setState(meniuAux);
            	
            	System.out.println("La masa " + tf1.getText() +  " a fost adaugata comanda.");
            	
            	
            	   

      
        }
    });
        JButton ADDButtonIngred = new JButton("Adauga Produse");
        
        ADDButtonIngred.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent startAddIngred) {
            	
            	//ProdusSimplu auxiliar = new ProdusSimplu(tf1IN.getText(), Integer.parseInt(tf2IN.getText()), Integer.parseInt(tf3IN.getText()));
            	//auxiliarFinal.addIngredient(auxiliar);
            	meniuAux.addItem(Restaurant.meniu.iteme.get(cb1_2.getSelectedIndex()));
            	
            	String toShow = "";
            	
            	for(OperatiiIteme a : meniuAux.iteme)
            	{
            		toShow = toShow + a.getNume().replaceAll("_", " ") + ", ";
            	}
            	
            	tf0.setText(toShow);
            	comandaAux.setIdcomanda(Integer.parseInt(tf1.getText()));
            	
            	System.out.println(" A fost adaugat un produs in comanda.");
            	   

      
        }
    });
        

        btnPanel.add(ADDButton);
        btnPanel.add(ADDButtonIngred);

    
}
}
