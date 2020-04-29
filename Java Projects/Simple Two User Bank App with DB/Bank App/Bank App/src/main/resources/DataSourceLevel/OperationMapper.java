package DataSourceLevel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class OperationMapper {
	
	
	public static ArrayList<Operation> view()
	{
		
		ArrayList<Operation> arrayToReturn = new ArrayList<Operation>();
		
        Connection connection = ConnectionFactory.getConnection();
        String findStatementString = "SELECT * FROM Assignment1Bank." + "operation";
        ResultSet rezultat;
        PreparedStatement findStatement;
        
        try {
            findStatement = connection.prepareStatement(findStatementString);
            rezultat = findStatement.executeQuery();
            rezultat.first();
            
            do {
                    Operation aux = new Operation(Integer.parseInt(rezultat.getObject(1).toString()), rezultat.getObject(2).toString(), Integer.parseInt(rezultat.getObject(3).toString()), rezultat.getObject(4).toString(), Integer.parseInt(rezultat.getObject(5).toString()));
                    arrayToReturn.add(aux);
                    
            } while (rezultat.next());
            

            ConnectionFactory.close(rezultat);
            ConnectionFactory.close(findStatement);
            ConnectionFactory.close(connection);
            
            
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        
		
		  for(Operation auxi : arrayToReturn) { System.out.println(auxi.toString()); }
		 
		return arrayToReturn;
	}
	
	public static void create(Operation admin) {
        Connection connection = ConnectionFactory.getConnection();
        
        String statement = "INSERT INTO `Assignment1Bank`.`" +"operation" + "` (";
        statement = statement.concat("`idOperation`,`dataOperation`,`idEmployee`,`operationdetail`,`idAccount`) VALUES (");
        statement = statement.concat(String.valueOf(admin.getIdOperation())+",'"+admin.getDataOperation()+"','"+admin.getIdEmployee()+"','"+admin.getOperationdetail()+"','"+String.valueOf(admin.getIdAccount()));
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
	
    public static void delete(Operation admin) {
        Connection connection = ConnectionFactory.getConnection();
        String statement = "DELETE FROM `assignment1bank`.`Operation` WHERE (`idOperation` = "+ String.valueOf((admin.getIdOperation()))+");";
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
    
    public static void update(Operation admin, int id) {
        Connection connection = ConnectionFactory.getConnection();
        String statement = "UPDATE `assignment1bank`.`Operation` SET `idOperation` = '"+ String.valueOf((admin.getIdOperation())) +"', `dataOperation` = '"+ admin.getDataOperation()+"', `idEmployee` = '"+ String.valueOf(admin.getIdEmployee()) +"', `operationdetail` = '"+ admin.getOperationdetail()+"', `idAccount` = '"+ admin.getIdAccount() +"' WHERE (`idOperation` = '"+ String.valueOf(id) +"');";
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
