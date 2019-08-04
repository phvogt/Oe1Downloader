package at.or.vogt.oe1downloader.json;

import java.io.File;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import at.or.vogt.oe1downloader.config.Configuration;
import at.or.vogt.oe1downloader.download.DownloadService;
import at.or.vogt.oe1downloader.download.FileDownloadService;
import at.or.vogt.oe1downloader.download.HttpClientFactory;
import at.or.vogt.oe1downloader.json.bean.Day;
import at.or.vogt.oe1downloader.json.bean.Show;
import at.or.vogt.oe1downloader.json.bean.ShowInfo;
import at.or.vogt.oe1downloader.json.bean.Stream;

/**
 * Test for {@link at.or.vogt.oe1downloader.json.JsonGetter}.
 */
public class JsonGetterTest {

    /** Logger. */
    private final Logger logger = LoggerFactory.getLogger(JsonGetterTest.class);

    /**
     * Gets the Days
     * ({@link at.or.vogt.oe1downloader.json.JsonGetter#getDays(String, long)}).
     * @throws Exception if an error occurs
     */
    @Test
    public void testGetDays() throws Exception {

        final String methodname = "testGetDays(): ";
        logger.info(methodname + "start");

        final DownloadService testDownloadService = new FileDownloadService(new HttpClientFactory());
        final JsonGetter dut = new JsonGetter(testDownloadService);

        final long dayOffset = Duration
                .between(DateParser.parseISO("2017-04-29T08:15:00+02:00").truncatedTo(ChronoUnit.DAYS),
                        LocalDateTime.now().truncatedTo(ChronoUnit.DAYS))
                .toDays();

        final List<Day> result = dut.getDays("src/test/resources/tag/broadcast20170429.json", dayOffset + 2);
        Assertions.assertNotNull(result);
        logger.info(methodname + "result = {}", result);
        result.forEach(day -> day.getBroadcasts()
                .forEach(b -> logger.info("day = " + day.getDateISO() + " broadcast = " + b.toString())));

        Assertions.assertEquals(3, result.size());

        final List<Show> shows1 = Show.forDay(result.get(0));
        Assertions.assertEquals(53, shows1.size());
        shows1.forEach(s -> logger.info(methodname + "  shows1 = {}", s));
    }

    /**
     * Gets the Days
     * ({@link at.or.vogt.oe1downloader.json.JsonGetter#getDays(String, long)}) and
     * dump the JSON.
     * @throws Exception if an error occurs
     */
    @Test
    public void testGetDaysDumpJSON() throws Exception {

        final String methodname = "testGetDaysDumpJSON(): ";
        logger.info(methodname + "start");

        final String defaultConfigFilename = Configuration.gtConfigFilename();
        try {
            Configuration.setConfigFilename("src/test/resources/config.properties");
            final String jsonFilename = "logs/testdir/program.json";
            final File jsonFile = new File(jsonFilename);
            jsonFile.delete();
            new File(FilenameUtils.getPath(jsonFilename)).delete();

            final DownloadService testDownloadService = new FileDownloadService(new HttpClientFactory());
            final JsonGetter dut = new JsonGetter(testDownloadService);

            final long dayOffset = Duration
                    .between(DateParser.parseISO("2017-04-29T08:15:00+02:00").truncatedTo(ChronoUnit.DAYS),
                            LocalDateTime.now().truncatedTo(ChronoUnit.DAYS))
                    .toDays();

            final List<Day> result = dut.getDays("src/test/resources/tag/broadcast20170429.json", dayOffset + 2);
            Assertions.assertNotNull(result);
            logger.info(methodname + "result = {}", result);
            result.forEach(day -> day.getBroadcasts()
                    .forEach(b -> logger.info("day = " + day.getDateISO() + " broadcast = " + b.toString())));

            Assertions.assertEquals(3, result.size());

            Assertions.assertTrue(jsonFile.exists());
            Assertions.assertTrue(jsonFile.isFile());

        } finally {
            Configuration.setConfigFilename(defaultConfigFilename);
        }

    }

    /**
     * Gets the Days
     * ({@link at.or.vogt.oe1downloader.json.JsonGetter#getDays(String, long)}) and
     * dump the JSON but it is not set.
     * @throws Exception if an error occurs
     */
    @Test
    public void testGetDaysDumpJSONNotSet() throws Exception {

        final String methodname = "testGetDaysDumpJSONNotSet(): ";
        logger.info(methodname + "start");

        final String defaultConfigFilename = Configuration.gtConfigFilename();
        try {
            Configuration.setConfigFilename("src/test/resources/configEmpty.properties");

            final DownloadService testDownloadService = new FileDownloadService(new HttpClientFactory());
            final JsonGetter dut = new JsonGetter(testDownloadService);

            final long dayOffset = Duration
                    .between(DateParser.parseISO("2017-04-29T08:15:00+02:00").truncatedTo(ChronoUnit.DAYS),
                            LocalDateTime.now().truncatedTo(ChronoUnit.DAYS))
                    .toDays();

            final List<Day> result = dut.getDays("src/test/resources/tag/broadcast20170429.json", dayOffset + 2);
            Assertions.assertNotNull(result);
            logger.info(methodname + "result = {}", result);
            result.forEach(day -> day.getBroadcasts()
                    .forEach(b -> logger.info("day = " + day.getDateISO() + " broadcast = " + b.toString())));

            Assertions.assertEquals(3, result.size());

        } finally {
            Configuration.setConfigFilename(defaultConfigFilename);
        }

    }

    /**
     * Gets the ShowInfo
     * ({@link at.or.vogt.oe1downloader.json.JsonGetter#getShowInfo(String)}).
     * @throws Exception if an error occurs
     */
    @Test
    public void testGetShowInfo() throws Exception {

        final String methodname = "testGetShowInfo(): ";
        logger.info(methodname + "start");

        final DownloadService testDownloadService = new FileDownloadService(new HttpClientFactory());
        final JsonGetter dut = new JsonGetter(testDownloadService);
        final ShowInfo result = dut.getShowInfo("src/test/resources/showinfo/20170429.json");
        Assertions.assertNotNull(result);
        logger.info(methodname + "result = {}", result);

        final List<Stream> streams = result.getStreams();
        logger.info(methodname + "streams = {}", streams);
        Assertions.assertNotNull(streams);
        Assertions.assertEquals(1, streams.size());

        final String streamId = streams.get(0).getLoopStreamId();
        logger.info(methodname + "streamId = {}", streamId);
    }

}
