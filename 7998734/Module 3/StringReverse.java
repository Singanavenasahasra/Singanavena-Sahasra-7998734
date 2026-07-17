import java.util.Scanner;

public class StringReverse {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter String: ");
        String str = sc.nextLine();

        String rev = new StringBuilder(str).reverse().toString();

        System.out.println("Reversed String: " + rev);
    }
}