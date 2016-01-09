package at.or.vogt.oe1downloader.cli;

import java.util.Arrays;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import at.or.vogt.oe1downloader.EventLogger;

/**
 * Command line options.
 */
public class CommandLineParser {

    /** event logger. */
    private static final Logger EVENTLOGGER = EventLogger.getLogger();

    /** Logger. */
    private final Logger logger = LoggerFactory.getLogger(CommandLineParser.class);

    /** command line options */
    private final Options cliOptions;

    /** parsed command line. */
    private CommandLine cmd;

    /**
     * Constructor.
     * 
     */
    public CommandLineParser() {

	this.cliOptions = new Options();

	for (final CliOption cliOption : CliOption.values()) {
	    cliOptions.addOption(cliOption.getOption());
	}

    }

    /**
     * parse the command line.
     * 
     * @param args
     *            command line arguments
     * @return command line parser
     * @throws ParseException
     *             if the command line could not be parsed
     */
    public CommandLineParser parseCommandLine(final String[] args) throws ParseException {

	final org.apache.commons.cli.CommandLineParser parser = new DefaultParser();

	try {
	    cmd = parser.parse(cliOptions, args);
	} catch (final ParseException e) {
	    final String message = "error parsing " + Arrays.toString(args);
	    logger.error(message, e);
	    EVENTLOGGER.error(message);
	    throw e;
	}

	return this;
    }

    /**
     * Checks if the help argument is present on command line.
     * 
     * @return true if help argument is present
     */
    public boolean isHelp() {
	return cmd.hasOption(CliOption.HELP.getOptionName());
    }

    /**
     * Gets the target directory on the command line or null
     * 
     * @return target directory or null
     */
    public String getTargetDirectory() {
	return cmd.getOptionValue(CliOption.TARGET_DIR.getOptionName());
    }

    /**
     * Get the cliOptions.
     * 
     * @return the cliOptions
     */
    Options getCliOptions() {
	return cliOptions;
    }

}
