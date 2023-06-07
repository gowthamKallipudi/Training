import java.util.*;

class Hello {

  public static void main(String args[]) {
    Scanner sc = new Scanner(System.in);
    String userName;
    String role;
    int menu;
    User user = null;
    List<User> newList = new ArrayList<User>();
    // System.out.println(newList);
    System.out.println("Welcome to the portal");
    System.out.println(
      "Access the below \" USER MENU \" \n 1.ADD USER \n 2.VIEW USERS \n 3.EXIT"
    );
    System.out.print("Enter the number from USER MENU : ");
    menu = Integer.parseInt(sc.nextLine());
    do {
      switch (menu) {
        case 1:
          user = new User();
          System.out.println("Hello, Enter your \"user name\" and \"role\"");
          System.out.print("Enter your user name : ");
          userName = sc.nextLine();
          System.out.print("Enter your role : ");
          role = sc.nextLine();
          user.setUserName(userName);
          user.setRole(role);
          newList.add(user);
        //   System.out.println(newList.get(0).toString());
          break;
        case 2:
          for (User currentUser : newList) {
            System.out.println(currentUser);
          }
          //   System.out.println(user.getUserName() + " " + user.getRole());
          break;
        default:
          System.exit(0);
          break;
      }
      //   System.out.println("Hello, Enter your \"user name\" and \"role\"");
      //   user = new User();
      //   System.out.print("Enter your user name : ");
      //   userName = sc.nextLine();
      //   System.out.print("Enter your role : ");
      //   role = sc.nextLine();
      //   user.setUserName(userName);
      //   user.setRole(role);
      //   System.out.print(user.getUserName() + " " + user.getRole());
      System.out.println(
        "--------------------------------------------------------"
      );
      System.out.println(
        "Access the below \" USER MENU \" \n 1.ADD USER \n 2.VIEW USERS \n 3.EXIT"
      );
      System.out.print("Enter the number from USER MENU : ");
      menu = Integer.parseInt(sc.nextLine());
    } while (true);
  }
}

class User {

  private String userName;
  private String role;

  User() {
    this.userName = "unKnown";
    this.role = "unKnown";
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getUserName() {
    return userName;
  }

  public void setRole(String role) {
    this.role = role;
  }

  public String getRole() {
    return role;
  }

  public String toString() {
    return this.userName + " " + this.role;
  }
}
