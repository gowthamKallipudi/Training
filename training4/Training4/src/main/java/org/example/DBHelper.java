package org.example;

import java.sql.*;
import java.util.ArrayList;

/**
 * Todo: 5) Can we approach Auto closable for this class hence it has resources to handle post process
 */
public class DBHelper {

    private static DBHelper dbHelper;

    private DBHelper() {

    }

    public static DBHelper getInstance() {
        if (dbHelper == null) {
            dbHelper = new DBHelper();
        }
        return dbHelper;
    }

    public void closeConnections() {
        try {
            BasicConnectionPool.getInstance().shutdown();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    public ArrayList<CustomerModel> customerData() {
        Connection con = BasicConnectionPool.getInstance().getConnection();
        ArrayList<CustomerModel> data = new ArrayList<>();
        try (Statement statement = con.createStatement();
             ResultSet resultSet = statement.executeQuery("select * from customers")) {
            while (resultSet.next()) {
                data.add(new CustomerModel(resultSet.getInt("customerNumber"), resultSet.getString("customerName")
                        , resultSet.getString("contactLastName"), resultSet.getString("contactFirstName")
                        , resultSet.getString("phone"), resultSet.getString("addressLine1")
                        , resultSet.getString("city"), resultSet.getString("country")));
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            BasicConnectionPool.getInstance().releaseConnection(con);
        }
        return data;
    }

    public void storeCustomerData(CustomerModel model) {
        Connection con = BasicConnectionPool.getInstance().getConnection();
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
            BasicConnectionPool.getInstance().releaseConnection(con);
        }
    }

    public ArrayList<EmployeeModel> fetchEmployeeData() {
        Connection con = BasicConnectionPool.getInstance().getConnection();
        ArrayList<EmployeeModel> data = new ArrayList<>();
        try (Statement statement = con.createStatement();
             ResultSet resultSet = statement.executeQuery("select * from employees")) {
            while (resultSet.next()) {
                data.add(new EmployeeModel(resultSet.getInt("employeeNumber"), resultSet.getString("lastName")
                        , resultSet.getString("firstName"), resultSet.getString("extension")
                        , resultSet.getString("email"), resultSet.getString("officeCode")
                        , resultSet.getString("jobTitle")));
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            BasicConnectionPool.getInstance().releaseConnection(con);
        }
        return data;
    }

    public void storeEmployeeData(EmployeeModel employeeModel) {
        Connection con = BasicConnectionPool.getInstance().getConnection();
        try (PreparedStatement statement = con.prepareStatement("insert into employees (employeeNumber, lastName," +
                " firstName, extension, email, officeCode, jobTitle) values(?, ?, ?, ?, ?, ?, ?)")) {

            statement.setInt(1, employeeModel.getEmployeeNumber());
            statement.setString(2, employeeModel.getLastName());
            statement.setString(3, employeeModel.getFirstName());
            statement.setString(4, employeeModel.getExtension());
            statement.setString(5, employeeModel.getEmail());
            statement.setString(6, employeeModel.getOfficeCode());
            statement.setString(7, employeeModel.getJobTitle());
            statement.executeUpdate();
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            BasicConnectionPool.getInstance().releaseConnection(con);
        }
    }
}
