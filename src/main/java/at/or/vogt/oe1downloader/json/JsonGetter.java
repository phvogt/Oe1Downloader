package at.or.vogt.oe1downloader.json;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import at.or.vogt.oe1downloader.EventLogger;
import at.or.vogt.oe1downloader.config.Configuration;
import at.or.vogt.oe1downloader.config.ConfigurationParameter;
import at.or.vogt.oe1downloader.download.DownloadService;
import at.or.vogt.oe1downloader.download.StringDownloadHandler;
import at.or.vogt.oe1downloader.json.bean.Day;
import at.or.vogt.oe1downloader.json.bean.ShowInfo;

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
     * Get Days from URL.
     * @param url url to load JSON from
     * @param daysback number of days back
     * @return list of days
     */
    public List<Day> getDays(final String url, final long daysback) {

        final StringDownloadHandler handler = new StringDownloadHandler();
        final boolean successfulDownload = downloadService.download(url, handler);
        if (!successfulDownload) {
            EVENTLOGGER.error("could not download the url " + url);
            return null;
        }

        final String json = handler.getResult();
        // dump JSON to the provided location
        final Configuration config = Configuration.getConfiguration();
        final String dumpJsonLocation = config.getProperty(ConfigurationParameter.DUMP_JSON_LOCATION);
        if (StringUtils.isNotBlank(dumpJsonLocation)) {
            dumpJson(json, dumpJsonLocation);
        }
        try {
            final TypeReference<List<Day>> trList = new TypeReference<List<Day>>() {
            };
            final List<Day> allDays = parseJson(json, trList);

            List<Day> result = new ArrayList<>();
            final LocalDateTime firstDayDate = LocalDateTime.now().minusDays(daysback).truncatedTo(ChronoUnit.DAYS);
            if (allDays != null) {
                result = allDays.stream().filter(d -> DateParser.parseISO(d.getDateISO()).isAfter(firstDayDate))
                        .collect(Collectors.toList());

            }
            return result;

        } catch (final Oe1JsonParseException e) {
            final String message = "error parsing " + url + " ignoring!";
            logger.error(message, e);
            EVENTLOGGER.error(message, e);
            return null;
        }
    }

    /**
     * Get the show info.
     * @param url the url
     * @return the ShowInfo
     */
    public ShowInfo getShowInfo(final String url) {

        final StringDownloadHandler handler = new StringDownloadHandler();
        final boolean successfulDownload = downloadService.download(url, handler);
        if (!successfulDownload) {
            EVENTLOGGER.error("could not download the url " + url);
            return null;
        }

        final String json = handler.getResult();
        try {
            final TypeReference<ShowInfo> trList = new TypeReference<ShowInfo>() {
            };
            final ShowInfo result = parseJson(json, trList);
            return result;

        } catch (final Oe1JsonParseException e) {
            final String message = "error parsing url " + url + " ignoring!";
            logger.error(message, e);
            EVENTLOGGER.error(message, e);
            return null;
        }
    }

    /**
     * Parses the JSON.
     * @param json json to parse
     * @return JSON
     * @throws Exception if parsing fails
     */
    <T> T parseJson(final String json, final TypeReference<T> tr) throws Oe1JsonParseException {

        final ObjectMapper om = new ObjectMapper();
        try {
            final T result = om.readValue(json, tr);
            return result;
        } catch (final IOException e) {
            throw new Oe1JsonParseException("error parsing json", e);
        }

    }

    /**
     * Dump the JSON string to file.
     * @param json json
     * @param dumpJsonLocation location of the json file
     */
    private void dumpJson(final String json, final String dumpJsonLocation) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final Object jsonObject = mapper.readValue(json, Object.class);
            final String jsonPretty = new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(jsonObject);
            final File dumpFile = new File(dumpJsonLocation);
            new File(FilenameUtils.getPath(dumpJsonLocation)).mkdirs();
            try (final FileWriter fw = new FileWriter(dumpFile, false)) {
                fw.write(jsonPretty);
                fw.flush();
            }
        } catch (final Throwable e) {
            logger.error("error occurred. dumpJsonLocation = " + dumpJsonLocation, e);
        }
    }

}
