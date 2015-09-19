// (c) 2015 by Philipp Vogt
package at.or.vogt.oe1downloader.download;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.HttpVersion;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicStatusLine;
import org.mockito.Mockito;

/**
 * {@link HttpClientFactory} that reads from files.
 */
public class FileHttpClientFactory extends HttpClientFactory {

    /** name of file to load response from */
    private final String responseFilename;

    /**
     * Constructor.
     * @param responseFilename response file name
     */
    public FileHttpClientFactory(final String responseFilename) {
        this.responseFilename = responseFilename;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CloseableHttpClient getHttpClient() {

        final CloseableHttpClient result = Mockito.mock(CloseableHttpClient.class);
        final CloseableHttpResponse response = Mockito.mock(CloseableHttpResponse.class);
        final HttpEntity entity = Mockito.mock(HttpEntity.class);
        try {
            Mockito.when(response.getStatusLine()).thenReturn(new BasicStatusLine(HttpVersion.HTTP_1_1, HttpStatus.SC_OK, "FINE!"));
            Mockito.when(entity.getContent()).thenReturn(new FileInputStream(new File(responseFilename)));
            Mockito.when(response.getEntity()).thenReturn(entity);
            Mockito.when(result.execute((HttpGet) Mockito.any())).thenReturn(response);
        } catch (UnsupportedOperationException | IOException e) {
            throw new RuntimeException(e);
        }

        return result;

    }

}
