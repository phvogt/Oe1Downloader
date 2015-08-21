// (c) 2015 by Philipp Vogt
package at.or.vogt.oe1downloader;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.PropertyConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import at.or.vogt.oe1downloader.json.Tag;

/**
 * Main class for Oe1 downloader.
 */
public class Main {

    static {
        PropertyConfigurator.configure("conf/log4j.properties");
    }

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
     * Constructor.
     */
    public Main() {
    }

    /**
     * Runs the program.
     * @throws Exception if an error occurs
     */
    public void run() throws Exception {
        final String methodname = "run(): ";
        logger.info(methodname);

        final RulesVO rules = new RulesVO();
        rules.loadRules();

        final DateCalc dateCalc = new DateCalc();
        final List<String> jsonUrls = dateCalc.getJsonUrls("http://oe1.orf.at/programm/konsole/tag/");

        final JsonGetter jsonGetter = new JsonGetter();
        final List<Tag> tage = jsonGetter.getTage(jsonUrls);

        final List<RecordVO> records = rules.checkForRecords(tage);

        final RuleIndexCounter counter = new RuleIndexCounter();

        final ExecutorService executors = Executors.newFixedThreadPool(3);
        final List<Future<?>> futures = new ArrayList<>();

        for (final RecordVO recordVO : records) {
            logger.info(methodname + "recordVO = {}", recordVO);
            final String downloadUrl = recordVO.getDownloadUrl();
            logger.info(methodname + "downloadUrl = {}", downloadUrl);
            final String filename = recordVO.getFileName(counter);
            logger.info(methodname + "filename = {}", filename);

            futures.add(executors.submit(new Runnable() {

                @Override
                public void run() {
                    final DownloadMp3 downloader = new DownloadMp3();
                    downloader.downloadMp3(downloadUrl, new IDownloadHandler() {

                        @Override
                        public void processFile(final InputStream input) {
                            logger.info(methodname + "downloading file = {}", filename);
                            try (final FileOutputStream fos = new FileOutputStream(new File("data/" + filename))) {
                                IOUtils.copy(input, fos);

                            } catch (final IOException e) {
                                logger.error(methodname, e);
                            }
                        }
                    });

                }
            }));
        }

        for (final Future<?> future : futures) {
            final Object result = future.get();
            logger.info(methodname + "result = {}", result);
        }

        logger.info(methodname + "stopping executors");
        executors.shutdown();
        logger.info(methodname + "waiting 60s");
        executors.awaitTermination(60, TimeUnit.SECONDS);
        logger.info(methodname + "bye");

    }

}
