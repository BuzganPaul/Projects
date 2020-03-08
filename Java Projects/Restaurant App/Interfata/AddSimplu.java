package Interfata;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import Bussiness.*;


public class AddSimplu extends JFrame{
	public JPanel titlePanel;
    public JPanel topPanel;
    public JPanel btnPanel;
    public JScrollPane scrollPane;

    public AddSimplu(){
        setTitle("Add Produs Simplu Window");
        setSize(550,200);
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
        final JTextField tf2 = new JTextField(" ");
        final JTextField tf3 = new JTextField(" ");

        

        	JLabel l1_2 = new JLabel("ADD PRODUS SIMPLU: ");
            titlePanel.add(l1_2);
        	
        	JLabel a1 = new JLabel("Nume: ");
        	JLabel a2 = new JLabel("Pret:    ");
        	JLabel a3 = new JLabel("Gramaj:   ");
        	
        	
        	 tf1.setText("nume produs simplu... ");
       		tf1.setPreferredSize(new Dimension(450, 20));
       		
       		 tf2.setText("pret... ");
       		tf2.setPreferredSize(new Dimension(450, 20));
       		
       		tf3.setText("gramaj... ");
       		tf3.setPreferredSize(new Dimension(450, 20));

        	
        	topPanel.add(a1,BorderLayout.CENTER);
        	topPanel.add(tf1,BorderLayout.CENTER);
        	
        	topPanel.add(a2,BorderLayout.CENTER);
        	topPanel.add(tf2,BorderLayout.CENTER);
        	
        	topPanel.add(a3,BorderLayout.CENTER);
        	topPanel.add(tf3,BorderLayout.CENTER);


        

        
        
        JButton ADDButton = new JButton("Adauga");
        
        ADDButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent startAdd) {
            	
            	final ProdusSimplu auxProdSimplu = new ProdusSimplu(tf1.getText(), Integer.parseInt(tf2.getText()), Integer.parseInt(tf3.getText()));
            	Restaurant.meniu.addItem(auxProdSimplu);
            	
            	System.out.println(tf1.getText() +  " a fost adaugat ca produs simplu in meniu.");
            	   

      
        }
    });
        btnPanel.add(ADDButton);

    
}
}