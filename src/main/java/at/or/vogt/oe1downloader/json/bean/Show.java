package at.or.vogt.oe1downloader.json.bean;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;

import at.or.vogt.oe1downloader.json.DateParser;

/**
 * Represents a Show.
 */
public class Show {

    /** ID. */
    private final int id;
    /** HREF. */
    private final String href;
    /** Title. */
    private final String programTitle;
    /** Title. */
    private final String title;
    /** day. */
    private final String day;
    /** Title. */
    private final String subtitle;
    /** scheduled start. */
    private final LocalDateTime scheduledStart;
    /** scheduled end. */
    private final LocalDateTime scheduledEnd;
    /** start. */
    private final LocalDateTime start;
    /** end. */
    private final LocalDateTime end;
    /** niceTime. */
    private final LocalDateTime niceTime;

    /**
     * Constructor.
     * @param b the broadcast
     */
    public Show(final Broadcast b) {
        this(b.getId(), b.getHref(), b.getProgramTitle(), b.getTitle(), "" + b.getBroadcastDay(),
                b.getSubtitle() == null ? null : Jsoup.parse(b.getSubtitle()).text(),
                DateParser.parseISO(b.getScheduledStartISO()), DateParser.parseISO(b.getScheduledEndISO()),
                DateParser.parseISO(b.getStartISO()), DateParser.parseISO(b.getEndISO()),
                DateParser.parseISO(b.getNiceTimeISO()));
    }

    /**
     * Constructor.
     * @param id             id
     * @param href           href
     * @param programTitle   programTitle
     * @param title          title
     * @param day            day
     * @param subtitle       subtitle
     * @param scheduledStart scheduledStart
     * @param scheduledEnd   scheduledEnd
     * @param start          start
     * @param end            end
     * @param niceTime       niceTime
     */
    public Show(final int id, final String href, final String programTitle, final String title, final String day,
            final String subtitle, final LocalDateTime scheduledStart, final LocalDateTime scheduledEnd,
            final LocalDateTime start, final LocalDateTime end, final LocalDateTime niceTime) {
        this.id = id;
        this.href = href;
        this.programTitle = programTitle == null ? "" : programTitle;
        this.title = title;
        this.day = day;
        this.subtitle = subtitle;
        this.scheduledStart = scheduledStart;
        this.scheduledEnd = scheduledEnd;
        this.start = start;
        this.end = end;
        this.niceTime = niceTime;
    }

    /**
     * Get the id.
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Get the href.
     * @return the href
     */
    public String getHref() {
        return href;
    }

    /**
     * Get the programTitle.
     * @return the programTitle
     */
    public String getProgramTitle() {
        return programTitle;
    }

    /**
     * Get the title.
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Get the day.
     * @return the day
     */
    public String getDay() {
        return day;
    }

    /**
     * Get the subtitle.
     * @return the subtitle
     */
    public String getSubtitle() {
        return subtitle;
    }

    /**
     * Get the scheduledStart.
     * @return the scheduledStart
     */
    public LocalDateTime getScheduledStart() {
        return scheduledStart;
    }

    /**
     * Get the scheduledEnd.
     * @return the scheduledEnd
     */
    public LocalDateTime getScheduledEnd() {
        return scheduledEnd;
    }

    /**
     * Get the start.
     * @return the start
     */
    public LocalDateTime getStart() {
        return start;
    }

    /**
     * Get the end.
     * @return the end
     */
    public LocalDateTime getEnd() {
        return end;
    }

    /**
     * Get the niceTime.
     * @return the niceTime
     */
    public LocalDateTime getNiceTime() {
        return niceTime;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Show [super = ");
        builder.append(super.toString());
        builder.append(" id=");
        builder.append(id);
        builder.append(", href=");
        builder.append(href);
        builder.append(", programTitle=");
        builder.append(programTitle);
        builder.append(", title=");
        builder.append(title);
        builder.append(", day=");
        builder.append(day);
        builder.append(", subtitle=");
        builder.append(subtitle);
        builder.append(", scheduledStart=");
        builder.append(scheduledStart);
        builder.append(", scheduledEnd=");
        builder.append(scheduledEnd);
        builder.append(", start=");
        builder.append(start);
        builder.append(", end=");
        builder.append(end);
        builder.append(", niceTime=");
        builder.append(niceTime);
        builder.append("]");
        return builder.toString();
    }

    public static List<Show> forDay(final Day day) {
        final List<Show> result = new ArrayList<>();
        final List<Broadcast> broadcasts = day.getBroadcasts();
        if (broadcasts != null) {
            broadcasts.stream().forEach(b -> result.add(new Show(b)));
        }
        return result;
    }

}
