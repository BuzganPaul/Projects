package Interfata;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import Bussiness.*;


public class DeleteWindow extends JFrame{
    public JPanel topPanel;
    public JPanel btnPanel;
    public JScrollPane scrollPane;

    public DeleteWindow(){
        setTitle("Delete Window");
        setSize(600,150);
        setBackground(Color.gray);
        setLayout(new BorderLayout());
        
        topPanel = new JPanel();
        btnPanel = new JPanel();
        
        add(topPanel, BorderLayout.CENTER);
        add(btnPanel, BorderLayout.SOUTH);

        topPanel.setLayout(new BorderLayout());
        
        JLabel l1_2 = new JLabel("Delete Item Meniu:");
        
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
		
        
        topPanel.add(l1_2,BorderLayout.NORTH);
        topPanel.add(cb1_2,BorderLayout.CENTER);
        
        
        JButton exButton = new JButton("STERGE");
        
        exButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent startDelete) {
            	Restaurant.meniu.removeItem(cb1_2.getSelectedIndex());
            	System.out.println(cb1_2.getSelectedItem() +  " a fost sters ca produs in meniu.");
            	
    	}
        });


        btnPanel.add(exButton);

    }
}