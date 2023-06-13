package org.example;

import java.sql.ResultSet;
import java.sql.SQLException;

public class View {

    private Controller controller;
    ResultSet rs;
    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void displayData() {
            rs = controller.getData();
            try {
            while (rs.next()) {
                System.out.println(rs.getInt("customerNumber"));
            }
        }  catch (Exception exception) {
                exception.printStackTrace();
            }
    }

}
