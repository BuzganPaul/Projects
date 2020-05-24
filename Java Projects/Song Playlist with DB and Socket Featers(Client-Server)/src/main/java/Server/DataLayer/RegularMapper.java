package Server.DataLayer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;


public class RegularMapper {


	public Integer addRegular(String username, String password) {

		Session session = Connection.getSession().openSession();
		Transaction tx = null;
		Integer RegularID = null;

		try {
			tx = session.beginTransaction();
			Regular Regular = new Regular(username, password);
			RegularID = (Integer) session.save(Regular);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return RegularID;
	}

	public ArrayList<Regular> listRegular() {
		Session session = Connection.getSession().openSession();
		Transaction tx = null;
		ArrayList<Regular> toRet = new ArrayList<Regular>();

		try {
			tx = session.beginTransaction();
			@SuppressWarnings("rawtypes")
			List Regulars = session.createQuery("FROM Regular").list();
			for (@SuppressWarnings("rawtypes")
			Iterator iterator = Regulars.iterator(); iterator.hasNext();) {
				Regular Regular = (Regular) iterator.next();
				//System.out.print("ID Regular: " + Regular.getRegularID());
				//System.out.print(" Userame: " + Regular.getUsernameReg());
				//System.out.print(" Password: " + Regular.getPasswordReg() + "\n");
				toRet.add(Regular);
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

	public void updateRegular(Integer RegularID, String username, String password) {


		Session session = Connection.getSession().openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			Regular Regular = (Regular) session.get(Regular.class, RegularID);
			// System.out.println(Regular.getUsernameRegular());
			Regular.setUsernameReg(username);
			Regular.setPasswordReg(password);
			session.update(Regular);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	public void deleteRegular(Integer RegularID) {

		Session session = Connection.getSession().openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			Regular Regular = (Regular) session.get(Regular.class, RegularID);
			session.delete(Regular);
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
