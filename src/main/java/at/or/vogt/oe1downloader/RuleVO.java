// (c) 2015 by Philipp Vogt
package at.or.vogt.oe1downloader;

import org.apache.commons.lang3.StringUtils;

import at.or.vogt.oe1downloader.json.Sendung;

/**
 * Rule.
 */
public class RuleVO {

    /** name of rule. */
    private final String name;
    /** short title. */
    private final String shortTitle;
    /** time the Sendung starts. */
    private final String time;
    /** mp3 file name start index number. */
    private final int mp3StartIndex;
    /** mp3 file name postfix. */
    private final String mp3postfix;

    /**
     * Constructor.
     * @param name name
     * @param shortTitle short title
     * @param time time the Sendung starts
     * @param mp3StartIndex mp3 file name start index number
     * @param mp3postfix mp3 file name postfix
     */
    public RuleVO(final String name, final String shortTitle, final String time, final int mp3StartIndex, final String mp3postfix) {
        super();
        this.name = name;
        this.shortTitle = shortTitle;
        this.time = time;
        this.mp3StartIndex = mp3StartIndex;
        this.mp3postfix = mp3postfix;
    }

    /**
     * Get the name.
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Get the shortTitle.
     * @return the shortTitle
     */
    public String getShortTitle() {
        return shortTitle;
    }

    /**
     * Get the time.
     * @return the time
     */
    public String getTime() {
        return time;
    }

    /**
     * Get the mp3StartIndex.
     * @return the mp3StartIndex
     */
    public int getMp3StartIndex() {
        return mp3StartIndex;
    }

    /**
     * Get the mp3postfix.
     * @return the mp3postfix
     */
    public String getMp3postfix() {
        return mp3postfix;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("RuleVO [super = ");
        builder.append(super.toString());
        builder.append(" name=");
        builder.append(name);
        builder.append(", shortTitle=");
        builder.append(shortTitle);
        builder.append(", time=");
        builder.append(time);
        builder.append(", mp3StartIndex=");
        builder.append(mp3StartIndex);
        builder.append(", mp3postfix=");
        builder.append(mp3postfix);
        builder.append("]");
        return builder.toString();
    }

    /**
     * @param sendung
     * @return
     */
    public boolean matches(final Sendung sendung) {

        if (StringUtils.isNotEmpty(shortTitle)) {
            if (!sendung.getShortTitle().contains(shortTitle)) {
                return false;
            }
        }
        if (StringUtils.isNotEmpty(time)) {
            if (!sendung.getTime().equals(time)) {
                return false;
            }
        }

        return true;
    }

}