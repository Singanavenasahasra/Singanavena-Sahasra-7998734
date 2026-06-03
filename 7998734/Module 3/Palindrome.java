import java.util.Scanner;

public class Palindrome {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter String: ");
        String str = sc.nextLine();

        String clean = str.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        String rev = new StringBuilder(clean).reverse().toString();

        if(clean.equals(rev))
            System.out.println("Palindrome");
        else
            System.out.println("Not Palindrome");
    }
}