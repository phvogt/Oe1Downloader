package at.or.vogt.oe1downloader.download;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.HttpStatus;
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
            Mockito.when(response.getCode()).thenReturn(HttpStatus.SC_OK);
            Mockito.when(entity.getContent()).thenReturn(new FileInputStream(new File(responseFilename)));
            Mockito.when(response.getEntity()).thenReturn(entity);
            Mockito.when(result.execute((HttpGet) Mockito.any())).thenReturn(response);
        } catch (UnsupportedOperationException | IOException e) {
            throw new RuntimeException(e);
        }

        return result;

    }

}
