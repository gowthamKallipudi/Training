package org.example;

import java.sql.*;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) throws ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/classicmodels", "root", "")) {
            Statement statement = connection.createStatement();
            System.out.println("=====================================");
            ResultSet rs = statement.executeQuery("select * from customers");
            while(rs.next()) {
                System.out.println(rs.getInt("customerNumber") + " " + rs.getString("customerName"));
            }
            System.out.println("=====================================");
            rs = statement.executeQuery("select * from employees");
            while(rs.next()) {
                System.out.println(rs.getInt("employeeNumber"));
            }
            System.out.println("=====================================");
            rs = statement.executeQuery("select * from customers inner join employees on customers.salesRepEmployeeNumber=employees.employeeNumber");
            while(rs.next()) {
                System.out.println(rs.getInt("employeeNumber"));
            }
            System.out.println("=====================================");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}