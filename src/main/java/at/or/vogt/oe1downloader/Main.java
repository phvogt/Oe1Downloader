// (c) 2015 by Philipp Vogt
package at.or.vogt.oe1downloader;

import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.PropertyConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import at.or.vogt.oe1downloader.json.Day;

/**
 * Main class for Oe1 downloader.
 */
public class Main {

    static {
        PropertyConfigurator.configure("conf/log4j.properties");
    }

    /** event logger. */
    private static final EventLogger EVENTLOGGER = new EventLogger();

    /** Logger. */
    private final Logger logger = LoggerFactory.getLogger(Main.class);

    /**
     * Main method.
     * @param args arguments
     * @throws Exception if an error occurs
     */
    public static void main(final String[] args) throws Exception {
        final Main main = new Main();
        main.run();
    }

    /**
     * Runs the program.
     */
    public void run() {
        try {
            final String methodname = "run(): ";
            logger.info(methodname);

            EVENTLOGGER.log(Level.INFO, "starting");

            final Configuration config = new Configuration();

            final RulesVO rules = new RulesVO();
            rules.loadRules();

            final String jsonPathPrefix = config.getProperty(ConfigurationParameter.JSON_BASE_URL,
                    "http://oe1.orf.at/programm/konsole/tag/");
            final DateCalc dateCalc = new DateCalc();
            final List<String> jsonUrls = dateCalc.getJsonUrls(jsonPathPrefix);

            final JsonGetter jsonGetter = new JsonGetter();
            final List<Day> days = jsonGetter.getDays(jsonUrls);

            final RuleIndexCounter counter = new RuleIndexCounter();
            final List<RecordVO> records = rules.checkForRecords(days, counter);

            final String targetDirectory = config.getProperty(ConfigurationParameter.TARGET_DIRECTORY, "./mp3s");
            final DownloadService downloader = new DownloadService(targetDirectory);
            downloader.downloadRecords(records);

            EVENTLOGGER.log(Level.INFO, "end");
        } catch (final Exception e) {
            EVENTLOGGER.log(Level.ERROR, "error", e);
        }

    }

}
