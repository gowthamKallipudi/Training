package org.example;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Controller {

    private Model model;
    private View view;

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
        this.view.setController(this);
    }

    public ResultSet getData() throws SQLException {
        return model.getData();
    }

    public void storeData(int customerNumber, String customerName) {
        model.setData(customerNumber, customerName);
        model.storeData();
    }

}
