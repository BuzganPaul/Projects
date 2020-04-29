package DataSourceLevel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class ClientInformationMapper {
	public static ArrayList<ClientInformation> view()
	{
		
		ArrayList<ClientInformation> arrayToReturn = new ArrayList<ClientInformation>();
		
        Connection connection = ConnectionFactory.getConnection();
        String findStatementString = "SELECT * FROM Assignment1Bank." + "ClientInformation";
        ResultSet rezultat;
        PreparedStatement findStatement;
        
        try {
            findStatement = connection.prepareStatement(findStatementString);
            rezultat = findStatement.executeQuery();
            rezultat.first();
            
            do {
                    ClientInformation aux = new ClientInformation(Integer.parseInt(rezultat.getObject(1).toString()), rezultat.getObject(2).toString(), Long.parseLong(rezultat.getObject(3).toString()), rezultat.getObject(4).toString());
                    arrayToReturn.add(aux);
                    
            } while (rezultat.next());
            

            ConnectionFactory.close(rezultat);
            ConnectionFactory.close(findStatement);
            ConnectionFactory.close(connection);
            
            
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        
		for(ClientInformation auxi : arrayToReturn)
		{
			System.out.println(auxi.toString());
		}
		return arrayToReturn;
	}
	
	public static void create(ClientInformation admin) {
        Connection connection = ConnectionFactory.getConnection();
        
        String statement = "INSERT INTO `Assignment1Bank`.`" +"ClientInformation" + "` (";
        statement = statement.concat("`idclient`,`nameclient`,`idNumber`,`address`) VALUES (");
        statement = statement.concat(String.valueOf((admin.getIdclient())+",'"+admin.getNameClient()+"','"+admin.getIdNumber()+"','"+admin.getAddress()));
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
	
    public static void delete(ClientInformation admin) {
        Connection connection = ConnectionFactory.getConnection();
        String statement = "DELETE FROM `assignment1bank`.`ClientInformation` WHERE (`idclient` = "+ String.valueOf((admin.getIdclient()))+");";
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
    
    public static void update(ClientInformation admin, int id) {
        Connection connection = ConnectionFactory.getConnection();
        String statement = "UPDATE `assignment1bank`.`ClientInformation` SET `idclient` = '"+ String.valueOf((admin.getIdclient())) +"', `nameClient` = '"+ admin.getNameClient()+"', `idNumber` = '"+ String.valueOf(admin.getIdNumber()) +"', `address` = '"+ admin.getAddress() +"' WHERE (`idclient` = '"+ String.valueOf(id) +"');";
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
