package example;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Date;
import java.util.Arrays;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;

import static javax.xml.bind.DatatypeConverter.printHexBinary;

import pt.ulisboa.tecnico.sdis.kerby.*;
import pt.ulisboa.tecnico.sdis.kerby.cli.KerbyClient;

public class KerbyExperiment {

	public static void main(String[] args) throws Exception {

		/* Init program. */
		System.out.println("Hi!");
		System.out.printf("Received %d arguments%n", args.length);
		System.out.println();

		/* Setup enviroment. */
		try {
			InputStream inputStream = KerbyExperiment.class.getClassLoader().getResourceAsStream("config.properties");

			Properties properties = new Properties();
			properties.load(inputStream);

			System.out.printf("Loaded %d properties%n", properties.size());

		} catch (IOException e) {
			System.out.printf("Failed to load configuration: %s%n", e);
		}

		/***********************************************
		 * 
		 * SHARED
		 * 
		 **********************************************/

		String plainText = "This is the message authentication code known by client and server.";
		byte[] plainBytes = plainText.getBytes();
		String validServerName = "binas@T09.binas.org";

		KerbyClient kerby = new KerbyClient("http://sec.sd.rnl.tecnico.ulisboa.pt:8888/kerby");
		System.out.println("KerbyClient created at http://sec.sd.rnl.tecnico.ulisboa.pt:8888/kerby");

		/***********************************************
		 * 
		 * CLIENT
		 * 
		 **********************************************/

		System.out.println("[ CLIENT ] Experiment with Kerberos client-side processing");

		String validClientName = "alice@T09.binas.org";
		String validClientPassword = "WD5zra6C";
		int validDuration = 30;

		Key clientKey = SecurityHelper.generateKeyFromPassword(validClientPassword);
		SecureRandom randomGenerator = new SecureRandom();
		long nounce = randomGenerator.nextLong();
	
		// (1) get session key and ticket view.
		SessionKeyAndTicketView result = kerby.requestTicket(validClientName, validServerName, nounce, validDuration);
		System.out.println("Session Key and Ticket View received");

		// (2) open Kcs session key with its Kc key
		CipheredView cipheredSessionKey = result.getSessionKey();
		SessionKey sessionKey = new SessionKey(cipheredSessionKey, clientKey);

		// (3) save ticket for later
		CipheredView cipheredTicket = result.getTicket();

		// (4) create authenticator
		Date currDate = new Date();
		Auth auth = new Auth(validClientName, currDate);

		// Instantiates the MAC cipher.
		Mac cipherClient = Mac.getInstance("HmacSHA256");

		// Initializes the cipher with the MAC Key.
		cipherClient.init(sessionKey.getKeyXY());
	
		// Generates the digest of the plain bytes.
		byte[] cipherDigestClient = cipherClient.doFinal(plainBytes);

		System.out.println("Client ---- raw message: " + plainText);
		System.out.println("Client ---- raw message bytes: " + printHexBinary(plainBytes));
		System.out.println("Client ---- cypher digest bytes: " + printHexBinary(cipherDigestClient));

		/***********************************************
		 * 
		 * SERVER
		 *
		 **********************************************/

		System.out.println("[ SERVER ] Experiment with Kerberos server-side processing");
		String validServerPassword = "VOL6yuFj";
		
		Key serverKey = SecurityHelper.generateKeyFromPassword(validServerPassword);
		
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

		Key ticketMasterKey = ticket.getKeyXY();

		// (3) reply with a RequestTime class from kerby-lib
		Date authDate = auth.getTimeRequest();
		RequestTime requestTime = new RequestTime(authDate);
		CipheredView cipheredRequestTime = requestTime.cipher(ticketMasterKey);

		System.out.println();
		System.out.println();

		/* These are shared */
		System.out.println("Ticket: " + ticket.toString() + "\n");
		System.out.println("Session key: " + sessionKey.toString() + "\n");

		// Instantiates the MAC cipher.
		Mac cipherServer = Mac.getInstance("HmacSHA256");

		// Initializes the cipher with the MAC Key.
		cipherServer.init(ticketMasterKey);
		
		// Generates the digest of the plain bytes.
		byte[] cipherDigestServer = cipherServer.doFinal(plainBytes);

		// Verifiy the MAC of the received message.
		System.out.println("Server ---- Validating MAC.");
		System.out.println("Server ---- Raw message bytes: " + printHexBinary(plainBytes));
		System.out.println("Client ---- Cipher digest bytes: " + printHexBinary(cipherDigestClient));
		System.out.println("Server ---- Cipher digest bytes: " + printHexBinary(cipherDigestServer));

		if (Arrays.equals(cipherDigestServer, cipherDigestClient)) { System.out.println("Server ---- Validated MAC."); }
		else { System.out.println("Server ---- MAC is wrong. Message was tampered"); }
		
		System.out.println();
		System.out.println("Bye!");

		System.out.println();

	}
}
