package example;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

import pt.ulisboa.tecnico.sdis.kerby.*;
import pt.ulisboa.tecnico.sdis.kerby.cli.KerbyClient;

public class KerbyExperiment {

    public static void main(String[] args) throws Exception {
        System.out.println("Hi!");

        System.out.println();

        // receive arguments
        System.out.printf("Received %d arguments%n", args.length);

        System.out.println();

        // load configuration properties
        try {
            InputStream inputStream = KerbyExperiment.class.getClassLoader().getResourceAsStream("config.properties");
            // variant for non-static methods:
            // InputStream inputStream = getClass().getClassLoader().getResourceAsStream("config.properties");

            Properties properties = new Properties();
            properties.load(inputStream);

            System.out.printf("Loaded %d properties%n", properties.size());

        // client-side code experiments
        System.out.println("Experiment with Kerberos client-side processing");
        System.out.println("...TODO...");
        
        SecureRandom randomGenerator = new SecureRandom();

        String wsURL = properties.getProperty("ws.url");
        KerbyClient client = new KerbyClient(wsURL);

        Key clientKey = SecurityHelper.generateKeyFromPassword("WD5zra6C");
		Key serverKey = SecurityHelper.generateKeyFromPassword("VOL6yuFj");
		long nounce = randomGenerator.nextLong();
		
		SessionKeyAndTicketView result = client.requestTicket("alice@T09.binas.org", "binas@T09.binas.org", nounce, 30);
		
		CipheredView cipheredSessionKey = result.getSessionKey();
		CipheredView cipheredTicket = result.getTicket();
		
		SessionKey sessionKey = new SessionKey(cipheredSessionKey, clientKey);
		
		Ticket ticket = new Ticket(cipheredTicket, serverKey);
		long timeDiff = ticket.getTime2().getTime() - ticket.getTime1().getTime();

        System.out.println();

		// server-side code experiments
        System.out.println("Experiment with Kerberos server-side processing");
        
        System.out.println("Ticket: " + ticket.toString() + "\n");
		System.out.println("Session key: " + sessionKey.toString() + "\n");

        System.out.println();
		
		System.out.println("Bye!");

        } catch (IOException e) {
            System.out.printf("Failed to load configuration: %s%n", e);
        }

        System.out.println();

    }
}
