import java.util.Scanner;

public class SimpleCalculator {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter first number: ");
            double num1 = scanner.nextDouble();

            System.out.print("Enter second number: ");
            double num2 = scanner.nextDouble();

            System.out.print("Choose an operation (+, -, *, /): ");
            char operation = scanner.next().charAt(0);

            switch (operation) {
                case '+' -> System.out.println("Result: " + (num1 + num2));
                case '-' -> System.out.println("Result: " + (num1 - num2));
                case '*' -> System.out.println("Result: " + (num1 * num2));
                case '/' -> {
                    if (num2 != 0) {
                        System.out.println("Result: " + (num1 / num2));
                    } else {
                        System.out.println("Error: Division by zero is not allowed.");
                    }
                }
                default -> System.out.println("Error: Invalid operation chosen.");
            }
        }
    }
}