package training1;

import java.util.*;

class Pattern {

  public static void main(String args[]) {
    // Whenever we use try-with-resource and when we are exiting the try-catch block
    // it automatically executes the close() method of AutoClosable interface
    // the resources which are opened in the try(........) will get automatically closed

    if (args.length != 0) {
      if (args[0].compareTo("-i") == 0 || args[0].compareTo("-input") == 0) {
        try (Scanner sc = new Scanner(System.in)) {
          System.out.print("Enter the Height: ");
          int height = sc.nextInt();
          System.out.print("Enter the character: ");
          char symbol = sc.next().charAt(0);
          pattern(height, symbol);
        } catch (Exception e) {
          System.out.println(e);
        }
      } else {
        int height1 = 0;
        char symbol1 = ' ';

        for (int index = 0; index < args.length; index++) {
          if (
            args[index].compareTo("-h") == 0 ||
            args[index].compareTo("-height") == 0
          ) {
            height1 = Integer.parseInt(args[index + 1]);
          }
          if (
            args[index].compareTo("-s") == 0 ||
            args[index].compareTo("-symbol") == 0
          ) {
            symbol1 = args[index + 1].charAt(0);
          }
        }
        pattern(height1, symbol1);
      }
    } else {
        System.out.println("No Command-Line Arguments");
    }
    // Without implementing try-with-resource block we can close the resource manually
    // by declaring the resource variable as null outside try-catch block
    // and we can close the resource in the finally block as follows

    // Scanner sc = null;
    // try {
    //     sc = new Scanner(System.in);
    //     System.out.print("Enter the Height: ");
    //     int height = sc.nextInt();
    //     System.out.print("Enter the character: ");
    //     char symbol = sc.next().charAt(0);
    //     pattern(height, symbol);
    // } catch(Exception e) {
    //     System.out.println(e);
    // } finally {
    //     sc.close();
    // }
  }

  public static void pattern(int height, char symbol) {
    for (int i = 0; i < height; i++) {
      if (i < height / 2) {
        for (int j = 0; j < i + 1; j++) {
          System.out.print(symbol);
        }
        System.out.println();
      } else {
        for (int j = height - i; j > 0; j--) {
          System.out.print(symbol);
        }
        System.out.println();
      }
    }
  }
}
