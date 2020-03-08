package termen;
public class Monom {
	public int exponent;
	public float coeficient;
	//monomul contine un exponent si un coeficient

	public Monom(int a, int b)
	{
		exponent=a;
		coeficient=b;
	}
	//gettere si setare
	public int getExponent() {
		return exponent;
	}

	public void setExponent(int exponent) {
		this.exponent = exponent;
	}

	public float getCoeficient() {
		return coeficient;
	}

	public void setCoeficient(float coeficient) {
		this.coeficient = coeficient;
	}

	public String toString()
	{
		if(this.coeficient>0) {
		return "+"+ this.coeficient + "X^" + this.exponent;
		}
		else
		{
			return " "+ this.coeficient + "X^" + this.exponent;
		}
		//se afiseaza dupa o maniera usor de citit ca string
	}
}
