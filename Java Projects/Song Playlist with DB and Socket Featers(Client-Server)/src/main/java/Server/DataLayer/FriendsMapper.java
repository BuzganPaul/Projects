package Server.DataLayer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class FriendsMapper {
	
	
	public Integer addFriends(int friend1, int friend2) {

		Session session = Connection.getSession().openSession();
		Transaction tx = null;
		Integer FriendsID = null;

		try {
			tx = session.beginTransaction();
			Friends Friends = new Friends(friend1, friend2);
			FriendsID = (Integer) session.save(Friends);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return FriendsID;
	}

	public ArrayList<Friends> listFriends() {
		Session session = Connection.getSession().openSession();
		Transaction tx = null;
		ArrayList<Friends> toRet = new ArrayList<Friends>();

		try {
			tx = session.beginTransaction();
			@SuppressWarnings("rawtypes")
			List Friendss = session.createQuery("FROM Friends").list();
			for (@SuppressWarnings("rawtypes")
			Iterator iterator = Friendss.iterator(); iterator.hasNext();) {
				Friends Friends = (Friends) iterator.next();
				//System.out.print("ID Friends: " + Friends.getFriendsID());
				//System.out.print(" Friend1: " + Friends.getRegularID1());
				//System.out.print(" Friend2: " + Friends.getRegularID2() + "\n");
				toRet.add(Friends);
			}
			System.out.print("\n");
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

	public void updateFriends(Integer FriendsID, int friend1, int friend2) {


		Session session = Connection.getSession().openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			Friends Friends = (Friends) session.get(Friends.class, FriendsID);
			// System.out.println(Friends.getUsernameFriends());
			Friends.setRegularID1(friend1);
			Friends.setRegularID2(friend2);
			session.update(Friends);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	public void deleteFriends(Integer FriendsID) {

		Session session = Connection.getSession().openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			Friends Friends = (Friends) session.get(Friends.class, FriendsID);
			session.delete(Friends);
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
