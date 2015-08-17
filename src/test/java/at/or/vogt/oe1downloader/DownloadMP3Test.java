// (c) 2015 by Philipp Vogt
package at.or.vogt.oe1downloader;

import java.io.FileWriter;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

/**
 * Test for downloading MP3.
 */
public class DownloadMP3Test {

    /**
     * Test to download MP3.
     * @throws Exception if an error occurs
     */
    @Test
    public void testDownloadUrl() throws Exception {

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
        final byte[] mp3 = dut.downloadMp3(url);
        IOUtils.write(mp3, new FileWriter("data/test.mp3"));
    }
}
