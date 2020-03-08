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

public class Interfata extends JFrame{

	
	public JPanel btnPanel;
	public JTable t1;
    
    
    
    public Interfata(){
        setTitle("Aplicatie Depozit");
        setSize(600,100);
        setBackground(Color.gray);

        btnPanel = new JPanel();
        
        
        getContentPane().add(btnPanel);
        
        JLabel l0 = new JLabel("APLICATII DEPOZIT: ");
        JButton clienti = new JButton("CLIENTI");
        JButton produse = new JButton("PRODUSE");
        JButton comenzi = new JButton("COMENZI");
        JButton distribuitori = new JButton("DISTRIBUITORI");
        
        clienti.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
            	ModificatoriTabel opclienti = new ModificatoriTabel("Clienti Window", "Clienti:");
                opclienti.setVisible(true);
            }

        });
        
        
        produse.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e2) {
            	ModificatoriTabel opproduse = new ModificatoriTabel("Produse Window", "Produse:");
                opproduse.setVisible(true);
            }

        });
        
        comenzi.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e3) {
            	ModificatoriTabel opcomenzi = new ModificatoriTabel("Comenzi Window", "Comenzi:");
                opcomenzi.setVisible(true);
            }

        });
        
        distribuitori.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e4) {
            	ModificatoriTabel opdistribuitori = new ModificatoriTabel("Distribuitori Window", "Distribuitori:");
                opdistribuitori.setVisible(true);
            }

        });

        btnPanel.add(l0);
        btnPanel.add(clienti);
        btnPanel.add(produse);
        btnPanel.add(comenzi);
        btnPanel.add(distribuitori);

    }
}
