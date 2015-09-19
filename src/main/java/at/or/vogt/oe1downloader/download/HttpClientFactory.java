// (c) 2015 by Philipp Vogt
package at.or.vogt.oe1downloader.download;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

/**
 * Factory for http clients that are needed for downloading.
 */
public class HttpClientFactory {

    /**
     * Get the http client.
     * @return http client
     */
    public CloseableHttpClient getHttpClient() {
        return HttpClients.createDefault();
    }

}
