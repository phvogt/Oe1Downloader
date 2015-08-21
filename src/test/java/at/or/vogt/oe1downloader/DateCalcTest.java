// (c) 2015 by Philipp Vogt
package at.or.vogt.oe1downloader;

import java.io.FileReader;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import at.or.vogt.oe1downloader.json.Tag;

/**
 * Test for {@link DateCalc}
 */
public class DateCalcTest {

    static {
        PropertyConfigurator.configure("conf/log4j.properties");
    }

    /** Logger. */
    private final Logger logger = LoggerFactory.getLogger(DateCalcTest.class);

    /**
     * Test the RulesVO.
     * @throws Exception if an error occurs
     */
    @Test
    public void testGetJsonUrls() throws Exception {

        final String methodname = "testRulesVO(): ";

        final DateCalc dut = new DateCalc();
        final List<String> urls = dut.getJsonUrls("src/test/resources/tag/");
        for (final String url : urls) {
            logger.info(methodname + "url = {}", url);
        }
    }

    /**
     * Test the RulesVO.
     * @throws Exception if an error occurs
     */
    @Test
    public void testRules() throws Exception {

        final String methodname = "testRulesVO(): ";

        final DateCalc dateCalc = new DateCalc();
        final List<String> urls = dateCalc.getJsonUrls("src/test/resources/tag/");
        for (final String url : urls) {
            logger.info(methodname + "url = {}", url);

            final JsonGetter jsonGetter = new JsonGetter();
            final String jsonString = IOUtils.toString(new FileReader(url + ".json"));
            final Tag tag = jsonGetter.parseJson(jsonString);

            final RulesVO dut = new RulesVO();
            dut.loadRules();
            final List<RecordVO> records = dut.checkForRecords(tag);
            for (final RecordVO recordVO : records) {
                logger.info(methodname + "recordVO = {}", recordVO);
            }

        }
    }

}
