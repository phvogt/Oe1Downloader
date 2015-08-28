// (c) 2015 by Philipp Vogt
package at.or.vogt.oe1downloader;

import org.apache.log4j.Level;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Test;

/**
 * Tests for {@link EventLogger}.
 */
public class EventLoggerTest {

    static {
        PropertyConfigurator.configure("conf/log4j.properties");
    }

    /**
     * Test logging.
     * @throws Exception if an error occurs
     */
    @Test
    public void testLog() throws Exception {

        final EventLogger dut = new EventLogger();
        dut.log(Level.ERROR, "error message");
        dut.log(Level.WARN, "warn message", new Exception("test"));

    }

}
