// (c) 2015 by Philipp Vogt
package at.or.vogt.oe1downloader;

import java.io.FileReader;
import java.text.SimpleDateFormat;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.PropertyConfigurator;
import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import at.or.vogt.oe1downloader.json.Day;

/**
 * Test for {@link at.or.vogt.oe1downloader.JsonGetter}.
 */
public class JsonGetterTest {

    static {
        PropertyConfigurator.configure("conf/log4j.properties");
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

        final JsonGetter dut = new JsonGetter();

        final String jsonString = IOUtils.toString(new FileReader("src/test/resources/tag/20150810.json"));
        final Day result = dut.parseJson(jsonString);
        Assert.assertNotNull(result);
        logger.info(methodname + "result = {}", result.toStringShort());
    }

    /**
     * Test to download from URL.
     * @throws Exception if an error occurs.
     */
    // @Test
    public void testDownloadContentFromUrl() throws Exception {

        final String methodname = "testDownloadContentFromUrl(): ";
        logger.info(methodname + "start");

        final JsonGetter dut = new JsonGetter();
        final String date = getTestDateString();
        final String result = dut.downloadContentFromUrl("http://oe1.orf.at/programm/konsole/tag/" + date);
        logger.info(methodname + "result = {}", result);
    }

    /**
     * Gets the Day.
     * @throws Exception if an error occurs
     */
    // @Test
    public void testGetDay() throws Exception {

        final String methodname = "testGetDay(): ";
        logger.info(methodname + "start");

        final JsonGetter dut = new JsonGetter();
        final String date = getTestDateString();
        final Day result = dut.getDay("http://oe1.orf.at/programm/konsole/tag/" + date);
        logger.info(methodname + "result = {}", result.toStringShort());
    }

    /**
     * Gets the yesterday.
     * @throws Exception if an error occurs
     */
    @Test
    public void testGetYesterday() throws Exception {

        final String methodname = "testGetYesterday(): ";
        logger.info(methodname + "start");

        final String result = getTestDateString();
        logger.info(methodname + "result = {}", result);
    }

    /**
     * Gets the test date (yesterday).
     * @return the test date
     */
    private String getTestDateString() {
        final DateTime yesterday = new DateTime().minusDays(1);
        final String result = new SimpleDateFormat("yyyyMMdd").format(yesterday.toDate());
        return result;
    }

}
