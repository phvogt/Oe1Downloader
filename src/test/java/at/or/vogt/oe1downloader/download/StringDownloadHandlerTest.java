package at.or.vogt.oe1downloader.download;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Test for {@link StringDownloadHandler}.
 */
class StringDownloadHandlerTest {

    /** Logger. */
    private final Logger logger = LoggerFactory.getLogger(StringDownloadHandlerTest.class);

    /**
     * Test for {@link StringDownloadHandler#handleDownload(java.io.InputStream)}.
     */
    @Test
    void testHandleDownload() {

        final StringDownloadHandler dut = new StringDownloadHandler();
        dut.handleDownload(IOUtils.toInputStream("test", Charset.defaultCharset()));
        Assertions.assertEquals("test", dut.getResult());
    }

    /**
     * Test for {@link StringDownloadHandler#handleDownload(java.io.InputStream)}.
     */
    @Test
    void testHandleDownloadException() {

        final String methodname = "testHandleDownloadException(): ";

        final StringDownloadHandler dut = new StringDownloadHandler();
        final RuntimeException exception = Assertions
                .assertThrows(RuntimeException.class, () -> dut.handleDownload(new InputStream() {

                    @Override
                    public int read() throws IOException {
                        logger.info("{}throwing exception", methodname);
                        throw new IOException("test");
                    }
                }));
        Assertions.assertEquals("java.io.IOException: test", exception.getMessage());
    }

}
