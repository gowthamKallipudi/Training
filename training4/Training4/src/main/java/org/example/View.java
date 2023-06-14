package org.example;

import java.util.ArrayList;

public class View {

    private Controller controller;
    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void displayData() {
        ArrayList<Model> data = controller.getData();
        for(Model model: data) {
            System.out.println(model.getCustomerNumber() + " " + model.getCustomerName() + " " + model.getContactLastName() + " "
                    + model.getContactFirstName() + " " + model.getPhone() + " " + model.getAddressLine1() + " " + model.getCity() + " "
                    + model.getCountry());
        }
    }

    public void addData() {
        controller.addData(1004, "Gowtham", "aaaaa", "bbbbbbbb", "999999", "chennai", "chennai", "india");
    }

}
