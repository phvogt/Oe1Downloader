package at.or.vogt.oe1downloader;

import java.util.List;

import org.apache.log4j.PropertyConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import at.or.vogt.oe1downloader.config.Configuration;
import at.or.vogt.oe1downloader.config.ConfigurationParameter;
import at.or.vogt.oe1downloader.download.DownloadService;
import at.or.vogt.oe1downloader.download.HttpClientFactory;
import at.or.vogt.oe1downloader.json.Day;
import at.or.vogt.oe1downloader.json.JsonGetter;

/**
 * Main class for Oe1 downloader.
 */
public class Main {

    static {
        PropertyConfigurator.configure("conf/log4j.properties");
    }

    /** event logger. */
    private static final Logger EVENTLOGGER = EventLogger.getLogger();

    /** Logger. */
    private final Logger logger = LoggerFactory.getLogger(Main.class);

    /**
     * Main method.
     * @param args arguments
     * @throws Exception if an error occurs
     */
    public static void main(final String[] args) throws Exception {

        final Main main = new Main();
        final DownloadService downloadService = main.getDownloadService();
        final String jsonPathPrefix = main.getJsonPathPrefix();
        final String targetDirectory = main.getTargetDirectory();

        main.run(downloadService, jsonPathPrefix, targetDirectory);
    }

    /**
     * Runs the program.
     * @param downloadService download service to use
     * @param jsonPathPrefix prefix for JSON path
     * @param targetDirectory target directory for MP3s
     */
    public void run(final DownloadService downloadService, final String jsonPathPrefix, final String targetDirectory) {

        final String methodname = "run(): ";
        logger.info(methodname);

        try {

            EVENTLOGGER.info("starting");

            // determine URLs with the JSON data for each day
            final DateCalc dateCalc = new DateCalc();
            final List<String> jsonUrls = dateCalc.getJsonUrls(jsonPathPrefix);

            // download JSON data
            final JsonGetter jsonGetter = new JsonGetter(downloadService);
            final List<Day> days = jsonGetter.getDays(jsonUrls);

            // determine which records to download
            final RulesVO rules = RulesVO.getRulesVO();
            final RuleIndexCounter counter = new RuleIndexCounter();
            final List<RecordVO> records = rules.checkForRecords(days, counter);

            // download the records
            downloadService.downloadRecords(targetDirectory, records);

            EVENTLOGGER.info("end");

        } catch (final Exception e) {
            final String message = "error";
            logger.error(message, e);
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
     * Gets the JSON path prefix to use.
     * @return JSON path prefix
     */
    String getJsonPathPrefix() {
        final Configuration config = Configuration.getConfiguration();
        final String result = config.getProperty(ConfigurationParameter.JSON_BASE_URL);
        return result;
    }

    /**
     * Gets the target directory for the MP3s.
     * @return target directory
     */
    String getTargetDirectory() {
        final Configuration config = Configuration.getConfiguration();
        final String result = config.getProperty(ConfigurationParameter.TARGET_DIRECTORY);
        return result;

    }

}
