// (c) 2015 by Philipp Vogt
package at.or.vogt.oe1downloader;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Calculates dates.
 */
public class DateCalc {

    /** Logger. */
    private final Logger logger = LoggerFactory.getLogger(DateCalc.class);

    /**
     * Gets the JSON urls.
     * @param pathPrefix prefix for path
     * @return the urls
     */
    public List<String> getJsonUrls(final String pathPrefix) {

        final String methodname = "getJsonUrls(): ";

        final List<String> result = new ArrayList<>();

        final DateTime today = new DateTime();
        final DateTime saturdayLastWeek = today.minusDays(today.getDayOfWeek() + 1);
        logger.info(methodname + "saturdayLastWeek = {}", saturdayLastWeek);

        final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        DateTime jsonDay = saturdayLastWeek;
        final DateTime jsonEndDay = today.plusDays(1);
        while (jsonDay.isBefore(jsonEndDay)) {
            final String url = pathPrefix + sdf.format(jsonDay.toDate());
            result.add(url);
            jsonDay = jsonDay.plusDays(1);
        }

        return result;
    }

}
