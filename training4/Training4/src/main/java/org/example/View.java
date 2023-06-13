package org.example;

import java.sql.ResultSet;

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
            rs.close();
        }  catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void addData() {
        controller.addData(1, "Gowtham");
    }

}
