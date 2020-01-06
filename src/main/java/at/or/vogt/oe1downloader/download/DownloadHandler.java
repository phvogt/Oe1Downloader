package at.or.vogt.oe1downloader.download;

import java.io.InputStream;

/**
 * Handler for downloads.
 */
public interface DownloadHandler {

    /**
     * Content length reported by server.
     * @param length the length reported by the server
     */
    void setContentLength(final long length);

    /**
     * Get the content length reported by the server.
     * @return length reported by the server
     */
    long getContentLength();

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
