// (c) 2015 by Philipp Vogt
package at.or.vogt.oe1downloader;

import org.apache.log4j.PropertyConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Main class for Oe1 downloader.
 */
public class Main implements Runnable {

    static {
        PropertyConfigurator.configure("conf/log4j.properties");
    }

    /** Logger. */
    private final Logger logger = LoggerFactory.getLogger(Main.class);

    /**
     * Main method.
     * @param args arguments
     */
    public static void main(final String[] args) {
        final Main main = new Main();
        main.run();
    }

    /**
     * Constructor.
     */
    public Main() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void run() {
        final String methodname = "run(): ";
        logger.info(methodname);
    }

}
