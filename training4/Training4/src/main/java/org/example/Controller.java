package org.example;

import java.util.ArrayList;

/**
 * Todo: 2) Controller is responsible to solve all the problems requests by {@link org.example.View}
 */
public class Controller {

    private View view;
    JDBCConnection con;

    public Controller(View view) {
        this.view = view;
        this.view.setController(this);
        /**
         * Todo: 3)
         * Why are we keeping a Connection all the time?
         * The controller can have 'n' number of functions. In that 'n' or less number of function might use DB. Let say the functions that doesn't use DB as 'm' functions
         * So your closing connection after doing all the operation, when any of the 'm' function is in use or View is doing some read or write activities, the DB connection would be
         * Idle which inefficient and costly. So create connection based on the function
         */
        this.con = new JDBCConnection();
    }

    public ArrayList<Model> getData() {
        return con.customerData();
    }

    public void addData(Model model) {
        con.storeCustomerData(model);
    }

    public void stop() {
        con.closeConnections();
    }

}
