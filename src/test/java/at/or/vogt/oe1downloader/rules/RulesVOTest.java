package at.or.vogt.oe1downloader.rules;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import at.or.vogt.oe1downloader.TestParameters;
import at.or.vogt.oe1downloader.config.Configuration;
import at.or.vogt.oe1downloader.config.ConfigurationHelper;
import at.or.vogt.oe1downloader.config.ConfigurationParameter;
import at.or.vogt.oe1downloader.download.DownloadService;
import at.or.vogt.oe1downloader.download.FileDownloadService;
import at.or.vogt.oe1downloader.download.HttpClientFactory;
import at.or.vogt.oe1downloader.download.RecordVO;
import at.or.vogt.oe1downloader.json.JsonGetter;
import at.or.vogt.oe1downloader.json.bean.Program;
import at.or.vogt.oe1downloader.json.bean.Show;

/**
 * Tests the {@link RulesVO}.
 */
class RulesVOTest {

    /** Logger. */
    private final Logger logger = LoggerFactory.getLogger(RulesVOTest.class);

    @BeforeEach
    public void before() {
        Configuration.setConfigFilename(TestParameters.TEST_CONFIG_FILENAME);
    }

    /**
     * Tests the rules.
     */
    @Test
    void testRules() {

        final Configuration config = ConfigurationHelper.getTestConfiguration();

        final Map<String, String> parsedRules = config.getPropertyMap(ConfigurationParameter.RULES);

        final RulesVO dut = new RulesVO();
        final Map<String, Map<String, String>> rules = dut.parseMap(parsedRules);
        Assertions.assertNotNull(rules);

        final String methodname = "logRules(): ";
        final Set<String> rulesKeys = rules.keySet();
        Assertions.assertNotNull(rulesKeys);
        Assertions.assertTrue(rulesKeys.size() > 0);

        final Map<String, String> rule01 = rules.get("01");
        Assertions.assertNotNull(rule01);
        final String rule01ShortTitle = rule01.get("shortTitle");
        Assertions.assertNotNull(rule01ShortTitle);
        Assertions.assertEquals("Vom Leben der Natur", rule01ShortTitle);

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
    void testMatching() {

        final String methodname = "testMatching(): ";

        final DownloadService downloadService = new FileDownloadService(new HttpClientFactory());

        final JsonGetter jsonGetter = new JsonGetter(downloadService);

        final List<Program> programs = jsonGetter.getProgram("src/test/resources/program/program_20200106.json");

        final RulesVO dut = new RulesVO();
        dut.loadRules();
        final RuleIndexCounter counter = new RuleIndexCounter();
        final List<RecordVO> records = dut.checkForRecords(programs, counter);
        for (final RecordVO recordVO : records) {
            logger.info("{}recordVO = {}", methodname, recordVO);
        }
        Assertions.assertEquals(1, records.size());
    }

    /**
     * Tests {@link at.or.vogt.oe1downloader.rules.RulesVO#matches(RuleVO, Show)}.
     */
    @Test
    void testMatches() {

        final LocalDateTime now = LocalDateTime.now();
        final String time = now.format(DateTimeFormatter.ofPattern("HH:mm"));

        final RuleVO rule = new RuleVO("name", "title", time, 60, "mp3postfix");

        // Show matches title
        final Show show = new Show(15, "href", "yyy", "title", "day", "subtitle", now, now, now, now, now);
        Assertions.assertTrue(RulesVO.matches(rule, show));

        // Show matches programTitle
        final Show showTitle = new Show(15, "href", "title", "zzz", "day", "subtitle", now, now, now, now, now);
        Assertions.assertTrue(RulesVO.matches(rule, showTitle));

        // Show does not match because of title
        final Show showNotTitle = new Show(15, "href", "yyy", "xxx", "day", "subtitle", now, now, now, now, now);
        Assertions.assertFalse(RulesVO.matches(rule, showNotTitle));

        // Show does not match because of time
        final Show showNotTime = new Show(15, "href", "yyy", "xxx", "day", "subtitle", now, now, now, now, now);
        Assertions.assertFalse(RulesVO.matches(rule, showNotTime));

    }

    @Test
    void testCheckForRecordsDayRuleIndexCounterNoBroadcasts() {

        final String methodname = "testCheckForRecordsDayRuleIndexCounterNoBroadcasts(): ";
        logger.info(methodname);

        final DownloadService downloadService = new FileDownloadService(new HttpClientFactory());

        final JsonGetter jsonGetter = new JsonGetter(downloadService);

        final List<Program> programs = jsonGetter.getProgram("src/test/resources/program/program_none.json");

        final RulesVO dut = new RulesVO();
        dut.loadRules();
        final RuleIndexCounter counter = new RuleIndexCounter();
        final List<RecordVO> records = dut.checkForRecords(programs, counter);
        Assertions.assertNotNull(records);
        Assertions.assertEquals(0, records.size());

    }

}
