package at.or.vogt.oe1downloader.json;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import at.or.vogt.oe1downloader.EventLogger;
import at.or.vogt.oe1downloader.download.DownloadService;
import at.or.vogt.oe1downloader.download.StringDownloadHandler;

/**
 * Gets the JSONs.
 */
public class JsonGetter {

    /** event logger. */
    private static final Logger EVENTLOGGER = EventLogger.getLogger();

    /** Logger. */
    private final Logger logger = LoggerFactory.getLogger(JsonGetter.class);

    /** service to download. */
    private final DownloadService downloadService;

    /**
     * Constructor.
     * @param downloadService service to download JSONs
     */
    public JsonGetter(final DownloadService downloadService) {
        this.downloadService = downloadService;
    }

    /**
     * Gets the days from the list of URLs.
     * @param urls urls to load JSON from
     * @return days
     */
    public List<Day> getDays(final List<String> urls) {

        final List<Day> result = new ArrayList<>();

        for (final String url : urls) {
            try {
                final Day day = getDay(url);
                result.add(day);
            } catch (final JSONException e) {
                final String message = "error parsing " + url + " ignoring!";
                logger.error(message, e);
                EVENTLOGGER.error(message);
            }
        }

        return result;
    }

    /**
     * Get Day from URL.
     * @param url url to load JSON from
     * @return Day
     */
    Day getDay(final String url) {

        final StringDownloadHandler handler = new StringDownloadHandler();
        downloadService.download(url, handler);
        final String json = handler.getResult();
        return parseJson(json);
    }

    /**
     * Parses the JSON.
     * @param json json to parse
     * @return JSON
     */
    Day parseJson(final String json) {

        final JSONObject obj = new JSONObject(json);
        return new Day(obj);
    }

}
