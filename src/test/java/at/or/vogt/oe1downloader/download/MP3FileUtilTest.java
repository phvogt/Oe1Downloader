package at.or.vogt.oe1downloader.download;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mpatric.mp3agic.Mp3File;

import at.or.vogt.oe1downloader.json.DateParser;

class MP3FileUtilTest {

    /** Logger. */
    private final Logger logger = LoggerFactory.getLogger(MP3FileUtilTest.class);

    @Test
    void testSetId3v1Tag() throws Exception {
        final String methodname = "testSetId3v1Tag(): ";
        logger.info(methodname);

        final RecordVO recordVO = new RecordVO("20170429", "Pasticcio but now it is very long and with $#%",
                "Mit den Händen kann ich es auch",
                "<p>mit Irene Suchy. \"An die Künstler, Dichter und Musiker. Damit wir uns nicht vor dem Firmament zu schämen haben, müssen wir uns endlich aufmachen und mithelfen, dass eine gerechte Ordnung in Staat und Gesellschaft eingesetzt werde.\" <br/>(Ludwig Meidner, 1919).</p>",
                DateParser.parseISO("2017-04-29T08:15:00+02:00"), "41", "matrix", "http://oe1.orf.at/pasticcio");
        final File tmpMp3File = createTmpMp3("test.mp3");
        logger.info("{}tmpMp3File = {}", methodname, tmpMp3File);

        final Mp3File mp3file = new Mp3File(tmpMp3File);

        final MP3FileUtil dut = new MP3FileUtil();
        dut.setId3v1Tag(mp3file, recordVO);
        mp3file.save(FileUtils.getTempDirectoryPath() + File.separator + "testv1.mp3");

    }

    @Test
    void testSetId3v2Tag() throws Exception {
        final String methodname = "testSetId3v1Tag(): ";
        logger.info(methodname);

        final RecordVO recordVO = new RecordVO("20170429", "Pasticcio but now it is very long and with $#%",
                "Mit den Händen kann ich es auch",
                "<p>mit Irene Suchy. \"An die Künstler, Dichter und Musiker. Damit wir uns nicht vor dem Firmament zu schämen haben, müssen wir uns endlich aufmachen und mithelfen, dass eine gerechte Ordnung in Staat und Gesellschaft eingesetzt werde.\" <br/>(Ludwig Meidner, 1919).</p>",
                DateParser.parseISO("2017-04-29T08:15:00+02:00"), "41", "matrix", "http://oe1.orf.at/pasticcio");
        final File tmpMp3File = createTmpMp3("test.mp3");
        logger.info("{}tmpMp3File = {}", methodname, tmpMp3File);

        final Mp3File mp3file = new Mp3File(tmpMp3File);

        final MP3FileUtil dut = new MP3FileUtil();
        dut.setId3v2Tag(mp3file, recordVO);
        mp3file.save(FileUtils.getTempDirectoryPath() + File.separator + "testv2.mp3");

    }

    private File createTmpMp3(final String filename) throws IOException {
        final File tmpMp3File = new File(FileUtils.getTempDirectoryPath() + File.separator + filename);
        FileUtils.deleteQuietly(tmpMp3File);
        FileUtils.copyFile(new File("src/test/resources/test.mp3"), tmpMp3File);

        return tmpMp3File;

    }

}
