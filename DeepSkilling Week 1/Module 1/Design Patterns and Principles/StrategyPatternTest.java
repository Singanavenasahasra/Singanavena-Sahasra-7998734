public class StrategyPatternTest {

    interface PaymentStrategy {
        void pay(double amount);
    }

    static class CreditCardPayment implements PaymentStrategy {
        private final String cardNumber;

        public CreditCardPayment(String cardNumber) {
            this.cardNumber = cardNumber;
        }

        public String getCardNumber() { 
            return cardNumber; 
        }

        @Override
        public void pay(double amount) {
            System.out.println("Paid $" + amount + " using Credit Card.");
        }
    }

    static class PayPalPayment implements PaymentStrategy {
        private final String email;

        public PayPalPayment(String email) {
            this.email = email;
        }

        public String getEmail() { 
            return email; 
        }

        @Override
        public void pay(double amount) {
            System.out.println("Paid $" + amount + " using PayPal.");
        }
    }

    static class PaymentContext {
        private PaymentStrategy strategy;

        public void setPaymentStrategy(PaymentStrategy strategy) {
            this.strategy = strategy;
        }

        public void executePayment(double amount) {
            if (strategy != null) {
                strategy.pay(amount);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("--- Running Exercise 8: Strategy Pattern ---");
        
        PaymentContext context = new PaymentContext();

        context.setPaymentStrategy(new CreditCardPayment("1234-5678"));
        context.executePayment(50.0);

        context.setPaymentStrategy(new PayPalPayment("user@test.com"));
        context.executePayment(75.50);

        System.out.println("--- Execution Finished Successfully! ---");
    }
}