// (c) 2015 by Philipp Vogt
package at.or.vogt.oe1downloader;

import java.io.InputStream;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Downloader for MP3.
 */
public class DownloadMp3 {

    /** Logger. */
    private final Logger logger = LoggerFactory.getLogger(DownloadMp3.class);

    /**
     * Downloads the MP3 from the URL.
     * @param url the url to download from
     */
    public void downloadMp3(final String url, final IDownloadHandler handler) {

        final String methodname = "downloadFromUrl(): ";

        final CloseableHttpClient httpclient = HttpClients.createDefault();
        final HttpGet httpGet = new HttpGet(url);
        httpGet.addHeader("User-Agent", "Mozilla/5.0 (X11; Linux x86_64; rv:40.0) Gecko/20100101 Firefox/40.0 Iceweasel/40.0");

        try (final CloseableHttpResponse response1 = httpclient.execute(httpGet)) {
            logger.debug(methodname + "url = {} statuscode = {}", url, response1.getStatusLine());
            final HttpEntity entity1 = response1.getEntity();
            final Header[] headers = response1.getAllHeaders();
            for (final Header header : headers) {
                logger.debug(methodname + "  header = {}: {}", header.getName(), header.getValue());
            }
            logger.debug(methodname + "  Content-Type = {}", entity1.getContentType());
            logger.debug(methodname + "  Content-Length = {}", entity1.getContentLength());

            try (final InputStream in = entity1.getContent()) {
                handler.processFile(in);
            }
            // result = EntityUtils.toByteArray(entity1);
            // logger.info(methodname + " result.length = {}", result.length);
            EntityUtils.consume(entity1);
        } catch (final Exception e) {
            logger.error(methodname, e);
        }

    }

}
