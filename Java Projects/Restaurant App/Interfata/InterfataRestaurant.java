package Interfata;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import Bussiness.Restaurant;

public class InterfataRestaurant extends JFrame{

	
	public JPanel btnPanel;
	public JTable t1;
    
    
    
    public InterfataRestaurant(){
        setTitle("Aplicatie Restaurant");
        setSize(600,100);
        setBackground(Color.gray);

        btnPanel = new JPanel();
        
        
        getContentPane().add(btnPanel);
        
        JLabel l0 = new JLabel("APLICATII RESTAURANT: ");
        JButton administrator = new JButton("ADMINISTRATOR");
        JButton ospatar = new JButton("OSPATAR");
        Restaurant.bringData();
        
        administrator.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
            	AdministratorGUI opadministrator = new AdministratorGUI("Administrator Window");
                opadministrator.setVisible(true);
            }

        });
        
        
        ospatar.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e2) {
            	OspatarGUI opospatar = new OspatarGUI("Ospatar Window");
                opospatar.setVisible(true);
            }

        });
        
        

        btnPanel.add(l0);
        btnPanel.add(administrator);
        btnPanel.add(ospatar);

    }
}