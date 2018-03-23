package org.binas.station.ws.it;

import org.junit.Assert;
import org.junit.Test;

/**
 * Class that tests Ping operation
 */
public class PingIT extends BaseIT {

	// tests
	// assertEquals(expected, actual);

	// public String ping(String x)

	@Test
	public void pingEmptyTest() {
		Assert.assertNotNull(client.testPing("test"));
	}

}
