import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpsExample {
    public static void main(String[] args) {
        
        HttpClient client = HttpClient.newHttpClient();

        
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://jsonplaceholder.typicode.com/posts/1"))
                .GET()
                .build();

        System.out.println("Sending secure HTTPS request...");

        try {
            
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            
            System.out.println("Response HTTP Status Code: " + response.statusCode());
            System.out.println("\n--- Secure Response Body JSON ---");
            System.out.println(response.body());

        } catch (IOException | InterruptedException e) {
            System.out.println("An error occurred during the HTTPS request: " + e.getMessage());
            if (e instanceof InterruptedException) {
                Thread.currentThread().interrupt();
            }
        }
    }
}