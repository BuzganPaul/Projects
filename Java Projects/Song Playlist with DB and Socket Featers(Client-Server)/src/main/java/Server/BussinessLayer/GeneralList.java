package Server.BussinessLayer;


import Server.DataLayer.PlaylistMapper;
import Server.DataLayer.RegularMapper;
import Server.DataLayer.Song;
import Server.DataLayer.SongMapper;
import Server.DataLayer.SongPlaylistMapper;

public class GeneralList extends SpecificSongList{
	
	public GeneralList()
	{
		
	}
	
	public GeneralList(RegularMapper rm, SongMapper sm, PlaylistMapper pm, SongPlaylistMapper em)
	{
		this.rm = rm;
		this.sm = sm;
		this.pm = pm;
		this.em = em;
	}

	@Override
	public String[] getSong() {
		String[] aux = new String[contains.size()];
		
		
		for(int i = 0; i< contains.size(); i++)
		{
			aux[i] = contains.get(i);
			
		}
		
		return aux;
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return "General";
	}

	@Override
	public void updateCriteria(String user) {
		
	}

	@Override
	public void updateList() {
		
		contains.clear();
		for(Song aux : songList)
		{
			String toAdd = aux.getSongArtist() + "-"+aux.getSongName();
			contains.add(toAdd);
		}
		
	}

}
