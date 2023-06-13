package org.example;

public class Main {
    public static void main(String[] args){

        JDBCConnection obj = JDBCConnection.getInstance();

        Model model = new Model(0, null);
        View view = new View();
        Controller controller = new Controller(model, view);
        view.displayData();

    }
}