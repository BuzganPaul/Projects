package Interfata;
import Bussiness.*;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class AdministratorGUI extends JFrame{
    public static JPanel topPanel;
    public JPanel btnPanel;
    public static JScrollPane scrollPanetabel;
    public static JTable  tb1 = new JTable();

    public AdministratorGUI( String nume){
        setTitle(nume);
        setSize(800,600);
        setBackground(Color.gray);
        setLayout(new BorderLayout());
        
        topPanel = new JPanel();
        btnPanel = new JPanel();
        
        add(topPanel, BorderLayout.CENTER);
        add(btnPanel, BorderLayout.SOUTH);

        
        topPanel.setLayout(new BorderLayout());
        
        String[] columnNames = {"Nume",
                "Pret",
                "Gramaj",
                "Contine"};
        
        Object[][] data = new Object[Restaurant.meniu.iteme.size()][4];
        int a=0;
        
        
        for(OperatiiIteme auxi : Restaurant.meniu.iteme)
        {
        	data[a][0]=auxi.getNume().replace("_", " ");
        	data[a][1]=auxi.getPret();
        	data[a][2]=auxi.getCantitate();
        	data[a][3]=auxi.printIngrediente();
        	a++;
        	
        }
        
        tb1=new JTable(data, columnNames);
        
        scrollPanetabel = new JScrollPane(tb1);
        topPanel.add(scrollPanetabel,BorderLayout.CENTER);
        
        JButton addButton = new JButton("ADAUGA ITEM SIMPLU");
        JButton addButton2 = new JButton("ADAUGA ITEM COMPUS");
        JButton editButton = new JButton("MODIFICA ITEM");
        JButton delButton = new JButton("STERGE ITEM");
        JButton saveButton = new JButton("SAVE");

        
        
        addButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent add) {
            	AddSimplu ad = new AddSimplu();
                ad.setVisible(true);
            }

        });
        
        addButton2.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent add2) {
            	AddCompus ad2 = new AddCompus();
                ad2.setVisible(true);
            }

        });
        

        
        editButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent edit) {
            	EditProdus ed = new EditProdus();
                ed.setVisible(true);
            }

        });

        
        
        delButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent delete) {
            	DeleteWindow del = new DeleteWindow();
                del.setVisible(true);
            }

        });
        
        saveButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent save) {
            	Restaurant.saveallData();
            }

        });
      

        btnPanel.add(addButton);
        btnPanel.add(addButton2);
        btnPanel.add(editButton);
        btnPanel.add(delButton);
        btnPanel.add(saveButton);
        }		
}