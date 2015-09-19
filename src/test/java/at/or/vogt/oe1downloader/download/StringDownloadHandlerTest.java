// (c) 2015 by Philipp Vogt
package at.or.vogt.oe1downloader.download;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Test for {@link StringDownloadHandler}.
 */
public class StringDownloadHandlerTest {

    static {
        PropertyConfigurator.configure("src/test/resources/log4j.properties");
    }

    /** Logger. */
    private final Logger logger = LoggerFactory.getLogger(StringDownloadHandlerTest.class);

    /**
     * Test for
     * {@link StringDownloadHandler#handleDownload(java.io.InputStream)}.
     */
    @Test
    public void testHandleDownload() {

        final StringDownloadHandler dut = new StringDownloadHandler();
        dut.handleDownload(IOUtils.toInputStream("test"));
        Assert.assertEquals("test", dut.getResult());
    }

    /**
     * Test for
     * {@link StringDownloadHandler#handleDownload(java.io.InputStream)}.
     */
    @Test(expected = RuntimeException.class)
    public void testHandleDownloadException() {

        final String methodname = "testHandleDownloadException(): ";

        final StringDownloadHandler dut = new StringDownloadHandler();
        dut.handleDownload(new InputStream() {

            @Override
            public int read() throws IOException {
                logger.info(methodname + "throwing exception");
                throw new IOException("test");
            }
        });
    }

}
