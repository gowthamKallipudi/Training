package org.example;

import org.ini4j.Wini;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;

/**
 * Todo: 5) Can we approach Auto closable for this class hence it has resources to handle post process
 */
public class JDBCConnection {

    private String url;
    private String uname;
    private String password;
    private static JDBCConnection jdbcConnection;

    private JDBCConnection() {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        try (InputStream inputStream = classLoader.getResourceAsStream("db.ini")) {
            Wini ini = new Wini(inputStream);
            String url = ini.get("database", "dbURL", String.class);
            String uname = ini.get("database", "uname", String.class);
            String password = ini.get("database", "password", String.class);
            this.url = url;
            this.uname = uname;
            this.password = password;
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public static JDBCConnection getInstance() {
        if (jdbcConnection == null) {
            jdbcConnection = new JDBCConnection();
        }
        return jdbcConnection;
    }

    public void closeConnections() {
        try {
            BaseConnectionPool.getInstance().shutdown();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    public ArrayList<Model> customerData() {
        Connection con = BaseConnectionPool.getInstance().getConnection(url, uname, password);
        ArrayList<Model> data = new ArrayList<>();
        try (Statement statement = con.createStatement();
             ResultSet resultSet = statement.executeQuery("select * from customers")) {
            while (resultSet.next()) {
                data.add(new Model(resultSet.getInt("customerNumber"), resultSet.getString("customerName")
                        , resultSet.getString("contactLastName"), resultSet.getString("contactFirstName")
                        , resultSet.getString("phone"), resultSet.getString("addressLine1")
                        , resultSet.getString("city"), resultSet.getString("country")));
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            BaseConnectionPool.getInstance().releaseConnection(con);
        }
        return data;
    }

    public void storeCustomerData(Model model) {
        Connection con = BaseConnectionPool.getInstance().getConnection(url, uname, password);
        try (PreparedStatement statement = con.prepareStatement("insert into customers (customerNumber, customerName," +
                " contactLastName, contactFirstName, phone, addressLine1, city, country) values(?, ?, ?, ?, ?, ?, ?, ?)")) {

            statement.setInt(1, model.getCustomerNumber());
            statement.setString(2, model.getCustomerName());
            statement.setString(3, model.getContactLastName());
            statement.setString(4, model.getContactFirstName());
            statement.setString(5, model.getPhone());
            statement.setString(6, model.getAddressLine1());
            statement.setString(7, model.getCity());
            statement.setString(8, model.getCountry());
            statement.executeUpdate();
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            BaseConnectionPool.getInstance().releaseConnection(con);
        }
    }
}
