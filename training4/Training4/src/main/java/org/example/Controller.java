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

    public void addData(int customerNumber, String customerName) {
        Model model = new Model();
        model.setCustomerNumber(customerNumber);
        model.setCustomerName(customerName);
        con.storeCustomerData(model);
    }


}
