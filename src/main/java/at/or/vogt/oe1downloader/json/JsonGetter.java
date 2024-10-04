package at.or.vogt.oe1downloader.json;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.util.List;

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
import at.or.vogt.oe1downloader.json.bean.Broadcast;
import at.or.vogt.oe1downloader.json.bean.Program;

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
     * Get the {@link Program} from URL.
     * @param url url to load JSON from
     * @return list of {@link Program}
     */
    public List<Program> getProgram(final String url) {

        final StringDownloadHandler handler = new StringDownloadHandler();
        final boolean successfulDownload = downloadService.download(url, handler);
        if (!successfulDownload) {
            EVENTLOGGER.error("could not download the url " + url);
            return null;
        }

        final String json = handler.getResult();
        dumpJson(json, "program.json");
        try {
            final TypeReference<List<Program>> trList = new TypeReference<>() {
            };
            final List<Program> program = parseJson(json, trList);
            return program;

        } catch (final Oe1JsonParseException e) {
            final String message = "error parsing " + url + " ignoring!";
            logger.error(message, e);
            EVENTLOGGER.error(message, e);
            return null;
        }
    }

    /**
     * Get {@link Broadcast} from URL.
     * @param url url to load JSON from
     * @return {@link Broadcast}
     */
    public Broadcast getBroadcast(final String url) {

        final StringDownloadHandler handler = new StringDownloadHandler();
        final boolean successfulDownload = downloadService.download(url, handler);
        if (!successfulDownload) {
            EVENTLOGGER.error("could not download the url {}", url);
            return null;
        }

        final String json = handler.getResult();
        dumpJson(json, url);
        try {
            final TypeReference<Broadcast> tr = new TypeReference<>() {
            };
            final Broadcast broadcast = parseJson(json, tr);
            return broadcast;

        } catch (final Oe1JsonParseException e) {
            final String message = "error parsing " + url + " ignoring!";
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
            return om.readValue(json, tr);
        } catch (final IOException e) {
            throw new Oe1JsonParseException("error parsing json", e);
        }

    }

    /**
     * Dump the JSON string to file.
     * @param json     json
     * @param filename name of file to dump to
     */
    void dumpJson(final String json, final String filename) {
        try {

            // dump JSON to the provided location
            final Configuration config = Configuration.getConfiguration();
            final String dumpJsonLocation = config.getProperty(ConfigurationParameter.DUMP_JSON_LOCATION);
            if (StringUtils.isNotBlank(dumpJsonLocation)) {

                final ObjectMapper mapper = new ObjectMapper();
                final Object jsonObject = mapper.readValue(json, Object.class);
                final String jsonPretty = new ObjectMapper()
                        .writerWithDefaultPrettyPrinter()
                        .writeValueAsString(jsonObject);
                final File dumpFile = new File(dumpJsonLocation + escapeFilename(filename));
                final String dumpFilePath = FilenameUtils.getPath(dumpJsonLocation);
                if (dumpFilePath != null && !"".equals(dumpFilePath)) {
                    new File(dumpFilePath).mkdirs();
                }
                try (final FileWriter fw = new FileWriter(dumpFile, false)) {
                    fw.write(jsonPretty);
                    fw.flush();
                }
            }
        } catch (final Throwable e) {
            logger.error("error occurred. filename = " + filename, e);
        }
    }

    /**
     * Escapes the file name, e.g. a URL to only the escaped path.
     * @param filename file name to escape
     * @return escaped file name.
     */
    String escapeFilename(final String filename) {
        String result = filename;
        try {
            final URI uri = URI.create(filename);
            final String path = uri.getPath();
            result = path.replaceAll("\\/", "_");
        } finally {

        }

        return result;

    }

}
