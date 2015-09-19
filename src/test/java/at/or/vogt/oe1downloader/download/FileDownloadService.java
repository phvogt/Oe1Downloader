// (c) 2015 by Philipp Vogt
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
    public void download(final String url, final DownloadHandler handler) {
        try {
            handler.handleDownload(new FileInputStream(new File(url)));
        } catch (final IOException e) {
            throw new RuntimeException("error testing", e);
        }
    }

}
