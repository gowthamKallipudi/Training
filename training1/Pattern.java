package training1;

import java.util.*;

class Pattern {

  public static void main(String args[]) {
    Configuration config = argument2Configuration(args);
    pattern(config.getHeight(), config.getSymbol());
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
    System.out.println("\nFollow the following options to explore");
    System.out.println(
      "\n java Pattern -i runtime (for user interface) \n java Pattern -install runtime (for user interface) "
    );
    System.out.println(
      " java Pattern -h <integer> -s <character> \n java Pattern -height <integer> -symbol <character> \n"
    );
  }

  public static Configuration argument2Configuration(String args[]) {
    Configuration config = new Configuration();

    int height = 0;
    char symbol = 0;

    if (args.length == 0 || args.length % 2 == 1 || args.length > 4) {
      System.out.println("Invalid Command");
      commandOptions();
    }

    if (args.length == 2) {
      if (
        (args[0].compareTo("-i") == 0 || args[0].compareTo("-input") == 0) &&
        (args.length != 1 && args[1].compareTo("runtime") == 0)
      ) {
        try (Scanner sc = new Scanner(System.in)) {
          System.out.print("Enter the Height: ");
          height = sc.nextInt();
          System.out.print("Enter the character: ");
          symbol = sc.next().charAt(0);
          config.setHeight(height);
          config.setSymbol(symbol);
        } catch (Exception e) {
          System.out.println(e);
        }
      } else {
        System.out.println("Invalid Command");
        commandOptions();
      }
    }

    if (args.length == 4) {
      if (args[0].compareTo("-h") == 0 || args[0].compareTo("-height") == 0) {
        boolean check = true;
        for (int index = 0; index < args[1].length(); index++) {
          if (!Character.isDigit(args[1].charAt(index))) {
            check = false;
            break;
          }
        }
        if (check) {
          height = Integer.parseInt(args[1]);
        } else {
          System.out.println(args[1] + "Enter a valid integer");
          commandOptions();
        }
      } else {
        System.out.println(args[0] + "Invalid Command");
        commandOptions();
      }

      if (args[2].compareTo("-s") == 0 || args[2].compareTo("-symbol") == 0) {
        if (args[3].length() == 1) {
          symbol = args[3].charAt(0);
        } else {
          System.out.println("Enter a Single Character \n");
          commandOptions();
        }
      } else {
        System.out.println(args[2] + "Invalid Command");
        commandOptions();
      }
    }
    if (height != 0 && symbol != 0) {
      config.setHeight(height);
      config.setSymbol(symbol);
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
