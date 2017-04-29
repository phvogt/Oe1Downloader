package at.or.vogt.oe1downloader.download;

import java.io.File;
import java.time.LocalDateTime;
import java.util.Arrays;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import at.or.vogt.oe1downloader.json.DateParser;

/**
 * Test for downloading MP3.
 */
public class DownloadServiceTest {

    static {
        PropertyConfigurator.configure("src/test/resources/log4j.properties");
    }

    /** Logger. */
    private final Logger logger = LoggerFactory.getLogger(DownloadServiceTest.class);

    /**
     * Test of {@link DownloadService#download(String, DownloadHandler)}.
     */
    @Test
    public void testDownloadWithHandler() {

        final String methodname = "testDownloadWithHandler(): ";

        final DownloadService dut = new DownloadService(new FileHttpClientFactory("src/test/resources/tag/broadcast20170429.json"));
        final StringDownloadHandler handler = new StringDownloadHandler();
        final boolean result = dut.download("src/test/resources/tag/broadcast20170429.json", handler);
        Assert.assertTrue(result);
        Assert.assertNotNull(handler.getResult());
        logger.info(methodname + "result = {}", handler.getResult());
    }

    /**
     * Test of {@link DownloadService#download(String, DownloadHandler)}.
     */
    @Test
    public void testDownloadWithHandlerConnectionReset() {

        final String methodname = "testDownloadWithHandlerConnectionReset(): ";

        final DownloadService dut = new DownloadService(
                new FileHttpClientFactoryConnectionReset("src/test/resources/tag/broadcast20170429.json"));
        final StringDownloadHandler handler = new StringDownloadHandler();
        final boolean result = dut.download("src/test/resources/tag/broadcast20170429.json", handler);
        Assert.assertFalse(result);
        Assert.assertEquals("", handler.getResult());
        logger.info(methodname + "result = {}", handler.getResult());
    }

    /**
     * Test of {@link DownloadService#download(RecordVO)}.
     * @throws Exception if an error occurs
     */
    @Test
    public void testDownloadWithRecord() throws Exception {

        final String methodname = "testDownloadWithRecord(): ";

        final DownloadService dut = new DownloadService(new FileHttpClientFactory("src/test/resources/showinfo/20170429.json"));
        final LocalDateTime scheduledStartLdt = DateParser.parseISO("2017-04-29T08:15:00+02:00");
        final RecordVO record = new RecordVO("20170429", "Pasticcio", scheduledStartLdt, "60", "mp3postfix",
                "src/test/resources/showinfo/20170429.json");
        final File tmpFile = File.createTempFile("test", ".mp3");
        FileUtils.deleteQuietly(tmpFile);
        final String targetFilename = tmpFile.getCanonicalPath();
        logger.info(methodname + "targetFilename = {}", targetFilename);
        final File targetFile = new File(targetFilename);
        record.setTargetFilename(targetFilename);

        dut.download(record);

        Assert.assertTrue(targetFile.exists());
        FileUtils.deleteQuietly(targetFile);

    }

    /**
     * Test of {@link DownloadService#downloadRecords(String, java.util.List)}.
     * @throws Exception if an error occurs
     */
    @Test
    public void testDownloadRecords() throws Exception {

        final String methodname = "testDownloadRecords(): ";

        final DownloadService dut = new DownloadService(new FileHttpClientFactory("src/test/resources/showinfo/20170429.json"));
        final LocalDateTime scheduledStartLdt = DateParser.parseISO("2017-04-29T08:15:00+02:00");
        final RecordVO record = new RecordVO("20170429", "Pasticcio", scheduledStartLdt, "60", "mp3postfix",
                "src/test/resources/showinfo/20170429.json");
        final File tmpFile = File.createTempFile("test", ".mp3");
        FileUtils.deleteQuietly(tmpFile);
        final String targetFilename = tmpFile.getName();
        record.setTargetFilename(targetFilename);
        logger.info(methodname + "targetFilename = {}", targetFilename);
        final File targetFile = new File(FileUtils.getTempDirectoryPath() + "/" + record.getFilename());
        FileUtils.deleteQuietly(targetFile);

        dut.downloadRecords(FileUtils.getTempDirectoryPath(), Arrays.asList(new RecordVO[] { record }));

        Assert.assertTrue(targetFile.exists());
        FileUtils.deleteQuietly(targetFile);
    }

}
