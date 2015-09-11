// (c) 2015 by Philipp Vogt
package at.or.vogt.oe1downloader;

import java.io.FileReader;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import at.or.vogt.oe1downloader.json.Day;

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
     * Tests the rules.
     * @throws Exception if an error occurs
     */
    @Test
    public void testRules() throws Exception {

        final Configuration config = new Configuration();

        final Map<String, String> parsedRules = config.getPropertyMap(ConfigurationParameter.RULES);

        final RulesVO dut = new RulesVO();

        final Map<String, Map<String, String>> rules = dut.parseMap(parsedRules);

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

    /**
     * Tests the Matching
     * @throws Exception if an error occurs
     */
    @Test
    public void testMatching() throws Exception {

        final String methodname = "testMatching(): ";

        final JsonGetter jsonGetter = new JsonGetter();
        final String jsonString = IOUtils.toString(new FileReader("src/test/resources/tag/20150811.json"));
        final Day day = jsonGetter.parseJson(jsonString);

        final RulesVO dut = new RulesVO();
        dut.loadRules();
        final RuleIndexCounter counter = new RuleIndexCounter();
        final List<RecordVO> records = dut.checkForRecords(day, counter);
        for (final RecordVO recordVO : records) {
            logger.info(methodname + "recordVO = {}", recordVO);
        }
    }

}
