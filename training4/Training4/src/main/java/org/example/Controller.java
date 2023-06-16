package org.example;

import java.util.ArrayList;

/**
 * Todo: 2) Controller is responsible to solve all the problems requests by {@link org.example.View}
 */
public class Controller {

    /**
     * Todo: 3)
     * Why are we keeping a Connection all the time?
     * The controller can have 'n' number of functions. In that 'n' or less number of function might use DB. Let say the functions that doesn't use DB as 'm' functions
     * So your closing connection after doing all the operation, when any of the 'm' function is in use or View is doing some read or write activities, the DB connection would be
     * Idle which inefficient and costly. So create connection based on the function
     */


    public ArrayList<CustomerModel> getData() {
        return DBHelper.getInstance().customerData();
    }

    public void addData(CustomerModel model) {
        DBHelper.getInstance().storeCustomerData(model);
    }

    public ArrayList<EmployeeModel> getEmployeeData() {
        return DBHelper.getInstance().fetchEmployeeData();
    }

    public void addEmployeeData(EmployeeModel employeeModel) {
        DBHelper.getInstance().storeEmployeeData(employeeModel);
    }

    public void stop() {
        DBHelper.getInstance().closeConnections();
    }

}
