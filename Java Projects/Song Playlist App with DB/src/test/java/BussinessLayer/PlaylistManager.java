package BussinessLayer;

import java.util.ArrayList;
import java.util.Collections;

import DataLayer.Playlist;
import DataLayer.PlaylistMapper;
import DataLayer.Regular;
import DataLayer.RegularMapper;
import DataLayer.Song;
import DataLayer.SongMapper;
import DataLayer.SongPlaylist;
import DataLayer.SongPlaylistMapper;

public class PlaylistManager {
	
	ArrayList<Regular> regularList = new ArrayList<Regular>();
	ArrayList<Song> songList = new ArrayList<Song>();
	ArrayList<Playlist> playlistList = new ArrayList<Playlist>();
	ArrayList<SongPlaylist> entriesList = new ArrayList<SongPlaylist>();
	
	RegularMapper RM = new RegularMapper();
	SongMapper SM = new SongMapper();
	PlaylistMapper PM = new PlaylistMapper();
	SongPlaylistMapper EM = new SongPlaylistMapper();
	
	public void refreshList()
	{
		regularList = RM.listRegular();
		songList = SM.listSong();
		playlistList = PM.listPlaylist();
		entriesList = EM.listSongPlaylist();
	}
	
	public ArrayList<String> getByRegular(String username)
	{
		refreshList();
		
		int id = 0;
		
		ArrayList<String> toRet = new ArrayList<String>();
		
		
		for(Regular aux : regularList)
		{
			if(aux.getUsernameReg().equals(username))
			{
			  id = aux.getRegularID();
			}
		}
		
		for(Playlist aux : playlistList)
		{
			if(aux.getRegularID() == id)
			{
			  toRet.add(aux.getPlaylistName());
			}
		}
		
		return toRet;
	}
	
	public ArrayList<String> getSongInPlaylist(String playlist)
	{
		
		int id = 0;
		//System.out.println(playlist);
		
		
		ArrayList<Integer> et = new ArrayList<Integer>();
		
		ArrayList<String> toRet = new ArrayList<String>();
		
		for(Playlist aux : playlistList)
		{
			if(aux.getPlaylistName().equals(playlist))
			{
				id = aux.getPlaylistID();
			}
		}
		
		for(SongPlaylist aux : entriesList)
		{
			if(aux.getPlaylistID() == id)
			{
				et.add(new Integer(aux.getSongID()));
			}
		}
		
		for(Song aux : songList)
		{
			for(Integer aux2 : et)
			{
				if(aux.getSongID() == aux2)
				{
					toRet.add(aux.getSongArtist()+"-"+aux.getSongName());
					//System.out.println(aux.getSongArtist()+"-"+aux.getSongName());
				}
			}
		}
		
		return toRet;
		
	}
	
	
	public void addPlaylist(String name, String username)
	{
		refreshList();
		int RegularID = 0;
		for(Regular aux : regularList)
		{
			if(aux.getUsernameReg().equals(username))
			{
				RegularID = aux.getRegularID();
			}
		}
		
		PM.addPlaylist(name, RegularID);
		refreshList();
		
	}
	
	public void playSong(String songtitle, String artist)
	{
		refreshList();
		int id = 0;
		int count = 0;
		String genre = "";
		for(Song aux : songList)
		{
			if(aux.getSongArtist().equals(artist) && aux.getSongName().equals(songtitle))
			{
				id = aux.getSongID();
				genre = aux.getGenre();
				count = aux.getViews();
				
				break;
			}
		}
		
		
		SM.updateSong(id, songtitle, artist, genre, count+1);
		refreshList();
	}
	
	public void addEntry(String songtitle, String artist, String playlistname)
	{
		refreshList();
		int playlistID = 0;
		int songID = 0;
		for(Song aux : songList)
		{
			if(aux.getSongName().equals(songtitle) && aux.getSongArtist().equals(artist))
			{
				songID = aux.getSongID();
			}
		}
		
		for(Playlist aux : playlistList)
		{
			if(aux.getPlaylistName().equals(playlistname))
			{
				playlistID = aux.getPlaylistID();
			}
		}
		//System.out.println("playlist: " + playlistID + "song: "+ songID);
		EM.addSongPlaylist(playlistID, songID);
		refreshList();
		
	}
	
	public void deleteEntry(String songtitle, String artist, String playlistname)
	{
		refreshList();
		int playlistID = 0;
		int songID = 0;
		int entryID = 0;
		
		
		for(Song aux : songList)
		{
			if(aux.getSongName().equals(songtitle) && aux.getSongArtist().equals(artist))
			{
				songID = aux.getSongID();
				//System.out.println(songID);
			}
		}
		
		for(Playlist aux : playlistList)
		{
			if(aux.getPlaylistName().equals(playlistname))
			{
				playlistID = aux.getPlaylistID();
				//System.out.println(playlistID);
			}
		}
		
		for(SongPlaylist aux : entriesList)
		{
			if(aux.getPlaylistID() == playlistID && aux.getSongID() == songID)
			{
				entryID = aux.getEntryID();
				//System.out.println(entryID);
			}
		}
		
		
		EM.deleteSongPlaylist(entryID);
		refreshList();
		
	}
	
	public String[] searchSong(String title, String artist, String genre, boolean views)
	{
		
		//System.out.println("Titlu: " + title+ "Artist: " + artist);
		String[] toRet =  new String[songList.size()];
		ArrayList<Song> list = (ArrayList<Song>) songList.clone();
		
		
		
		
		if(genre != null && !genre.isEmpty())
		{
			for(Song aux : songList)
			{
				if(!aux.getGenre().equals(genre))
				{
					list.remove(aux);
				}
			}
		}
		
		ArrayList<Song> list2 = (ArrayList<Song>) list.clone();
		
		if(artist != null && !artist.isEmpty())
		{
			for(Song aux : list)
			{
				if(!aux.getSongArtist().equals(artist))
				{
					list2.remove(aux);
				}
			}
		}
		
		ArrayList<Song> list3 = (ArrayList<Song>) list2.clone();
		
		if(title != null && !title.isEmpty())
		{
			for(Song aux : list2)
			{
				if(!aux.getSongName().equals(title))
				{
					list3.remove(aux);
				}
			}
		}
		
		
		
		if(views == true)
		{
			ArrayList<SongViews> auxi = new ArrayList<SongViews>();
			for(Song aux : list3)
			{
				SongViews a = new SongViews(aux.getSongArtist() + "-" + aux.getSongName(), aux.getViews());
				auxi.add(a);
			}
			
			Collections.sort(auxi);
			
			for (int j = 0; j < auxi.size(); j++) { 
	            toRet[j] = auxi.get(j).getSongName();
	        } 
		}
		else
		{
	        for (int j = 0; j < list3.size(); j++) { 
	            toRet[j] = list3.get(j).getSongArtist() + "-" + list3.get(j).getSongName(); 
	        } 
		}
		
		return toRet;
		
		
	}

}
