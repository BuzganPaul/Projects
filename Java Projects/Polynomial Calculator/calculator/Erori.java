package calculator;

public class Erori  extends Exception {
	String s="Introduceti alt polinom!";
	//exceptia ce afiseaza eroare daca polinomul nu a fost scris corespunzator cerintei
	public String getExceptie() {
	      return s;
	   }
}