import java.util.Arrays;

public class SearchEngine {

    
    public static class Product implements Comparable<Product> {
        private final String productId;
        private final String productName;
        private final String category;

        public Product(String productId, String productName, String category) {
            this.productId = productId;
            this.productName = productName;
            this.category = category;
        }

        public String getProductId() { 
            return productId; 
        }

        public String getProductName() { 
            return productName; 
        }

        public String getCategory() { 
            return category; 
        }

        
        @Override
        public int compareTo(Product other) {
            return this.productName.compareToIgnoreCase(other.productName);
        }
    }

    
    public static int linearSearch(Product[] products, String targetName) {
        for (int i = 0; i < products.length; i++) {
            if (products[i].getProductName().equalsIgnoreCase(targetName)) {
                return i; 
            }
        }
        return -1;
    }

    
    public static int binarySearch(Product[] products, String targetName) {
        int low = 0;
        int high = products.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            int comp = products[mid].getProductName().compareToIgnoreCase(targetName);

            if (comp == 0) {
                return mid; 
            }
            if (comp < 0) {
                low = mid + 1; 
            } else {
                high = mid - 1; 
            }
        }
        return -1; 
    }

    
    public static void main(String[] args) {
        System.out.println("--- Running Exercise 2: E-commerce Platform Search ---");
        
        
        Product[] products = {
            new Product("P101", "Shoes", "Apparel"),
            new Product("P102", "Laptop", "Electronics"),
            new Product("P103", "Book", "Media")
        };

        String searchTarget = "Laptop";

        
        int linearIndex = linearSearch(products, searchTarget);
        System.out.println("Linear Search for '" + searchTarget + "': Found at index " + linearIndex);
        
        
        Arrays.sort(products); 
        int binaryIndex = binarySearch(products, searchTarget);
        System.out.println("Binary Search for '" + searchTarget + "' (Sorted): Found at index " + binaryIndex);

        if (binaryIndex != -1) {
            System.out.println("Product Found Details -> ID: " + products[binaryIndex].getProductId() + 
                               ", Category: " + products[binaryIndex].getCategory());
        }

        System.out.println("--- Execution Finished Successfully ---");
    }
}