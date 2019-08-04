package at.or.vogt.oe1downloader.json.bean;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import at.or.vogt.oe1downloader.download.DownloadService;
import at.or.vogt.oe1downloader.download.FileDownloadService;
import at.or.vogt.oe1downloader.download.HttpClientFactory;
import at.or.vogt.oe1downloader.json.DateParser;
import at.or.vogt.oe1downloader.json.JsonGetter;

public class ShowTest {

    /** Logger. */
    private final Logger logger = LoggerFactory.getLogger(ShowTest.class);

    @Test
    public void testForDayNoBroadcasts() throws Exception {
        final String methodname = "testForDayNoBroadcasts(): ";
        logger.info(methodname);

        final DownloadService testDownloadService = new FileDownloadService(new HttpClientFactory());
        final JsonGetter jsonGetter = new JsonGetter(testDownloadService);

        final long dayOffset = Duration
                .between(DateParser.parseISO("2017-04-29T08:15:00+02:00").truncatedTo(ChronoUnit.DAYS),
                        LocalDateTime.now().truncatedTo(ChronoUnit.DAYS))
                .toDays();

        final List<Day> days = jsonGetter.getDays("src/test/resources/tag/broadcast_none.json", dayOffset + 7);

        final List<Show> result = Show.forDay(days.get(0));
        Assertions.assertNotNull(result);
    }

}
