package Server.BussinessLayer;

import java.util.ArrayList;

import Server.DataLayer.Playlist;
import Server.DataLayer.PlaylistMapper;
import Server.DataLayer.Regular;
import Server.DataLayer.RegularMapper;
import Server.DataLayer.Song;
import Server.DataLayer.SongMapper;
import Server.DataLayer.SongPlaylist;
import Server.DataLayer.SongPlaylistMapper;


public class ArtistList extends SpecificSongList{
	
	public ArtistList()
	{
		
	}
	
	public ArtistList(RegularMapper rm, SongMapper sm, PlaylistMapper pm, SongPlaylistMapper em)
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
		return "Artist";
	}

	@Override
	public void updateCriteria(String user) {
		refreshList();
		
		int saveID  = 0;
		
		for(Regular aux : regularList)
		{
			if(aux.getUsernameReg().equals(user))
			{
				saveID = aux.getRegularID();
				break;
			}
		}
		
		ArrayList<Integer> auxPlaylist = new ArrayList<Integer>();
		
		for(Playlist aux : playlistList)
		{
			if(aux.getRegularID() == saveID)
			{
				auxPlaylist.add(aux.getPlaylistID());
			}
		}
		
		ArrayList<Integer> auxSong = new ArrayList<Integer>();
		
		for(SongPlaylist aux : entriesList)
		{
			for(Integer aux2 : auxPlaylist)
			{
			
			if(aux.getPlaylistID() == aux2)
			{
				auxSong.add(aux.getSongID());
			}
			
			}
		}
		
		for(Song aux : songList)
		{
			for(Integer aux2 : auxSong)
			{
				if(aux.getSongID() == aux2)
				{
					criteria.add(aux.getSongArtist());
				}
			}
		}
		
	}

	@Override
	public void updateList() {
		
		contains.clear();
		for(Song aux : songList)
		{
			for(String aux2 : criteria)
			{
				if(aux.getSongArtist().equals(aux2))
				{
					String toAdd = aux.getSongArtist() + "-"+aux.getSongName();
					contains.add(toAdd);
				}
			}
			
		}
		
	}

}
