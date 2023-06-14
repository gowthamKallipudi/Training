package org.example;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Properties;

public class JDBCConnection {

    BaseConnectionPool connection = null;
    public JDBCConnection(BaseConnectionPool connection) {
        this.connection = connection;
    }

//    private static JDBCConnection connection;
//
//    private JDBCConnection() {
//
//    }
//
//    public static JDBCConnection getInstance() {
//        if(connection == null) {
//            return new JDBCConnection();
//        }
//        return connection;
//    }

//    public Connection getConnections() {
//        Connection con = null;
////        FileInputStream fileInputStream = null;
//        try{
//            String classLoader = "com.mysql.cj.jdbc.Driver";
//            String url = "jdbc:mysql://localhost/";
//            String dataBase = "classicmodels";
//            String uname = "root";
//            String password = "";
////            String rootPath = Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("")).getPath();
////            fileInputStream = new FileInputStream("D:\\Training\\training4\\Training4\\src\\main" +
////                    "\\java\\org.example\\" + "connection.properties");
////            Properties properties = new Properties();
////            properties.load(fileInputStream);
////            String classLoader = (String) properties.get("classLoader");
////            String url = (String) properties.get("dbURL");
////            String dataBase = (String) properties.get("dbName");
////            String uname = (String) properties.get("uName");
////            String password = (String) properties.get("password");
////            System.out.println(classLoader + url + dataBase + uname + password);
//            Class.forName(classLoader);
//            con =  DriverManager.getConnection(url + dataBase, uname, password);
//        } catch (Exception exception) {
//            exception.printStackTrace();
//        }
////        finally {
////            if(fileInputStream != null) {
////                try {
////                    fileInputStream.close();
////                } catch (IOException e) {
////                    e.printStackTrace();
////                }
////            }
////        }
//        return con;
//    }

    public ArrayList<Model> customerData() {
        Connection con = null;
        Statement statement = null;
        ResultSet resultSet = null;
        ArrayList<Model> data = new ArrayList<>();
        try{
            con = connection.getConnection();
            statement = con.createStatement();
            resultSet = statement.executeQuery("select * from customers");
            while(resultSet.next()) {
                data.add(new Model(resultSet.getInt("customerNumber"), resultSet.getString("customerName")
                        , resultSet.getString("contactLastName"), resultSet.getString("contactFirstName")
                        , resultSet.getString("phone"), resultSet.getString("addressLine1")
                        , resultSet.getString("city"), resultSet.getString("country")));
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            try {
                connection.releaseConnection(con);
//                if (con != null)
//                    con.close();
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
        Connection con = null;
        PreparedStatement statement = null;
        int num;
        try {
            con = connection.getConnection();
            statement = con.prepareStatement("insert into customers (customerNumber, customerName, contactLastName, contactFirstName," +
                    " phone, addressLine1, city, country) values(?, ?, ?, ?, ?, ?, ?, ?)");
            statement.setInt(1, model.getCustomerNumber());
            statement.setString(2, model.getCustomerName());
            statement.setString(3, model.getContactLastName());
            statement.setString(4, model.getContactFirstName());
            statement.setString(5,model.getPhone());
            statement.setString(6, model.getAddressLine1());
            statement.setString(7, model.getCity());
            statement.setString(8, model.getCountry());
            num = statement.executeUpdate();
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            try {
//                if (con != null)
//                    con.close();
                connection.releaseConnection(con);
                if (statement != null)
                    statement.close();
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
        }
    }

}
