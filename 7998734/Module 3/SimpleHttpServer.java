import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class SimpleHttpServer {

    public static void main(String[] args) throws IOException {
        
        int port = 8080;
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
        
        
        server.createContext("/", new RootHandler());
        
        
        server.setExecutor(null); 
        
        System.out.println("Starting web server...");
        server.start();
        System.out.println("Server is running successfully on http://localhost:" + port + "/");
        System.out.println("Press Ctrl+C in the terminal to stop the server.");
    }

    
    static class RootHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String response = "<h1>Hello! Your local Java HTTP Server is working perfectly.</h1>";
            
            
            exchange.sendResponseHeaders(200, response.length());
            
            
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(response.getBytes());
            }
        }
    }
}