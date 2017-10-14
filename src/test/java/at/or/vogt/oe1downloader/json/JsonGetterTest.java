package at.or.vogt.oe1downloader.json;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

        final long dayOffset = Duration.between(DateParser.parseISO("2017-04-29T08:15:00+02:00").truncatedTo(ChronoUnit.DAYS),
                LocalDateTime.now().truncatedTo(ChronoUnit.DAYS)).toDays();

        final List<Day> result = dut.getDays("src/test/resources/tag/broadcast20170429.json", dayOffset + 2);
        Assert.assertNotNull(result);
        logger.info(methodname + "result = {}", result);
        result.forEach(
                day -> day.getBroadcasts().forEach(b -> logger.info("day = " + day.getDateISO() + " broadcast = " + b.toString())));

        Assert.assertEquals(3, result.size());

        final List<Show> shows1 = Show.forDay(result.get(0));
        Assert.assertEquals(53, shows1.size());
        shows1.forEach(s -> logger.info(methodname + "  shows1 = {}", s));
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
        Assert.assertNotNull(result);
        logger.info(methodname + "result = {}", result);

        final List<Stream> streams = result.getStreams();
        logger.info(methodname + "streams = {}", streams);
        Assert.assertNotNull(streams);
        Assert.assertEquals(1, streams.size());

        final String streamId = streams.get(0).getLoopStreamId();
        logger.info(methodname + "streamId = {}", streamId);
    }

}
