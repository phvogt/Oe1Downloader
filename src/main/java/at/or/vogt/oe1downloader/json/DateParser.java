package at.or.vogt.oe1downloader.json;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

/**
 * Parser for dates.
 */
public class DateParser {

    /**
     * Parses the ISO date.
     * @param isodate ISO date
     * @return LocalDateTime
     */
    public static LocalDateTime parseISO(final String isodate) {
        if (isodate == null) {
            return null;
        }
        return LocalDateTime.parse(isodate, DateTimeFormatter.ISO_DATE_TIME);
    }

    /**
     * Gets the epoch milli seconds.
     * @param ldt LocalDateTime
     * @return millis
     */
    public static long getEpochMillis(final LocalDateTime ldt) {
        return ldt.toInstant(ZoneOffset.UTC).toEpochMilli();
    }

}
