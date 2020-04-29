package DataLayer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class PlaylistMapper {
	

	public Integer addPlaylist(String name, int id) {

		Session session = Connection.getSession().openSession();
		Transaction tx = null;
		Integer PlaylistID = null;

		try {
			tx = session.beginTransaction();
			Playlist Playlist = new Playlist(name, id);
			PlaylistID = (Integer) session.save(Playlist);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return PlaylistID;
	}

	public ArrayList<Playlist> listPlaylist() {

		Session session = Connection.getSession().openSession();
		Transaction tx = null;
		ArrayList<Playlist> toRet = new ArrayList<Playlist>();

		try {
			tx = session.beginTransaction();
			@SuppressWarnings("rawtypes")
			List Playlists = session.createQuery("FROM Playlist").list();
			for (@SuppressWarnings("rawtypes")
			Iterator iterator = Playlists.iterator(); iterator.hasNext();) {
				Playlist Playlist = (Playlist) iterator.next();
				//System.out.print("ID Playlist: " + Playlist.getPlaylistID());
				//System.out.print(" Name: " + Playlist.getPlaylistName());
				//System.out.print(" Regular: " + Playlist.getRegularID() + "\n");
				toRet.add(Playlist);
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

	public void updatePlaylist(Integer PlaylistID, String name) {

		Session session = Connection.getSession().openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			Playlist Playlist = (Playlist) session.get(Playlist.class, PlaylistID);
			// System.out.println(Playlist.getUsernamePlaylist());
			Playlist.setPlaylistName(name);
			session.update(Playlist);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	public void deletePlaylist(Integer PlaylistID) {

		Session session = Connection.getSession().openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			Playlist Playlist = (Playlist) session.get(Playlist.class, PlaylistID);
			session.delete(Playlist);
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
