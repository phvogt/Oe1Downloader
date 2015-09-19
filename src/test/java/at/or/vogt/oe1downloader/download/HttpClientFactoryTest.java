// (c) 2015 by Philipp Vogt
package at.or.vogt.oe1downloader.download;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Tests for {@link HttpClientFactory}.
 */
public class HttpClientFactoryTest {

    static {
        PropertyConfigurator.configure("src/test/resources/log4j.properties");
    }

    /** Logger. */
    private final Logger logger = LoggerFactory.getLogger(HttpClientFactoryTest.class);

    /**
     * Test of {@link HttpClientFactory#getHttpClient()}.
     */
    @Test
    public void testGetHttpClient() {

        final String methodname = "testGetHttpClient(): ";

        final HttpClientFactory dut = new HttpClientFactory();
        final CloseableHttpClient result = dut.getHttpClient();

        Assert.assertNotNull(result);
        logger.info(methodname + "result = {}", result);
    }

}
