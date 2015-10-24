package at.or.vogt.oe1downloader;

import java.io.File;

import at.or.vogt.oe1downloader.json.Show;

/**
 * What to record.
 */
public class RecordVO {

    /** the show to record. */
    private final Show show;
    /** the rule that matched the show. */
    private final RuleVO rule;
    /** download URL. */
    private final String downloadUrl;
    /** index number of record. */
    private final String index;
    /** file name. */
    private final String filename;

    /** full target file name. */
    private String targetFilename;

    /**
     * Constructor.
     * @param show Show
     * @param rule rule
     * @param indexCounter counter for file index
     */
    public RecordVO(final Show show, final RuleVO rule, final RuleIndexCounter indexCounter) {
        super();
        this.show = show;
        this.rule = rule;
        this.downloadUrl = show.getUrlStream() + "&shoutcast=0&ua=flash";
        this.index = String.format("%02d", indexCounter.getNextIndex(rule));
        filename = this.index + "_" + rule.getMp3postfix() + "_" + show.getDayLabel() + ".mp3";
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
     * Get the show.
     * @return the show
     */
    public Show getShow() {
        return show;
    }

    /**
     * Get the rule.
     * @return the rule
     */
    public RuleVO getRule() {
        return rule;
    }

    /**
     * Get the downloadUrl.
     * @return the downloadUrl
     */
    public String getDownloadUrl() {
        return downloadUrl;
    }

    /**
     * Get the index.
     * @return the index
     */
    public String getIndex() {
        return index;
    }

    /**
     * Get the filename.
     * @return the filename
     */
    public String getFilename() {
        return filename;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("RecordVO [super = ");
        builder.append(super.toString());
        builder.append(" show=");
        builder.append(show);
        builder.append(", rule=");
        builder.append(rule);
        builder.append(", downloadUrl=");
        builder.append(downloadUrl);
        builder.append(", index=");
        builder.append(index);
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
