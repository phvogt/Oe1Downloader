// (c) 2015 by Philipp Vogt
package at.or.vogt.oe1downloader;

import at.or.vogt.oe1downloader.json.Sendung;

/**
 * What to record.
 */
public class RecordVO {

    /** the sendung to record. */
    private final Sendung sendung;
    /** the rule that matched the sendung. */
    private final RuleVO rule;

    /**
     * Constructor.
     * @param sendung sendung
     * @param rule rule
     */
    public RecordVO(final Sendung sendung, final RuleVO rule) {
        super();
        this.sendung = sendung;
        this.rule = rule;
    }

    /**
     * Get the sendung.
     * @return the sendung
     */
    public Sendung getSendung() {
        return sendung;
    }

    /**
     * Get the rule.
     * @return the rule
     */
    public RuleVO getRule() {
        return rule;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("RecordVO [super = ");
        builder.append(super.toString());
        builder.append(" rule=");
        builder.append(rule);
        builder.append("]");
        return builder.toString();
    }

    /**
     * Gets the filen ame for this record.
     * @param counter counter to use
     * @return file name
     */
    public String getFileName(final RuleIndexCounter counter) {

        final int index = counter.getNextIndex(rule);
        final String result = String.format("%02d", index) + "_" + rule.getMp3postfix() + ".mp3";
        return result;

    }

    /**
     * Gets the download URL.
     * @return download URL
     */
    public String getDownloadUrl() {

        final String result = sendung.getUrlStream() + "&shoutcast=0&ua=flash";
        return result;
    }

}
