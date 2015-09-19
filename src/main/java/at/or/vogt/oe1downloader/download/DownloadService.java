// (c) 2015 by Philipp Vogt
package at.or.vogt.oe1downloader.download;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mpatric.mp3agic.ID3v1;
import com.mpatric.mp3agic.ID3v1Tag;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.NotSupportedException;
import com.mpatric.mp3agic.UnsupportedTagException;

import at.or.vogt.oe1downloader.EventLogger;
import at.or.vogt.oe1downloader.RecordVO;
import at.or.vogt.oe1downloader.config.Configuration;
import at.or.vogt.oe1downloader.config.ConfigurationParameter;
import at.or.vogt.oe1downloader.json.Show;

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
     * Handle the download.
     * @param url URL
     * @param handler handler to process content
     */
    public void download(final String url, final DownloadHandler handler) {

        final String methodname = "downloadFromUrl(): ";

        EVENTLOGGER.info("downloading from URL {}.", url);
        final CloseableHttpClient httpclient = httpClientFactory.getHttpClient();
        final HttpGet httpGet = new HttpGet(url);

        final Configuration config = Configuration.getConfiguration();
        final String useragent = config.getProperty(ConfigurationParameter.USER_AGENT_STRING);
        httpGet.addHeader("User-Agent", useragent);

        try (final CloseableHttpResponse response = httpclient.execute(httpGet)) {
            logger.info(methodname + "url = {} statuscode = {}", url, response.getStatusLine());
            final HttpEntity entity = response.getEntity();
            if (logger.isDebugEnabled()) {
                final Header[] headers = response.getAllHeaders();
                for (final Header header : headers) {
                    logger.debug(methodname + "  header = {}: {}", header.getName(), header.getValue());
                }
                logger.debug(methodname + "  Content-Type = {}", entity.getContentType());
                logger.debug(methodname + "  Content-Length = {}", entity.getContentLength());
            }

            try (final InputStream in = entity.getContent()) {
                handler.handleDownload(in);
            }
            EntityUtils.consume(entity);
        } catch (final IOException e) {
            final String message = "error downloading from url = " + url;
            logger.error(message, e);
            EVENTLOGGER.error(message);
        }
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

        try {
            for (final Future<?> future : futures) {
                final Object result = future.get();
                logger.info(methodname + "result = {}", result);
            }

            logger.info(methodname + "stopping executors");
            executors.shutdown();
            logger.info(methodname + "waiting 60s");
            executors.awaitTermination(60, TimeUnit.SECONDS);
        } catch (final InterruptedException | ExecutionException e) {
            EVENTLOGGER.error("error downloading", e);
        }

    }

    /**
     * Downloads the MP3 from the URL.
     * @param record record to download
     */
    void download(final RecordVO record) {

        final String methodname = "download(): ";

        logger.info(methodname + "record = {}", record);

        final String url = record.getDownloadUrl();
        try {
            // remember time for later duration calculation
            final long start = System.currentTimeMillis();
            final Show show = record.getShow();
            EVENTLOGGER.info("start downloading {} {} {} to {}", show.getDayLabel(), show.getTime(), show.getTitle(),
                    record.getTargetFilename());

            // remove old temporary files
            final String tempFilename = record.getTargetFilename() + ".tmp";
            final File tempFile = new File(tempFilename);
            if (tempFile.exists()) {
                tempFile.delete();
            }

            // download to file
            download(url, new DownloadHandler() {

                @Override
                public void handleDownload(final InputStream input) {
                    try (final FileOutputStream fos = new FileOutputStream(tempFilename)) {
                        IOUtils.copy(input, fos);
                    } catch (final IOException e) {
                        EVENTLOGGER.error("error downloading url {}", url, e);
                    }

                }
            });

            FileUtils.moveFile(new File(tempFilename), new File(record.getTargetFilename()));
            EVENTLOGGER.info("done downloading {} {} {} to {} took {} ms", show.getDayLabel(), show.getTime(), show.getTitle(),
                    record.getTargetFilename(), (System.currentTimeMillis() - start));
        } catch (final Exception e) {
            EVENTLOGGER.error("error downloading url {}", url, e);
        }

    }

    /**
     * Sets the mp3 tag in the file.
     * @param record record to set the id3 tag for
     */
    void setMp3Tag(final RecordVO record) {

        try {
            final String mp3filename = record.getTargetFilename();
            final Mp3File mp3file = new Mp3File(mp3filename);

            final ID3v1 id3v1Tag;
            if (mp3file.hasId3v1Tag()) {
                id3v1Tag = mp3file.getId3v1Tag();
            } else {
                id3v1Tag = new ID3v1Tag();
                mp3file.setId3v1Tag(id3v1Tag);
            }
            id3v1Tag.setArtist("OE1");
            final Show show = record.getShow();
            id3v1Tag.setTitle(show.getDayLabel() + " " + StringUtils.replace(show.getTime(), ":", ""));
            id3v1Tag.setAlbum(show.getShortTitle());
            id3v1Tag.setTrack(record.getIndex());
            id3v1Tag.setGenre(12);
            id3v1Tag.setYear("" + Calendar.getInstance().get(Calendar.YEAR));
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

}
