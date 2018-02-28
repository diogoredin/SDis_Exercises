package example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer 
{
    public static void main( String[] args ) throws IOException {
        // Check arguments
        if (args.length < 1) {
            System.err.println("Argument(s) missing!");
            System.err.printf("Usage: java %s port%n", SocketServer.class.getName());
            return;
        }

        // Convert port from String to int
        int port = Integer.parseInt(args[0]);

        // Create server socket
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.printf("Server accepting connections on port %d %n", port);

        // wait for and then accept client connection
        // a socket is created to handle the created connection
        Socket clientSocket = serverSocket.accept();
        System.out.printf("Connected to client %s on port %d %n",
            clientSocket.getInetAddress().getHostAddress(), clientSocket.getPort());

        // Create stream to receive data from client
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        // Receive data until client closes the connection
        int key = 0;
        String response;
        String toSend = "";
        while (true) {
        	
            //Reads a line of text. 
        	//A line ends with a line feed ('\n').
        	response = in.readLine();
        	if (response == null) {
        		break;
        	}

            for (int i = 0; i < response.length(); i++) {

                if ( i == 0 ) {
                    key = Character.getNumericValue( response.charAt(0) ); 
                } 
                
                else {
                    
                    if ( response.charAt(i) != ' ' ) {

                        int character = (int) response.charAt(i);
                        int encrypted = character + key;
                        String e = Character.toString ((char) encrypted);
    
                        toSend = toSend + String.valueOf(e);
                    }

                }

            }

        	System.out.printf("Received message with content: %s, %s %n", response, toSend);  	
        }

        // Close connection to current client
        clientSocket.close();
        System.out.println("Closed connection with client");

        // Close server socket
        serverSocket.close();
        System.out.println("Closed server socket");
    }
}
