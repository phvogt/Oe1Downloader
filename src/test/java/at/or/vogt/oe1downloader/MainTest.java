package at.or.vogt.oe1downloader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Collection;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import at.or.vogt.oe1downloader.cli.CommandLineParser;
import at.or.vogt.oe1downloader.config.Configuration;
import at.or.vogt.oe1downloader.download.DownloadHandler;
import at.or.vogt.oe1downloader.download.DownloadService;
import at.or.vogt.oe1downloader.download.HttpClientFactory;

/**
 * Test for {@link at.or.vogt.oe1downloader.Main}.
 */
class MainTest {

    /** Logger. */
    private final Logger logger = LoggerFactory.getLogger(MainTest.class);

    @BeforeEach
    public void before() {
        Configuration.setConfigFilename(TestParameters.TEST_CONFIG_FILENAME);
    }

    /**
     * Tests
     * {@link at.or.vogt.oe1downloader.Main#doDownloads(DownloadService, String, String)}
     * .
     */
    @Test
    @Disabled("jsonGetter works with current date")
    void testDoDownloads() {

        final String methodname = "testDoDownloads(): ";
        logger.info("{}start", methodname);

        final Main dut = new Main();
        final DownloadService downloadService = new DownloadService(new HttpClientFactory()) {

            /**
             * {@inheritDoc}
             */
            @Override
            public boolean download(final String url, final DownloadHandler handler) {

                boolean result = false;
                try {
                    if (url.startsWith("src/test/resources")) {
                        handler.handleDownload(new FileInputStream("src/test/resources/tag/broadcast20170429.json"));
                    } else if ("https://audioapi.orf.at/oe1/api/json/current/broadcast/468460/20170423".equals(url)) {
                        handler.handleDownload(new FileInputStream("src/test/resources/showinfo/20170429.json"));
                    } else {
                        handler.handleDownload(new FileInputStream("src/test/resources/test.mp3"));
                    }
                    result = handler.successful();
                } catch (final FileNotFoundException e) {
                    throw new RuntimeException(e);
                }

                return result;
            }

        };

        // delete old files
        final Collection<File> oldFiles = FileUtils
                .listFiles(FileUtils.getTempDirectory(), new String[] { "mp3" }, false);
        logger.info("{}oldFiles = {}", methodname, oldFiles);
        for (final File file : oldFiles) {
            if (file.getName().contains("kolleg_17.08.2015")) {
                file.delete();
            }
        }

        final File expectedFile = new File(FileUtils.getTempDirectoryPath() + "/41_matrix_23.04.2017.mp3");
        if (expectedFile.exists()) {
            expectedFile.delete();
        }
        dut.doDownloads(downloadService, "src/test/resources/tag/", FileUtils.getTempDirectoryPath());
        Assertions.assertTrue(expectedFile.exists());
    }

    /**
     * Tests {@link at.or.vogt.oe1downloader.Main#getDownloadService()}.
     */
    @Test
    void testGetDownloadService() {

        final String methodname = "testGetDownloadService(): ";
        logger.info("{}start", methodname);

        final Main dut = new Main();

        final DownloadService downloadService = dut.getDownloadService();
        Assertions.assertNotNull(downloadService);
        logger.info("{}downloadService = {}", methodname, downloadService);
    }

    /**
     * Tests {@link at.or.vogt.oe1downloader.Main#getJsonBroadcastsUrl()}.
     */
    @Test
    void testGetJsonBroadcastsUrl() {

        final String methodname = "testGetJsonBroadcastsUrl(): ";
        logger.info("{}start", methodname);

        final Main dut = new Main();

        final String jsonBroadcastsUrl = dut.getJsonBroadcastsUrl();
        Assertions.assertNotNull(jsonBroadcastsUrl);
        logger.info("{}jsonBroadcastsUrl = {}", methodname, jsonBroadcastsUrl);

    }

    /**
     * Tests {@link at.or.vogt.oe1downloader.Main#getTargetDirectory(String)}.
     */
    @Test
    void testGetTargetDirectory() {

        final String methodname = "testGetter(): ";
        logger.info("{}start", methodname);

        final Main dut = new Main();

        final String targetDirectory = dut.getTargetDirectory(null);
        Assertions.assertNotNull(targetDirectory);
        logger.info("{}targetDirectory = {}", methodname, targetDirectory);
        Assertions.assertEquals("./150826", targetDirectory);

        final String targetDirectory2 = dut.getTargetDirectory("testdir");
        Assertions.assertNotNull(targetDirectory2);
        logger.info("{}targetDirectory2 = {}", methodname, targetDirectory2);
        Assertions.assertEquals("testdir", targetDirectory2);

    }

    /**
     * Tests {@link at.or.vogt.oe1downloader.Main#showUsage(String)}.
     */
    @Test
    void testShowUsage() {

        final String methodname = "testShowUsage(): ";
        logger.info("{}start", methodname);

        final Main dut = new Main();
        dut.showUsage(null);

        dut.showUsage("error x");
    }

    /**
     * Tests {@link at.or.vogt.oe1downloader.Main#processCommandline(String[])}.
     */
    @Test
    void testProcessCommandline() {

        final String methodname = "testProcessCommandline(): ";
        logger.info("{}start", methodname);

        final Main dut = new Main();

        try {
            final CommandLineParser cmd = dut.processCommandline(new String[] {});
            Assertions.assertNotNull(cmd);
        } catch (final SystemExitException e) {
            logger.error("error", e);
            Assertions.fail();
        }

        try {
            dut.processCommandline(new String[] { "-h" });
            logger.error("error");
            Assertions.fail();
        } catch (final SystemExitException e) {
            Assertions.assertEquals(1, e.getExitCode());
        }

        try {
            dut.processCommandline(new String[] { "-x" });
            logger.error("error");
            Assertions.fail();
        } catch (final SystemExitException e) {
            Assertions.assertEquals(2, e.getExitCode());
        }
    }

}
