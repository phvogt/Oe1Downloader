package at.or.vogt.oe1downloader.rules;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Test for {@link RuleVO}.
 */
public class RuleVOTest {

    /** Logger. */
    private final Logger logger = LoggerFactory.getLogger(RuleVOTest.class);

    /**
     * Tests the rule.
     */
    @Test
    public void testRuleVO() {

        final String methodname = "testRuleVO(): ";

        final RuleVO dut = new RuleVO("name", "shortTitle", "time", 60, "mp3postfix");
        logger.info(methodname + "dut = ", dut);
        Assertions.assertEquals("name", dut.getName());
        Assertions.assertEquals("shortTitle", dut.getShortTitle());
        Assertions.assertEquals("time", dut.getTime());
        Assertions.assertEquals(60, dut.getMp3StartIndex());
        Assertions.assertEquals("mp3postfix", dut.getMp3postfix());
        Assertions.assertNotNull(dut.toString());

    }

}
