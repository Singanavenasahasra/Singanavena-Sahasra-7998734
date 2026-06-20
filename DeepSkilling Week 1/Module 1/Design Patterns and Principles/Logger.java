public class Logger {
    private static Logger instance;

    private Logger() {}

    public static Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }

    public void log(String message) {
        System.out.println("[LOG]: " + message);
    }

    public static void main(String[] args) {
        System.out.println("--- Exercise 1: Singleton Pattern ---");
        Logger logger1 = Logger.getInstance();
        Logger logger2 = Logger.getInstance();

        logger1.log("Testing singleton instance.");
        System.out.println("Are both instances the same? " + (logger1 == logger2));
    }
}