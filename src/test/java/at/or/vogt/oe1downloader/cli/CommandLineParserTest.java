package at.or.vogt.oe1downloader.cli;

import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Test for {@link at.or.vogt.oe1downloader.cli.CommandLineParser}.
 */
public class CommandLineParserTest {

    /** Logger. */
    private final Logger logger = LoggerFactory.getLogger(CommandLineParserTest.class);

    /**
     * Tests {@link CommandLineParser#getCliOptions()}.
     */
    @Test
    public void testGetCliOptions() {

        final String methodname = "testGetCliOptions(): ";
        logger.info(methodname + "start");

        final CommandLineParser dut = new CommandLineParser();

        final Options cliOptions = dut.getCliOptions();
        Assert.assertNotNull(cliOptions);
        logger.info(methodname + "cliOptions = {}", cliOptions);

        Assert.assertTrue(cliOptions.hasOption(CliOption.HELP.getOptionName()));
    }

    /**
     * Tests {@link CommandLineParser#parseCommandLine(String[])}.
     * @throws ParseException if an error occurs
     */
    @Test
    public void testParseCommandLine() throws ParseException {

        final String methodname = "testParseCommandLine(): ";
        logger.info(methodname + "start");

        final CommandLineParser dut = new CommandLineParser();

        final CommandLineParser cmd = dut.parseCommandLine(new String[] { "" });
        Assert.assertNotNull(cmd);
        Assert.assertFalse(cmd.isHelp());
        Assert.assertNull(cmd.getTargetDirectory());

        final CommandLineParser cmdH = dut.parseCommandLine(new String[] { "-h" });
        Assert.assertNotNull(cmdH);
        Assert.assertTrue(cmd.isHelp());
        Assert.assertNull(cmd.getTargetDirectory());

        final CommandLineParser cmdD = dut.parseCommandLine(new String[] { "-d", "testdir" });
        Assert.assertNotNull(cmdD);
        Assert.assertFalse(cmd.isHelp());
        Assert.assertEquals("testdir", cmd.getTargetDirectory());

        try {
            dut.parseCommandLine(new String[] { "-x" });
            Assert.fail("-x should not be allowed");
        } catch (final ParseException e) {
            // should be thrown
        }
    }

}
