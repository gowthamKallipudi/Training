package org.example;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Feedback: View looks good
 */
public class View {

    Controller controller = new Controller();

    public void start() {
        try (Scanner scanner = new Scanner(System.in)){//Todo: 5) Suggestion: Always keep the file fully formatted. It will help the other developers who touches the same file.
            boolean applicationStatus = true;
            do {
                System.out.print("""
                        Welcome to the Application
                         Find the Below Options:
                          1.Print Data
                          2.Add Data
                          3.Exit
                        Your Option :\s""");
                int option = Integer.parseInt(scanner.nextLine());
                switch (option) {
                    case 1:
                        this.displayData();
                        break;
                    case 2:
                        this.addData(scanner);
                        break;
                    case 3:
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

    public void displayData() {
        ArrayList<Model> data = controller.getData();
        for(Model model: data) {
            System.out.println(model.getCustomerNumber() + " " + model.getCustomerName() + " " + model.getContactLastName() + " "
                    + model.getContactFirstName() + " " + model.getPhone() + " " + model.getAddressLine1() + " " + model.getCity() + " "
                    + model.getCountry());
        }
    }

    public void addData(Scanner scanner) {
        Model model = new Model();

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

}
