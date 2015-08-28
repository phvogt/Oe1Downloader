// (c) 2015 by Philipp Vogt
package at.or.vogt.oe1downloader;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Level;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import at.or.vogt.oe1downloader.json.Tag;

/**
 * Gets the JSONs.
 */
public class JsonGetter {

    /** event logger. */
    private static final EventLogger eventLogger = new EventLogger();

    /** Logger. */
    private final Logger logger = LoggerFactory.getLogger(JsonGetter.class);

    /**
     * Gets the Tage from the list of URLs.
     * @param urls urls to load JSON from
     * @return Tage
     */
    public List<Tag> getTage(final List<String> urls) {

        final List<Tag> result = new ArrayList<>();

        for (final String url : urls) {
            final Tag tag = getTag(url);
            result.add(tag);
        }

        return result;
    }

    /**
     * Get Tag from URL.
     * @param url url to load JSON from
     * @return Tag
     */
    Tag getTag(final String url) {

        final String json = downloadContentFromUrl(url);
        return parseJson(json);
    }

    /**
     * Parses the JSON.
     * @param json json to parse
     * @return JSON
     */
    Tag parseJson(final String json) {
        final JSONObject obj = new JSONObject(json);
        return new Tag(obj);
    }

    /**
     * Downloads the content from the URL.
     * @param url URL to download from
     * @return content
     */
    String downloadContentFromUrl(final String url) {

        final String methodname = "downloadFromUrl(): ";

        eventLogger.log(Level.INFO, "downloading JSON from URL " + url + ".");

        final CloseableHttpClient httpclient = HttpClients.createDefault();
        final HttpGet httpGet = new HttpGet(url);

        String result = null;
        try (final CloseableHttpResponse response1 = httpclient.execute(httpGet)) {
            logger.info(methodname + "url = {} statuscode = {}", url, response1.getStatusLine());
            final HttpEntity entity1 = response1.getEntity();
            try (final InputStream in = entity1.getContent()) {
                result = IOUtils.toString(in);
            }
            EntityUtils.consume(entity1);
        } catch (final IOException e) {
            result = null;
        }

        return result;
    }

}
