package org.example;

public class Main {
    public static void main(String[] args) {
        View view = new View();
        Controller controller = new Controller(view);
        view.start();
        controller.stop();
    }
}