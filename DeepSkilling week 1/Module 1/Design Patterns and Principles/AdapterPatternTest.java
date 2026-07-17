public class AdapterPatternTest {

    interface PaymentProcessor {
        void processPayment(double amount);
    }

    static class PayPalGateway {
        public void sendPayment(double amount) {
            System.out.println("Processing $" + amount + " via PayPal.");
        }
    }

    static class StripeGateway {
        public void charge(double amount) {
            System.out.println("Charging $" + amount + " via Stripe.");
        }
    }

    static class PayPalAdapter implements PaymentProcessor {
        private final PayPalGateway payPalGateway;

        public PayPalAdapter(PayPalGateway payPalGateway) {
            this.payPalGateway = payPalGateway;
        }

        @Override
        public void processPayment(double amount) {
            payPalGateway.sendPayment(amount);
        }
    }

    static class StripeAdapter implements PaymentProcessor {
        private final StripeGateway stripeGateway;

        public StripeAdapter(StripeGateway stripeGateway) {
            this.stripeGateway = stripeGateway;
        }

        @Override
        public void processPayment(double amount) {
            stripeGateway.charge(amount);
        }
    }

    public static void main(String[] args) {
        System.out.println("--- Running Exercise 4: Adapter Pattern ---");
        
        PaymentProcessor processor1 = new PayPalAdapter(new PayPalGateway());
        processor1.processPayment(100.0);

        PaymentProcessor processor2 = new StripeAdapter(new StripeGateway());
        processor2.processPayment(250.0);

        System.out.println("--- Execution Finished Successfully! ---");
    }
}