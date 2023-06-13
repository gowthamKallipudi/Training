package org.example;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Model {
    private int customerNumber;
    private String customerName;
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;

    public Model(JDBCConnection con){
        this.connection = con.getConnections();
    }

    public void setData(int customerNumber, String customerName) {
        this.customerNumber = customerNumber;
        this.customerName = customerName;
    }

    public ResultSet getData() {
        try{
            this.statement = connection.createStatement();
            this.resultSet = statement.executeQuery("select * from customers");
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return resultSet;
    }

    public void storeData() {
//        need to store data into the database
//        but dont have custom table to do !!!
    }
}
