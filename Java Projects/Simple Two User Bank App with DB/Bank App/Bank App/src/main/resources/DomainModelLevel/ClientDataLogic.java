package DomainModelLevel;

import java.util.ArrayList;

import javax.swing.JTable;

import DataSourceLevel.ClientInformation;
import DataSourceLevel.ClientInformationMapper;

public class ClientDataLogic {
	
static ArrayList<ClientInformation> ClientInformations = new ArrayList<ClientInformation>();
	
	public static String[] getCombo()
	{
		ClientInformations.clear();
		ClientInformations=ClientInformationMapper.view();

		String[] auxcombo = new String[ClientInformations.size()];
		int i=0;
		for(ClientInformation auxEmp : ClientInformations)
		{
			auxcombo[i]=auxEmp.getNameClient()+" "+auxEmp.getIdclient();
			i++;
		}
		return auxcombo;
	}
	
	public static void clientDataOperation(int clientID, String name, long idNumber, String adress, int upID, String operation)
	{
		ClientInformation aux = new ClientInformation(clientID, name, idNumber, adress);
		
		switch(operation)
		{
			case "Add": ClientInformationMapper.create(aux);
						break;
			case "Update": ClientInformationMapper.update(aux, upID);
						break;
			case "Delete": ClientInformationMapper.delete(aux);
						break;
		}
	}
	
	public static JTable getTable()
	{
		ClientInformations.clear();
		ClientInformations=ClientInformationMapper.view();
		String column[]={"ID Client","Name Client","ID Number", "Adress"};
		String[][] data = new String[ClientInformations.size()][4];
		int i=0;
		for(ClientInformation aux : ClientInformations)
		{
			data[i][0]=String.valueOf(aux.getIdclient());
			data[i][1] = aux.getNameClient();
			data[i][2]=String.valueOf(aux.getIdNumber());
			data[i][3] = aux.getAddress();
			i++;
			
		}
		JTable aux = new JTable(data, column);
		return aux;
		
	}
	
	public static boolean correctData(String clientID, String name, String idNumber, String adress, String upID)
	{
		if(FieldChecker.allDigit(clientID) && FieldChecker.allDigit(upID) && FieldChecker.noDigit(name) && FieldChecker.allDigit(idNumber))
		{
			return true;
		}
		return false;
	}

}


