package at.or.vogt.oe1downloader.download;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * What to record.
 */
public class RecordVO {

	/** day. */
	private final String day;
	/** program title. */
	private final String programTitle;
	/** title. */
	private final String title;
	/** sub title. */
	private final String subtitle;
	/** scheduled start. */
	private final LocalDateTime scheduledStart;
	/** download URL. */
	private final String href;
	/** index number of record. */
	private final String index;
	/** file name. */
	private final String filename;

	/** full target file name. */
	private String targetFilename;

	/**
	 * Constructor.
	 *
	 * @param day            day
	 * @param programTitle   program title
	 * @param title          title
	 * @param subtitle       subtitle
	 * @param scheduledStart scheduled start
	 * @param index          file index
	 * @param mp3postfix     postfix for MP3 file
	 * @param href           href URL
	 */
	public RecordVO(final String day, final String programTitle, final String title, final String subtitle,
			final LocalDateTime scheduledStart, final String index, final String mp3postfix, final String href) {
		this.day = day;
		this.programTitle = programTitle;
		this.title = title;
		this.subtitle = subtitle;
		this.scheduledStart = scheduledStart;
		this.href = href;
		this.index = index;
		filename = this.index + "_" + mp3postfix + "_"
				+ scheduledStart.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")) + ".mp3";
	}

	/**
	 * Get the targetFilename.
	 *
	 * @return the targetFilename
	 */
	public String getTargetFilename() {
		return targetFilename;
	}

	/**
	 * Sets the targetFilename.
	 *
	 * @param targetFilename the targetFilename to set
	 */
	public void setTargetFilename(final String targetFilename) {
		this.targetFilename = targetFilename;
	}

	/**
	 * Get the day.
	 *
	 * @return the day
	 */
	public String getDay() {
		return day;
	}

	public String getProgramTitle() {
		return programTitle;
	}

	/**
	 * Get the title.
	 *
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	public String getSubtitle() {
		return subtitle;
	}

	/**
	 * Get the scheduledStart.
	 *
	 * @return the scheduledStart
	 */
	public LocalDateTime getScheduledStart() {
		return scheduledStart;
	}

	/**
	 * Get the href.
	 *
	 * @return the href
	 */
	public String getHref() {
		return href;
	}

	/**
	 * Get the index.
	 *
	 * @return the index
	 */
	public String getIndex() {
		return index;
	}

	/**
	 * Get the filename.
	 *
	 * @return the filename
	 */
	public String getFilename() {
		return filename;
	}

	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append("RecordVO [day=");
		builder.append(day);
		builder.append(", programTitle=");
		builder.append(programTitle);
		builder.append(", title=");
		builder.append(title);
		builder.append(", subtitle=");
		builder.append(subtitle);
		builder.append(", scheduledStart=");
		builder.append(scheduledStart);
		builder.append(", href=");
		builder.append(href);
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
	 *
	 * @return true if it exists otherwise false
	 */
	public boolean checkTargetFileExists() {

		final File targetFile = new File(targetFilename);

		return targetFile.exists();
	}

}
