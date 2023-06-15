package org.example;

/**
 * Todo: 4) Suggestion: Give main class a proper name like App or something which you think is good.
 */
public class Main {
    public static void main(String[] args) {
        View view = new View();
        /**
         * Todo: 1) Don't keep controller in main. Then what is the roles View. If you are opening and closing the controller resources on
         */
        Controller controller = new Controller(view);
        view.start();
        controller.stop();
    }
}