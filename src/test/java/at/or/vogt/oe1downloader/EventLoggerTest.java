package at.or.vogt.oe1downloader;

import org.apache.log4j.PropertyConfigurator;
import org.junit.Test;
import org.slf4j.Logger;

/**
 * Tests for {@link EventLogger}.
 */
public class EventLoggerTest {

    static {
        PropertyConfigurator.configure("src/test/resources/log4j.properties");
    }

    /**
     * Test logging.
     */
    @Test
    public void testLog() {

        final Logger dut = EventLogger.getLogger();
        dut.error("error message");
        dut.warn("warn message", new Exception("test"));
    }

}
