package Server.DataLayer;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Connection {
	
	private static SessionFactory factory;
	
	public static SessionFactory getSession()
	{
		return factory;
	}
	
	public static void openConnection() {
	
	{
		try {
			factory = new Configuration().configure().buildSessionFactory();
		} catch (Throwable ex) {
			System.err.println("Failed to create sessionFactory object." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}
	}
	
	public static void closeConnection() {
		factory.close();
	}

}
