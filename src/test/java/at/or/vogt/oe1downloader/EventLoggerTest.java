package at.or.vogt.oe1downloader;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;

/**
 * Tests for {@link EventLogger}.
 */
public class EventLoggerTest {

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
