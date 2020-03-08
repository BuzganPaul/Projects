package Working;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

public class MonitoredData {
	
	
	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private Date start_time;
	private Date end_time;
	private String activity;
	
	
	public MonitoredData(String start_time, String end_time, String activity) {
		try {
			this.start_time = dateFormat.parse(start_time);
			this.end_time = dateFormat.parse(end_time);
		} catch (ParseException e) {
			System.out.println("Nu sa putut parsa data: " + start_time + "   " + end_time);
			e.printStackTrace();
		}
		this.activity = activity;
	}
	
	public String toString()
	{
		long diff = TimeUnit.HOURS.convert(Math.abs(this.getEnd_time().getTime() - this.getStart_time().getTime()), TimeUnit.MILLISECONDS);
		long diff2 = TimeUnit.MINUTES.convert(Math.abs(this.getEnd_time().getTime() - this.getStart_time().getTime()), TimeUnit.MILLISECONDS) - 60* diff;
		long diff3 = TimeUnit.SECONDS.convert(Math.abs(this.getEnd_time().getTime() - this.getStart_time().getTime()), TimeUnit.MILLISECONDS) - 60* diff2 - 3600*diff;
		
		String aux = dateFormat.format(start_time) + "  " + dateFormat.format(end_time) + "  " + activity + " Durata: " +  diff +" ore " + diff2 + " minute " + diff3 + " secunde\n";
		return aux;
	}
	
	public static void filtreazaActivitati()
	{
		SavedData.dataSaved.removeIf(a -> TimeUnit.SECONDS.convert(Math.abs(a.getEnd_time().getTime() - a.getStart_time().getTime()), TimeUnit.MILLISECONDS) < 300 );
		
		
		for(MonitoredData a : SavedData.dataSaved)
		{
			if(SavedData.nrActivities2.containsKey(a.getActivity()))
			{
				int auxi = SavedData.nrActivities2.get(a.getActivity());
				SavedData.nrActivities2.put(a.getActivity(), auxi+1);
				
			}
			else
			{
				SavedData.nrActivities2.put(a.getActivity(), 1);
			}
		}
		
		System.out.println("Activitate : Numar de repetari : Procent Activitate peste 5 min");
		
		for (Entry<String, Integer> entry : SavedData.nrActivities2.entrySet()) {
		    System.out.println(entry.getKey()+" : "+entry.getValue() + " : "+ (float)((float)entry.getValue() / (float)SavedData.nrActivities.get(entry.getKey())));
		    if((float)((float)entry.getValue() / (float)SavedData.nrActivities.get(entry.getKey())) < 0.10)
		    {
		    	SavedData.dataSaved2.removeIf(a -> a.activity.equals(entry.getKey()) );
		    }
		    
		}
		
		System.out.println("\n \n");
		
		System.out.println("Activitati dupa filtrare: ");
		
		SavedData.dataSaved2.stream().forEach(num -> System.out.println(num.toString()));
		
		System.out.println("\n \n");
	}


	public Date getStart_time() {
		return start_time;
	}


	public void setStart_time(Date start_time) {
		this.start_time = start_time;
	}


	public Date getEnd_time() {
		return end_time;
	}


	public void setEnd_time(Date end_time) {
		this.end_time = end_time;
	}


	public String getActivity() {
		return activity;
	}


	public void setActivity(String activity) {
		this.activity = activity;
	}
	
	

}
