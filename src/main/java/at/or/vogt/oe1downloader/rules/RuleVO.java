package at.or.vogt.oe1downloader.rules;

/**
 * Rule.
 */
public class RuleVO {

    /** name of rule. */
    private final String name;
    /** short title. */
    private final String title;
    /** time the Show starts. */
    private final String time;
    /** mp3 file name start index number. */
    private final int mp3StartIndex;
    /** mp3 file name postfix. */
    private final String mp3postfix;

    /**
     * Constructor.
     * @param name name
     * @param title title
     * @param time time the Show starts
     * @param mp3StartIndex mp3 file name start index number
     * @param mp3postfix mp3 file name postfix
     */
    public RuleVO(final String name, final String title, final String time, final int mp3StartIndex, final String mp3postfix) {
        this.name = name;
        this.title = title;
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
     * Get the title.
     * @return the title
     */
    public String getShortTitle() {
        return title;
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
        builder.append(", title=");
        builder.append(title);
        builder.append(", time=");
        builder.append(time);
        builder.append(", mp3StartIndex=");
        builder.append(mp3StartIndex);
        builder.append(", mp3postfix=");
        builder.append(mp3postfix);
        builder.append("]");
        return builder.toString();
    }

}
