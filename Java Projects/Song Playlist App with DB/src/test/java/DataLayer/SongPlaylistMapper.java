package DataLayer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class SongPlaylistMapper {
	


	public Integer addSongPlaylist(int playlistID, int songID) {

		Session session = Connection.getSession().openSession();
		Transaction tx = null;
		Integer SongPlaylistID = null;

		try {
			tx = session.beginTransaction();
			SongPlaylist SongPlaylist = new SongPlaylist(playlistID, songID);
			SongPlaylistID = (Integer) session.save(SongPlaylist);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return SongPlaylistID;
	}

	public ArrayList<SongPlaylist> listSongPlaylist() {

		Session session = Connection.getSession().openSession();
		Transaction tx = null;
		ArrayList<SongPlaylist> toRet = new ArrayList<SongPlaylist>();

		try {
			tx = session.beginTransaction();
			@SuppressWarnings("rawtypes")
			List SongPlaylists = session.createQuery("FROM SongPlaylist").list();
			for (@SuppressWarnings("rawtypes")
			Iterator iterator = SongPlaylists.iterator(); iterator.hasNext();) {
				SongPlaylist SongPlaylist = (SongPlaylist) iterator.next();
				//System.out.print("ID Entry: " + SongPlaylist.getEntryID());
				//System.out.print(" Playlist: " + SongPlaylist.getPlaylistID());
				//System.out.print(" Song: " + SongPlaylist.getSongID() + "\n");
				toRet.add(SongPlaylist);
			}
			//System.out.print("\n");
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return toRet;
	}

	public void updateSongPlaylist(Integer SongPlaylistID, int playlistID, int songID) {

		Session session = Connection.getSession().openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			SongPlaylist SongPlaylist = (SongPlaylist) session.get(SongPlaylist.class, SongPlaylistID);
			// System.out.println(SongPlaylist.getUsernameSongPlaylist());
			SongPlaylist.setPlaylistID(playlistID);
			SongPlaylist.setSongID(songID);
			session.update(SongPlaylist);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	public void deleteSongPlaylist(Integer SongPlaylistID) {

		Session session = Connection.getSession().openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			SongPlaylist SongPlaylist = (SongPlaylist) session.get(SongPlaylist.class, SongPlaylistID);
			session.delete(SongPlaylist);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

}
