public class OperatorPrecedence {
    public static void main(String[] args) {
        int result = 10 + 5 * 2;
        System.out.println("Expression: 10 + 5 * 2");
        System.out.println("Result: " + result);
        
        System.out.println("\nExplanation of Order of Operations:");
        System.out.println("1. Multiplication (*) has a higher precedence than addition (+).");
        System.out.println("2. Therefore, '5 * 2' is evaluated first, which equals 10.");
        System.out.println("3. Finally, 10 is added to that result: '10 + 10', giving a total of 20.");

        int complexResult = (10 + 5) * 2;
        System.out.println("\nExpression with Parentheses: (10 + 5) * 2");
        System.out.println("Result: " + complexResult);
        System.out.println("Explanation: Parentheses () have the highest precedence, forcing '10 + 5' to evaluate first.");
    }
}