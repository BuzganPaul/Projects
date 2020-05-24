package Server.BussinessLayer;

import java.util.ArrayList;
import java.util.Collections;

import Server.DataLayer.Playlist;
import Server.DataLayer.PlaylistMapper;
import Server.DataLayer.Regular;
import Server.DataLayer.RegularMapper;
import Server.DataLayer.Song;
import Server.DataLayer.SongMapper;
import Server.DataLayer.SongPlaylist;
import Server.DataLayer.SongPlaylistMapper;

public class PlaylistManager {

	ArrayList<Regular> regularList = new ArrayList<Regular>();
	ArrayList<Song> songList = new ArrayList<Song>();
	ArrayList<Playlist> playlistList = new ArrayList<Playlist>();
	ArrayList<SongPlaylist> entriesList = new ArrayList<SongPlaylist>();

	RegularMapper rm = new RegularMapper();
	SongMapper sm = new SongMapper();
	PlaylistMapper pm = new PlaylistMapper();
	SongPlaylistMapper em = new SongPlaylistMapper();

	public PlaylistManager() {

	}

	public PlaylistManager(RegularMapper r, SongMapper s, PlaylistMapper p, SongPlaylistMapper e) {
		rm = r;
		sm = s;
		pm = p;
		em = e;
	}

	public void refreshList() {
		regularList = rm.listRegular();
		songList = sm.listSong();
		playlistList = pm.listPlaylist();
		entriesList = em.listSongPlaylist();
	}

	public ArrayList<String> getByRegular(String username) {
		refreshList();

		int id = 0;

		ArrayList<String> toRet = new ArrayList<String>();

		for (Regular aux : regularList) {
			if (aux.getUsernameReg().equals(username)) {
				id = aux.getRegularID();
			}
		}

		for (Playlist aux : playlistList) {
			if (aux.getRegularID() == id) {
				toRet.add(aux.getPlaylistName());
			}
		}

		return toRet;
	}

	public ArrayList<String> getSongInPlaylist(String playlist) {

		int id = 0;
		// System.out.println(playlist);

		ArrayList<Integer> et = new ArrayList<Integer>();

		ArrayList<String> toRet = new ArrayList<String>();

		for (Playlist aux : playlistList) {
			if (aux.getPlaylistName().equals(playlist)) {
				id = aux.getPlaylistID();
			}
		}

		for (SongPlaylist aux : entriesList) {
			if (aux.getPlaylistID() == id) {
				et.add(new Integer(aux.getSongID()));
			}
		}

		for (Song aux : songList) {
			for (Integer aux2 : et) {
				if (aux.getSongID() == aux2) {
					toRet.add(aux.getSongArtist() + "-" + aux.getSongName());
					// System.out.println(aux.getSongArtist()+"-"+aux.getSongName());
				}
			}
		}

		return toRet;

	}

	public void addPlaylist(String name, String username) {
		refreshList();
		int RegularID = 0;
		for (Regular aux : regularList) {
			if (aux.getUsernameReg().equals(username)) {
				RegularID = aux.getRegularID();
			}
		}

		pm.addPlaylist(name, RegularID);
		refreshList();

	}

	public void playSong(String songtitle, String artist) {
		refreshList();
		int id = 0;
		int count = 0;
		String genre = "";
		float rating = 0;
		int nrRatings = 0;

		for (Song aux : songList) {
			if (aux.getSongArtist().equals(artist) && aux.getSongName().equals(songtitle)) {
				id = aux.getSongID();
				genre = aux.getGenre();
				count = aux.getViews();
				rating = aux.getRating();
				nrRatings = aux.getNrRatings();

				break;
			}
		}

		sm.updateSong(id, songtitle, artist, genre, count + 1, rating, nrRatings);
		refreshList();
	}

	public void rateSong(String songtitle, String artist, int rate) {
		refreshList();
		int id = 0;
		int count = 0;
		String genre = "";
		float rating = 0;
		int nrRatings = 0;

		for (Song aux : songList) {
			if (aux.getSongArtist().equals(artist) && aux.getSongName().equals(songtitle)) {
				id = aux.getSongID();
				genre = aux.getGenre();
				count = aux.getViews();
				rating = aux.getRating();
				nrRatings = aux.getNrRatings();

				break;
			}
		}

		rating = (rating * nrRatings + rate) / (nrRatings + 1);

		sm.updateSong(id, songtitle, artist, genre, count, rating, nrRatings + 1);
		refreshList();
	}

	public void addEntry(String songtitle, String artist, String playlistname) {
		refreshList();
		int playlistID = 0;
		int songID = 0;
		for (Song aux : songList) {
			if (aux.getSongName().equals(songtitle) && aux.getSongArtist().equals(artist)) {
				songID = aux.getSongID();
			}
		}

		for (Playlist aux : playlistList) {
			if (aux.getPlaylistName().equals(playlistname)) {
				playlistID = aux.getPlaylistID();
			}
		}
		// System.out.println("playlist: " + playlistID + "song: "+ songID);
		em.addSongPlaylist(playlistID, songID);
		refreshList();

	}

	public void deleteEntry(String songtitle, String artist, String playlistname) {
		refreshList();
		int playlistID = 0;
		int songID = 0;
		int entryID = 0;

		for (Song aux : songList) {
			if (aux.getSongName().equals(songtitle) && aux.getSongArtist().equals(artist)) {
				songID = aux.getSongID();
				// System.out.println(songID);
			}
		}

		for (Playlist aux : playlistList) {
			if (aux.getPlaylistName().equals(playlistname)) {
				playlistID = aux.getPlaylistID();
				// System.out.println(playlistID);
			}
		}

		for (SongPlaylist aux : entriesList) {
			if (aux.getPlaylistID() == playlistID && aux.getSongID() == songID) {
				entryID = aux.getEntryID();
				// System.out.println(entryID);
			}
		}

		em.deleteSongPlaylist(entryID);
		refreshList();

	}

	@SuppressWarnings("unchecked")
	public String[] searchSong(String title, String artist, String genre, boolean views, boolean ratings) {

		// System.out.println("Titlu: " + title+ "Artist: " + artist);
		String[] toRet = new String[songList.size()];
		ArrayList<Song> list = (ArrayList<Song>) songList.clone();

		if (genre != null && !genre.isEmpty()) {
			for (Song aux : songList) {
				if (!aux.getGenre().equals(genre)) {
					list.remove(aux);
				}
			}
		}

		ArrayList<Song> list2 = (ArrayList<Song>) list.clone();

		if (artist != null && !artist.isEmpty()) {
			for (Song aux : list) {
				if (!aux.getSongArtist().equals(artist)) {
					list2.remove(aux);
				}
			}
		}

		ArrayList<Song> list3 = (ArrayList<Song>) list2.clone();

		if (title != null && !title.isEmpty()) {
			for (Song aux : list2) {
				if (!aux.getSongName().equals(title)) {
					list3.remove(aux);
				}
			}
		}

		if (views == true) {
			ArrayList<SongViews> auxi = new ArrayList<SongViews>();
			for (Song aux : list3) {
				SongViews a = new SongViews(aux.getSongArtist() + "-" + aux.getSongName(), aux.getViews());
				auxi.add(a);
			}

			Collections.sort(auxi);

			if (ratings == true) {
				ArrayList<SongRatings> auxiRatings = new ArrayList<SongRatings>();
				for(SongViews aux1 :  auxi)
				{
					
				
				for (Song aux : list3) {
					
					if(aux1.getSongName().equals(aux.getSongArtist() + "-" + aux.getSongName()))
					{
						SongRatings a = new SongRatings(aux1.getSongName(), aux.getRating());
						auxiRatings.add(a);
						break;
					}
				}
				
				}

				Collections.sort(auxiRatings);
				
				for (int j = 0; j < auxiRatings.size(); j++) {
					toRet[j] = auxiRatings.get(j).getSongName();
				}
				

			}

			else {
				for (int j = 0; j < auxi.size(); j++)
				{
					toRet[j] = auxi.get(j).getSongName();

				}
			}
		} 
		else 
		{
			if (ratings == true) {
				
				ArrayList<SongRatings> auxiRatings = new ArrayList<SongRatings>();
				for (Song aux : list3) {
					SongRatings a = new SongRatings(aux.getSongArtist() + "-" + aux.getSongName(), aux.getRating());
					auxiRatings.add(a);
				}

				Collections.sort(auxiRatings);
				
				for (int j = 0; j < auxiRatings.size(); j++) {
					toRet[j] = auxiRatings.get(j).getSongName();
				}

			} else {

				for (int j = 0; j < list3.size(); j++) {
					toRet[j] = list3.get(j).getSongArtist() + "-" + list3.get(j).getSongName();
				}
			}
		}

		return toRet;

	}

}
