package example;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;


public class SocketClient {

    public static void main( String[] args ) throws IOException {
        // Check arguments
        if (args.length < 3) {
            System.err.println("Argument(s) missing!");
            System.err.printf("Usage: java %s host port file%n", SocketClient.class.getName());
            return;
        }

        String host = args[0];
        // Convert port from String to int
        int port = Integer.parseInt(args[1]);
        // Concatenate arguments using a string builder
        StringBuilder sb = new StringBuilder();
        for (int i = 2; i < args.length; i++) {
            sb.append(args[i]);
            if (i < args.length-1) {
                sb.append(" ");
            }
        }

        // servidor codifica a mensagem
        String text = sb.toString();

        // Create client socket
        Socket socket = new Socket(host, port);
        System.out.printf("Connected to server %s on port %d %n", host, port);

        // Create stream to send data to server
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());

        // Send text to server as bytes

        out.writeBytes(text);
        out.writeBytes("\n");
        System.out.println("Sent text: " + text);

        // Close client socket
        socket.close();
        System.out.println("Connection closed");
    }
}
