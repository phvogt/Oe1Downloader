// (c) 2015 by Philipp Vogt
package at.or.vogt.oe1downloader;

import java.io.FileReader;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import at.or.vogt.oe1downloader.json.Tag;

/**
 * Tests the {@link RulesVO}.
 */
public class RulesVOTest {

    static {
        PropertyConfigurator.configure("conf/log4j.properties");
    }

    /** Logger. */
    private final Logger logger = LoggerFactory.getLogger(RulesVOTest.class);

    /**
     * Test the RulesVO.
     * @throws Exception if an error occurs
     */
    @Test
    public void testRulesVO() throws Exception {

        final String methodname = "testRulesVO(): ";

        final RulesVO dut = new RulesVO();
        logger.info(methodname + "loading properties");
        final Properties props = dut.loadProperties();
        logProperties(props);
        logger.info(methodname + "parsing rules");
        final Map<String, String> rulesMap = dut.parseRules(props);
        logMap(rulesMap);
        logger.info(methodname + "parsing map");
        final Map<String, Map<String, String>> rules = dut.parseMap(rulesMap);
        logRules(rules);
        final List<RuleVO> rulesList = dut.convert(rules);
        for (final RuleVO ruleVO : rulesList) {
            logger.info(methodname + "ruleVO = {}", ruleVO);
        }
    }

    /**
     * Tests the Matching
     * @throws Exception
     */
    @Test
    public void testMatching() throws Exception {

        final String methodname = "testMatching(): ";

        final JsonGetter jsonGetter = new JsonGetter();
        final String jsonString = IOUtils.toString(new FileReader("src/test/resources/tag/20150811.json"));
        final Tag tag = jsonGetter.parseJson(jsonString);

        final RulesVO dut = new RulesVO();
        dut.loadRules();
        final List<RecordVO> records = dut.checkForRecords(tag);
        for (final RecordVO recordVO : records) {
            logger.info(methodname + "recordVO = {}", recordVO);
        }
    }

    /**
     * Log the properties.
     * @param props properties
     */
    private void logProperties(final Properties props) {

        final String methodname = "logProperties(): ";

        final Set<Object> keys = props.keySet();
        for (final Object key : keys) {
            final Object value = props.get(key);
            logger.info(methodname + "key = {} / value = {}", key, value);
        }
    }

    /**
     * Logs the rules map.
     * @param rulesMap map with rules
     */
    private void logMap(final Map<String, String> rulesMap) {

        final String methodname = "logMap(): ";

        final Set<String> keys = rulesMap.keySet();
        for (final String key : keys) {
            final String value = rulesMap.get(key);
            logger.info(methodname + "key = {} / value = {}", key, value);
        }
    }

    /**
     * Log the rules.
     * @param rules rules to log
     */
    private void logRules(final Map<String, Map<String, String>> rules) {

        final String methodname = "logRules(): ";
        final Set<String> rulesKeys = rules.keySet();
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
}
