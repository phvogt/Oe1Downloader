// (c) 2015 by Philipp Vogt
package at.or.vogt.oe1downloader;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Test for downloading MP3.
 */
public class DownloadMP3Test {

    static {
        PropertyConfigurator.configure("conf/log4j.properties");
    }

    /** Logger. */
    private final Logger logger = LoggerFactory.getLogger(DownloadMP3Test.class);

    /**
     * Test to download MP3.
     * @throws Exception if an error occurs
     */
    @Test
    public void testDownloadUrl() throws Exception {

        final String methodname = "testDownloadUrl(): ";

        // curl
        // 'http://loopstream01.apa.at/?channel=oe1&shoutcast=0&ua=flash&id=20150813_0500_1_2_nachrichten_XXX_w_'
        // -H 'Host: loopstream01.apa.at' -H 'User-Agent: Mozilla/5.0 (X11;
        // Linux x86_64; rv:40.0) Gecko/20100101 Firefox/40.0 Iceweasel/40.0' -H
        // 'Accept:
        // text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8' -H
        // 'Accept-Language: de,en;q=0.5' -H 'Accept-Encoding: gzip, deflate' -H
        // 'DNT: 1' -H 'Referer:
        // http://oe1.orf.at/static/swf/oe1-audioplayer.swf' -H 'Connection:
        // keep-alive'

        final String url = "http://loopstream01.apa.at/?channel=oe1&shoutcast=0&ua=flash&id=20150813_0500_1_2_nachrichten_XXX_w_";
        final DownloadMp3 dut = new DownloadMp3();
        dut.downloadMp3(url, new IDownloadHandler() {

            @Override
            public void processFile(final InputStream input) {
                try (final FileOutputStream fos = new FileOutputStream(new File("data/test.mp3"))) {
                    IOUtils.copy(input, fos);

                } catch (final IOException e) {
                    logger.error(methodname, e);
                }
            }
        });
        logger.info(methodname + "done");

    }

}
