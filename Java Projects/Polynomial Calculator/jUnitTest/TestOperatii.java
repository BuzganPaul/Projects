package jUnitTest;

import calculator.Erori;
import calculator.OperatiiPolinoame;
import junit.framework.TestCase;
import termen.Polinom;

public class TestOperatii extends TestCase{
	
	public void testOperatii(){
		Polinom A1 = new Polinom();
        Polinom A2 = new Polinom();
        
        Polinom B1 = new Polinom();
        Polinom B2 = new Polinom();
        
        Polinom C1 = new Polinom();
        Polinom C2 = new Polinom();
        
        Polinom D1 = new Polinom();
        Polinom D2 = new Polinom();
        
        Polinom E1 = new Polinom();

        
        Polinom F1 = new Polinom();
        
        Polinom A11 = new Polinom();
        Polinom A22 = new Polinom();
        
        Polinom B11 = new Polinom();
        Polinom B22 = new Polinom();
        
        Polinom C11 = new Polinom();
        Polinom C22 = new Polinom();
        
        Polinom D11 = new Polinom();
        Polinom D22 = new Polinom();
        
        Polinom E11 = new Polinom();
        
        Polinom F11 = new Polinom();
        
		try {
			A1.citestePolinom("X^10+2X^5-1");
			A2.citestePolinom("X^6+23X^2-1");
			
			B1.citestePolinom("X^4-X^5");
			B2.citestePolinom("X^11+23X^1");
			
			C1.citestePolinom("X^1+1X^0");
			C2.citestePolinom("X^10");
			
			D1.citestePolinom("X^10+2X^5-1");
			D2.citestePolinom("^10+23X^-1");
			
			E1.citestePolinom("X^12+2X^5");
			
			F1.citestePolinom("X^11-7");
			
			A11.citestePolinom("X^10+2X^5-1");
			A22.citestePolinom("^10+23X^-1");
			
			B11.citestePolinom("X^10+2X^5-1");
			B22.citestePolinom("^10+23X^-1");
			
			C11.citestePolinom("X^10+2X^5-1");
			C22.citestePolinom("^10+23X^-1");
			
			D11.citestePolinom("X^10+2X^5-1");
			D22.citestePolinom("^10+23X^-1");
			
			E11.citestePolinom("X^10+2X^5-1");
			
			F11.citestePolinom("X^10+2X^5-1");
			
		} catch (Erori e) {
			System.out.println("Eroare de citire.");
			//e.printStackTrace();
		}
		
		assertEquals( " +1.0X^10+1.0X^6+2.0X^5+23.0X^2 0.0X^-1", OperatiiPolinoame.adunare(A1, A2));
		assertEquals( " -1.0X^5+1.0X^4 -23.0X^1 -1.0X^11", OperatiiPolinoame.scadere(B1, B2));
		assertEquals( " +1.0X^10+1.0X^11", OperatiiPolinoame.inmultire(C1, C2));
		//assertEquals( " +1.0X^10+1.0X^11", OperatiiPolinoame.inmultire(D1, D2));
		assertEquals( " +10.0X^4+12.0X^11", OperatiiPolinoame.derivare(E1));
		assertEquals( "  -0.0X^-6+0.09090909X^12", OperatiiPolinoame.integrare(F1));
		
		assertEquals( " +1.00X^10+1.0X^6+2.0X^5+23.0X^2 0.0X^-1", OperatiiPolinoame.adunare(A11, A22));
		assertEquals( " -1.0X^5+1.0X^4 -223.0X^1 -1.0X^11", OperatiiPolinoame.scadere(B11, B22));
		assertEquals( " +1.0X^100+1.0X^11", OperatiiPolinoame.inmultire(C11, C22));
		//assertEquals( " +1.0X^10+1.0X^11", OperatiiPolinoame.inmultire(D11, D22));
		assertEquals( " +10.0X^4+12s.0X^11", OperatiiPolinoame.derivare(E11));
		assertEquals( "  -0.03X^-6+f0.09090909X^12", OperatiiPolinoame.integrare(F11));
		
		
	}

}
