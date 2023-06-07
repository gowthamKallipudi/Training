package training1;
import java.util.*;

class Pattern {
    
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the Height: ");
        int height = sc.nextInt();
        System.out.print("Enter the character: ");
        char symbol = sc.next().charAt(0);
        pattern(height, symbol);
    }

    public static void pattern(int height, char symbol) {
        for(int i = 0; i < height; i++) {
            if(i < height / 2) {
                for(int j = 0; j < i + 1; j++){
                    System.out.print(symbol);
                }
                System.out.println();
            }
            else{
                for(int j = height - i; j > 0; j--) {
                    System.out.print(symbol);
                }
                System.out.println();
            }
        }
    }
    
}