package org.example;

import java.sql.*;

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

    public Connection getConnections() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String URL = "jdbc:mysql://localhost/classicmodels";
        String root = "root";
        String pass = "";
        return DriverManager.getConnection(URL, root, pass);
    }

}
