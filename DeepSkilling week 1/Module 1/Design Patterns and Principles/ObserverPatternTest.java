import java.util.ArrayList;
import java.util.List;

public class ObserverPatternTest {

    interface Observer {
        void update(double stockPrice);
    }

    interface Stock {
        void register(Observer o);
        void deregister(Observer o);
        void notifyObservers();
    }

    static class StockMarket implements Stock {
        private final List<Observer> observers = new ArrayList<>();
        private double price;

        public void setPrice(double price) {
            this.price = price;
            notifyObservers();
        }

        @Override
        public void register(Observer o) { 
            observers.add(o); 
        }

        @Override
        public void deregister(Observer o) { 
            observers.remove(o); 
        }
        
        @Override
        public void notifyObservers() {
            for (Observer o : observers) {
                o.update(price);
            }
        }
    }

    static class MobileApp implements Observer {
        @Override
        public void update(double stockPrice) {
            System.out.println("Mobile Notification: Stock updated to $" + stockPrice);
        }
    }

    static class WebApp implements Observer {
        @Override
        public void update(double stockPrice) {
            System.out.println("Web Dashboard: Stock updated to $" + stockPrice);
        }
    }

    public static void main(String[] args) {
        System.out.println("--- Running Exercise 7: Observer Pattern ---");
        
        StockMarket market = new StockMarket();
        
        Observer mobile = new MobileApp();
        Observer web = new WebApp();

        market.register(mobile);
        market.register(web);

        market.setPrice(150.75);

        System.out.println("\nUpdating Stock Price Again...");
        market.setPrice(152.30);

        System.out.println("--- Execution Finished Successfully! ---");
    }
}