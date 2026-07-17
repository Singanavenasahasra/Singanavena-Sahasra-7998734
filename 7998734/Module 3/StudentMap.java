import java.util.HashMap;
import java.util.Scanner;

public class StudentMap {
    public static void main(String[] args) {
        HashMap<Integer, String> studentMap = new HashMap<>();

        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("How many student records do you want to enter? ");
            int count = scanner.nextInt();

            for (int i = 0; i < count; i++) {
                System.out.print("Enter Student ID (Integer): ");
                int id = scanner.nextInt();
                scanner.nextLine(); 

                System.out.print("Enter Student Name: ");
                String name = scanner.nextLine();

                studentMap.put(id, name);
            }

            System.out.println("\nAll entries successfully saved.");
            System.out.print("Enter a Student ID to search for: ");
            int searchId = scanner.nextInt();

            if (studentMap.containsKey(searchId)) {
                String studentName = studentMap.get(searchId);
                System.out.println("Match Found: Student with ID " + searchId + " is " + studentName);
            } else {
                System.out.println("No record found for Student ID: " + searchId);
            }
        }
    }
}