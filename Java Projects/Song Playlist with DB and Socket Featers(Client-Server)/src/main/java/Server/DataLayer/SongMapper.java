package Server.DataLayer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class SongMapper {


	public Integer addSong(String name, String artist, String genre) {

		Session session = Connection.getSession().openSession();
		Transaction tx = null;
		Integer SongID = null;

		try {
			tx = session.beginTransaction();
			Song Song = new Song(name, artist, genre, 0, 0, 0);
			SongID = (Integer) session.save(Song);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return SongID;
	}

	public ArrayList<Song> listSong() {

		Session session = Connection.getSession().openSession();
		Transaction tx = null;
		ArrayList<Song> toRet = new ArrayList<Song>();

		try {
			tx = session.beginTransaction();
			@SuppressWarnings("rawtypes")
			List Songs = session.createQuery("FROM Song").list();
			for (@SuppressWarnings("rawtypes")
			Iterator iterator = Songs.iterator(); iterator.hasNext();) {
				Song Song = (Song) iterator.next();
				//System.out.print("ID Song: " + Song.getSongID());
				//System.out.print(" Name: " + Song.getSongName());
				//System.out.print(" Artist: " + Song.getSongArtist());
				//System.out.print(" Genre: " + Song.getGenre());
				//System.out.print(" Views: " + Song.getViews() + "\n");
				toRet.add(Song);
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

	public void updateSong(Integer SongID, String name, String artist, String genre, int views, float rating, int nrRatings) {

		Session session = Connection.getSession().openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			Song Song = (Song) session.get(Song.class, SongID);
			// System.out.println(Song.getUsernameSong());
			Song.setSongName(name);
			Song.setSongArtist(artist);
			Song.setGenre(genre);
			Song.setViews(views);
			Song.setRating(rating);
			Song.setNrRatings(nrRatings);
			session.update(Song);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	public void deleteSong(Integer SongID) {

		Session session = Connection.getSession().openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			Song Song = (Song) session.get(Song.class, SongID);
			session.delete(Song);
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
