package org.example;

import org.example.models.Customer;
import org.example.models.Employee;

import java.util.ArrayList;
import java.util.Scanner;

public class View {

    Controller controller = new Controller();

    public void start() {
        try (Scanner scanner = new Scanner(System.in)) {
            boolean applicationStatus = true;
            do {
                System.out.print("""
                        Welcome to the Application
                         Find the Below Options:
                          1.Print Customer Data
                          2.Add Customer Data
                          3.Print Employee Data
                          4.Add Employee Data
                          5.Customer Data - JSON Format
                          6.Employee Data - JSON Format
                          7.Exit
                        Your Option :\s""");
                int option = Integer.parseInt(scanner.nextLine());
                switch (option) {
                    case 1:
                        this.displayCustomerData();
                        break;
                    case 2:
                        this.addCustomerData(scanner);
                        break;
                    case 3:
                        this.displayEmployeeData();
                        break;
                    case 4:
                        this.addEmployeeData(scanner);
                        break;
                    case 5:
                        this.printCustomerJson(); // for checking json values
                        break;
                    case 6:
                        this.printEmployeeJson(); // for checking json values
                        break;
                    case 7:
                        applicationStatus = false;
                        break;
                    default:
                        System.out.println("Enter a valid command");
                        break;
                }
            } while (applicationStatus);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void stop() {
        controller.stop();
    }

    public void displayCustomerData() {
        ArrayList<Customer> data = controller.getData();
        for (Customer customer : data) {
            System.out.println(customer.getCustomerNumber() + " " + customer.getCustomerName() + " " + customer.getContactLastName()
                    + " " + customer.getContactFirstName() + " " + customer.getPhone() + " " + customer.getAddressLine1() + " "
                    + customer.getCity() + " " + customer.getCountry());
        }
    }

    public void addCustomerData(Scanner scanner) {
        Customer customer = new Customer();

        System.out.println("Add the data to enter");
        System.out.print("Enter your customer number : ");
        customer.setCustomerNumber(Integer.parseInt(scanner.nextLine()));
        System.out.print("Enter the name : ");
        customer.setCustomerName(scanner.nextLine());
        System.out.print("Enter contactLastName : ");
        customer.setContactLastName(scanner.nextLine());
        System.out.print("Enter contactFirstName : ");
        customer.setContactFirstName(scanner.nextLine());
        System.out.print("Enter phone number : ");
        customer.setPhone(scanner.nextLine());
        System.out.print("Enter address line : ");
        customer.setAddressLine1(scanner.nextLine());
        System.out.print("Enter your city : ");
        customer.setCity(scanner.nextLine());
        System.out.print("Enter your country : ");
        customer.setCountry(scanner.nextLine());

        controller.addData(customer);
    }

    public void displayEmployeeData() {
        ArrayList<Employee> data = controller.getEmployeeData();
        for (Employee employee : data) {
            System.out.println(employee.getEmployeeNumber() + " " + employee.getLastName() + " " +
                    employee.getFirstName() + " " + employee.getExtension() + " " + employee.getEmail()
                    + " " + employee.getOfficeCode() + " " + employee.getJobTitle());
        }
    }

    public void addEmployeeData(Scanner scanner) {
        Employee employee = new Employee();

        System.out.println("Add the data to enter");
        System.out.print("Enter your employee number : ");
        employee.setEmployeeNumber(Integer.parseInt(scanner.nextLine()));
        System.out.print("Enter the lastName : ");
        employee.setLastName(scanner.nextLine());
        System.out.print("Enter firstName : ");
        employee.setFirstName(scanner.nextLine());
        System.out.print("Enter extension : ");
        employee.setExtension(scanner.nextLine());
        System.out.print("Enter email : ");
        employee.setEmail(scanner.nextLine());
        System.out.print("Enter officeCode : ");
        employee.setOfficeCode(scanner.nextLine());
        System.out.print("Enter jobTitle : ");
        employee.setJobTitle(scanner.nextLine());

        controller.addEmployeeData(employee);
    }

    public void printCustomerJson() { //for checking json values
        String data = controller.printCustomerJson();
        System.out.println(data);
    }

    public void printEmployeeJson() { //for checking json values
        String data = controller.printEmployeeJson();
        System.out.println(data);
    }
}