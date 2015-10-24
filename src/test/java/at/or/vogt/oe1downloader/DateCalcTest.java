package at.or.vogt.oe1downloader;

import java.io.File;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import at.or.vogt.oe1downloader.download.DownloadService;
import at.or.vogt.oe1downloader.download.FileDownloadService;
import at.or.vogt.oe1downloader.download.HttpClientFactory;
import at.or.vogt.oe1downloader.json.Day;
import at.or.vogt.oe1downloader.json.JsonGetter;

/**
 * Test for {@link DateCalc}
 */
public class DateCalcTest {

    static {
        PropertyConfigurator.configure("src/test/resources/log4j.properties");
    }

    /** Logger. */
    private final Logger logger = LoggerFactory.getLogger(DateCalcTest.class);

    /**
     * Test the RulesVO.
     */
    @Test
    public void testGetJsonUrls() {

        final String methodname = "testRulesVO(): ";

        final DateCalc dut = new DateCalc();
        final List<String> urls = dut.getJsonUrls("src/test/resources/tag/");
        for (final String url : urls) {
            logger.info(methodname + "url = {}", url);
        }
    }

    /**
     * Test the RulesVO.
     */
    @Test
    public void testRules() {

        final String methodname = "testRulesVO(): ";

        final Collection<File> jsonFiles = FileUtils.listFiles(new File("src/test/resources/tag"), new String[] { "json" }, false);
        for (final File jsonFile : jsonFiles) {
            logger.info(methodname + "jsonFile = {}", jsonFile);

            final DownloadService downloadService = new FileDownloadService(new HttpClientFactory());
            final JsonGetter jsonGetter = new JsonGetter(downloadService);
            final List<Day> days = jsonGetter.getDays(Arrays.asList(jsonFile.getAbsolutePath()));
            final Day day = days.get(0);

            final RulesVO dut = new RulesVO();
            dut.loadRules();
            final RuleIndexCounter counter = new RuleIndexCounter();
            final List<RecordVO> records = dut.checkForRecords(day, counter);
            for (final RecordVO recordVO : records) {
                logger.info(methodname + "recordVO = {}", recordVO);
            }

        }
    }

}
