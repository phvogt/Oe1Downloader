package at.or.vogt.oe1downloader.download;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Arrays;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

import at.or.vogt.oe1downloader.json.DateParser;

/**
 * Test for downloading MP3.
 */
public class DownloadServiceTest {

    /** Logger. */
    private final Logger logger = LoggerFactory.getLogger(DownloadServiceTest.class);

    /**
     * Test of {@link DownloadService#download(String, DownloadHandler)}.
     */
    @Test
    public void testDownloadWithHandler() {

        final String methodname = "testDownloadWithHandler(): ";

        final DownloadService dut = new DownloadService(
                new FileHttpClientFactory("src/test/resources/tag/broadcast20170429.json"));
        final StringDownloadHandler handler = new StringDownloadHandler();
        final boolean result = dut.download("src/test/resources/tag/broadcast20170429.json", handler);
        Assertions.assertTrue(result);
        Assertions.assertNotNull(handler.getResult());
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
        Assertions.assertFalse(result);
        Assertions.assertEquals("", handler.getResult());
        logger.info(methodname + "result = {}", handler.getResult());
    }

    /**
     * Test of {@link DownloadService#download(RecordVO)}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    public void testDownloadWithRecord() throws Exception {

        final String methodname = "testDownloadWithRecord(): ";

        final DownloadService dut = new DownloadService(
                new FileHttpClientFactory("src/test/resources/showinfo/20170429.json"));
        final LocalDateTime scheduledStartLdt = DateParser.parseISO("2017-04-29T08:15:00+02:00");
        final RecordVO record = new RecordVO("20170429", "Pasticcio", "Mit den Händen kann ich es auch",
                "<p>mit Irene Suchy. \"An die Künstler, Dichter und Musiker. Damit wir uns nicht vor dem Firmament zu schämen haben, müssen wir uns endlich aufmachen und mithelfen, dass eine gerechte Ordnung in Staat und Gesellschaft eingesetzt werde.\" <br/>(Ludwig Meidner, 1919).</p>",
                scheduledStartLdt, "60", "mp3postfix", "src/test/resources/showinfo/20170429.json");
        final File tmpFile = File.createTempFile("test", ".mp3");
        FileUtils.deleteQuietly(tmpFile);
        final String targetFilename = tmpFile.getCanonicalPath();
        logger.info(methodname + "targetFilename = {}", targetFilename);
        final File targetFile = new File(targetFilename);
        record.setTargetFilename(targetFilename);

        dut.download(record);

        Assertions.assertTrue(targetFile.exists());
        FileUtils.deleteQuietly(targetFile);

    }

    /**
     * Test of {@link DownloadService#downloadRecords(String, java.util.List)}.
     *
     * @throws Exception if an error occurs
     */
    @Test
    public void testDownloadRecords() throws Exception {

        final String methodname = "testDownloadRecords(): ";

        final DownloadService dut = new DownloadService(
                new FileHttpClientFactory("src/test/resources/showinfo/20170429.json"));
        final LocalDateTime scheduledStartLdt = DateParser.parseISO("2017-04-29T08:15:00+02:00");
        final RecordVO record = new RecordVO("20170429", "Pasticcio", "Mit den Händen kann ich es auch",
                "<p>mit Irene Suchy. \"An die Künstler, Dichter und Musiker. Damit wir uns nicht vor dem Firmament zu schämen haben, müssen wir uns endlich aufmachen und mithelfen, dass eine gerechte Ordnung in Staat und Gesellschaft eingesetzt werde.\" <br/>(Ludwig Meidner, 1919).</p>",
                scheduledStartLdt, "60", "mp3postfix", "src/test/resources/showinfo/20170429.json");
        final File tmpFile = File.createTempFile("test", ".mp3");
        FileUtils.deleteQuietly(tmpFile);
        final String targetFilename = tmpFile.getName();
        record.setTargetFilename(targetFilename);
        logger.info(methodname + "targetFilename = {}", targetFilename);
        final File targetFile = new File(FileUtils.getTempDirectoryPath() + "/" + record.getFilename());
        FileUtils.deleteQuietly(targetFile);

        dut.downloadRecords(FileUtils.getTempDirectoryPath(), Arrays.asList(new RecordVO[] { record }));

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
    public void testDownloadJson() throws Exception {

        final String methodname = "testDownloadJson(): ";

        final DownloadService dut = new DownloadService(new HttpClientFactory());
        final StringDownloadHandler handler = new StringDownloadHandler();
        final boolean result = dut.download("https://audioapi.orf.at/oe1/api/json/current/broadcasts?_s=1511553601980",
                handler);
        Assertions.assertTrue(result);
        Assertions.assertNotNull(handler.getResult());
        final ObjectMapper mapper = new ObjectMapper();
        final Object json = mapper.readValue(handler.getResult(), Object.class);
        final String jsonPretty = new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(json);
        logger.info(methodname + "result = {}", jsonPretty);
        IOUtils.write(jsonPretty, new FileOutputStream(new File("program.json")), StandardCharsets.UTF_8);
    }

    @Test
    public void testSetMp3Tag() throws Exception {
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
    public void testDownloadRecordVONoBroadcast() throws Exception {
        final String methodname = "testDownloadRecordVONoBroadcast(): ";
        logger.info(methodname);

        final DownloadService dut = new DownloadService(
                new FileHttpClientFactory("src/test/resources/showinfo/20170430_no_Streams.json"));
        final LocalDateTime scheduledStartLdt = DateParser.parseISO("2017-04-29T08:15:00+02:00");
        final RecordVO record = new RecordVO("20170429", "Pasticcio", "Mit den Händen kann ich es auch",
                "<p>mit Irene Suchy. \"An die Künstler, Dichter und Musiker. Damit wir uns nicht vor dem Firmament zu schämen haben, müssen wir uns endlich aufmachen und mithelfen, dass eine gerechte Ordnung in Staat und Gesellschaft eingesetzt werde.\" <br/>(Ludwig Meidner, 1919).</p>",
                scheduledStartLdt, "60", "mp3postfix", "src/test/resources/showinfo/20170429.json");

        final boolean result = dut.download(record);

        Assertions.assertFalse(result);

    }

    @Test
    public void testDownloadRecordVONoShowInfo() throws Exception {
        final String methodname = "testDownloadRecordVONoShowInfo(): ";
        logger.info(methodname);

        final DownloadService dut = new DownloadService(
                new FileHttpClientFactory("src/test/resources/showinfo/20170430_no_ShowInfo.json"));
        final LocalDateTime scheduledStartLdt = DateParser.parseISO("2017-04-29T08:15:00+02:00");
        final RecordVO record = new RecordVO("20170429", "Pasticcio", "Mit den Händen kann ich es auch",
                "<p>mit Irene Suchy. \"An die Künstler, Dichter und Musiker. Damit wir uns nicht vor dem Firmament zu schämen haben, müssen wir uns endlich aufmachen und mithelfen, dass eine gerechte Ordnung in Staat und Gesellschaft eingesetzt werde.\" <br/>(Ludwig Meidner, 1919).</p>",
                scheduledStartLdt, "60", "mp3postfix", "src/test/resources/showinfo/20170429.json");

        final boolean result = dut.download(record);

        Assertions.assertFalse(result);

    }

}
