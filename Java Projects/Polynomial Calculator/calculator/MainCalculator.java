//Tema 1: Calculator de polinoame
//Buzgan Paul Grupa 30226



package calculator;

import java.awt.Dimension;


import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import termen.Polinom;


public class MainCalculator implements OperatiiPolinoame{
	

	public static void main(String[] args) {
	
		//cream inetrfata grafica
		JFrame frame = new JFrame("Calculator de polinoame:");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(600, 300);
		
		//impartim in diferite zone cu diferite caracteristici
		JPanel panel1 = new JPanel();
		JPanel panel2 = new JPanel();
		JPanel panel3 = new JPanel();
		JPanel panel4 = new JPanel();
		
		JPanel p = new JPanel();
		p.add(panel1);
		p.add(panel2);
		p.add(panel3);
		p.add(panel4);
		
		
		//construim zona pt primul polinom de scris
		JLabel l1 = new JLabel("Primul polinom: ");
		JTextField tf1 = new JTextField(" Scrie un polinom ");
		tf1.setPreferredSize(new Dimension(450, 20));
		panel1.add(l1);
		panel1.setLayout(new FlowLayout());
		panel1.add(tf1);
		
		//construim zona pt al doilea polinom de scris
		JLabel l2 = new JLabel("Al doilea polinom: ");
		JTextField tf2 = new JTextField(" Scrie un polinom ");
		tf2.setPreferredSize(new Dimension(450, 20));
		panel2.add(l2);
		panel2.setLayout(new FlowLayout());
		panel2.add(tf2);
		
		
		
		
		//cream butoane
		JButton b1 = new JButton("Adunare");
		JButton b2 = new JButton("Scadere");
		JButton b3 = new JButton("Inmultire");
		JButton b4 = new JButton("Impartire");
		JButton b5 = new JButton("Derivare");
		JButton b6 = new JButton("Integrare");
		JLabel l4 = new JLabel("Rezultat: ");
		
		//folosim doua polinoame care sa retina ce scriem in campuri
		
		Polinom A1 =new Polinom();
   		Polinom A2 =new Polinom();
   		JTextField tf4 = new JTextField("Rezultat ");
   		tf4.setPreferredSize(new Dimension(450, 20));
   		//pornim butonul
        b1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
					try {
						String pol1=tf1.getText();//iau valorile din camp
						String pol2=tf2.getText();
						A1.citestePolinom(pol1);//se formeaza din string in polinom din strcutura
						A2.citestePolinom(pol2);
						System.out.println(A1.toString());//afisam in consola pt siguranta
						System.out.println(A2.toString());
						tf4.setText(OperatiiPolinoame.adunare(A1, A2));//facem operatia
						panel4.add(tf4);//refresh la campul cu raspusul pt a afisa direct raspusul dupa apasare
						A1.poli.clear(); //golim memoria din hash
						A2.poli.clear(); 
					} catch (Erori e1) {
						System.out.println("Nu e bine scris polinomul!  " + e1.getExceptie());//exceptie in caz ca se scrie aiurea polinomul
						//e1.printStackTrace();
					}
			}

		});
        
       b2.addActionListener(new ActionListener() {
			//la fel ca primul numai ca alta operatie si tot asa la celelalte
			@Override
			public void actionPerformed(ActionEvent e2) {
					try {
						String pol1=tf1.getText();
						String pol2=tf2.getText();
						A1.citestePolinom(pol1);
						A2.citestePolinom(pol2);
						System.out.println(A1.toString());
						System.out.println(A2.toString());
						tf4.setText(OperatiiPolinoame.scadere(A1, A2));
						panel4.add(tf4);
						A1.poli.clear(); 
						A2.poli.clear();
					} catch (Erori e1) {
						System.out.println("Nu e bine scris polinomul!  " + e1.getExceptie());
						//e1.printStackTrace();
					}
			}
		});
       
       b3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e3) {
					try {
						String pol1=tf1.getText();
						String pol2=tf2.getText();
						A1.citestePolinom(pol1);
						A2.citestePolinom(pol2);
						System.out.println(A1.toString());
						System.out.println(A2.toString());
						tf4.setText(OperatiiPolinoame.inmultire(A1, A2));
						panel4.add(tf4);
						A1.poli.clear(); 
						A2.poli.clear();
					} catch (Erori e1) {
						System.out.println("Nu e bine scris polinomul!  " + e1.getExceptie());
						//e1.printStackTrace();
					}
			}
		});
       
       b4.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e4) {
					try {
						String pol1=tf1.getText();
						String pol2=tf2.getText();
						A1.citestePolinom(pol1);
						A2.citestePolinom(pol2);
						System.out.println(A1.toString());
						System.out.println(A2.toString());
					} catch (Erori e1) {
						System.out.println("Nu e bine scris polinomul!  " + e1.getExceptie());
						//e1.printStackTrace();
					}
			}
		});
       
       b5.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e5) {
					try {
						String pol1=tf1.getText();
						A1.citestePolinom(pol1);
						System.out.println(A1.toString());
						tf4.setText(OperatiiPolinoame.derivare(A1));
						panel4.add(tf4);
						A1.poli.clear(); 
					} catch (Erori e1) {
						System.out.println("Nu e bine scris polinomul!  " + e1.getExceptie());
						//e1.printStackTrace();
					}
			}
		});
       
       b6.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e6) {
					try {
						String pol1=tf1.getText();
						A1.citestePolinom(pol1);
						System.out.println(A1.toString());
						tf4.setText(OperatiiPolinoame.integrare(A1));
						panel4.add(tf4);
						A1.poli.clear(); 
					} catch (Erori e1) {
						System.out.println("Nu e bine scris polinomul!  " + e1.getExceptie());
						//e1.printStackTrace();
					}
			}
		});
       

		//adaugam butoane
		
        panel3.add(b1);
        panel3.add(b2);
        panel3.add(b3);
        panel3.add(b4);
        panel3.add(b5);
        panel3.add(b6);
        //adaugam unde se afiseaza raspusul
        panel4.add(l4);
        panel4.add(tf4);
		
        //terminam de facut interfata grafica
		p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
   		frame.setContentPane(p);
   		frame.setVisible(true);
   		
   		
 
	}



	}


