import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        int port = 5000; // Match the port used by your TcpClient
        System.out.println("Initializing Chat Server on port " + port + "...");

        // FIXED: ServerSocket is declared inside the try() construct. 
        // Java will auto-close it securely, clearing your VS Code hint!
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server is online and listening for incoming connections...");

            // Wait and intercept an incoming handshake request from the client
            try (Socket clientSocket = serverSocket.accept();
                 BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                 PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

                System.out.println("Client successfully connected from: " + clientSocket.getRemoteSocketAddress());
                String incomingMessage;

                // Read incoming transmission frames dynamically
                while ((incomingMessage = in.readLine()) != null) {
                    System.out.println("Received from Client: " + incomingMessage);

                    // Acknowledge receipt back to the client console channel
                    out.println("Message received securely at server milestone frame.");

                    if (incomingMessage.equalsIgnoreCase("exit")) {
                        System.out.println("Client initiated termination routine. Disconnecting...");
                        break;
                    }
                }
            }

        } catch (IOException e) {
            System.err.println("[Server Error Handled]: Failed to manage connection streams.");
            System.err.println("Details: " + e.getMessage());
        }
    }
}