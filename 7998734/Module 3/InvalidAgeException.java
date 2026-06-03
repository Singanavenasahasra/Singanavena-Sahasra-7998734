public class InvalidAgeException extends Exception {
    
    public InvalidAgeException(String message) {
        super(message);
    }

    public static void main(String[] args) {
        int userAge = 16; 

        try {
            checkAge(userAge);
        } catch (InvalidAgeException e) {
            System.out.println("Exception caught: " + e.getMessage());
        }
    }

    public static void checkAge(int age) throws InvalidAgeException {
        if (age < 18) {
            throw new InvalidAgeException("Age is less than 18. Access denied.");
        } else {
            System.out.println("Access granted. You are old enough.");
        }
    }
}