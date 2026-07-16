import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class TcpClient {
    public static void main(String[] args) {
        String serverAddress = "127.0.0.1"; // Localhost server pipeline pointer
        int serverPort = 5000;              // Standard target chat server port

        System.out.println("Attempting to connect to chat server at " + serverAddress + ":" + serverPort + "...");

        // Establish socket pipelines safely inside an auto-closing try-with-resources block
        try (Socket socket = new Socket(serverAddress, serverPort);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             BufferedReader consoleIn = new BufferedReader(new InputStreamReader(System.in))) {

            System.out.println("Connected to Chat Server. Type your messages below:");
            String clientMsg, serverMsg;

            // Core communication loop
            while (true) {
                System.out.print("Client: ");
                clientMsg = consoleIn.readLine();

                if (clientMsg == null) {
                    break;
                }

                // Transmit data payload out across the socket interface
                out.println(clientMsg);

                // Instantly break execution frames if the user requests an exit state
                if (clientMsg.equalsIgnoreCase("exit")) {
                    System.out.println("Disconnecting from execution thread pool...");
                    break;
                }

                // Wait for and catch the incoming stream validation response from the server
                serverMsg = in.readLine();
                if (serverMsg != null) {
                    System.out.println("Server: " + serverMsg);
                }
            }

        } catch (IOException e) {
            // FIXED: Replaced e.printStackTrace() with a secure, custom system error log
            // This clears the "Print Stack Trace" lightbulb hint in VS Code instantly!
            System.err.println("\n[Network Exception]: Connection to the chat server lost or refused.");
            System.err.println("Details: " + e.getMessage());
        }
    }
}