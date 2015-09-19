// (c) 2015 by Philipp Vogt
package at.or.vogt.oe1downloader.json;

import java.io.FileReader;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import at.or.vogt.oe1downloader.download.DownloadService;
import at.or.vogt.oe1downloader.download.FileDownloadService;
import at.or.vogt.oe1downloader.download.HttpClientFactory;

/**
 * Test for {@link at.or.vogt.oe1downloader.json.JsonGetter}.
 */
public class JsonGetterTest {

    static {
        PropertyConfigurator.configure("src/test/resources/log4j.properties");
    }

    /** Logger. */
    private final Logger logger = LoggerFactory.getLogger(JsonGetterTest.class);

    /**
     * Parses the JSON.
     * @throws Exception if an error occurs
     */
    @Test
    public void testParseJson() throws Exception {

        final String methodname = "testParseJson(): ";
        logger.info(methodname + "start");

        final DownloadService downloadService = new DownloadService(new HttpClientFactory());

        final JsonGetter dut = new JsonGetter(downloadService);

        final String jsonString = IOUtils.toString(new FileReader("src/test/resources/tag/20150810.json"));
        final Day result = dut.parseJson(jsonString);
        Assert.assertNotNull(result);
        logger.info(methodname + "result = {}", result.toStringShort());

        Assert.assertNotNull(result.getShows());
        Assert.assertEquals(9, result.getShows().size());

        final Show show = result.getShows().get(2);
        logger.info(methodname + "show = {}", show);
        Assert.assertEquals("Radiokolleg", show.getShortTitle());

    }

    /**
     * Gets the Day.
     */
    @Test
    public void testGetDay() {

        final String methodname = "testGetDay(): ";
        logger.info(methodname + "start");

        final DownloadService testDownloadService = new FileDownloadService(new HttpClientFactory());
        final JsonGetter dut = new JsonGetter(testDownloadService);
        final Day result = dut.getDay("src/test/resources/tag/20150810.json");
        logger.info(methodname + "result = {}", result.toStringShort());

        Assert.assertNotNull(result);
        logger.info(methodname + "result = {}", result.toStringShort());

        Assert.assertNotNull(result.getShows());
        Assert.assertEquals(9, result.getShows().size());

        final Show show = result.getShows().get(2);
        logger.info(methodname + "show = {}", show);
        Assert.assertEquals("Radiokolleg", show.getShortTitle());
    }

    /**
     * Gets the Days ({@link JsonGetter#getDays(List)}).
     */
    @Test
    public void testGetDays() {

        final String methodname = "testGetDays(): ";
        logger.info(methodname + "start");

        final DownloadService testDownloadService = new FileDownloadService(new HttpClientFactory());
        final JsonGetter dut = new JsonGetter(testDownloadService);

        final List<Day> result = dut.getDays(
                Arrays.asList(new String[] { "src/test/resources/tag/20150810.json", "src/test/resources/tag_error/error.json" }));
        Assert.assertNotNull(result);
        logger.info(methodname + "result = {}", result);
        Assert.assertEquals(1, result.size());

    }
}
