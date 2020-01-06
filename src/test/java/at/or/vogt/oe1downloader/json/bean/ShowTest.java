package at.or.vogt.oe1downloader.json.bean;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import at.or.vogt.oe1downloader.download.DownloadService;
import at.or.vogt.oe1downloader.download.FileDownloadService;
import at.or.vogt.oe1downloader.download.HttpClientFactory;
import at.or.vogt.oe1downloader.json.JsonGetter;

public class ShowTest {

    /** Logger. */
    private final Logger logger = LoggerFactory.getLogger(ShowTest.class);

    @Test
    public void testShowBroadcast() throws Exception {
        final String methodname = "testShowBroadcast(): ";
        logger.info(methodname);

        final DownloadService testDownloadService = new FileDownloadService(new HttpClientFactory());
        final JsonGetter jsonGetter = new JsonGetter(testDownloadService);

        final List<Program> programs = jsonGetter.getProgram("src/test/resources/program/program_20200106.json");
        final Show show = new Show(programs.get(0).getBroadcasts().get(0));
        logger.info("{}show = {}", methodname, show);

        Assertions.assertEquals(92336L, show.getId());

    }

}
