package Working;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

public class SavedData {
	
	static ArrayList<MonitoredData> dataSaved = new ArrayList<MonitoredData>();
	static ArrayList<MonitoredData> dataSaved2 = new ArrayList<MonitoredData>();
	static HashMap<String, Integer> nrActivities = new HashMap<String, Integer>();
	static HashMap<String, Integer> nrActivities2 = new HashMap<String, Integer>();
	HashMap<String, Long> durataActivities = new HashMap<String, Long>();
	HashMap<Integer, ArrayList<Activitate>> zilnicActivities = new HashMap<Integer, ArrayList<Activitate>>();
	
	public void AddAll() throws IOException
	{
		DataReading.reading();
		
	}
	
	public void printAll()
	{
		System.out.println("Activitati: \n");
		for(MonitoredData a : dataSaved)
		{
			System.out.println(a.toString());
		}
		System.out.println("\n\n");
	}
	
	public void diferentaOre()
	{
		MonitoredData aux1 = dataSaved.get(0);
		MonitoredData aux2 = dataSaved.get(dataSaved.size() -1);
		
		
		long diffInMillies = Math.abs(aux1.getStart_time().getTime() - aux2.getEnd_time().getTime());
	    long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
	    System.out.println("Numar zile in care s-au contorizat activitatile complet: " + diff + "\nNumar zile in care s-au contorizat activitatile partial: " + (diff+1));
	    System.out.println("\n \n");
		
	}
	
	public void contorizareActivitati()
	{
		
		for(MonitoredData a : dataSaved)
		{
			if(nrActivities.containsKey(a.getActivity()))
			{
				int auxi = nrActivities.get(a.getActivity());
				nrActivities.put(a.getActivity(), auxi+1);
				
				
				
				long diffInMillies = Math.abs(a.getEnd_time().getTime() - a.getStart_time().getTime());
			    long diff = TimeUnit.SECONDS.convert(diffInMillies, TimeUnit.MILLISECONDS);

				long auxi2 = durataActivities.get(a.getActivity());
				durataActivities.put(a.getActivity(), auxi2+diff);
			}
			else
			{
				nrActivities.put(a.getActivity(), 1);
				
				long diffInMillies = Math.abs(a.getEnd_time().getTime() - a.getStart_time().getTime());
			    long diff = TimeUnit.SECONDS.convert(diffInMillies, TimeUnit.MILLISECONDS);
				durataActivities.put(a.getActivity(), diff);
			}
		}
		
		
		System.out.println("Activitate : Numar de repetari");
		
		for (Entry<String, Integer> entry : nrActivities.entrySet()) {
		    System.out.println(entry.getKey()+" : "+entry.getValue());
		}
		
		System.out.println("\n \n");
		
		System.out.println("Activitate : Durata Totala (Ore: Minute: Secunde: )");
		
		for (Entry<String, Long> entry2 : durataActivities.entrySet()) {
			
			
			long diff = TimeUnit.HOURS.convert(entry2.getValue(), TimeUnit.SECONDS);
			long diff2 = TimeUnit.MINUTES.convert(entry2.getValue(), TimeUnit.SECONDS) % 60;
			long diff3 = TimeUnit.SECONDS.convert(entry2.getValue(), TimeUnit.SECONDS) % 60;
			
		    System.out.println(entry2.getKey()+" : "+ " Ore: "+ diff + " Minute: " + diff2 + " Secunde: " +diff3);
		}
		System.out.println("\n \n");

		
	}
	
	public void cautareActivitati()
	{
		for(MonitoredData a : dataSaved)
		{
			if(zilnicActivities.containsKey((int)(a.getStart_time().getDate())))
			{
				boolean checker = false;
				ArrayList<Activitate> auxList = zilnicActivities.get((int)a.getStart_time().getDate());
				for(Activitate aaa : auxList)
				{
					if(a.getActivity().equals(aaa.getActivitate()))
					{
						int aInt = aaa.getRepetari();
						aaa.setRepetari(aInt+1);
						checker=true;
					}
				}
				if(checker==false)
				{
					Activitate auxiliar = new Activitate(1, a.getActivity());
					auxList.add(auxiliar);
				}
				zilnicActivities.put((int)a.getStart_time().getDate(), auxList);
				
			}
			else
			{
				ArrayList<Activitate> auxList = new ArrayList<Activitate>();
				Activitate auxAct = new Activitate(1, a.getActivity());
				auxList.add(auxAct);
				zilnicActivities.put((int)a.getStart_time().getDate(), auxList);
			}
			
			
		
	}
		
		for (Entry<Integer, ArrayList<Activitate>> entry2 : zilnicActivities.entrySet()) {
			
			
		    System.out.println("Ziua " + entry2.getKey() + ":");
		    for(Activitate alfa : entry2.getValue())
		    {
		    	System.out.println("Activitate: " + alfa.getActivitate() + "  Repetari: " +  alfa.getRepetari());
		    }
		    System.out.println("\n");
		}
	}
	
	public void filtreazaActivities()
	{
		dataSaved2=(ArrayList<MonitoredData>) dataSaved.clone();
		MonitoredData.filtreazaActivitati();
	}

}
