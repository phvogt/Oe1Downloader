// (c) 2015 by Philipp Vogt
package at.or.vogt.oe1downloader;

import org.apache.log4j.PropertyConfigurator;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import at.or.vogt.oe1downloader.json.Show;

/**
 * Test for {@link RuleVO}.
 */
public class RuleVOTest {

    static {
        PropertyConfigurator.configure("src/test/resources/log4j.properties");
    }

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

    /**
     * Tests {@link RuleVO#matches(at.or.vogt.oe1downloader.json.Show)}.
     */
    @Test
    public void testMatches() {

        final RuleVO dut = new RuleVO("name", "shortTitle", "time", 60, "mp3postfix");

        // Show matches
        final Show show = new Show("shortTitle", 15, "title", "dayLabel", "urlJson", "urlStream", "info", "time");
        Assert.assertTrue(dut.matches(show));

        // Show does not match because of short title
        final Show showNotShortTitle = new Show("xxx", 15, "title", "dayLabel", "urlJson", "urlStream", "info", "time");
        Assert.assertFalse(dut.matches(showNotShortTitle));

        // Show does not match because of time
        final Show showNotTime = new Show("shortTitle", 15, "title", "dayLabel", "urlJson", "urlStream", "info", "");
        Assert.assertFalse(dut.matches(showNotTime));

    }

}
