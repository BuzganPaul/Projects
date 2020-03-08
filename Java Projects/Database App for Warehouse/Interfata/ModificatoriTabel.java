package Interfata;
import Bussiness.*;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ModificatoriTabel extends JFrame{
    public static JPanel topPanel;
    public JPanel btnPanel;
    public static JScrollPane scrollPanetabel;
    public static JTable  tb1 = new JTable();

    public ModificatoriTabel( String nume, final String comp){
        setTitle(nume);
        setSize(600,600);
        setBackground(Color.gray);
        setLayout(new BorderLayout());
        
        topPanel = new JPanel();
        btnPanel = new JPanel();
        
        add(topPanel, BorderLayout.CENTER);
        add(btnPanel, BorderLayout.SOUTH);

        
        topPanel.setLayout(new BorderLayout());
        
        if(comp == "Clienti:")
        {
        	tb1= Tabele.generareClient();
        }
        if(comp == "Produse:")
        {
        	tb1= Tabele.generareProdus();
        }
        if(comp == "Comenzi:")
        {
        	tb1= Tabele.generareComanda();
        }
        if(comp == "Distribuitori:")
        {
        	tb1= Tabele.generareDistribuitor();
        }

        scrollPanetabel = new JScrollPane(tb1);
        topPanel.add(scrollPanetabel,BorderLayout.CENTER);
        
        JButton addButton = new JButton("ADD");
        JButton editButton = new JButton("EDIT");
        JButton delButton = new JButton("DELETE");

        
        
        addButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent add) {
            	AddWindow ad = new AddWindow(comp);
                ad.setVisible(true);
            }

        });
        
        editButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent edit) {
            	EditWindow ed = new EditWindow(comp);
                ed.setVisible(true);
            }

        });
        
        
        delButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent delete) {
            	DeleteWindow del = new DeleteWindow(comp);
                del.setVisible(true);
            }

        });
      
    

        btnPanel.add(addButton);
        if(comp!="Comenzi:") {
        btnPanel.add(editButton);
        }
        btnPanel.add(delButton);
        }		
}
