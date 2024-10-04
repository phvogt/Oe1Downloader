package at.or.vogt.oe1downloader.download;

import java.time.format.DateTimeFormatter;
import java.util.Calendar;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mpatric.mp3agic.ID3v1;
import com.mpatric.mp3agic.ID3v1Tag;
import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.ID3v23Tag;
import com.mpatric.mp3agic.Mp3File;

public class MP3FileUtil {

    /** Logger. */
    private final Logger logger = LoggerFactory.getLogger(MP3FileUtil.class);

    ID3v1 setId3v1Tag(final Mp3File mp3file, final RecordVO record) {
        final String methodname = "setId3v1Tag(): ";
        logger.info(methodname);

        final ID3v1 id3v1Tag = new ID3v1Tag();
        setID3Tag(id3v1Tag, record);
        mp3file.setId3v1Tag(id3v1Tag);

        return id3v1Tag;
    }

    ID3v2 setId3v2Tag(final Mp3File mp3file, final RecordVO record) {

        final String methodname = "setId3v1Tag(): ";
        logger.info(methodname);

        final ID3v2 id3v2Tag = new ID3v23Tag();

        setID3Tag(id3v2Tag, record);
        mp3file.setId3v2Tag(id3v2Tag);

        return id3v2Tag;
    }

    private void setID3Tag(final ID3v1 id3v1Tag, final RecordVO record) {
        final String methodname = "setID3Tag(): ";
        logger.info(methodname);

        id3v1Tag.setArtist("OE1");
        final String date = record.getScheduledStart().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        final String title = record.getProgramTitle() + " " + record.getTitle();
        id3v1Tag.setTitle(date + " " + title.trim());
        id3v1Tag.setAlbum(date);
        id3v1Tag.setTrack(record.getIndex());
        id3v1Tag.setGenre(12);
        final String subtitle = record.getSubtitle();
        id3v1Tag.setComment(StringUtils.isEmpty(subtitle) ? "" : Jsoup.parse(subtitle).text());
        id3v1Tag.setYear("" + Calendar.getInstance().get(Calendar.YEAR));

        logger.info("{}id3v1Tag = {}", methodname, id3v1Tag);
    }

}
