package Interfata;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import Bussiness.*;


public class EditProdus extends JFrame{
	public JPanel titlePanel;
    public JPanel topPanel;
    public JPanel btnPanel;
    public JScrollPane scrollPane;

    public EditProdus(){
        setTitle("Edit Produs Window");
        setSize(550,600);
        setBackground(Color.gray);
        setLayout(new BorderLayout());
        
        
        titlePanel = new JPanel();
        topPanel = new JPanel();
        btnPanel = new JPanel();
        
        add(titlePanel, BorderLayout.NORTH);
        add(topPanel, BorderLayout.CENTER);
        add(btnPanel, BorderLayout.SOUTH);
        
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
	    cb1_2.setPreferredSize(new Dimension(500, 30));

	    cb1_2.setVisible(true);

        final JTextField tf0 = new JTextField(" ");
        final JTextField tf1 = new JTextField(" ");
        final JTextField tf2 = new JTextField(" ");
        final JTextField tf3 = new JTextField(" ");
        
        final JTextField tf1IN = new JTextField(" ");
        final JTextField tf2IN = new JTextField(" ");
        final JTextField tf3IN = new JTextField(" ");
        
        final ProdusCompus auxiliarFinal = new ProdusCompus(" ", 0, 0);

        

        	JLabel l1_2 = new JLabel("EDITEAZA PRODUS: ");
            titlePanel.add(l1_2);
            

            JLabel a0 = new JLabel("Ingredinete adaugate: ");
        	JLabel a1 = new JLabel("Nume:   ");
        	JLabel a2 = new JLabel("Pret:    ");
        	JLabel a3 = new JLabel("Gramaj:   ");
        	
        	JLabel a1IN = new JLabel("Nume Ingredient: ");
        	JLabel a2IN = new JLabel("Pret Ingredient:    ");
        	JLabel a3IN = new JLabel("Gramaj Ingredient:   ");
        	
        	tf0.setText("ingrediente... ");
       		tf0.setPreferredSize(new Dimension(500, 100));
       		
        	 tf1.setText("nume produs compus... ");
       		tf1.setPreferredSize(new Dimension(500, 20));
       		
       		 tf2.setText("pret... ");
       		tf2.setPreferredSize(new Dimension(500, 20));
       		
       		tf3.setText("gramaj... ");
       		tf3.setPreferredSize(new Dimension(500, 20));
       		
       		
       		tf1IN.setText("nume ingredient... ");
    		tf1IN.setPreferredSize(new Dimension(450, 20));
    		
    		tf2IN.setText("pret ingredient... ");
    		tf2IN.setPreferredSize(new Dimension(450, 20));
    		
    		tf3IN.setText("gramaj ingredient... ");
    		tf3IN.setPreferredSize(new Dimension(450, 20));

        	topPanel.add(cb1_2,BorderLayout.CENTER);
    		
        	topPanel.add(a1,BorderLayout.CENTER);
        	topPanel.add(tf1,BorderLayout.CENTER);
        	
        	topPanel.add(a2,BorderLayout.CENTER);
        	topPanel.add(tf2,BorderLayout.CENTER);
        	
        	topPanel.add(a3,BorderLayout.CENTER);
        	topPanel.add(tf3,BorderLayout.CENTER);

        
        	topPanel.add(a0,BorderLayout.CENTER);
        	topPanel.add(tf0,BorderLayout.CENTER);
        
        	
        	topPanel.add(a1IN,BorderLayout.CENTER);
        	topPanel.add(tf1IN,BorderLayout.CENTER);
        	
        	topPanel.add(a2IN,BorderLayout.CENTER);
        	topPanel.add(tf2IN,BorderLayout.CENTER);
        	
        	topPanel.add(a3IN,BorderLayout.CENTER);
        	topPanel.add(tf3IN,BorderLayout.CENTER);
        
        JButton ADDButton = new JButton("Editeaza");
        
        ADDButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent startAdd) {
            	auxiliarFinal.setNume(tf1.getText());
            	auxiliarFinal.setPret(Integer.parseInt(tf2.getText()));
            	auxiliarFinal.setCantitate(Integer.parseInt(tf3.getText()));
            	
            	Restaurant.meniu.removeItem(cb1_2.getSelectedIndex());
            	Restaurant.meniu.addItem(auxiliarFinal);
            	
            	System.out.println(tf1.getText() +  " a fost editat in meniu.");
            	
            	
            	   

      
        }
    });
        JButton ADDButtonIngred = new JButton("Editeaza Ingrediente");
        
        ADDButtonIngred.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent startAddIngred) {
            	
            	ProdusSimplu auxiliar = new ProdusSimplu(tf1IN.getText(), Integer.parseInt(tf2IN.getText()), Integer.parseInt(tf3IN.getText()));
            	auxiliarFinal.addIngredient(auxiliar);
            	
            	String toShow = "";
            	
            	for(ProdusSimplu a : auxiliarFinal.ingrediente)
            	{
            		toShow = toShow + a.getNume() + "\n";
            	}
            	
            	tf0.setText(toShow);
            	
            	auxiliarFinal.setNume(tf1.getText());
            	auxiliarFinal.setPret(Integer.parseInt(tf2.getText()));
            	auxiliarFinal.setCantitate(Integer.parseInt(tf3.getText()));
            	
            	System.out.println(tf1IN.getText() +  " a fost editat un ingredient in produs.");
            	   

      
        }
    });
        

        btnPanel.add(ADDButton);
        btnPanel.add(ADDButtonIngred);

    
}
}