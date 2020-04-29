package DataSourceLevel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class EmployeeMapper {
	public static ArrayList<Employee> view()
	{
		
		ArrayList<Employee> arrayToReturn = new ArrayList<Employee>();
		
        Connection connection = ConnectionFactory.getConnection();
        String findStatementString = "SELECT * FROM Assignment1Bank." + "employee";
        ResultSet rezultat;
        PreparedStatement findStatement;
        
        try {
            findStatement = connection.prepareStatement(findStatementString);
            rezultat = findStatement.executeQuery();
            rezultat.first();
            
            do {
                    Employee aux = new Employee(Integer.parseInt(rezultat.getObject(1).toString()), rezultat.getObject(2).toString(), rezultat.getObject(3).toString());
                    arrayToReturn.add(aux);
                    
            } while (rezultat.next());
            

            ConnectionFactory.close(rezultat);
            ConnectionFactory.close(findStatement);
            ConnectionFactory.close(connection);
            
            
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        
		for(Employee auxi : arrayToReturn)
		{
			System.out.println(auxi.toString());
		}
		return arrayToReturn;
	}
	
	public static void create(Employee admin) {
        Connection connection = ConnectionFactory.getConnection();
        
        String statement = "INSERT INTO `Assignment1Bank`.`" +"employee" + "` (";
        statement = statement.concat("`idEmployee`,`nameEmployee`,`position`) VALUES (");
        statement = statement.concat(String.valueOf((admin.getIdEmployee())+",'"+admin.getNameEmployee()+"','"+admin.getPosition()));
        statement = statement.concat("');");
        //System.out.println(statement);
        PreparedStatement preparedStatement;

        try {
            preparedStatement = connection.prepareStatement(statement);
            preparedStatement.executeUpdate();
            ConnectionFactory.close(preparedStatement);
            ConnectionFactory.close(connection);
        } catch (SQLIntegrityConstraintViolationException e) {
            JOptionPane.showMessageDialog(null, "Incorrect input");
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
	}
	
    public static void delete(Employee admin) {
        Connection connection = ConnectionFactory.getConnection();
        String statement = "DELETE FROM `assignment1bank`.`Employee` WHERE (`idEmployee` = "+ String.valueOf((admin.getIdEmployee()))+");";
        //System.out.println(statement);
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(statement);
            preparedStatement.executeUpdate();
            ConnectionFactory.close(preparedStatement);
            ConnectionFactory.close(connection);
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        
    }
    
    public static void update(Employee admin, int id) {
        Connection connection = ConnectionFactory.getConnection();
        String statement = "UPDATE `assignment1bank`.`Employee` SET `idEmployee` = '"+ String.valueOf((admin.getIdEmployee())) +"', `nameEmployee` = '"+ admin.getNameEmployee() +"', `position` = '"+ admin.getPosition() +"' WHERE (`idEmployee` = '"+ String.valueOf(id) +"');";
        PreparedStatement preparedStatement;

        try {
            preparedStatement = connection.prepareStatement(statement);
            preparedStatement.executeUpdate();
            ConnectionFactory.close(preparedStatement);
            ConnectionFactory.close(connection);
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println(statement);
            JOptionPane.showMessageDialog(null, "Wrong field input!");
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }
}
