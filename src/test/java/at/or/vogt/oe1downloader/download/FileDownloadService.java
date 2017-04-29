package at.or.vogt.oe1downloader.download;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Test download service that loads the URLs from the file.
 */
public class FileDownloadService extends DownloadService {

    /**
     * Constructor.
     * @param httpClientFactory http client factory
     */
    public FileDownloadService(final HttpClientFactory httpClientFactory) {
        super(httpClientFactory);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean download(final String url, final DownloadHandler handler) {

        boolean result = false;
        try {
            handler.handleDownload(new FileInputStream(new File(url)));
            result = handler.successful();
        } catch (final IOException e) {
            throw new RuntimeException("error testing", e);
        }
        return result;
    }

}
