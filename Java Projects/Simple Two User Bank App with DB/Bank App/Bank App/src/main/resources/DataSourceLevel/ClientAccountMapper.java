package DataSourceLevel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class ClientAccountMapper {

	public static ArrayList<ClientAccount> view()
	{
		
		ArrayList<ClientAccount> arrayToReturn = new ArrayList<ClientAccount>();
		
        Connection connection = ConnectionFactory.getConnection();
        String findStatementString = "SELECT * FROM Assignment1Bank." + "ClientAccount";
        ResultSet rezultat;
        PreparedStatement findStatement;
        
        try {
            findStatement = connection.prepareStatement(findStatementString);
            rezultat = findStatement.executeQuery();
            rezultat.first();
            
            do {
                    ClientAccount aux = new ClientAccount(Integer.parseInt(rezultat.getObject(1).toString()), rezultat.getObject(2).toString(), Integer.parseInt(rezultat.getObject(3).toString()), rezultat.getObject(4).toString(),Integer.parseInt(rezultat.getObject(5).toString()));
                    arrayToReturn.add(aux);
                    
            } while (rezultat.next());
            

            ConnectionFactory.close(rezultat);
            ConnectionFactory.close(findStatement);
            ConnectionFactory.close(connection);
            
            
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        
		for(ClientAccount auxi : arrayToReturn)
		{
			System.out.println(auxi.toString());
		}
		return arrayToReturn;
	}
	
	public static void create(ClientAccount admin) {
        Connection connection = ConnectionFactory.getConnection();
        
        String statement = "INSERT INTO `Assignment1Bank`.`" +"ClientAccount" + "` (";
        statement = statement.concat("`idclient`,`accountType`,`money`,`creationDate`,`idAccount`) VALUES (");
        statement = statement.concat(String.valueOf(admin.getIdclient())+",'"+admin.getAccountType()+"','"+admin.getMoney()+"','"+admin.getCreationDate()+"','"+String.valueOf(admin.getIdAccount()));
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
	
    public static void delete(ClientAccount admin) {
        Connection connection = ConnectionFactory.getConnection();
        String statement = "DELETE FROM `assignment1bank`.`ClientAccount` WHERE (`idAccount` = "+ String.valueOf((admin.getIdAccount()))+");";
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
    
    public static void update(ClientAccount admin, int id) {
        Connection connection = ConnectionFactory.getConnection();
        String statement = "UPDATE `assignment1bank`.`ClientAccount` SET `idclient` = '"+ String.valueOf((admin.getIdclient())) +"', `accountType` = '"+ admin.getAccountType()+"', `money` = '"+ String.valueOf(admin.getMoney()) +"', `creationDate` = '"+ admin.getCreationDate()+"', `idAccount` = '"+ admin.getIdAccount() +"' WHERE (`idAccount` = '"+ String.valueOf(id) +"');";
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
