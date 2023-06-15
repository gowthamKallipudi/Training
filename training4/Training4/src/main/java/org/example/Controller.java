package org.example;

import java.util.ArrayList;

public class Controller {

    private View view;
    JDBCConnection con;
    public Controller(View view, BaseConnectionPool connection) {
        this.view = view;
        this.view.setController(this);
        this.con = new JDBCConnection(connection);
    }

    public ArrayList<Model> getData() {
        return con.customerData();
    }

    public void addData(Model model) {
        con.storeCustomerData(model);
    }

}
