package training1;

import java.util.*;

class Pattern {

  public static void main(String args[]) {
    Configuration config = argument2Configuration(args);

    pattern(config.getHeight(), config.getSymbol());
    // Whenever we use try-with-resource and when we are exiting the try-catch block
    // it automatically executes the close() method of AutoClosable interface
    // the resources which are opened in the try(........) will get automatically closed

    // if (args.length != 0) {
    //   if (
    //     (args[0].compareTo("-i") == 0 || args[0].compareTo("-input") == 0) &&
    //     (args[1].compareTo("runtime") == 0)
    //   ) {
    //     try (Scanner sc = new Scanner(System.in)) {
    //       System.out.print("Enter the Height: ");
    //       int height = sc.nextInt();
    //       System.out.print("Enter the character: ");
    //       char symbol = sc.next().charAt(0);
    //       pattern(height, symbol);
    //     } catch (Exception e) {
    //       System.out.println(e);
    //     }
    //   } else {
    //     int height1 = 0;
    //     char symbol1 = ' ';
    //     boolean flag = false;

    //     for (int index = 0; index < args.length; index++) {
    //       if (
    //         args[index].compareTo("-h") == 0 ||
    //         args[index].compareTo("-height") == 0
    //       ) {
    //         height1 = Integer.parseInt(args[index + 1]);
    //       } else if (
    //         args[index].compareTo("-s") == 0 ||
    //         args[index].compareTo("-symbol") == 0
    //       ) {
    //         if (args[index + 1].length() == 1) {
    //           symbol1 = args[index + 1].charAt(0);
    //         } else {
    //           System.out.println("Enter a Single Character \n");
    //           flag = true;
    //           break;
    //         }
    //       } else {
    //         System.out.println(args[index] + " is not a valid option \n");
    //         flag = true;
    //         break;
    //       }
    //     }
    //     if (flag) {
    //       commandOptions();
    //     } else {
    //       pattern(height1, symbol1);
    //     }
    //   }
    // } else {
    //   commandOptions();
    // }

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

  public static void commandOptions() {
    System.out.println(
      "No Such Command Found \nFollow the following options to explore"
    );
    System.out.println("\n java Pattern -i runtime (for user interface) \n java Pattern -install runtime (for user interface) ");
    System.out.println(
      "\n java Pattern -h <integer> -s <character> \n java Pattern -height <integer> -symbol <character> \n"
    );
  }

  public static Configuration argument2Configuration(String args[]) {
    Configuration config = new Configuration();

    // for(String str : args) {
    //   System.out.println(str);
    // }

    if (args.length != 0) {
      if (
        (args[0].compareTo("-i") == 0 || args[0].compareTo("-input") == 0) &&
        (args.length != 1 && args[1].compareTo("runtime") == 0)
      ) {
        try (Scanner sc = new Scanner(System.in)) {
          System.out.print("Enter the Height: ");
          int height = sc.nextInt();
          System.out.print("Enter the character: ");
          char symbol = sc.next().charAt(0);
          // pattern(height, symbol);
          config.setHeight(height);
          config.setSymbol(symbol);
        } catch (Exception e) {
          System.out.println(e);
        }
      } else if (args.length == 4) {
        int height1 = 0;
        char symbol1 = ' ';
        boolean flag = false;

        for (int index = 0; index < args.length; index += 2) {
          if (
            args[index].compareTo("-h") == 0 ||
            args[index].compareTo("-height") == 0
          ) {
            height1 = Integer.parseInt(args[index + 1]);
          } else if (
            args[index].compareTo("-s") == 0 ||
            args[index].compareTo("-symbol") == 0
          ) {
            if (args[index + 1].length() == 1) {
              symbol1 = args[index + 1].charAt(0);
            } else {
              System.out.println("Enter a Single Character \n");
              flag = true;
              break;
            }
          } else {
            System.out.println(args[index] + " is not a valid option \n");
            flag = true;
            break;
          }
        }
        if (flag) {
          commandOptions();
        } else {
          // pattern(height1, symbol1);
          config.setHeight(height1);
          config.setSymbol(symbol1);
        }
      } else {
        commandOptions();
      }
    } else {
      commandOptions();
    }

    return config;
  }
}

class Configuration {

  private int height;
  private char symbol;

  public void setHeight(int height) {
    this.height = height;
  }

  public int getHeight() {
    return height;
  }

  public void setSymbol(char symbol) {
    this.symbol = symbol;
  }

  public char getSymbol() {
    return symbol;
  }
}
