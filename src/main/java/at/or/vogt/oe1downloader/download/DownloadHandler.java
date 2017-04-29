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

    /**
     * Returns the number of bytes downloaded.
     * @return number of bytes downloaded
     */
    long getBytesDownloaded();

    /**
     * Check if download was successful.
     * @return true if successful otherwise false
     */
    boolean successful();

}
