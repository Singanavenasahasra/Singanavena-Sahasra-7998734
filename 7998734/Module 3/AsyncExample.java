import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class AsyncExample {
    
    public static void main(String[] args) {
        System.out.println("[" + Thread.currentThread().getName() + "] Main thread started.");

        CompletableFuture<String> asyncTask = CompletableFuture.supplyAsync(() -> {
            System.out.println("[" + Thread.currentThread().getName() + "] Fetching data from cloud API...");
            simulateHeavyDataLoad();
            return "Raw Weather Data: Sunny, 28°C";
        });

        CompletableFuture<String> processedTask = asyncTask.thenApply(rawData -> {
            System.out.println("[" + Thread.currentThread().getName() + "] Formatting data...");
            return "Processed Report -> " + rawData.toUpperCase();
        });

        CompletableFuture<String> securedTask = processedTask.exceptionally(ex -> {
            System.out.println("An error occurred during background computation: " + ex.getMessage());
            return "Fallback Data: Report Unavailable";
        });

        System.out.println("[" + Thread.currentThread().getName() + "] Main thread is free to do other operations simultaneously...");
        
        int i = 1;
        while (i <= 3) {
            System.out.println("[" + Thread.currentThread().getName() + "] Main thread processing local animation loop step " + i);
            executeDelay(300);
            i++;
        }

        try {
            System.out.println("\nRetrieving final background results...");
            String ultimateResult = securedTask.get(); 
            System.out.println("Final Output: " + ultimateResult);
        } catch (InterruptedException | ExecutionException e) {
            System.out.println("Execution failed to retrieve thread values: " + e.getMessage());
        }

        System.out.println("[" + Thread.currentThread().getName() + "] Main thread gracefully exiting.");
    }

    private static void simulateHeavyDataLoad() {
        executeDelay(1500);
    }

    private static void executeDelay(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}