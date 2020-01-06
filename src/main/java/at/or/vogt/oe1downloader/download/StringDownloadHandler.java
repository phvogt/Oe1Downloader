package at.or.vogt.oe1downloader.download;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

import org.apache.commons.io.IOUtils;

/**
 * Download handler that stores the result in a String.
 */
public class StringDownloadHandler implements DownloadHandler {

    /** Contains result. */
    private final StringBuffer result = new StringBuffer();

    /** content length. */
    private long contentLength = 0;

    /** flag if download was successful. */
    private boolean successful = false;

    /**
     * {@inheritDoc}
     */
    @Override
    public void handleDownload(final InputStream input) {
        try {
            result.append(IOUtils.toString(input, Charset.defaultCharset()));
            successful = true;
        } catch (final IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Gets the result as String.
     * @return String with result
     */
    public String getResult() {
        return result.toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean successful() {
        return successful;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long getBytesDownloaded() {
        return result.length();
    }

    @Override
    public long getContentLength() {
        return contentLength;
    }

    @Override
    public void setContentLength(final long contentLength) {
        this.contentLength = contentLength;
    }

}
