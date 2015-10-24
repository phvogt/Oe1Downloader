package at.or.vogt.oe1downloader.download;

import java.io.InputStream;

/**
 * Handler for downloads.
 */
public interface DownloadHandler {

    /**
     * Handle the download.
     * @param input input stream with content
     */
    void handleDownload(final InputStream input);

}
