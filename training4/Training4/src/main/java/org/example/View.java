package org.example;

import java.sql.ResultSet;
import java.sql.SQLException;

public class View {

    private Controller controller;
    ResultSet rs;
    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void displayData() throws SQLException {
            rs = controller.getData();
            while (rs.next()) {
            System.out.println(rs.getInt("customerNumber"));
        }
    }

}
