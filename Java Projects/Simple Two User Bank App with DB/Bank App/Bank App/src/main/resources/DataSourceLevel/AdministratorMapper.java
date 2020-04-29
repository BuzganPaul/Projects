package DataSourceLevel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class AdministratorMapper {
	
	public static ArrayList<Administrator> view()
	{
		
		ArrayList<Administrator> arrayToReturn = new ArrayList<Administrator>();
		
        Connection connection = ConnectionFactory.getConnection();
        String findStatementString = "SELECT * FROM Assignment1Bank." + "administrator";
        ResultSet rezultat;
        PreparedStatement findStatement;
        
        try {
            findStatement = connection.prepareStatement(findStatementString);
            rezultat = findStatement.executeQuery();
            rezultat.first();
            
            do {
                    Administrator aux = new Administrator(Integer.parseInt(rezultat.getObject(1).toString()), rezultat.getObject(2).toString(), rezultat.getObject(3).toString());
                    arrayToReturn.add(aux);
                    
            } while (rezultat.next());
            

            ConnectionFactory.close(rezultat);
            ConnectionFactory.close(findStatement);
            ConnectionFactory.close(connection);
            
            
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        
		for(Administrator auxi : arrayToReturn)
		{
			System.out.println(auxi.toString());
		}
		return arrayToReturn;
	}
	
	public static void create(Administrator admin) {
        Connection connection = ConnectionFactory.getConnection();
        
        String statement = "INSERT INTO `Assignment1Bank`.`" +"administrator" + "` (";
        statement = statement.concat("`idAdministrator`,`user`,`password`) VALUES (");
        statement = statement.concat(String.valueOf((admin.getIdAdministrator())+",'"+admin.getUser()+"','"+admin.getPassword()));
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
	
    public static void delete(Administrator admin) {
        Connection connection = ConnectionFactory.getConnection();
        String statement = "DELETE FROM `assignment1bank`.`administrator` WHERE (`idAdministrator` = "+ String.valueOf((admin.getIdAdministrator()))+");";
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
    
    public static void update(Administrator admin, int id) {
        Connection connection = ConnectionFactory.getConnection();
        String statement = "UPDATE `assignment1bank`.`administrator` SET `idAdministrator` = '"+ String.valueOf((admin.getIdAdministrator())) +"', `user` = '"+ admin.getUser() +"', `password` = '"+ admin.getPassword() +"' WHERE (`idAdministrator` = '"+ String.valueOf(id) +"');";
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
