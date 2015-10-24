package at.or.vogt.oe1downloader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Logs events.
 */
public class EventLogger {

    /** Logger. */
    private static final Logger EVENTLOGGER = LoggerFactory.getLogger("eventlogger");

    /**
     * Hide constructor.
     */
    private EventLogger() {
        // intentionally blank
    }

    /**
     * Get the event EVENTLOGGER.
     * @return the event EVENTLOGGER
     */
    public static Logger getLogger() {
        return EVENTLOGGER;
    }

}
