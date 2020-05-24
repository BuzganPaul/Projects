package Server.DataLayer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class MessageMapper {
	
	public Integer addMessage(int friend1, int friend2) {

		Session session = Connection.getSession().openSession();
		Transaction tx = null;
		Integer MessageID = null;

		try {
			tx = session.beginTransaction();
			Message Message = new Message(friend1, friend2);
			MessageID = (Integer) session.save(Message);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return MessageID;
	}

	public ArrayList<Message> listMessage() {
		Session session = Connection.getSession().openSession();
		Transaction tx = null;
		ArrayList<Message> toRet = new ArrayList<Message>();

		try {
			tx = session.beginTransaction();
			@SuppressWarnings("rawtypes")
			List Messages = session.createQuery("FROM Message").list();
			for (@SuppressWarnings("rawtypes")
			Iterator iterator = Messages.iterator(); iterator.hasNext();) {
				Message Message = (Message) iterator.next();
				System.out.print("ID Message: " + Message.getMessageID());
				System.out.print(" Friends: " + Message.getFriendsID());
				System.out.print(" Song: " + Message.getSongID() + "\n");
				toRet.add(Message);
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

	public void updateMessage(Integer MessageID, int friends, int song) {


		Session session = Connection.getSession().openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			Message Message = (Message) session.get(Message.class, MessageID);
			// System.out.println(Message.getUsernameMessage());
			Message.setFriendsID(friends);
			Message.setSongID(song);
			session.update(Message);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	public void deleteMessage(Integer MessageID) {

		Session session = Connection.getSession().openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			Message Message = (Message) session.get(Message.class, MessageID);
			session.delete(Message);
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
