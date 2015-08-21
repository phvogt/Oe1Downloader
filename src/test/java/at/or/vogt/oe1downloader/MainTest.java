// (c) 2015 by Philipp Vogt
package at.or.vogt.oe1downloader;

import org.apache.log4j.PropertyConfigurator;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Test for {@link at.or.vogt.oe1downloader.Main}.
 */
public class MainTest {

    static {
        PropertyConfigurator.configure("conf/log4j.properties");
    }

    /** Logger. */
    private final Logger logger = LoggerFactory.getLogger(MainTest.class);

    /**
     * Tests for main.
     * @throws Exception if an error occurs
     */
    @Test
    public void testMain() throws Exception {

        final String methodname = "testMain(): ";
        logger.info(methodname + "start");

        final Main dut = new Main();
        dut.run();

    }

}
