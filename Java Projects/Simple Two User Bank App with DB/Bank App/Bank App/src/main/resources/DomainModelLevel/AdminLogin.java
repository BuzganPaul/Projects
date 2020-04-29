package DomainModelLevel;
import java.util.ArrayList;

import DataSourceLevel.*;

public class AdminLogin {
	static ArrayList<Administrator> accounts = new ArrayList<Administrator>();
	
	public static boolean loginChecker(String user, String password)
	{

		accounts.clear();
		accounts = AdministratorMapper.view();
		for(Administrator aux : accounts)
		{
			if(user.matches(aux.getUser()) && password.matches(aux.getPassword()))
			{
				return true;
			}
		}
		return false;
	}
	
	

}
