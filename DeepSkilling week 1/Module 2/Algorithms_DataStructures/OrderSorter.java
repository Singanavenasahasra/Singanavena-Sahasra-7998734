import java.util.Arrays;

public class OrderSorter {

    public static class Order {
        private final String orderId;
        private final String customerName;
        private final double totalPrice;

        public Order(String orderId, String customerName, double totalPrice) {
            this.orderId = orderId;
            this.customerName = customerName;
            this.totalPrice = totalPrice;
        }

        public String getOrderId() { 
            return orderId; 
        }

        public String getCustomerName() { 
            return customerName; 
        }

        public double getTotalPrice() { 
            return totalPrice; 
        }

        @Override
        public String toString() { 
            return customerName + " ($" + totalPrice + ")"; 
        }
    }

    public static void bubbleSort(Order[] orders) {
        int n = orders.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (orders[j].getTotalPrice() > orders[j + 1].getTotalPrice()) {
                    Order temp = orders[j];
                    orders[j] = orders[j + 1];
                    orders[j + 1] = temp;
                }
            }
        }
    }

    public static void quickSort(Order[] orders, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(orders, low, high);
            quickSort(orders, low, pivotIndex - 1);
            quickSort(orders, pivotIndex + 1, high);
        }
    }

    private static int partition(Order[] orders, int low, int high) {
        double pivot = orders[high].getTotalPrice();
        int i = (low - 1); 
        
        for (int j = low; j < high; j++) {
            if (orders[j].getTotalPrice() <= pivot) {
                i++;
                Order temp = orders[i];
                orders[i] = orders[j];
                orders[j] = temp;
            }
        }
        Order temp = orders[i + 1];
        orders[i + 1] = orders[high];
        orders[high] = temp;
        
        return i + 1;
    }

    public static void main(String[] args) {
        System.out.println("--- Running Exercise 3: Sorting Customer Orders ---");
        
        Order[] ordersForBubbleSort = {
            new Order("O001", "Alice", 250.50),
            new Order("O002", "Bob", 50.25),
            new Order("O003", "Charlie", 500.00)
        };

        Order[] ordersForQuickSort = ordersForBubbleSort.clone();

        System.out.println("Original Array: " + Arrays.toString(ordersForBubbleSort));
        bubbleSort(ordersForBubbleSort);
        System.out.println("Sorted via Bubble Sort: " + Arrays.toString(ordersForBubbleSort));
        
        System.out.println();

        System.out.println("Original Array: " + Arrays.toString(ordersForQuickSort));
        quickSort(ordersForQuickSort, 0, ordersForQuickSort.length - 1);
        System.out.println("Sorted via Quick Sort: " + Arrays.toString(ordersForQuickSort));

        System.out.println("\nHighest Value Order ID: " + ordersForQuickSort[ordersForQuickSort.length - 1].getOrderId());
        System.out.println("--- Execution Finished Successfully ---");
    }
}