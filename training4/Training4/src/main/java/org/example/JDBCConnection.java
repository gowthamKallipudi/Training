package org.example;

import java.sql.*;

public class JDBCConnection {

    private static JDBCConnection connection;

    private Connection con;

    private Statement statement = null;
    private ResultSet resultSet;
    private JDBCConnection() {

    }

    public static JDBCConnection getInstance() {
        if(connection == null) {
            return new JDBCConnection();
        }
        return connection;
    }

    public Connection getConnections() {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            String URL = "jdbc:mysql://localhost/classicmodels";
            String root = "root";
            String pass = "";
            this.con =  DriverManager.getConnection(URL, root, pass);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return con;
    }

    public ResultSet customerData() {
        try{
            this.statement = JDBCConnection.getInstance().getConnections().createStatement();
            this.resultSet = statement.executeQuery("select * from customers");
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return resultSet;
    }

    public void storeCustomerData(Model model) {
//        need to implement storing of data
    }

}
