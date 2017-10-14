package at.or.vogt.oe1downloader.rules;

import org.junit.Assert;
import org.junit.Test;
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
        Assert.assertEquals("name", dut.getName());
        Assert.assertEquals("shortTitle", dut.getShortTitle());
        Assert.assertEquals("time", dut.getTime());
        Assert.assertEquals(60, dut.getMp3StartIndex());
        Assert.assertEquals("mp3postfix", dut.getMp3postfix());
        Assert.assertNotNull(dut.toString());

    }

}
