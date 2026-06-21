import java.util.HashMap;
import java.util.Map;

public class InventoryManager {

    
    public static class Product {
        
        private final String productId;   
        private final String productName; 
        private int quantity;             
        private double price;             

        
        public Product(String productId, String productName, int quantity, double price) {
            this.productId = productId;
            this.productName = productName;
            this.quantity = quantity;
            this.price = price;
        }

        
        public String getProductId() { 
            return productId; 
        }

        public String getProductName() { 
            return productName; 
        }

        public int getQuantity() { 
            return quantity; 
        }

        public void setQuantity(int quantity) { 
            this.quantity = quantity; 
        }

        public double getPrice() { 
            return price; 
        }

        public void setPrice(double price) { 
            this.price = price; 
        }

        @Override
        public String toString() {
            return "Product[ID=" + productId + ", Name=" + productName + ", Qty=" + quantity + ", Price=$" + price + "]";
        }
    }

    
    private final Map<String, Product> inventory = new HashMap<>(); 

    public void addProduct(Product product) {
        inventory.put(product.getProductId(), product);
        System.out.println("Added: " + product.getProductName());
    }


    public void updateProduct(String productId, int newQuantity, double newPrice) {
        Product product = inventory.get(productId);
        if (product != null) {
            product.setQuantity(newQuantity);
            product.setPrice(newPrice);
            System.out.println("Updated ID: " + productId + " successfully.");
        } else {
            System.out.println("Product with ID " + productId + " not found.");
        }
    }

   
    public void deleteProduct(String productId) {
        if (inventory.containsKey(productId)) {
            Product removed = inventory.remove(productId);
            System.out.println("Deleted: " + removed.getProductName());
        } else {
            System.out.println("Product not found.");
        }
    }

    public static void main(String[] args) {
        System.out.println("--- Running Exercise 1: Inventory Management System ---");
        
        InventoryManager manager = new InventoryManager();

        
        manager.addProduct(new Product("P001", "Laptop", 10, 999.99));
        manager.addProduct(new Product("P002", "Smart Phone", 25, 499.99));

        
        manager.updateProduct("P001", 15, 949.99);

    
        manager.deleteProduct("P002");
        
        System.out.println("--- Execution Finished Successfully! ---");
    }
}