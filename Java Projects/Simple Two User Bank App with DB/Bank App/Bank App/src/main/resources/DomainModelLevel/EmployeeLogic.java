package DomainModelLevel;

import java.util.ArrayList;

import javax.swing.JTable;

import DataSourceLevel.Employee;
import DataSourceLevel.EmployeeMapper;

public class EmployeeLogic {
	
	static ArrayList<Employee> employees = new ArrayList<Employee>();
	
	public static String[] getCombo()
	{
		employees.clear();
		employees=EmployeeMapper.view();

		String[] auxcombo = new String[employees.size()];
		int i=0;
		for(Employee auxEmp : employees)
		{
			auxcombo[i]=auxEmp.getNameEmployee()+" "+auxEmp.getIdEmployee();
			i++;
		}
		return auxcombo;
	}
	
	public static void empOperation(int empID, String name, String position, int upID, String operation)
	{
		Employee aux = new Employee(empID, name, position);
		
		switch(operation)
		{
			case "Add": EmployeeMapper.create(aux);
						break;
			case "Update": EmployeeMapper.update(aux, upID);
						break;
			case "Delete": EmployeeMapper.delete(aux);
						break;
		}
	}
	
	public static JTable getTable()
	{
		employees.clear();
		employees=EmployeeMapper.view();
		String column[]={"ID Employee","Name Employee","Postion"};
		String[][] data = new String[employees.size()][3];
		int i=0;
		for(Employee aux : employees)
		{
			data[i][0]=String.valueOf(aux.getIdEmployee());
			data[i][1] = aux.getNameEmployee();
			data[i][2] = aux.getPosition();
			i++;
			
		}
		JTable aux = new JTable(data, column);
		return aux;
		
	}
	
	public static boolean correctData(String empID, String name, String position, String upID)
	{
		if(FieldChecker.allDigit(empID) && FieldChecker.allDigit(upID) && FieldChecker.noDigit(name) && FieldChecker.noDigit(position))
		{
			return true;
		}
		return false;
	}

}
