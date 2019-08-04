package at.or.vogt.oe1downloader.download;

import org.apache.http.impl.client.CloseableHttpClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Tests for {@link HttpClientFactory}.
 */
public class HttpClientFactoryTest {

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

        Assertions.assertNotNull(result);
        logger.info(methodname + "result = {}", result);
    }

}
