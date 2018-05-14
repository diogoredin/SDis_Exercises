package example;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Date;

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

        } catch (IOException e) {
            System.out.printf("Failed to load configuration: %s%n", e);
        }

        /*
         * CLIENT-SIDE PART
         */

        System.out.println("[ CLIENT ] Experiment with Kerberos client-side processing");

        // necessary client/server credentials
        String validClientName = "alice@T09.binas.org";
        String validClientPassword = "WD5zra6C";
        String validServerName = "binas@T09.binas.org";
        String validServerPassword = "VOL6yuFj";
        
        int validDuration = 30;

        // not really
        // String wsURL = properties.getProperty("ws.url");
        // KerbyClient client = new KerbyClient(wsURL);

        // create KerbyClient at specified URL
        KerbyClient client = new KerbyClient("http://sec.sd.rnl.tecnico.ulisboa.pt:8888/kerby");
        System.out.println("KerbyClient created at http://sec.sd.rnl.tecnico.ulisboa.pt:8888/kerby");

        // generate client/server keys
        Key clientKey = SecurityHelper.generateKeyFromPassword(validClientPassword);
        Key serverKey = SecurityHelper.generateKeyFromPassword(validServerPassword);
        
        // generate nonce to be used for the ticket
        SecureRandom randomGenerator = new SecureRandom();
		long nounce = randomGenerator.nextLong();
        
        // (1) get session key and ticket view
		SessionKeyAndTicketView result = client.requestTicket(validClientName, validServerName, nounce, validDuration);
        System.out.println("Session Key and Ticket View received");

        // (2) open Kcs session key with its Kc key
		CipheredView cipheredSessionKey = result.getSessionKey();
        SessionKey sessionKey = new SessionKey(cipheredSessionKey, clientKey);
        
        // (3) save ticket for later
        CipheredView cipheredTicket = result.getTicket();

        // (4) create authenticator
		// long timeDiff = ticket.getTime2().getTime() - ticket.getTime1().getTime();
        Date currDate = new Date();
        Auth auth = new Auth(validClientName, currDate);
        

        /*
         * SERVER-SIDE PART
         */
		
        System.out.println("[ SERVER ] Experiment with Kerberos server-side processing");
        
        // (1) open ticket with its Ks key and validate it
        Ticket ticket = new Ticket(cipheredTicket, serverKey);
        String ticketY = ticket.getY();

        if (!validServerName.equals(ticketY)) {
            System.out.println("Ticket data doesn't match this server's name");
        }

        // (2) open authenticator with the Kcs session key and validate it
        // Auth auth2 = new Auth(cipheredAuth, serverKey); ??? TODO
        String authX = auth.getX();

        if (!validClientName.equals(authX)) {
            System.out.println("Authenticator data doesn't match client's name");
        }

        // (3) reply with a RequestTime class from kerby-lib
        Date authDate = auth.getTimeRequest();
		RequestTime requestTime = new RequestTime(authDate);

        // TODO REVIEW THESE LNES
        System.out.println("Ticket: " + ticket.toString() + "\n");
		System.out.println("Session key: " + sessionKey.toString() + "\n");

        System.out.println();
		
		System.out.println("Bye!");

        System.out.println();

    }
}
