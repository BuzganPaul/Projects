package Server.BussinessLayer;

import java.util.ArrayList;


public class PlaylistBuilder {
	
	ArtistList artist;
	GenreList genre;
	GeneralList general;
	
	ArrayList<String> toRet = new ArrayList<String>();
	
	public PlaylistBuilder()
	{
		this.artist = new ArtistList();
		this.genre = new GenreList();
		this.general = new GeneralList();
		
		//artist.refreshList();
		//genre.refreshList();
		//general.refreshList();	
	}
	
	public PlaylistBuilder(ArtistList artist, GenreList genre, GeneralList general)
	{
		this.artist = artist;
		this.genre = genre;
		this.general = general;
		//artist.refreshList();
		//genre.refreshList();
		//general.refreshList();	
	}
	
	public ArrayList<String> eliminateDuplicates(ArrayList<String> oldList)
	{
        ArrayList<String> newList = new ArrayList<String>(); 
        
        for (String element : oldList) { 
            if (!newList.contains(element)) { 
  
                newList.add(element); 
            } 
        } 
  
        return newList;
	}
	
	public String[] getBuildedPlaylist(String user, boolean artistCheck, boolean genreCheck)
	{
		
			artist.refreshList();
			genre.refreshList();
			general.refreshList();
		
			String[] artistAux = null;
			String[] genreAux = artist.getSong(); 
			String[] generalAux = artist.getSong(); 
			
			if(artistCheck && genreCheck)
			{
				artist.updateCriteria(user);
				genre.updateCriteria(user);
				
				artist.updateList();
				genre.updateList();
				
				artistAux = artist.getSong(); 
				genreAux = genre.getSong(); 
				
				toRet.clear();
			
				for(String aux : artistAux)
				{
					toRet.add(aux);
				}
				
				for(String aux : genreAux)
				{
					toRet.add(aux);
				}
				
				ArrayList<String> toRet2 = eliminateDuplicates(toRet);
				
				String[] toRetFinal = new String[toRet2.size()];
				
				for(int i = 0; i< toRet2.size(); i++)
				{
					toRetFinal[i] = toRet2.get(i);
					
				}
				
				
				return toRetFinal;
			}
			
			if(artistCheck)
			{
				artist.updateCriteria(user);
				
				artist.updateList();
				
				artistAux = artist.getSong(); 
				
				toRet.clear();
			
				for(String aux : artistAux)
				{
					toRet.add(aux);
				}
				
				
				ArrayList<String> toRet2 = eliminateDuplicates(toRet);
				
				String[] toRetFinal = new String[toRet2.size()];
				
				for(int i = 0; i< toRet2.size(); i++)
				{
					toRetFinal[i] = toRet2.get(i);
					
				}
				
				
				return toRetFinal;
			}
			
			if( genreCheck)
			{
				genre.updateCriteria(user);
				genre.updateList();
				genreAux = genre.getSong();
				
				toRet.clear();
				
				for(String aux : genreAux)
				{
					toRet.add(aux);
				}
				
				
				ArrayList<String> toRet2 = eliminateDuplicates(toRet);
				
				String[] toRetFinal = new String[toRet2.size()];
				
				for(int i = 0; i< toRet2.size(); i++)
				{
					toRetFinal[i] = toRet2.get(i);
					
				}
				
				
				return toRetFinal;
			}
			
			
			general.updateList();
			generalAux = general.getSong();
			
			toRet.clear();
			
			for(String aux : generalAux)
			{
				toRet.add(aux);
			}
			
			
			ArrayList<String> toRet2 = eliminateDuplicates(toRet);
			
			String[] toRetFinal = new String[toRet2.size()];
			
			for(int i = 0; i< toRet2.size(); i++)
			{
				toRetFinal[i] = toRet2.get(i);
				
			}
			
			
			return toRetFinal;
	}

}
