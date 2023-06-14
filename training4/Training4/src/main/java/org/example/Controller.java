package org.example;

import java.util.ArrayList;

public class Controller {

    private View view;
    JDBCConnection con = JDBCConnection.getInstance();

    public Controller(View view) {
        this.view = view;
        this.view.setController(this);
    }

    public ArrayList<Model> getData() {
        return con.customerData();
    }

    public void addData(int customerNumber, String customerName, String contactLastName, String contactFirstName, String phone, String addressLine1, String city, String country) {
        Model model = new Model();
        model.setCustomerNumber(customerNumber);
        model.setCustomerName(customerName);
        model.setContactLastName(contactLastName);
        model.setContactFirstName(contactFirstName);
        model.setPhone(phone);
        model.setAddressLine1(addressLine1);
        model.setCity(city);
        model.setCountry(country);
        con.storeCustomerData(model);
    }


}
