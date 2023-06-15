package org.example;

import org.ini4j.Wini;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;

public class JDBCConnection {

    BaseConnectionPool connection;
    public JDBCConnection () {
        InputStream inputStream = null;
        try {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            inputStream = classLoader.getResourceAsStream("db.ini");
            Wini ini = new Wini(inputStream);
            String url = ini.get("database", "dbURL", String.class);
            String uname = ini.get("database", "uname", String.class);
            String password = ini.get("database", "password", String.class);

            this.connection = BaseConnectionPool.create(url, uname, password);
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            try {
                if (inputStream != null)
                    inputStream.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }

    public void closeConnections() {
        try {
            connection.shutdown();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

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
            statement.executeUpdate();
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            try {
                connection.releaseConnection(con);
                if (statement != null)
                    statement.close();
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
        }
    }
}
