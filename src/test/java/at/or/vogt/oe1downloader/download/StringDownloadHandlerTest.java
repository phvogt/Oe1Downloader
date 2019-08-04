package at.or.vogt.oe1downloader.download;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Test for {@link StringDownloadHandler}.
 */
public class StringDownloadHandlerTest {

    /** Logger. */
    private final Logger logger = LoggerFactory.getLogger(StringDownloadHandlerTest.class);

    /**
     * Test for {@link StringDownloadHandler#handleDownload(java.io.InputStream)}.
     */
    @Test
    public void testHandleDownload() {

        final StringDownloadHandler dut = new StringDownloadHandler();
        dut.handleDownload(IOUtils.toInputStream("test"));
        Assertions.assertEquals("test", dut.getResult());
    }

    /**
     * Test for {@link StringDownloadHandler#handleDownload(java.io.InputStream)}.
     */
    @Test
    public void testHandleDownloadException() {

        final String methodname = "testHandleDownloadException(): ";

        final StringDownloadHandler dut = new StringDownloadHandler();
        final RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            dut.handleDownload(new InputStream() {

                @Override
                public int read() throws IOException {
                    logger.info(methodname + "throwing exception");
                    throw new IOException("test");
                }
            });
        });
        Assertions.assertEquals("java.io.IOException: test", exception.getMessage());
    }

}
