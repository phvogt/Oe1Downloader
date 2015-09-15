// (c) 2015 by Philipp Vogt
package at.or.vogt.oe1downloader;

import org.apache.log4j.PropertyConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import at.or.vogt.oe1downloader.json.Show;

/**
 * Test for downloading MP3.
 */
public class DownloadServiceTest {

    static {
        PropertyConfigurator.configure("conf/log4j.properties");
    }

    /** Logger. */
    private final Logger logger = LoggerFactory.getLogger(DownloadServiceTest.class);

    /**
     * Test to download MP3.
     * @throws Exception if an error occurs
     */
    // @Test
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
        final DownloadService dut = new DownloadService("data/test");

        final Show show = new Show("a", 1, "title", "today", "x", url, "", "");
        final RuleVO rule = new RuleVO("1", "a", null, 60, "test");
        final RuleIndexCounter indexCounter = new RuleIndexCounter();
        final RecordVO record = new RecordVO(show, rule, indexCounter);
        record.setTargetFilename("data/test/test.mp3");
        dut.download(record);
        dut.setMp3Tag(record);
        logger.info(methodname + "done");

    }

}
