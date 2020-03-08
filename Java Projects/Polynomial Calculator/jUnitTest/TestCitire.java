package jUnitTest;

import calculator.Erori;
import junit.framework.TestCase;
import termen.Polinom;

public class TestCitire extends TestCase{
	
	public void testCitire() {
        Polinom A1 = new Polinom();
        Polinom A2 = new Polinom();
			try {
				A1.citestePolinom("X^10+23X^5-1");
				A2.citestePolinom("A^10+23X^-1");
			} catch (Erori e) {
				System.out.println("Eroare de citire.");
				//e.printStackTrace();
			}
        assertEquals( "+1.0X^10+23.0X^5 0.0X^-1", A1.toString());
        assertFalse( "+1.0A^10+23.0X^5 0.0X^-1".equals(A2.toString()));

        
    }

}
