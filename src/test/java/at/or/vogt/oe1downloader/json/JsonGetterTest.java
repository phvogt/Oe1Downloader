package at.or.vogt.oe1downloader.json;

import java.io.File;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import at.or.vogt.oe1downloader.TestParameters;
import at.or.vogt.oe1downloader.config.Configuration;
import at.or.vogt.oe1downloader.config.ConfigurationParameter;
import at.or.vogt.oe1downloader.download.DownloadService;
import at.or.vogt.oe1downloader.download.FileDownloadService;
import at.or.vogt.oe1downloader.download.HttpClientFactory;
import at.or.vogt.oe1downloader.download.StringDownloadHandler;
import at.or.vogt.oe1downloader.json.bean.Broadcast;
import at.or.vogt.oe1downloader.json.bean.Program;

/**
 * Test for {@link at.or.vogt.oe1downloader.json.JsonGetter}.
 */
class JsonGetterTest {

    /** Logger. */
    private final Logger logger = LoggerFactory.getLogger(JsonGetterTest.class);

    /**
     * Gets the Days
     * ({@link at.or.vogt.oe1downloader.json.JsonGetter#getProgram(String)}).
     * @throws Exception if an error occurs
     */
    @Test
    void testGetProgram() throws Exception {

        final String methodname = "testGetProgram(): ";
        logger.info("{}start", methodname);

        final DownloadService testDownloadService = new FileDownloadService(new HttpClientFactory());
        final JsonGetter dut = new JsonGetter(testDownloadService);

        final List<Program> result = dut.getProgram("src/test/resources/program/program_20200106.json");

        logger.info("{}result = {}", methodname, result);
        Assertions.assertNotNull(result);
        result.forEach(p -> p.getBroadcasts().forEach(b -> logger.info(" broadcast = {}", b.toString())));
        Assertions.assertEquals(1, result.size());
    }

    @Test
    void testGetBroadcast() {
        final String methodname = "testGetBroadcast(): ";
        logger.info("{}start", methodname);

        final DownloadService testDownloadService = new FileDownloadService(new HttpClientFactory());
        final JsonGetter dut = new JsonGetter(testDownloadService);

        final Broadcast result = dut.getBroadcast("src/test/resources/broadcast/broadcast_20200106.json");

        logger.info("{}result = {}", methodname, result);
        Assertions.assertNotNull(result);
    }

    @Test
    void testDumpJson() {
        final String methodname = "testDumpJson(): ";
        logger.info("{}start", methodname);

        final DownloadService testDownloadService = new FileDownloadService(new HttpClientFactory());
        final JsonGetter dut = new JsonGetter(testDownloadService);
        Configuration.setConfigFilename(TestParameters.TEST_CONFIG_FILENAME);

        final String json = "{\"test\": \"1\"}";

        dut.dumpJson(json, "test.json");
        Assertions.assertTrue(new File("logs/testdir/test.json").exists());
        new File("logs/testdir/test.json").delete();

    }

    @Test
    void testDumpRealJson() {
        final String methodname = "testDumpRealJson(): ";
        logger.info("{}start", methodname);

        final DownloadService testDownloadService = new DownloadService(new HttpClientFactory());
        Configuration.setConfigFilename(TestParameters.TEST_CONFIG_FILENAME);

        final JsonGetter dut = new JsonGetter(testDownloadService);
        final StringDownloadHandler handler = new StringDownloadHandler();
        final boolean successfulDownload = testDownloadService
                .download(Configuration.getConfiguration().getProperty(ConfigurationParameter.JSON_BROADCASTS_URL),
                        handler);
        if (!successfulDownload) {
            Assertions.fail("should be successful!");
        }

        final String json = handler.getResult();
        dut.dumpJson(json, "program.json");
    }

    @Test
    void testEscapeFilename() {
        final String methodname = "testEscapeFilename(): ";
        logger.info("{}start", methodname);

        final JsonGetter dut = new JsonGetter(null);

        Assertions.assertEquals("program.json", dut.escapeFilename("program.json"));
        Assertions
                .assertEquals("_oe1_api_json_4.0_broadcast_583255_20200104",
                        dut.escapeFilename("https://audioapi.orf.at/oe1/api/json/4.0/broadcast/583255/20200104"));

    }
}
