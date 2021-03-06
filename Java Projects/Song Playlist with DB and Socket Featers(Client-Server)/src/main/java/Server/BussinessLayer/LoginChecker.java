package Server.BussinessLayer;

import java.util.ArrayList;

import Server.DataLayer.Administrator;
import Server.DataLayer.AdministratorMapper;
import Server.DataLayer.Connection;
import Server.DataLayer.Regular;
import Server.DataLayer.RegularMapper;

public class LoginChecker {
	
	ArrayList<Administrator> admins = new ArrayList<Administrator>();
	ArrayList<Regular> regs = new ArrayList<Regular>();
	
	public LoginChecker()
	{
		
	}
	
	public String checking(String username, String password)
	{
		Connection.openConnection();
		
		AdministratorMapper ad = new AdministratorMapper();
		
		admins = ad.listAdmin();
		
		RegularMapper rg = new RegularMapper();
		
		regs = rg.listRegular();
		
		for(Administrator aux : admins)
		{
			if(aux.getUsernameAdmin().equals(username) && aux.getPasswordAdmin().equals(password))
			{
				//Connection.closeConnection();
				return "Admin";
			}
			
		}
		
		for(Regular aux : regs)
		{
			if(aux.getUsernameReg().equals(username) && aux.getPasswordReg().equals(password))
			{
				//Connection.closeConnection();
				return "Regular";
			}
		}
		
		//Connection.closeConnection();
		return "Error";
	}

}
