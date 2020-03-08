package termen;
import java.util.Hashtable;

import calculator.Erori;

public class Polinom {
	
	public Hashtable<Integer, Monom> poli = new Hashtable<Integer, Monom>(); 
	//folosim hashtable pt a stoca monoamele
	
	
	public void citestePolinom(String polinom) throws Erori
	{
		String verificare="1234567890+-^X";//un string cu toate caracterele permise orice altceva scris da eroare
		boolean ver=false;//acesta verifica si daca sunt scrise cum trebuie ca ordine si daca contin alta caractere
		String polinom2;
		
		//am luat caractere la verificat pe rand daca contin alt caractere decat este nevoie dupa
		//pt fiecare caracter am verificat ce este in spatele si in fata lui pentru a nu exista semne scrise unul dupa altul
		//sau lipsa de anumiti termeni in polinom
		for(int i=0; i<polinom.length(); i++)
		{
			ver=true;
			
			for(int j=0; j<verificare.length(); j++)
			{
				if(polinom.charAt(i)==verificare.charAt(j))
				{
					ver=false;
				}
			}
			
			if(i==0)
			{
				if(polinom.charAt(i)=='^')
				{
					ver=true;
				}
				
			}
			else
			{
				char locatie=polinom.charAt(i);
				
				if(locatie=='X')
				{
					if(polinom.charAt(i-1)=='X')
					{
						ver=true;
					}
					
					if(i+1<polinom.length())
					{
					if(polinom.charAt(i+1)=='X')
					{
						ver=true;
					}
					}
					
					if(polinom.charAt(i-1)=='^')
					{
						ver=true;
					}
					
				}
				
				if(locatie=='-')
				{
					if(polinom.charAt(i-1)=='+')
					{
						ver=true;
					}
					
					if(i+1<polinom.length())
					{
					if(polinom.charAt(i+1)=='+')
					{
						ver=true;
					}
					}
					
					if(polinom.charAt(i-1)=='-')
					{
						ver=true;
					}
					
					if(i+1<polinom.length())
					{
					if(polinom.charAt(i+1)=='-')
					{
						ver=true;
					}
					}
				}
				
				if(locatie=='+')
				{
					if(polinom.charAt(i-1)=='+')
					{
						ver=true;
					}
					
					if(i+1<polinom.length())
					{
					if(polinom.charAt(i+1)=='+')
					{
						ver=true;
					}
					}
					
					if(polinom.charAt(i-1)=='-')
					{
						ver=true;
					}
					
					if(i+1<polinom.length())
					{
					if(polinom.charAt(i+1)=='-')
					{
						ver=true;
					}
					}
				}
				
				
				
				if(locatie=='^')
				{
					if(polinom.charAt(i-1)=='^')
					{
						ver=true;
					}
					
					if(i+1<polinom.length())
					{
					if(polinom.charAt(i+1)=='^')
					{
						ver=true;
					}
					}
					
					if(polinom.charAt(i-1)=='+')
					{
						ver=true;
					}
					
					if(i+1<polinom.length())
					{
					if(polinom.charAt(i+1)=='+')
					{
						ver=true;
					}
					}
					
					if(polinom.charAt(i-1)=='-')
					{
						ver=true;
					}
					
					if(i+1<polinom.length())
					{
					if(polinom.charAt(i+1)=='-')
					{
						ver=true;
					}
					}
					
				}
				
				if(Character.isDigit(locatie)==true)
				{
					if(polinom.charAt(i-1)=='X')
					{
						ver=true;
					}
					
					if(i+1<polinom.length())
					{
					if(polinom.charAt(i+1)=='^')
					{
						ver=true;
					}
					}
				}
					
				
			}
			
		}
		
		if(ver==true)
		{
			System.out.println("Eroare, polinomul nu este corespunzator!");
			throw new Erori();
		}
		//arucnam exceptie daca nu e scris bine polinomul
		polinom2=polinom.replaceAll("-", "+-");
		//facem o mica smecherie pt a schimba - cu +- pentru a fi usor de splituit

		String[] monoame = polinom2.split("\\+");
		//aici splituim si practic facem mai multe STringuri in fiecare avand un monom
		
		for (String mono : monoame) {
			String temp="";
		    String temp2="";
			    int c=0;
				for(int k=0; k<mono.length(); k++)
				{
					if(mono.charAt(k)=='X')
					{
						c=1;
					}
					
					if(mono.charAt(k)!='X' && c==0 && mono.charAt(k)!='^')
					{
						temp+=mono.charAt(k);
					}
					
					if(mono.charAt(k)!='X' && c==1 && mono.charAt(k)!='^')
					{
						temp2+=mono.charAt(k);
					}
					//aici citin din aceele monom pe care l-eam despartit pentru a putea fi folosite
					//prima data facem un alt string cu cifrele pana la X dupa un al doilea cu cele de dupa
					// ^ deci practic avem coeficientul si exponentul
				}
				if(c==0)
				{
					temp="0";
					temp2=mono;
				}
				int foo, foo2;
				if(0<temp.length()) {
					foo = Integer.parseInt(temp);
				}
				else
				{
					foo=1;
				}
				if(0<temp2.length()) {
					foo2 = Integer.parseInt(temp2);
				}
				else
				{
					foo2=0;
				}
				//folosim cele doua stringuri si le facme intruri pentru a putea fi scrise in monom care mai apoi
				//sa poata fi pus in polinom
				Monom dummy = new Monom(foo2, foo);
				
				if(poli.containsKey(foo2)==false) {
					poli.put(foo2, dummy);
				}
				else
				{
					Monom temporar=poli.get(foo2);
					temporar.coeficient+=foo;
					poli.put(foo2, temporar);
				}
				//se verifica daca exista un monom de acelasi coeficiente si daca nu exista se pune
				//daca exista il aduna la cel deja existent
	    }
	}
	
	public String toString()
	{
		String last = " ";
		for (Monom a1 : poli.values()) 
		{
			last = last + a1.toString();
	    }
		//aici se transpune in STring pt a fi usor de citit
		return last;
	}

}
