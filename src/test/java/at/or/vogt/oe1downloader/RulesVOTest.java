// (c) 2015 by Philipp Vogt
package at.or.vogt.oe1downloader;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.PropertyConfigurator;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import at.or.vogt.oe1downloader.config.Configuration;
import at.or.vogt.oe1downloader.config.ConfigurationHelper;
import at.or.vogt.oe1downloader.config.ConfigurationParameter;
import at.or.vogt.oe1downloader.download.DownloadService;
import at.or.vogt.oe1downloader.download.FileDownloadService;
import at.or.vogt.oe1downloader.download.HttpClientFactory;
import at.or.vogt.oe1downloader.json.Day;
import at.or.vogt.oe1downloader.json.JsonGetter;

/**
 * Tests the {@link RulesVO}.
 */
public class RulesVOTest {

    static {
        PropertyConfigurator.configure("src/test/resources/log4j.properties");
    }

    /** Logger. */
    private final Logger logger = LoggerFactory.getLogger(RulesVOTest.class);

    /**
     * Tests the rules.
     */
    @Test
    public void testRules() {

        final Configuration config = ConfigurationHelper.getTestConfiguration();

        final Map<String, String> parsedRules = config.getPropertyMap(ConfigurationParameter.RULES);

        final RulesVO dut = new RulesVO();
        final Map<String, Map<String, String>> rules = dut.parseMap(parsedRules);
        Assert.assertNotNull(rules);

        final String methodname = "logRules(): ";
        final Set<String> rulesKeys = rules.keySet();
        Assert.assertNotNull(rulesKeys);
        Assert.assertTrue(rulesKeys.size() > 0);

        final Map<String, String> rule01 = rules.get("01");
        Assert.assertNotNull(rule01);
        final String rule01ShortTitle = rule01.get("shortTitle");
        Assert.assertNotNull(rule01ShortTitle);
        Assert.assertEquals("Vom Leben der Natur", rule01ShortTitle);

        for (final String rulesKey : rulesKeys) {
            logger.info(methodname + "rule = {}", rulesKey);
            final Map<String, String> rulesMap = rules.get(rulesKey);
            final Set<String> ruleNames = rulesMap.keySet();
            for (final String ruleName : ruleNames) {
                final String ruleValue = rulesMap.get(ruleName);
                logger.info(methodname + "  {} = {}", ruleName, ruleValue);
            }
        }
    }

    /**
     * Tests the Matching
     */
    @Test
    public void testMatching() {

        final String methodname = "testMatching(): ";

        final DownloadService downloadService = new FileDownloadService(new HttpClientFactory());

        final JsonGetter jsonGetter = new JsonGetter(downloadService);

        final List<Day> days = jsonGetter.getDays(Arrays.asList("src/test/resources/tag/20150811.json"));
        final Day day = days.get(0);

        final RulesVO dut = new RulesVO();
        dut.loadRules();
        final RuleIndexCounter counter = new RuleIndexCounter();
        final List<RecordVO> records = dut.checkForRecords(Arrays.asList(new Day[] { day }), counter);
        for (final RecordVO recordVO : records) {
            logger.info(methodname + "recordVO = {}", recordVO);
        }
    }

}
