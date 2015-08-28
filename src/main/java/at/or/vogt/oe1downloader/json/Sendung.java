// (c) 2015 by Philipp Vogt
package at.or.vogt.oe1downloader.json;

import org.json.JSONObject;

/**
 * Represents a Sendung.
 */
public class Sendung {

    /** short title. */
    private final String shortTitle;
    /** ID. */
    private final int id;
    /** Title. */
    private final String title;
    /** label for day. */
    private final String dayLabel;
    /** URL for JSON. */
    private final String urlJson;
    /** URL for stream. */
    private final String urlStream;
    /** info. */
    private final String info;
    /** time. */
    private final String time;

    /**
     * Constructor.
     * @param jsonObj JSON object
     */
    public Sendung(final JSONObject jsonObj) {

        this(jsonObj.getString("short_title"), jsonObj.getInt("id"), jsonObj.getString("title"), jsonObj.getString("day_label"),
                jsonObj.getString("url_json"), jsonObj.getString("url_stream"), jsonObj.getString("info"),
                jsonObj.getString("time"));
    }

    /**
     * Constructor.
     * @param shortTitle short title
     * @param id id
     * @param title title
     * @param dayLabel day label
     * @param urlJson url JSON
     * @param urlStream url Stream
     * @param info info
     * @param time time
     */
    public Sendung(final String shortTitle, final int id, final String title, final String dayLabel, final String urlJson,
            final String urlStream, final String info, final String time) {
        super();
        this.shortTitle = shortTitle;
        this.id = id;
        this.title = title;
        this.dayLabel = dayLabel;
        this.urlJson = urlJson;
        this.urlStream = urlStream;
        this.info = info;
        this.time = time;
    }

    /**
     * Get the shortTitle.
     * @return the shortTitle
     */
    public String getShortTitle() {
        return shortTitle;
    }

    /**
     * Get the id.
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Get the title.
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Get the dayLabel.
     * @return the dayLabel
     */
    public String getDayLabel() {
        return dayLabel;
    }

    /**
     * Get the urlJson.
     * @return the urlJson
     */
    public String getUrlJson() {
        return urlJson;
    }

    /**
     * Get the urlStream.
     * @return the urlStream
     */
    public String getUrlStream() {
        return urlStream;
    }

    /**
     * Get the info.
     * @return the info
     */
    public String getInfo() {
        return info;
    }

    /**
     * Get the time.
     * @return the time
     */
    public String getTime() {
        return time;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Sendung [super = ");
        builder.append(super.toString());
        builder.append(" shortTitle=");
        builder.append(shortTitle);
        builder.append(", id=");
        builder.append(id);
        builder.append(", title=");
        builder.append(title);
        builder.append(", dayLabel=");
        builder.append(dayLabel);
        builder.append(", urlJson=");
        builder.append(urlJson);
        builder.append(", urlStream=");
        builder.append(urlStream);
        builder.append(", info=");
        builder.append(info);
        builder.append(", time=");
        builder.append(time);
        builder.append("]");
        return builder.toString();
    }

}
