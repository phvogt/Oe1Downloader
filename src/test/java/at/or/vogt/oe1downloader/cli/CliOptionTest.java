package at.or.vogt.oe1downloader.cli;

import org.apache.commons.cli.Option;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Test for {@link at.or.vogt.oe1downloader.cli.CliOption}.
 */
public class CliOptionTest {

    /** Logger. */
    private final Logger logger = LoggerFactory.getLogger(CliOptionTest.class);

    /**
     * Tests {@link CliOption#getOption()}.
     */
    @Test
    public void testOption() {

        final String methodname = "testOption(): ";
        logger.info(methodname + "start");

        final CliOption dut = CliOption.HELP;

        final Option helpOption = dut.getOption();
        Assert.assertNotNull(helpOption);
        logger.info(methodname + "helpOption = {}", helpOption);

        Assert.assertEquals("h", helpOption.getOpt());
        Assert.assertFalse(helpOption.hasArg());
        Assert.assertEquals(dut.isHasArguments(), helpOption.hasArg());
        Assert.assertNotNull(helpOption.getDescription());
        Assert.assertEquals(dut.getDescription(), helpOption.getDescription());
    }

}
