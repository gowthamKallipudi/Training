package org.example;

import java.sql.ResultSet;

public class Controller {

    private Model model;
    private View view;
    JDBCConnection con = JDBCConnection.getInstance();

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
        this.view.setController(this);
    }

    public ResultSet getData() {
        return con.customerData();
    }

    public void addData(int customerNumber, String customerName) {
        model.setCustomerNumber(customerNumber);
        model.setCustomerName(customerName);
        con.storeCustomerData(model);
    }


}
