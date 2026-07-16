public class MainApp {

    static class Utility {
        public static int add(int a, int b) {
            return a + b;
        }

        public static void printMessage(String message) {
            System.out.println("Utility Output: " + message);
        }
    }

    public static void main(String[] args) {
        System.out.println("Main program started successfully!");

        int result = Utility.add(15, 25);
        System.out.println("Result from Utility class: " + result);

        Utility.printMessage("Hello from inside MainApp!");
    }
}