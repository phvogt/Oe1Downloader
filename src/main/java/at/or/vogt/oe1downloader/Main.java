package at.or.vogt.oe1downloader;

import java.util.Comparator;
import java.util.List;

import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.ParseException;
import org.apache.log4j.PropertyConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import at.or.vogt.oe1downloader.cli.CliOption;
import at.or.vogt.oe1downloader.cli.CommandLineParser;
import at.or.vogt.oe1downloader.config.Configuration;
import at.or.vogt.oe1downloader.config.ConfigurationParameter;
import at.or.vogt.oe1downloader.download.DownloadService;
import at.or.vogt.oe1downloader.download.HttpClientFactory;
import at.or.vogt.oe1downloader.download.RecordVO;
import at.or.vogt.oe1downloader.json.JsonGetter;
import at.or.vogt.oe1downloader.json.bean.Program;
import at.or.vogt.oe1downloader.rules.RuleIndexCounter;
import at.or.vogt.oe1downloader.rules.RulesVO;

/**
 * Main class for Oe1 downloader.
 */
public class Main {

    static {
        PropertyConfigurator.configure("conf/log4j.properties");
    }

    /** event LOGGER. */
    private static final Logger EVENTLOGGER = EventLogger.getLogger();

    /** Logger. */
    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    /**
     * Main method.
     * @param args arguments
     * @throws Exception if an error occurs
     */
    public static void main(final String[] args) throws Exception {

        final Main main = new Main();
        try {
            final CommandLineParser cmd = main.processCommandline(args);
            main.doRun(cmd);
        } catch (final SystemExitException e) {
            LOGGER.error(e.getMessage(), e);
            System.exit(e.getExitCode());
        }

    }

    /**
     * Run the program.
     * @param cmd command line
     */
    private void doRun(final CommandLineParser cmd) {

        final DownloadService downloadService = getDownloadService();
        final String jsonBroadcastsUrl = getJsonBroadcastsUrl();
        final String targetDirectory = getTargetDirectory(cmd.getTargetDirectory());

        doDownloads(downloadService, jsonBroadcastsUrl, targetDirectory);
    }

    /**
     * Processes the download.
     * @param args command line arguments
     * @return exit code
     * @throws SystemExitException if an error occurs
     */
    CommandLineParser processCommandline(final String[] args) throws SystemExitException {

        final CommandLineParser cmd;
        try {
            cmd = new CommandLineParser().parseCommandLine(args);
        } catch (final ParseException e) {
            // if the command line was not parsable, show usage and exit
            showUsage(e.getMessage());
            throw new SystemExitException(2, e.getMessage());
        }

        if (cmd.isHelp()) {
            // show help
            showUsage(null);
            throw new SystemExitException(1, null);
        }

        return cmd;

    }

    /**
     * Shows the usage.
     * @param message the message to show, if it is not null
     */
    void showUsage(final String message) {

        final HelpFormatter formatter = new HelpFormatter();
        // set the order
        formatter.setOptionComparator(new Comparator<Option>() {

            private static final String OPTS_ORDER = "hd";

            @Override
            public int compare(final Option o1, final Option o2) {
                return OPTS_ORDER.indexOf(o1.getOpt()) - OPTS_ORDER.indexOf(o2.getOpt());
            }
        });

        if (message != null) {
            System.out.println(message);
        }

        formatter.printHelp("Oe1Downloader", CliOption.getOptions());
    }

    /**
     * Does the downloads.
     * @param downloadService   download service to use
     * @param jsonBroadcastsUrl broadcasts URL for JSON
     * @param targetDirectory   target directory for MP3s
     */
    public void doDownloads(final DownloadService downloadService, final String jsonBroadcastsUrl,
            final String targetDirectory) {

        final String methodname = "run(): ";
        LOGGER.info(methodname);

        try {

            EVENTLOGGER.info("starting");

            final JsonGetter jsonGetter = new JsonGetter(downloadService);

            final List<Program> programs = jsonGetter.getProgram(jsonBroadcastsUrl);
            if (programs == null) {
                EVENTLOGGER.warn("didn't find a program to download");
                return;
            }

            // determine which records to download
            final RulesVO rules = RulesVO.getRulesVO();
            final RuleIndexCounter counter = new RuleIndexCounter();
            final List<RecordVO> records = rules.checkForRecords(programs, counter);

            // download the records
            downloadService.downloadRecords(targetDirectory, records);

            EVENTLOGGER.info("end");

        } catch (final Exception e) {
            final String message = "error";
            LOGGER.error(message, e);
            EVENTLOGGER.error(message);
        }

    }

    /**
     * Get the download service.
     * @return DownloadService
     */
    DownloadService getDownloadService() {
        final DownloadService result = new DownloadService(new HttpClientFactory());
        return result;
    }

    /**
     * Gets the JSON broadcast URL to use.
     * @return JSON broadcast URL
     */
    String getJsonBroadcastsUrl() {
        final Configuration config = Configuration.getConfiguration();
        final String result = config.getProperty(ConfigurationParameter.JSON_BROADCASTS_URL);
        return result;
    }

    /**
     * Gets the target directory for the MP3s.
     * @param cliTargetDirectory target directory from the command line
     * @return target directory
     */
    String getTargetDirectory(final String cliTargetDirectory) {

        if (cliTargetDirectory != null) {
            return cliTargetDirectory;
        }

        final Configuration config = Configuration.getConfiguration();
        final String result = config.getProperty(ConfigurationParameter.TARGET_DIRECTORY);
        return result;

    }

}
