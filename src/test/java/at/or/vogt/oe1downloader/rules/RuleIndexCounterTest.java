package at.or.vogt.oe1downloader.rules;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Test for {@link RuleIndexCounter}.
 */
class RuleIndexCounterTest {

    /** Logger. */
    private final Logger logger = LoggerFactory.getLogger(RuleIndexCounterTest.class);

    /**
     * Test getting the next index.
     */
    @Test
    void testGetNextIndex() {

        final String methodname = "testGetNextIndex(): ";

        final RuleVO rule1 = new RuleVO("1", "a", "", 10, "");
        final RuleVO rule2 = new RuleVO("2", "a", "", 20, "");

        final RuleIndexCounter dut = new RuleIndexCounter();
        final int rule11 = dut.getNextIndex(rule1);
        logger.info("{}rule11 = {}", methodname, rule11);
        Assertions.assertEquals(10, rule11);
        final int rule12 = dut.getNextIndex(rule1);
        logger.info("{}rule12 = {}", methodname, rule12);
        Assertions.assertEquals(11, rule12);
        final int rule21 = dut.getNextIndex(rule2);
        logger.info("{}rule21 = {}", methodname, rule21);
        Assertions.assertEquals(20, rule21);
        final int rule22 = dut.getNextIndex(rule2);
        logger.info("{}rule22 = {}", methodname, rule22);
        Assertions.assertEquals(21, rule22);
        final int rule13 = dut.getNextIndex(rule1);
        logger.info("{}rule13 = {}", methodname, rule13);
        Assertions.assertEquals(12, rule13);
        final int rule23 = dut.getNextIndex(rule2);
        logger.info("{}rule23 = {}", methodname, rule23);
        Assertions.assertEquals(22, rule23);
    }
}
