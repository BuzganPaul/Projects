package DataAcces;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccessDB {

    public static Object[][] getContent(String comp) {
    	Object[][] datafinal = null;
    	
        if (comp.compareTo("client") == 0)
            comp = "client";
        else if (comp.compareTo("produs") == 0)
            comp = "produs";
        else if (comp.compareTo("comanda") == 0)
            comp = "comanda";
        else if (comp.compareTo("distribuitor") == 0)
            comp = "distribuitor";
        Connection connection = ConnectionFactory.getConnection();
        String findStatementString = "SELECT * FROM warehousedb." + comp;
        ResultSet rezultat;
        ResultSetMetaData rezultatdate;
        PreparedStatement findStatement;


        try {
            findStatement = connection.prepareStatement(findStatementString);
            rezultat = findStatement.executeQuery();
            rezultatdate = rezultat.getMetaData();

            String[] columnNames = new String[rezultatdate.getColumnCount()];
            for (int i = 1; i <= rezultatdate.getColumnCount(); i++)
                columnNames[i - 1] = rezultatdate.getColumnName(i);
            int nrrand = 0;
            while (rezultat.next())
                nrrand++;
            rezultat.first();
            Object[][] data = new Object[nrrand][rezultatdate.getColumnCount()];
            nrrand = 0;
            do {
                for (int i = 1; i <= rezultatdate.getColumnCount(); i++) {
                    data[nrrand][i - 1] = rezultat.getObject(i);
                }
                nrrand++;
            } while (rezultat.next());
            
            datafinal = data;
            
            ConnectionFactory.close(rezultat);
            ConnectionFactory.close(findStatement);
            ConnectionFactory.close(connection);
            
            
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        
        
        
        return datafinal;
    }

}
