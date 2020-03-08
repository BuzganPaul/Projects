package Bussiness;

import Interfata.BucatarGUI;

public class Bucatar extends Observer{

	   public Bucatar(Subject subject){
	      this.subject = subject;
	      this.subject.attach(this);
	   }

	   @Override
	   public void update() {
	      //System.out.println( "Binary String: " + Integer.toBinaryString( subject.getState() ) ); 
		   BucatarGUI a = new BucatarGUI( "Bucatar Window", subject.getState());
		   a.setVisible(true);
	   }
	}

