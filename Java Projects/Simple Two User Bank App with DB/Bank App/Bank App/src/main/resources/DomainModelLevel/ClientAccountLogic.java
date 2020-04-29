package DomainModelLevel;

import java.util.ArrayList;

import javax.swing.JTable;

import DataSourceLevel.ClientAccount;
import DataSourceLevel.ClientAccountMapper;

public class ClientAccountLogic {
	
static ArrayList<ClientAccount> ClientAccounts = new ArrayList<ClientAccount>();
	
	public static String[] getCombo()
	{
		ClientAccounts.clear();
		ClientAccounts=ClientAccountMapper.view();

		String[] auxcombo = new String[ClientAccounts.size()];
		int i=0;
		for(ClientAccount auxEmp : ClientAccounts)
		{
			auxcombo[i]=auxEmp.getIdAccount()+" "+auxEmp.getAccountType();
			i++;
		}
		return auxcombo;
	}
	
	public static void clientDataOperation(int idclient, String accountType, int money, String creationDate, int idAccount, int upID, String operation)
	{
		ClientAccount aux = new ClientAccount(idclient, accountType, money, creationDate, idAccount);
		
		switch(operation)
		{
			case "Add": ClientAccountMapper.create(aux);
						break;
			case "Update": ClientAccountMapper.update(aux, upID);
						break;
			case "Delete": ClientAccountMapper.delete(aux);
						break;
		}
	}
	
	public static JTable getTable()
	{
		ClientAccounts.clear();
		ClientAccounts=ClientAccountMapper.view();
		String column[]={"ID Client","Account Type", "Money", "CreationDate", "ID Account"};
		String[][] data = new String[ClientAccounts.size()][5];
		int i=0;
		for(ClientAccount aux : ClientAccounts)
		{
			data[i][0]=String.valueOf(aux.getIdclient());
			data[i][1] = aux.getAccountType();
			data[i][2]=String.valueOf(aux.getMoney());
			data[i][3] = aux.getCreationDate();
			data[i][4] = String.valueOf(aux.getIdAccount());
			i++;
			
		}
		JTable aux = new JTable(data, column);
		return aux;
		
	}
	
	public static boolean correctData(String idclient, String accountType, String money, String creationDate, String idAccount, String upID)
	{
		if(FieldChecker.allDigit(idclient) && FieldChecker.allDigit(upID) && FieldChecker.noDigit(accountType) && FieldChecker.allDigit(idAccount) && FieldChecker.allDigit(money) && FieldChecker.isValidDate(creationDate))
		{
			return true;
		}
		return false;
	}

}
