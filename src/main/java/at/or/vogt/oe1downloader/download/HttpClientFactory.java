package at.or.vogt.oe1downloader.download;

import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;

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
