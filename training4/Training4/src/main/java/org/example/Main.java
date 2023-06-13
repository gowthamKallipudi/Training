package org.example;

import java.sql.*;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        JDBCConnection obj = JDBCConnection.getInstance();

        Model model = new Model(obj);
        View view = new View();
        Controller controller = new Controller(model, view);
        view.displayData();

    }
}