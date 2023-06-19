package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.models.Customer;
import org.example.models.Employee;

import java.util.ArrayList;

public class Controller {

    public ArrayList<Customer> getData() {
        return DBHelper.customerData();
    }

    public void addData(Customer customer) {
        DBHelper.storeCustomerData(customer);
    }

    public ArrayList<Employee> getEmployeeData() {
        return DBHelper.fetchEmployeeData();
    }

    public void addEmployeeData(Employee employee) {
        DBHelper.storeEmployeeData(employee);
    }

    public void stop() {
        DBHelper.closeConnections();
    }

    public String printCustomerJson() { //for checking json values
        ArrayList<Customer> customers = DBHelper.customerData();
        ObjectMapper objectMapper = new ObjectMapper();
        String out = null;
        try {
            out = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(customers);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return out;
    }

    public String printEmployeeJson() { //for checking json values
        ArrayList<Employee> employees = DBHelper.fetchEmployeeData();
        ObjectMapper objectMapper = new ObjectMapper();
        String out = null;
        try {
            out = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(employees);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return out;
    }
}