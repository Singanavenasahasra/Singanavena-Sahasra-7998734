public class DecoratorPatternTest {

    interface Notifier {
        void send(String message);
    }

    static class EmailNotifier implements Notifier {
        @Override
        public void send(String message) {
            System.out.println("Sending Email: " + message);
        }
    }

    abstract static class NotifierDecorator implements Notifier {
        protected final Notifier wrappedNotifier;

        public NotifierDecorator(Notifier notifier) {
            this.wrappedNotifier = notifier;
        }

        @Override
        public void send(String message) {
            wrappedNotifier.send(message);
        }
    }

    static class SMSNotifierDecorator extends NotifierDecorator {
        public SMSNotifierDecorator(Notifier notifier) {
            super(notifier);
        }

        @Override
        public void send(String message) {
            super.send(message);
            System.out.println("Sending SMS: " + message);
        }
    }

    static class SlackNotifierDecorator extends NotifierDecorator {
        public SlackNotifierDecorator(Notifier notifier) {
            super(notifier);
        }

        @Override
        public void send(String message) {
            super.send(message);
            System.out.println("Sending Slack Notification: " + message);
        }
    }

    public static void main(String[] args) {
        System.out.println("--- Running Exercise 5: Decorator Pattern ---");
        
        Notifier alert = new EmailNotifier();
        alert = new SMSNotifierDecorator(alert);
        alert = new SlackNotifierDecorator(alert);

        alert.send("System maintenance alert!");

        System.out.println("--- Execution Finished Successfully! ---");
    }
}