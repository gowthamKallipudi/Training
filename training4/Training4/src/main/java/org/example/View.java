package org.example;

import java.util.ArrayList;

public class View {

    private Controller controller;
    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void displayData() {
        ArrayList<Model> data = controller.getData();
        for(Model mod: data) {
            System.out.println(mod.getCustomerNumber() + " " + mod.getCustomerName());
        }
    }

    public void addData() {
        controller.addData(1, "Gowtham");
//        still not implemented
    }

}
