package at.or.vogt.oe1downloader.download;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Arrays;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

import at.or.vogt.oe1downloader.json.DateParser;

/**
 * Test for downloading MP3.
 */
class DownloadServiceTest {

    /** Logger. */
    private final Logger logger = LoggerFactory.getLogger(DownloadServiceTest.class);

    /**
     * Test of {@link DownloadService#download(String, DownloadHandler)}.
     */
    @Test
    void testDownloadWithHandler() {

        final String methodname = "testDownloadWithHandler(): ";
        logger.info("{}start", methodname);

        final DownloadService dut = new DownloadService(
                new FileHttpClientFactory("src/test/resources/broadcast/broadcast_20200106.json"));
        final StringDownloadHandler handler = new StringDownloadHandler();

        final boolean result = dut.download("src/test/resources/broadcast/broadcast_20200106.json", handler);

        Assertions.assertTrue(result);
        Assertions.assertNotNull(handler.getResult());
        logger.info("{}result = {}", methodname, handler.getResult());
    }

    /**
     * Test of {@link DownloadService#download(String, DownloadHandler)}.
     */
    @Test
    void testDownloadWithHandlerConnectionReset() {

        final String methodname = "testDownloadWithHandlerConnectionReset(): ";
        logger.info("{}start", methodname);

        final StringDownloadHandler handler = new StringDownloadHandler();
        final DownloadService dut = new DownloadService(
                new FileHttpClientFactoryConnectionReset("src/test/resources/broadcast/broadcast_20200106.json"));

        final boolean result = dut.download("src/test/resources/broadcast/broadcast_20200106.json", handler);

        Assertions.assertFalse(result);
        Assertions.assertEquals("", handler.getResult());
        logger.info("{}result = {}", methodname, handler.getResult());
    }

    /**
     * Test of {@link DownloadService#download(RecordVO)}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    void testDownloadWithRecord() throws Exception {

        final String methodname = "testDownloadWithRecord(): ";

        final DownloadService dut = new DownloadService(
                new FileHttpClientFactory("src/test/resources/broadcast/broadcast_20200106.json"));
        final LocalDateTime scheduledStartLdt = DateParser.parseISO("2017-04-29T08:15:00+02:00");
        final RecordVO recordVO = new RecordVO("20170429", "Pasticcio", "Mit den Händen kann ich es auch",
                "<p>mit Irene Suchy. \"An die Künstler, Dichter und Musiker. Damit wir uns nicht vor dem Firmament zu schämen haben, müssen wir uns endlich aufmachen und mithelfen, dass eine gerechte Ordnung in Staat und Gesellschaft eingesetzt werde.\" <br/>(Ludwig Meidner, 1919).</p>",
                scheduledStartLdt, "60", "mp3postfix", "src/test/resources/broadcast/broadcast_20200106.json");
        final File tmpFile = File.createTempFile("test", ".mp3");
        FileUtils.deleteQuietly(tmpFile);
        final String targetFilename = tmpFile.getCanonicalPath();
        logger.info("{}targetFilename = {}", methodname, targetFilename);
        final File targetFile = new File(targetFilename);
        recordVO.setTargetFilename(targetFilename);

        dut.download(recordVO);

        Assertions.assertTrue(targetFile.exists());
        FileUtils.deleteQuietly(targetFile);

    }

    /**
     * Test of {@link DownloadService#downloadRecords(String, java.util.List)}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    void testDownloadRecords() throws Exception {

        final String methodname = "testDownloadRecords(): ";

        final DownloadService dut = new DownloadService(
                new FileHttpClientFactory("src/test/resources/broadcast/broadcast_20200106.json"));
        final LocalDateTime scheduledStartLdt = DateParser.parseISO("2017-04-29T08:15:00+02:00");
        final RecordVO recordVO = new RecordVO("20170429", "Pasticcio", "Mit den Händen kann ich es auch",
                "<p>mit Irene Suchy. \"An die Künstler, Dichter und Musiker. Damit wir uns nicht vor dem Firmament zu schämen haben, müssen wir uns endlich aufmachen und mithelfen, dass eine gerechte Ordnung in Staat und Gesellschaft eingesetzt werde.\" <br/>(Ludwig Meidner, 1919).</p>",
                scheduledStartLdt, "60", "mp3postfix", "src/test/resources/broadcast/broadcast_20200106.json");
        final File tmpFile = File.createTempFile("test", ".mp3");
        FileUtils.deleteQuietly(tmpFile);
        final String targetFilename = tmpFile.getName();
        recordVO.setTargetFilename(targetFilename);
        logger.info("{}targetFilename = {}", methodname, targetFilename);
        final File targetFile = new File(FileUtils.getTempDirectoryPath() + "/" + recordVO.getFilename());
        FileUtils.deleteQuietly(targetFile);

        dut.downloadRecords(FileUtils.getTempDirectoryPath(), Arrays.asList(recordVO));

        Assertions.assertTrue(targetFile.exists());
        FileUtils.deleteQuietly(targetFile);
    }

    /**
     * Test of {@link DownloadService#download(String, DownloadHandler)}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    @Disabled
    void testDownloadJson() throws Exception {

        final String methodname = "testDownloadJson(): ";

        final DownloadService dut = new DownloadService(new HttpClientFactory());
        final StringDownloadHandler handler = new StringDownloadHandler();

        final boolean result = dut.download("https://audioapi.orf.at/oe1/json/4.0/broadcasts?_o=oe1.orf.at", handler);

        Assertions.assertTrue(result);
        Assertions.assertNotNull(handler.getResult());
        final ObjectMapper mapper = new ObjectMapper();
        final Object json = mapper.readValue(handler.getResult(), Object.class);
        final String jsonPretty = new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(json);
        logger.info("{}result = {}", methodname, jsonPretty);
        IOUtils.write(jsonPretty, new FileOutputStream(new File("program.json")), StandardCharsets.UTF_8);
    }

    @Test
    void testSetMp3Tag() throws Exception {
        final String methodname = "testSetMp3Tag(): ";
        logger.info(methodname);

        final DownloadService dut = new DownloadService(new HttpClientFactory());
        final RecordVO record = new RecordVO("20170429", "Pasticcio but now it is very long and with $#%",
                "Mit den Händen kann ich es auch",
                "<p>mit Irene Suchy. \"An die Künstler, Dichter und Musiker. Damit wir uns nicht vor dem Firmament zu schämen haben, müssen wir uns endlich aufmachen und mithelfen, dass eine gerechte Ordnung in Staat und Gesellschaft eingesetzt werde.\" <br/>(Ludwig Meidner, 1919).</p>",
                DateParser.parseISO("2017-04-29T08:15:00+02:00"), "41", "matrix", "http://oe1.orf.at/pasticcio");

        final String tmpFilename = FileUtils.getTempDirectoryPath() + File.separator + "testSetMp3.mp3";
        final File tmpMp3File = new File(tmpFilename);
        FileUtils.deleteQuietly(tmpMp3File);
        FileUtils.copyFile(new File("src/test/resources/test.mp3"), tmpMp3File);
        record.setTargetFilename(tmpFilename);

        dut.setMp3Tag(record);

    }

    @Test
    void testDownloadRecordVONoStreams() {
        final String methodname = "testDownloadRecordVONoStreams(): ";
        logger.info(methodname);

        final DownloadService dut = new DownloadService(
                new FileHttpClientFactory("src/test/resources/broadcast/broadcast_20200107_no_streams.json"));
        final LocalDateTime scheduledStartLdt = DateParser.parseISO("2017-04-29T08:15:00+02:00");
        final RecordVO recordVO = new RecordVO("20170429", "Pasticcio", "Mit den Händen kann ich es auch",
                "<p>mit Irene Suchy. \"An die Künstler, Dichter und Musiker. Damit wir uns nicht vor dem Firmament zu schämen haben, müssen wir uns endlich aufmachen und mithelfen, dass eine gerechte Ordnung in Staat und Gesellschaft eingesetzt werde.\" <br/>(Ludwig Meidner, 1919).</p>",
                scheduledStartLdt, "60", "mp3postfix",
                "src/test/resources/broadcast/broadcast_20200107_no_streams.json");

        final boolean result = dut.download(recordVO);

        Assertions.assertFalse(result);

    }

    @Test
    void testDownloadRecordVONoBroadcast() {
        final String methodname = "testDownloadRecordVONoBroadcast(): ";
        logger.info(methodname);

        final DownloadService dut = new DownloadService(
                new FileHttpClientFactory("src/test/resources/broadcast/broadcast_20200107_no_broadcast.json"));
        final LocalDateTime scheduledStartLdt = DateParser.parseISO("2017-04-29T08:15:00+02:00");
        final RecordVO recordVO = new RecordVO("20170429", "Pasticcio", "Mit den Händen kann ich es auch",
                "<p>mit Irene Suchy. \"An die Künstler, Dichter und Musiker. Damit wir uns nicht vor dem Firmament zu schämen haben, müssen wir uns endlich aufmachen und mithelfen, dass eine gerechte Ordnung in Staat und Gesellschaft eingesetzt werde.\" <br/>(Ludwig Meidner, 1919).</p>",
                scheduledStartLdt, "60", "mp3postfix",
                "src/test/resources/broadcast/broadcast_20200107_no_broadcast.json");

        final boolean result = dut.download(recordVO);

        Assertions.assertFalse(result);

    }

    @Test
    void testPercentageForSuccessReached() {
        final String methodname = "testPercentageForSuccessReached(): ";
        logger.info("{}start", methodname);

        final DownloadService dut = new DownloadService(new HttpClientFactory());

        final boolean result = dut.percentageForSuccessReached(new DownloadHandler() {

            @Override
            public boolean successful() {
                return false;
            }

            @Override
            public void setContentLength(final long length) {
            }

            @Override
            public void handleDownload(final InputStream input) {
            }

            @Override
            public long getContentLength() {
                return 5000;
            }

            @Override
            public long getBytesDownloaded() {
                return 4990;
            }
        });
        logger.info("{}dut = {}", methodname, dut);
        Assertions.assertTrue(result);
    }

    @Test
    void testDebugHeaders() {
        final String methodname = "testDebugHeaders(): ";
        logger.info("{}start", methodname);

        final DownloadService dut = new DownloadService(new HttpClientFactory());
        final CloseableHttpResponse response = Mockito.mock(CloseableHttpResponse.class);
        final HttpEntity entity = Mockito.mock(HttpEntity.class);

        final Header[] headers = new Header[1];
        headers[0] = Mockito.mock(Header.class);
        Mockito.when(response.getAllHeaders()).thenReturn(headers);

        dut.debugHeaders(response, entity);

    }

}
