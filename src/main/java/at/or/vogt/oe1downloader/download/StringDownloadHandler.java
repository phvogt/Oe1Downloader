// (c) 2015 by Philipp Vogt
package at.or.vogt.oe1downloader.download;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;

/**
 * Download handler that stores the result in a String.
 */
public class StringDownloadHandler implements DownloadHandler {

    /** Contains result. */
    private final StringBuffer result = new StringBuffer();

    /**
     * {@inheritDoc}
     */
    @Override
    public void handleDownload(final InputStream input) {
        try {
            result.append(IOUtils.toString(input));
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

}
