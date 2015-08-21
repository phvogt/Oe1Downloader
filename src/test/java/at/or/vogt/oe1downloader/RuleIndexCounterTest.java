// (c) 2015 by Philipp Vogt
package at.or.vogt.oe1downloader;

import org.apache.log4j.PropertyConfigurator;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Test for {@link RuleIndexCounter}.
 */
public class RuleIndexCounterTest {

    static {
        PropertyConfigurator.configure("conf/log4j.properties");
    }

    /** Logger. */
    private final Logger logger = LoggerFactory.getLogger(RuleIndexCounterTest.class);

    /**
     * Test getting the nex index.
     * @throws Exception if an error occurs
     */
    @Test
    public void testGetNextIndex() throws Exception {

        final String methodname = "testGetNextIndex(): ";

        final RuleVO rule1 = new RuleVO("1", "a", "", 10, "");
        final RuleVO rule2 = new RuleVO("2", "a", "", 20, "");

        final RuleIndexCounter dut = new RuleIndexCounter();
        final int rule11 = dut.getNextIndex(rule1);
        logger.info(methodname + "rule11 = {}", rule11);
        final int rule12 = dut.getNextIndex(rule1);
        logger.info(methodname + "rule12 = {}", rule12);
        final int rule21 = dut.getNextIndex(rule2);
        logger.info(methodname + "rule21 = {}", rule21);
        final int rule22 = dut.getNextIndex(rule2);
        logger.info(methodname + "rule22 = {}", rule22);
        final int rule13 = dut.getNextIndex(rule1);
        logger.info(methodname + "rule13 = {}", rule13);
        final int rule23 = dut.getNextIndex(rule2);
        logger.info(methodname + "rule23 = {}", rule23);
    }
}
