package org.example;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        BaseConnectionPool connection = BaseConnectionPool.create("jdbc:mysql://localhost/classicmodels", "root", "");
        View view = new View();
        Controller controller = new Controller(view, connection);
        view.addData();
        view.displayData();
        connection.shutdown();
    }
}