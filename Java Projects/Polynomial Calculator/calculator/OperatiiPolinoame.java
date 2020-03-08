package calculator;

import termen.Monom;
import termen.Polinom;

public interface OperatiiPolinoame {
	
     //interfata ce tine toate operatiile pt polinom
	
	static String adunare(Polinom A, Polinom B) 
    { 
		Polinom rez = new Polinom();
		
		for (Monom value : A.poli.values()) {
			if(rez.poli.containsKey(value.exponent)==false) {
				rez.poli.put(value.exponent, value);
			}
			else
			{
				Monom temporar=rez.poli.get(value.exponent);
				temporar.coeficient+=value.coeficient;
				rez.poli.put(value.exponent, temporar);
				//la adunare punem polinomul dupa adaugam peste cellalt adunandu-l
			}
		}
		for (Monom value : B.poli.values()) {
			if(rez.poli.containsKey(value.exponent)==false) {
				rez.poli.put(value.exponent, value);
			}
			else
			{
				Monom temporar=rez.poli.get(value.exponent);
				temporar.coeficient+=value.coeficient;
				rez.poli.put(value.exponent, temporar);
			}
		}
		String rezultatC1=rez.toString();
		rez.poli.clear();
		System.out.println("Adunare facuta cu succes!"); 
		return rezultatC1;
		//rezultatul vine ca un string de afisat jos
    } 
	
	static String scadere(Polinom A, Polinom B) 
    { 
		Polinom rez = new Polinom();
		//scaderea este asemaanatoare cu adunarea numai ca numai adunam al doilea ci in scadem
		//la toate se parcurg monom cu monom polinoamele
		for (Monom value : A.poli.values()) {
			if(rez.poli.containsKey(value.exponent)==false) {
				rez.poli.put(value.exponent, value);
			}
			else
			{
				Monom temporar=rez.poli.get(value.exponent);
				temporar.coeficient+=value.coeficient;
				rez.poli.put(value.exponent, temporar);
			}
		}
		
		for (Monom value : B.poli.values()) {
			if(rez.poli.containsKey(value.exponent)==false) {
				value.coeficient=-value.coeficient;
				rez.poli.put(value.exponent, value);
			}
			else
			{
				Monom temporar=rez.poli.get(value.exponent);
				temporar.coeficient=temporar.coeficient-value.coeficient;
				rez.poli.put(value.exponent, temporar);
			}
		}
		
		String rezultatC1=rez.toString();
		System.out.println("Scadere facuta cu succes!"); 
		rez.poli.clear();
		return rezultatC1;
        
    } 
	
	static String inmultire(Polinom A, Polinom B) 
    { 
		Polinom rez = new Polinom();
		//se inmulteste monom cu monom din fiecare polinom si se pun dupa intr-un rezultat
		
		for (Monom value : A.poli.values()) {
			for (Monom value2 : B.poli.values()) {
				
				Monom temporar= new Monom(0, 0);
				temporar.exponent=value.exponent+value2.exponent;
				temporar.coeficient=value.coeficient*value2.coeficient;
				
				rez.poli.put(temporar.exponent, temporar);

			}
			
		}
		
		String rezultatC1=rez.toString();
		System.out.println("Inmultire facuta cu succes!"); 
		rez.poli.clear();
		return rezultatC1;
        
    }
	/*
	static String impartire(Polinom A, Polinom B) 
    { 
		if(B.poli.isEmpty()==true)
		{
			return("Impartire cu un polinom nul.");
		}
		Polinom cat = new Polinom();

		
		for (Monom value : A.poli.values()) {
			for (Monom value2 : B.poli.values()) {
				
				Monom temporar= new Monom(0, 0);
				temporar.exponent=value.exponent+value2.exponent;
				temporar.coeficient=value.coeficient*value2.coeficient;
				
				rez.poli.put(temporar.exponent, temporar);

			}
			
		}
		
		String rezultatC1=rez.toString();
		System.out.println("Impartire facuta cu succes!"); 
		rez.poli.clear();
		return rezultatC1;
        
    }
	*/
	static String derivare(Polinom A) 
    { 
		Polinom rez = new Polinom();
		//aici e operatia clasica de derivare care modifica numai primul polinom trimsi al doilea nu e trimis 
		for (Monom value : A.poli.values()) {
				
				Monom temporar= new Monom(0, 0);
				temporar.exponent=value.exponent-1;
				temporar.coeficient=value.coeficient*value.exponent;
				
				rez.poli.put(temporar.exponent, temporar);
			
		}
		
		String rezultatC1=rez.toString();
		System.out.println("Derivate facuta cu succes!"); 
		rez.poli.clear();
		return rezultatC1;
        
    } 
	
	static String integrare(Polinom A) 
    { 
		Polinom rez = new Polinom();
		//operatia d eintegrare
		for (Monom value : A.poli.values()) {
				
				Monom temporar= new Monom(0, 0);
				temporar.exponent=value.exponent+1;
				temporar.coeficient=value.coeficient*(float)(1/(float)value.exponent);
				
				rez.poli.put(temporar.exponent, temporar);
			
		}
		
		String rezultatC1=rez.toString();
		System.out.println("Integrare facuta cu succes!"); 
		rez.poli.clear();
		return rezultatC1;
        
    } 
	
	

}
