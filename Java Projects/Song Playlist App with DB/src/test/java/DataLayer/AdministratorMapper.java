package DataLayer;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class AdministratorMapper {

	public Integer addAdmin(String usernameAdmin, String passwordAdmin) {
		Session session = Connection.getSession().openSession();
		Transaction tx = null;
		Integer adminID = null;

		try {
			tx = session.beginTransaction();
			Administrator admin = new Administrator(usernameAdmin, passwordAdmin);
			adminID = (Integer) session.save(admin);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return adminID;
	}

	public ArrayList<Administrator> listAdmin() {
		Session session = Connection.getSession().openSession();
		Transaction tx = null;
		
		ArrayList<Administrator> toRet = new ArrayList<Administrator>();

		try {
			tx = session.beginTransaction();
			@SuppressWarnings("rawtypes")
			List admins = session.createQuery("FROM Administrator").list();
			for (@SuppressWarnings("rawtypes")
			Iterator iterator = admins.iterator(); iterator.hasNext();) {
				Administrator admin = (Administrator) iterator.next();
				//System.out.print("ID Admin: " + admin.getAdminID());
				//System.out.print("Username Admin: " + admin.getUsernameAdmin());
				//System.out.print("    Password Admin: " + admin.getPasswordAdmin() + "\n");
				toRet.add(admin);
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

	public void updateAdmin(Integer AdminID, String passwordAdmin) {
		Session session = Connection.getSession().openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			Administrator admin = (Administrator) session.get(Administrator.class, AdminID);
			// System.out.println(admin.getUsernameAdmin());
			admin.setPasswordAdmin(passwordAdmin);
			session.update(admin);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	public void deleteAdmin(Integer AdminID) {
		Session session = Connection.getSession().openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			Administrator admin = (Administrator) session.get(Administrator.class, AdminID);
			session.delete(admin);
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