// (c) 2015 by Philipp Vogt
package at.or.vogt.oe1downloader;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 * Logs events.
 */
public class EventLogger {

    /** Logger. */
    final Logger logger = Logger.getLogger("eventlogger");

    /**
     * Log the message.
     * @param level level
     * @param message message
     */
    public void log(final Level level, final String message) {
        log(level, message, null);
    }

    /**
     * Log the message and throwable.
     * @param level level
     * @param message message
     * @param e throwable
     */
    public void log(final Level level, final String message, final Throwable e) {
        logger.log(level, message, e);
    }

}
