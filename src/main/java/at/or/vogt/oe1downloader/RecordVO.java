// (c) 2015 by Philipp Vogt
package at.or.vogt.oe1downloader;

import java.io.File;

import at.or.vogt.oe1downloader.json.Sendung;

/**
 * What to record.
 */
public class RecordVO {

    /** the sendung to record. */
    private final Sendung sendung;
    /** the rule that matched the sendung. */
    private final RuleVO rule;
    /** download URL. */
    private final String downloadUrl;
    /** file name. */
    private final String filename;

    /** full target file name. */
    private String targetFilename;

    /**
     * Constructor.
     * @param sendung sendung
     * @param rule rule
     * @param indexCounter counter for file index
     */
    public RecordVO(final Sendung sendung, final RuleVO rule, final RuleIndexCounter indexCounter) {
        super();
        this.sendung = sendung;
        this.rule = rule;
        this.downloadUrl = sendung.getUrlStream() + "&shoutcast=0&ua=flash";
        final int index = indexCounter.getNextIndex(rule);
        filename = String.format("%02d", index) + "_" + rule.getMp3postfix() + ".mp3";

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
     * Get the filename.
     * @return the filename
     */
    public String getFilename() {
        return filename;
    }

    /**
     * Get the downloadUrl.
     * @return the downloadUrl
     */
    public String getDownloadUrl() {
        return downloadUrl;
    }

    /**
     * Get the targetFilename.
     * @return the targetFilename
     */
    public String getTargetFilename() {
        return targetFilename;
    }

    /**
     * Sets the targetFilename.
     * @param targetFilename the targetFilename to set
     */
    public void setTargetFilename(final String targetFilename) {
        this.targetFilename = targetFilename;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("RecordVO [super = ");
        builder.append(super.toString());
        builder.append(" sendung=");
        builder.append(sendung);
        builder.append(", rule=");
        builder.append(rule);
        builder.append(", downloadUrl=");
        builder.append(downloadUrl);
        builder.append(", filename=");
        builder.append(filename);
        builder.append(", targetFilename=");
        builder.append(targetFilename);
        builder.append("]");
        return builder.toString();
    }

    /**
     * Checks if the target file exists.
     * @return true if it exists otherwise false
     */
    public boolean checkTargetFileExists() {

        final File targetFile = new File(targetFilename);

        return targetFile.exists();
    }

}
