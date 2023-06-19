package org.example;

import org.example.models.Customer;
import org.example.models.Employee;

import java.sql.*;
import java.util.ArrayList;

public class DBHelper {

    public static void closeConnections() {
        try {
            BasicConnectionPool.getInstance().shutdown();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    public static ArrayList<Customer> customerData() {
        Connection con = BasicConnectionPool.getInstance().getConnection();
        ArrayList<Customer> data = new ArrayList<>();
        try (Statement statement = con.createStatement();
             ResultSet resultSet = statement.executeQuery("select * from customers")) {
            while (resultSet.next()) {
                data.add(new Customer(resultSet.getInt("customerNumber"), resultSet.getString("customerName")
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

    public static void storeCustomerData(Customer customer) {
        Connection con = BasicConnectionPool.getInstance().getConnection();
        try (PreparedStatement statement = con.prepareStatement("insert into customers (customerNumber, customerName," +
                " contactLastName, contactFirstName, phone, addressLine1, city, country) values(?, ?, ?, ?, ?, ?, ?, ?)")) {

            statement.setInt(1, customer.getCustomerNumber());
            statement.setString(2, customer.getCustomerName());
            statement.setString(3, customer.getContactLastName());
            statement.setString(4, customer.getContactFirstName());
            statement.setString(5, customer.getPhone());
            statement.setString(6, customer.getAddressLine1());
            statement.setString(7, customer.getCity());
            statement.setString(8, customer.getCountry());
            statement.executeUpdate();
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            BasicConnectionPool.getInstance().releaseConnection(con);
        }
    }

    public static ArrayList<Employee> fetchEmployeeData() {
        Connection con = BasicConnectionPool.getInstance().getConnection();
        ArrayList<Employee> data = new ArrayList<>();
        try (Statement statement = con.createStatement();
             ResultSet resultSet = statement.executeQuery("select * from employees")) {
            while (resultSet.next()) {
                data.add(new Employee(resultSet.getInt("employeeNumber"), resultSet.getString("lastName")
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

    public static void storeEmployeeData(Employee employee) {
        Connection con = BasicConnectionPool.getInstance().getConnection();
        try (PreparedStatement statement = con.prepareStatement("insert into employees (employeeNumber, lastName," +
                " firstName, extension, email, officeCode, jobTitle) values(?, ?, ?, ?, ?, ?, ?)")) {

            statement.setInt(1, employee.getEmployeeNumber());
            statement.setString(2, employee.getLastName());
            statement.setString(3, employee.getFirstName());
            statement.setString(4, employee.getExtension());
            statement.setString(5, employee.getEmail());
            statement.setString(6, employee.getOfficeCode());
            statement.setString(7, employee.getJobTitle());
            statement.executeUpdate();
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            BasicConnectionPool.getInstance().releaseConnection(con);
        }
    }
}