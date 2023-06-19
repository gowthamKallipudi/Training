package com.example.railwayticketreservation;

import java.sql.*;

public class DB {
    public static Connection connect() throws SQLException {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String URL = "jdbc:mysql://localhost/railway";
            String root = "root";
            String pass = "";
            con =  DriverManager.getConnection(URL, root, pass);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return con;
    }

    public static boolean checkCredentials(Model model) throws SQLException {
        Connection connection = connect();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from user");
        while (resultSet.next()) {
            if (resultSet.getString("uname").compareTo(model.getUname()) == 0 &&
                    resultSet.getString("password").compareTo(model.getPassword()) == 0) {
                return true;
            }
        }
        return false;
    }
}
