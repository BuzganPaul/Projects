package Interfata;
import Bussiness.*;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class BucatarGUI extends JFrame{
    public static JPanel topPanel;
    public static JScrollPane scrollPanetabel;
    public static JTable  tb1 = new JTable();

    public BucatarGUI( String nume, ItemMeniu me){
        setTitle(nume);
        setSize(300,300);
        setBackground(Color.gray);
        setLayout(new BorderLayout());
        
        topPanel = new JPanel();
        
        add(topPanel, BorderLayout.CENTER);
        topPanel.setLayout(new BorderLayout());
        
        JLabel l1_2 = new JLabel("COMANDA PT BUCATAR: ");
        topPanel.add(l1_2, BorderLayout.NORTH);
        
        final JTextField tf0 = new JTextField(" ");
        tf0.setText(me.printItem().replaceAll("_", " "));
   		tf0.setPreferredSize(new Dimension(450, 100));
   		topPanel.add(tf0, BorderLayout.CENTER);
        
        
        
        
}
}