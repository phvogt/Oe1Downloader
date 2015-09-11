// (c) 2015 by Philipp Vogt
package at.or.vogt.oe1downloader;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.math.NumberUtils;
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
        final Configuration config = new Configuration();
        final int daysback = NumberUtils.toInt(config.getProperty(ConfigurationParameter.DAYSBACK, "7"), 7);

        final DateTime startDay = today.minusDays(daysback);
        logger.info(methodname + "startDay = {}", startDay);

        final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        DateTime jsonDay = startDay;
        final DateTime jsonEndDay = today.plusDays(1);
        while (jsonDay.isBefore(jsonEndDay)) {
            final String url = pathPrefix + sdf.format(jsonDay.toDate());
            result.add(url);
            jsonDay = jsonDay.plusDays(1);
        }

        return result;
    }

}
