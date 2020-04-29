package DomainModelLevel;

import java.util.ArrayList;

import DataSourceLevel.ClientAccount;
import DataSourceLevel.ClientAccountMapper;

public class EmployeeOperation {
	
	static ArrayList<ClientAccount> accounts = new ArrayList<ClientAccount>();
	
	
	public static void payBill(int account, int payment, String employee, String com)
	{
		accounts.clear();
		accounts = ClientAccountMapper.view();
		
		for(ClientAccount aux : accounts)
		{
			if(aux.getIdAccount() == account)
			{
				if(payment > aux.getMoney())
				{
					System.out.println("Not enought funds!");
				}
				else
				{
					String parts2[] = employee.split(" ");
					int auxMoney = aux.getMoney() - payment;
					ClientAccountLogic.clientDataOperation(aux.getIdclient(), aux.getAccountType(), auxMoney, aux.getCreationDate(), aux.getIdAccount(), aux.getIdAccount(), "Update");
					OperationLogic.newOperation(FieldChecker.getID(), FieldChecker.getDate(), Integer.parseInt(parts2[parts2.length-1]), "Payment to "+ com +" ammount: " + String.valueOf(payment), aux.getIdAccount(), 0, "Add");
					System.out.println("Payment to "+ com +" ammount: " + String.valueOf(payment));
				}
			}
		}
		
	}
	
	public static void moneyTransfer(int account, int account2, int payment, String employee)
	{
		accounts.clear();
		accounts = ClientAccountMapper.view();
		for(ClientAccount aux : accounts)
		{
			if(aux.getIdAccount() == account)
			{
				
				if(aux.getMoney()< payment)
				{
					System.out.println("Not enought funds!");
				}
				else
				{
				for(ClientAccount aux2 : accounts)
				{
					if(aux2.getIdAccount() == account2)
					{
						String parts2[] = employee.split(" ");
						int auxMoney = aux.getMoney() - payment;
						int auxMoney2 =  aux2.getMoney() + payment;
						ClientAccountLogic.clientDataOperation(aux.getIdclient(), aux.getAccountType(), auxMoney, aux.getCreationDate(), aux.getIdAccount(), aux.getIdAccount(), "Update");
						ClientAccountLogic.clientDataOperation(aux2.getIdclient(), aux2.getAccountType(), auxMoney2, aux2.getCreationDate(), aux2.getIdAccount(), aux2.getIdAccount(), "Update");
						OperationLogic.newOperation(FieldChecker.getID(), FieldChecker.getDate(), Integer.parseInt(parts2[parts2.length-1]), "Payment to "+ "Transfer from "+ account + " to "+ account2 + " ammount: " + String.valueOf(payment) +" ammount: " + String.valueOf(payment), aux.getIdAccount(), 0, "Add");
						System.out.println("Transfer from "+ account + " to "+ account2 + " ammount: " + String.valueOf(payment));
					}
				}
				}
			}
		}
		
		
		
	}
		

}
