package at.or.vogt.oe1downloader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Collection;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import at.or.vogt.oe1downloader.download.DownloadHandler;
import at.or.vogt.oe1downloader.download.DownloadService;
import at.or.vogt.oe1downloader.download.HttpClientFactory;

/**
 * Test for {@link at.or.vogt.oe1downloader.Main}.
 */
public class MainTest {

    static {
        PropertyConfigurator.configure("src/test/resources/log4j.properties");
    }

    /** Logger. */
    private final Logger logger = LoggerFactory.getLogger(MainTest.class);

    /**
     * Tests {@link Main#run(DownloadService, String, String)}.
     */
    @Test
    public void testRun() {

        final String methodname = "testRun(): ";
        logger.info(methodname + "start");

        final Main dut = new Main();
        final DownloadService downloadService = new DownloadService(new HttpClientFactory()) {

            /**
             * {@inheritDoc}
             */
            @Override
            public void download(final String url, final DownloadHandler handler) {

                try {
                    if (url.startsWith("src/test/resources")) {
                        handler.handleDownload(new FileInputStream("src/test/resources/tag_special/20150817.json"));
                    } else {
                        handler.handleDownload(new FileInputStream("src/test/resources/test.mp3"));
                    }
                } catch (final FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }

        };

        // delete old files
        final Collection<File> oldFiles = FileUtils.listFiles(FileUtils.getTempDirectory(), new String[] { "mp3" }, false);
        logger.info(methodname + "oldFiles = {}", oldFiles);
        for (final File file : oldFiles) {
            if (file.getName().contains("kolleg_17.08.2015")) {
                file.delete();
            }
        }

        dut.run(downloadService, "src/test/resources/tag/", FileUtils.getTempDirectoryPath());
        Assert.assertTrue(new File(FileUtils.getTempDirectoryPath() + "/60_kolleg_17.08.2015.mp3").exists());
    }

    /**
     * Tests the getters.
     */
    @Test
    public void testGetter() {

        final String methodname = "testRun(): ";
        logger.info(methodname + "start");

        final Main dut = new Main();

        final DownloadService downloadService = dut.getDownloadService();
        Assert.assertNotNull(downloadService);
        logger.info(methodname + "downloadService = {}", downloadService);

        final String jsonPathPrefix = dut.getJsonPathPrefix();
        Assert.assertNotNull(jsonPathPrefix);
        logger.info(methodname + "jsonPathPrefix = {}", jsonPathPrefix);

        final String targetDirectory = dut.getTargetDirectory();
        Assert.assertNotNull(targetDirectory);
        logger.info(methodname + "targetDirectory = {}", targetDirectory);

    }

}
