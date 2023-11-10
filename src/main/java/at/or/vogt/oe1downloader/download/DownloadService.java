package at.or.vogt.oe1downloader.download;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.NotSupportedException;
import com.mpatric.mp3agic.UnsupportedTagException;

import at.or.vogt.oe1downloader.EventLogger;
import at.or.vogt.oe1downloader.config.Configuration;
import at.or.vogt.oe1downloader.config.ConfigurationParameter;
import at.or.vogt.oe1downloader.json.JsonGetter;
import at.or.vogt.oe1downloader.json.bean.Broadcast;
import at.or.vogt.oe1downloader.json.bean.Stream;

/**
 * Download service for MP3.
 */
public class DownloadService {

    /** event logger. */
    private static final Logger EVENTLOGGER = EventLogger.getLogger();

    /** Logger. */
    private final Logger logger = LoggerFactory.getLogger(DownloadService.class);

    /** factory for http clients for downloading */
    private final HttpClientFactory httpClientFactory;

    /**
     * Constructor.
     * @param httpClientFactory factory for http clients for downloading
     */
    public DownloadService(final HttpClientFactory httpClientFactory) {
        this.httpClientFactory = httpClientFactory;
    }

    /**
     * Downloads the records.
     * @param targetDirectory target directory
     * @param records records to download
     * @throws IOException if an error occcurs
     */
    public void downloadRecords(final String targetDirectory, final List<RecordVO> records) throws IOException {

        final String methodname = "downloadRecords(): ";

        final File targetDirFile = new File(targetDirectory);
        FileUtils.forceMkdir(targetDirFile);

        final Configuration config = Configuration.getConfiguration();
        final int parallelDownloads = NumberUtils.toInt(config.getProperty(ConfigurationParameter.NUMBER_OF_PARALLEL_DOWNLOADS), 3);

        final ExecutorService executors = Executors.newFixedThreadPool(parallelDownloads);
        final List<Future<?>> futures = new ArrayList<>();

        for (final RecordVO record : records) {
            logger.info(methodname + "recordVO = {}", record);

            // set the target file name
            final String filePath = targetDirectory + File.separator + record.getFilename();
            record.setTargetFilename(filePath);

            // check if the file already exists
            if (record.checkTargetFileExists()) {
                EVENTLOGGER.warn("will not download {}, because file already exists.", record.getTargetFilename());

                logger.info(methodname + "  not downlading, record already exists. record = {}", record);
                continue;
            }

            futures.add(executors.submit(new Runnable() {

                @Override
                public void run() {
                    download(record);
                    setMp3Tag(record);
                }
            }));
        }

        for (final Future<?> future : futures) {
            try {
                final Object result = future.get();
                logger.info(methodname + "result = {}", result);
            } catch (final InterruptedException | ExecutionException e) {
                EVENTLOGGER.error("error downloading", e);
            }
        }

        logger.info(methodname + "stopping executors");
        executors.shutdown();
        try {
            logger.info(methodname + "waiting 60s");
            executors.awaitTermination(60, TimeUnit.SECONDS);
        } catch (final InterruptedException e) {
            EVENTLOGGER.error("error waiting for termination", e);
        }

    }

    /**
     * Downloads the MP3 from the URL.
     * @param record record to download
     * @return true if download was successful otherwise false
     */
    boolean download(final RecordVO record) {

        final String methodname = "download(): ";

        logger.info(methodname + "record = {}", record);
        boolean successful = false;

        // get Broadcast that contains the id of the stream
        final String href = record.getHref();
        final JsonGetter jsg = new JsonGetter(this);
        final Broadcast broadcast = jsg.getBroadcast(href);
        if (broadcast == null) {
            EVENTLOGGER.error("broadcast null for record {}", record);
            return false;
        }

        // extract the stream
        final List<Stream> streams = broadcast.getStreams();
        if (streams == null || streams.get(0) == null) {
            EVENTLOGGER.error("stream null / empty for record {}", record);
            return false;
        }
        final String loopStreamId = streams.get(0).getLoopStreamId();

        // create the download URL
        final String url = Configuration.getConfiguration().getProperty(ConfigurationParameter.MP3_BASE_URL,
                "" + ZonedDateTime.now().toEpochSecond() * 1000, loopStreamId);

        try {

            // remember time for later duration calculation
            final long start = System.currentTimeMillis();

            // remove old temporary files
            final String tempFilename = record.getTargetFilename() + ".tmp";
            final File tempFile = new File(tempFilename);
            EVENTLOGGER.info("start downloading on {} the broadcast \"{} - {}\" to file {}", record.getScheduledStart(),
                    record.getProgramTitle(), record.getTitle(), record.getTargetFilename());

            // download to file
            final DownloadHandler handler = new DownloadHandler() {

                private boolean successful = false;
                private long contentLength = -1;
                private long bytesDownloaded = 0;

                @Override
                public void handleDownload(final InputStream input) {

                    try (final FileOutputStream fos = new FileOutputStream(tempFilename, true)) {
                        bytesDownloaded = IOUtils.copyLarge(input, fos);
                        successful = true;
                    } catch (final IOException e) {
                        EVENTLOGGER.error("error downloading url {}", url, e);
                    }

                }

                @Override
                public boolean successful() {
                    return successful;
                }

                @Override
                public long getBytesDownloaded() {
                    if (tempFile.exists()) {
                        return tempFile.length();
                    }
                    return bytesDownloaded;
                }

                @Override
                public long getContentLength() {
                    return contentLength;
                }

                @Override
                public void setContentLength(final long contentLength) {
                    this.contentLength = contentLength;
                }

            };
            successful = download(url, handler);

            FileUtils.moveFile(new File(tempFilename), new File(record.getTargetFilename()));
            EVENTLOGGER.info("done downloading on {} the broadcast \"{} - {}\" to file {} and it took {} ms",
                    record.getScheduledStart(), record.getProgramTitle(), record.getTitle(), record.getTargetFilename(),
                    (System.currentTimeMillis() - start));
        } catch (final Exception e) {
            EVENTLOGGER.error("error downloading url {}", url, e);
            successful = false;
        }

        return successful;

    }

    /**
     * Handle the download.
     * @param url URL
     * @param handler handler to process content
     * @return true if download was successful otherwise false
     */
    public boolean download(final String url, final DownloadHandler handler) {

        final String methodname = "downloadFromUrl(): ";

        boolean result = false;

        final int maxRetries = NumberUtils.toInt(
                Configuration.getConfiguration().getProperty(ConfigurationParameter.NUMBER_OF_RETRIES),
                Integer.valueOf(ConfigurationParameter.NUMBER_OF_RETRIES.getDefaultValue()));
        int retries = 1;
        long bytesDownloaded = 0;

        while (!result && (retries <= maxRetries)) {
            final long startPosition = handler.getBytesDownloaded();

            EVENTLOGGER.info("try {}: downloading from URL {} on position {}", retries, url, startPosition);
            try (final CloseableHttpClient httpclient = httpClientFactory.getHttpClient()) {
                final HttpGet httpGet = new HttpGet(url + "&offset=" + bytesDownloaded);

                final Configuration config = Configuration.getConfiguration();
                final String useragent = config.getProperty(ConfigurationParameter.USER_AGENT_STRING);
                httpGet.addHeader("User-Agent", useragent);
                if (startPosition > 0) {
                    httpGet.addHeader("Range", "bytes=" + startPosition + "-");
                    EVENTLOGGER.info("try {}: Continuing {} on position {}", retries, url, startPosition);
                }

                try (final CloseableHttpResponse response = httpclient.execute(httpGet)) {
                    logger.info(methodname + "try {}: url = {} statuscode = {}", retries, url, response.getStatusLine());
                    final HttpEntity entity = response.getEntity();
                    if (logger.isDebugEnabled()) {
                        debugHeaders(response, entity);
                    }

                    handler.setContentLength(entity.getContentLength());

                    try (final InputStream in = entity.getContent()) {
                        handler.handleDownload(in);
                        result = handler.successful();
                    } finally {
                        bytesDownloaded = handler.getBytesDownloaded();
                    }
                    EntityUtils.consume(entity);
                } catch (final IOException e) {
                    final String message = "try " + retries + ": error downloading from url = " + url + " bytes downloaded = "
                            + bytesDownloaded;
                    logger.error(message, e);
                    EVENTLOGGER.error(message);
                }
            } catch (final IOException e) {
                final String message = "try " + retries + ":error instantiating downloader for url = " + url;
                logger.error(message, e);
                EVENTLOGGER.error(message);
            }

            if (!result && (handler.getContentLength() > 0)) {
                result = percentageForSuccessReached(handler);
            }

            // only increase the retries if we could not download anything.
            // Otherwise we try to resume
            if (!result && (bytesDownloaded == 0)) {
                ++retries;
            }
        }

        return result;
    }

    /**
     * Sets the mp3 tag in the file.
     * @param record record to set the id3 tag for
     */
    void setMp3Tag(final RecordVO record) {

        try {
            final String mp3filename = record.getTargetFilename();
            final Mp3File mp3file = new Mp3File(mp3filename);
            mp3file.removeId3v1Tag();
            mp3file.removeId3v2Tag();
            final MP3FileUtil fileUtil = new MP3FileUtil();
            fileUtil.setId3v1Tag(mp3file, record);
            fileUtil.setId3v2Tag(mp3file, record);

            final String tempFilename = mp3filename + ".tmp";
            mp3file.save(tempFilename);
            final File destFile = new File(mp3filename);
            if (destFile.exists()) {
                destFile.delete();
            }
            FileUtils.moveFile(new File(tempFilename), destFile);
        } catch (UnsupportedTagException | InvalidDataException | IOException | NotSupportedException e) {
            EVENTLOGGER.error("error setting mp3 tag", e);
        }
    }

    /**
     * Debug the headers.
     * @param response response
     * @param entity entity
     */
    void debugHeaders(final CloseableHttpResponse response, final HttpEntity entity) {
        final String methodname = "debugHeaders(): ";
        logger.info("{}start", methodname);

        final Header[] headers = response.getAllHeaders();
        for (final Header header : headers) {
            logger.debug("{}  header = {}: {}", methodname, header.getName(), header.getValue());
        }
        logger.debug("{}  Content-Type = {}", methodname, entity.getContentType());
        logger.debug("{}  Content-Length = {}", methodname, entity.getContentLength());
    }

    /**
     * Checks if the percentage for success is reached.
     * @param handler {@link DownloadHandler}
     * @return true if reached
     */
    boolean percentageForSuccessReached(final DownloadHandler handler) {
        final String methodname = "percentageForSuccessReached(): ";

        final float percentage = NumberUtils.toFloat(
                Configuration.getConfiguration().getProperty(ConfigurationParameter.DOWNLOAD_PERCENTAGE_FOR_SUCCESS),
                Float.valueOf(ConfigurationParameter.DOWNLOAD_PERCENTAGE_FOR_SUCCESS.getDefaultValue()));
        final float downloadPercentage = (float) handler.getBytesDownloaded() / (float) handler.getContentLength() * 100;
        if (logger.isDebugEnabled()) {
            logger.debug("{}downloadPercentage = {}", methodname, downloadPercentage);
        }
        if (downloadPercentage > percentage) {
            EVENTLOGGER.error("although only {} of {} bytes = {} % were downloaded, it is more than configured percentage {}",
                    handler.getBytesDownloaded(), handler.getContentLength(), downloadPercentage, percentage);
            return true;
        }
        return false;

    }

}
