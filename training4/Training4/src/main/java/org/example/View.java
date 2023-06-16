package org.example;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Feedback: View looks good
 */
public class View {

    Controller controller = new Controller();

    public void start() {
        try (Scanner scanner = new Scanner(System.in)) {//Todo: 5) Suggestion: Always keep the file fully formatted. It will help the other developers who touches the same file.
            boolean applicationStatus = true;
            do {
                System.out.print("""
                        Welcome to the Application
                         Find the Below Options:
                          1.Print Customer Data
                          2.Add Customer Data
                          3.Print Employee Data
                          4.Add Employee Data
                          5.Exit
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
        ArrayList<CustomerModel> data = controller.getData();
        for (CustomerModel model : data) {
            System.out.println(model.getCustomerNumber() + " " + model.getCustomerName() + " " + model.getContactLastName()
                    + " " + model.getContactFirstName() + " " + model.getPhone() + " " + model.getAddressLine1() + " "
                    + model.getCity() + " " + model.getCountry());
        }
    }

    public void addCustomerData(Scanner scanner) {
        CustomerModel model = new CustomerModel();

        System.out.println("Add the data to enter");
        System.out.print("Enter your customer number : ");
        model.setCustomerNumber(Integer.parseInt(scanner.nextLine()));
        System.out.print("Enter the name : ");
        model.setCustomerName(scanner.nextLine());
        System.out.print("Enter contactLastName : ");
        model.setContactLastName(scanner.nextLine());
        System.out.print("Enter contactFirstName : ");
        model.setContactFirstName(scanner.nextLine());
        System.out.print("Enter phone number : ");
        model.setPhone(scanner.nextLine());
        System.out.print("Enter address line : ");
        model.setAddressLine1(scanner.nextLine());
        System.out.print("Enter your city : ");
        model.setCity(scanner.nextLine());
        System.out.print("Enter your country : ");
        model.setCountry(scanner.nextLine());

        controller.addData(model);
    }

    public void displayEmployeeData() {
        ArrayList<EmployeeModel> data = controller.getEmployeeData();
        for (EmployeeModel employeeModel : data) {
            System.out.println(employeeModel.getEmployeeNumber() + " " + employeeModel.getLastName() + " " +
                    employeeModel.getFirstName() + " " + employeeModel.getExtension() + " " + employeeModel.getEmail()
                    + " " + employeeModel.getOfficeCode() + " " + employeeModel.getJobTitle());
        }
    }

    public void addEmployeeData(Scanner scanner) {
        EmployeeModel employeeModel = new EmployeeModel();

        System.out.println("Add the data to enter");
        System.out.print("Enter your employee number : ");
        employeeModel.setEmployeeNumber(Integer.parseInt(scanner.nextLine()));
        System.out.print("Enter the lastName : ");
        employeeModel.setLastName(scanner.nextLine());
        System.out.print("Enter firstName : ");
        employeeModel.setFirstName(scanner.nextLine());
        System.out.print("Enter extension : ");
        employeeModel.setExtension(scanner.nextLine());
        System.out.print("Enter email : ");
        employeeModel.setEmail(scanner.nextLine());
        System.out.print("Enter officeCode : ");
        employeeModel.setOfficeCode(scanner.nextLine());
        System.out.print("Enter jobTitle : ");
        employeeModel.setJobTitle(scanner.nextLine());

        controller.addEmployeeData(employeeModel);
    }
}