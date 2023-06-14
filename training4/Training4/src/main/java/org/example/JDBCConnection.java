package org.example;

import java.sql.*;
import java.util.ArrayList;

public class JDBCConnection {

    private static JDBCConnection connection;

    private JDBCConnection() {

    }

    public static JDBCConnection getInstance() {
        if(connection == null) {
            return new JDBCConnection();
        }
        return connection;
    }

    public Connection getConnections() {
        Connection con = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            String URL = "jdbc:mysql://localhost/classicmodels";
            String root = "root";
            String pass = "";
            con =  DriverManager.getConnection(URL, root, pass);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return con;
    }

    public ArrayList<Model> customerData() {
        Connection con = null;
        Statement statement = null;
        ResultSet resultSet = null;
        ArrayList<Model> data = new ArrayList<>();
        try{
            con = this.getConnections();
            statement = con.createStatement();
            resultSet = statement.executeQuery("select * from customers");
            while(resultSet.next()) {
                data.add(new Model(resultSet.getInt("customerNumber"), resultSet.getString("customerName")));
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            try {
                if (con != null)
                    con.close();
                if (statement != null)
                    statement.close();
                if (resultSet != null)
                    resultSet.close();
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
        }
        return data;
    }

    public void storeCustomerData(Model model) {
//        need to implement storing of data
    }

}
