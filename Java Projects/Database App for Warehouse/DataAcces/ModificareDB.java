package DataAcces;

import Model.Produs;

import javax.swing.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;

public class ModificareDB {
	
	public static void addItem(Object item) {
        Connection connection = ConnectionFactory.getConnection();

        String statement = "INSERT INTO `warehousedb`.`" +
                item.getClass().getSimpleName().toLowerCase() +
                "` (";

        Field[] fields = item.getClass().getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            statement = statement.concat("`" + fields[i].getName() + "`");

            if (i != fields.length - 1)
                statement = statement.concat(", ");
        }

        statement = statement.concat(") VALUES ('");

        for (int i = 0; i < fields.length; i++) {
            try {
                String numeMet = fields[i].getName();
                numeMet = numeMet.substring(0, 1).toUpperCase() + numeMet.substring(1);

                statement = statement.concat(item.getClass().getDeclaredMethod("get" + numeMet).invoke(item).toString());

                if (i != fields.length - 1)
                    statement = statement.concat("', '");
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                System.out.println(e.toString());
            }
        }
        statement = statement.concat("');");

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

    public static void editItem(Object item) {
        Connection connection = ConnectionFactory.getConnection();
        String statement = "UPDATE `warehousedb`.`" +
                item.getClass().getSimpleName().toLowerCase() +
                "` SET ";

        Field[] fields = item.getClass().getDeclaredFields();
        try {
            for (int i = 0; i < fields.length; i++) {

                String numeMet = fields[i].getName();
                numeMet = numeMet.substring(0, 1).toUpperCase() + numeMet.substring(1);

                statement = statement.concat("`" + fields[i].getName() + "`='" + item.getClass().getDeclaredMethod("get" + numeMet).invoke(item).toString());

                if (i != fields.length - 1) {
                    statement = statement.concat("', ");
                }

            }


            statement = statement.concat("' WHERE `id" + item.getClass().getSimpleName().toLowerCase() + "`='");
            statement = statement.concat(item.getClass().getDeclaredMethod("getId" + item.getClass().getSimpleName().toLowerCase()).invoke(item) + "';");
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            System.out.println(e.toString());
        }

        PreparedStatement preparedStatement;

        try {
            preparedStatement = connection.prepareStatement(statement);
            preparedStatement.executeUpdate();
            ConnectionFactory.close(preparedStatement);
            ConnectionFactory.close(connection);
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println(statement);
            JOptionPane.showMessageDialog(null, "Input gresit pt acest field!");
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }

    public static void deleteItem(Object item) {
        Connection connection = ConnectionFactory.getConnection();
        if (item.getClass().getSimpleName().toLowerCase().compareTo("comanda") == 0) {
            item = findItem(item);
            int idprodusspec;
            int nrprodusespec;

            try {
                idprodusspec = Integer.parseInt(item.getClass().getMethod("getProductid").invoke(item).toString());
                nrprodusespec = Integer.parseInt(item.getClass().getMethod("getProductamount").invoke(item).toString());
                Produs auxiliar = new Produs(idprodusspec);
                Produs produsauxiliar = (Produs) findItem(auxiliar);
                produsauxiliar.setNrstoc(produsauxiliar.getNrstoc() + nrprodusespec);
                editItem(produsauxiliar);
            } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                System.out.println(e.toString());
            }
        }
        String statement = "DELETE FROM `warehousedb`.`" +
                item.getClass().getSimpleName().toLowerCase() +
                "` WHERE `id" +
                item.getClass().getSimpleName().toLowerCase() +
                "`='";
        String numeMet = "getId" + item.getClass().getSimpleName().toLowerCase();

        try {
            statement = statement.concat(item.getClass().getDeclaredMethod(numeMet).invoke(item).toString());
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            System.out.println(e.toString());
        }
        statement = statement.concat("';");
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(statement);
            preparedStatement.executeUpdate();
            ConnectionFactory.close(preparedStatement);
            ConnectionFactory.close(connection);
        } catch (SQLIntegrityConstraintViolationException e) {
            if (item.getClass().getSimpleName().toLowerCase().compareTo("client") == 0) {
                JOptionPane.showMessageDialog(null, "Can not delete. Client has active order");
            } else if (item.getClass().getSimpleName().toLowerCase().compareTo("product") == 0){
                JOptionPane.showMessageDialog(null, "Can not delete. Product part of an order");
            } else {
                JOptionPane.showMessageDialog(null, "Can not delete. Distributor of a product that is part of an order");
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }

    public static Object findItem(Object item) {
        Connection connection = ConnectionFactory.getConnection();
        String statement = "SELECT * FROM `warehousedb`.`" +
                item.getClass().getSimpleName().toLowerCase() +
                "` WHERE `id" +
                item.getClass().getSimpleName().toLowerCase() +
                "`='";
        String numeMet = "getId" + item.getClass().getSimpleName().toLowerCase();
        try {
            statement = statement.concat(item.getClass().getDeclaredMethod(numeMet).invoke(item).toString());
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            System.out.println(e.toString());
        }
        statement = statement.concat("';");
        PreparedStatement findStatement;
        ResultSet rezultat;
        try {
            findStatement = connection.prepareStatement(statement);
            rezultat = findStatement.executeQuery();
            rezultat.next();
            Field[] fields = item.getClass().getDeclaredFields();
            for (int i = 1; i < fields.length; i++) {
                String field = fields[i].getName().substring(0, 1).toUpperCase() + fields[i].getName().substring(1);
                item.getClass().getDeclaredMethod("set" + field, rezultat.getObject(i + 1).getClass()).invoke(item, rezultat.getObject(i + 1));
            }
            
            ConnectionFactory.close(rezultat);
            ConnectionFactory.close(findStatement);
            ConnectionFactory.close(connection);
        } catch (SQLException | InvocationTargetException | IllegalAccessException | NoSuchMethodException e) {
            System.out.println(e.toString());
        }
        return item;
    }
	
	

}
