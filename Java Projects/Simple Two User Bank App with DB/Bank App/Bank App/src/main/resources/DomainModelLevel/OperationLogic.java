package DomainModelLevel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JTable;

import com.google.common.collect.Collections2;

import DataSourceLevel.Operation;
import DataSourceLevel.OperationMapper;

public class OperationLogic {
	
static ArrayList<Operation> Operations = new ArrayList<Operation>();

	public static void getReport(String time, int account)
	{
		Operations.clear();
		Operations = OperationMapper.view();
		
		for(Operation aux : Operations)
		{
			if(aux.getIdEmployee() == account)
			{
			String auxDate = aux.getDataOperation();
			String parts[] = auxDate.split("-");
			
			SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date(System.currentTimeMillis());
			//System.out.println(formatter.format(date));
			String parts2[] = formatter.format(date).toString().split("-");
			
			//System.out.println(parts[0] + parts2[0] +"\n");
			//System.out.println(parts[1] + parts2[1] +"\n");
			//System.out.println(parts[2] + parts2[2] +"\n");
			//System.out.println(String.valueOf((aux.getIdEmployee())) + " "+ account +"\n");
			
			if(parts[0].equals(parts2[0]) && parts[2].equals(parts2[2]) && parts[1].equals(parts2[1]) && time.equals("This day"))
			{
				System.out.println(aux.toString());
			}
			if(parts[0].equals(parts2[0]) && parts[1].equals(parts2[1]) && time.equals("This month"))
			{
				System.out.println(aux.toString());
			}
			if(parts[0].equals(parts2[0]) && time.equals("This year"))
			{
				System.out.println(aux.toString());
			}


			
			
			}
			//System.out.println("\n");
		}
		
	}
	
	
	public static void newOperation(int idOperation, String dataOperation, int idEmployee, String operationdetail, int idAccount, int upID, String operation)
	{
		Operation aux = new Operation( idOperation, dataOperation, idEmployee, operationdetail, idAccount);
		
		//System.out.println(aux.toString());
		
		switch(operation)
		{
			case "Add": OperationMapper.create(aux);
						break;
			case "Update": OperationMapper.update(aux, upID);
						break;
			case "Delete": OperationMapper.delete(aux);
						break;
		}
	}
	
	
	public static boolean correctData(String idOperation, String dataOperation, String idEmployee, String operationdetail, String idAccount, String upID)
	{
		if(FieldChecker.allDigit(idOperation) && FieldChecker.allDigit(upID) && FieldChecker.allDigit(idEmployee) && FieldChecker.allDigit(idAccount) && FieldChecker.isValidDate(dataOperation))
		{
			return true;
		}
		return false;
	}

}
