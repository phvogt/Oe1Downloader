package at.or.vogt.oe1downloader.cli;

import org.apache.commons.cli.Option;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
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
        Assertions.assertNotNull(helpOption);
        logger.info(methodname + "helpOption = {}", helpOption);

        Assertions.assertEquals("h", helpOption.getOpt());
        Assertions.assertFalse(helpOption.hasArg());
        Assertions.assertEquals(dut.isHasArguments(), helpOption.hasArg());
        Assertions.assertNotNull(helpOption.getDescription());
        Assertions.assertEquals(dut.getDescription(), helpOption.getDescription());
    }

}
