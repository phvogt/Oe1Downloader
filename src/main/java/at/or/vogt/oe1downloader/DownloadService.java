// (c) 2015 by Philipp Vogt
package at.or.vogt.oe1downloader;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Level;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import at.or.vogt.oe1downloader.json.Sendung;

/**
 * Download service for MP3.
 */
public class DownloadService {

    /** event logger. */
    private static final EventLogger eventLogger = new EventLogger();

    /** Logger. */
    private final Logger logger = LoggerFactory.getLogger(DownloadService.class);

    /** target directory. */
    private final String targetDirectory;

    /**
     * Constructor.
     * @param targetDirectory name of target directory
     * @throws IOException if directory can not be created
     */
    public DownloadService(final String targetDirectory) throws IOException {

        this.targetDirectory = targetDirectory;
        final File targetDirFile = new File(targetDirectory);
        FileUtils.forceMkdir(targetDirFile);
    }

    /**
     * Downloads the records.
     * @param records records to download
     */
    public void downloadRecords(final List<RecordVO> records) {

        final String methodname = "downloadRecords(): ";

        final ExecutorService executors = Executors.newFixedThreadPool(3);
        final List<Future<?>> futures = new ArrayList<>();

        for (final RecordVO record : records) {
            logger.info(methodname + "recordVO = {}", record);

            // set the target file name
            final String filePath = targetDirectory + File.separator + record.getFilename();
            record.setTargetFilename(filePath);

            // check if the file already exists
            if (record.checkTargetFileExists()) {
                eventLogger.log(Level.WARN, "will not download " + record.getTargetFilename() + ", because file already exists.");

                logger.info(methodname + "  not downlading, record already exists. record = {}", record);
                continue;
            }

            futures.add(executors.submit(new Runnable() {

                @Override
                public void run() {
                    download(record);
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
            eventLogger.log(Level.ERROR, "error downloading", e);
        }

    }

    /**
     * Downloads the MP3 from the URL.
     * @param record record to download
     */
    public void download(final RecordVO record) {

        final String methodname = "download(): ";

        logger.info(methodname + "record = {}", record);

        final long start = System.currentTimeMillis();
        final Sendung sendung = record.getSendung();
        eventLogger.log(Level.INFO, "start downloading " + sendung.getDayLabel() + " " + sendung.getTime() + " "
                + sendung.getTitle() + " to " + record.getTargetFilename());

        // download to file
        final CloseableHttpClient httpclient = HttpClients.createDefault();

        final String url = record.getDownloadUrl();
        final HttpGet httpGet = new HttpGet(url);

        httpGet.addHeader("User-Agent", "Mozilla/5.0 (X11; Linux x86_64; rv:40.0) Gecko/20100101 Firefox/40.0 Iceweasel/40.0");

        try (final CloseableHttpResponse response1 = httpclient.execute(httpGet)) {
            logger.debug(methodname + "url = {} statuscode = {}", url, response1.getStatusLine());
            final HttpEntity entity1 = response1.getEntity();
            final Header[] headers = response1.getAllHeaders();
            if (logger.isDebugEnabled()) {
                for (final Header header : headers) {
                    logger.debug(methodname + "  header = {}: {}", header.getName(), header.getValue());
                }
                logger.debug(methodname + "  Content-Type = {}", entity1.getContentType());
                logger.debug(methodname + "  Content-Length = {}", entity1.getContentLength());
            }

            final String tempFilename = record.getTargetFilename() + ".tmp";
            final File tempFile = new File(tempFilename);
            if (tempFile.exists()) {
                tempFile.delete();
            }
            try (final InputStream input = entity1.getContent()) {

                try (final FileOutputStream fos = new FileOutputStream(tempFilename)) {
                    IOUtils.copy(input, fos);
                } catch (final IOException e) {
                    eventLogger.log(Level.ERROR, "error downloading url " + url, e);
                }
            }
            EntityUtils.consume(entity1);
            FileUtils.moveFile(new File(tempFilename), new File(record.getTargetFilename()));
            eventLogger.log(Level.INFO,
                    "done downloading " + sendung.getDayLabel() + " " + sendung.getTime() + " " + sendung.getTitle() + " to "
                            + record.getTargetFilename() + " took " + (System.currentTimeMillis() - start) + " ms");

        } catch (final Exception e) {
            eventLogger.log(Level.ERROR, "error downloading url " + url, e);
        }

    }

}
