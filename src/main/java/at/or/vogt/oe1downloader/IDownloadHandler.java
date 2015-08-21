// (c) 2015 by Philipp Vogt
package at.or.vogt.oe1downloader;

import java.io.InputStream;

/**
 * Handles the download
 */
public interface IDownloadHandler {

    /**
     * Processes the inputstream of the file.
     * @param input input stream
     */
    void processFile(final InputStream input);

}
