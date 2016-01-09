package at.or.vogt.oe1downloader.cli;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

/**
 * Option on the command line.
 */
public enum CliOption {

    /** Option to show help. */
    HELP("h", false, "help"),
    /** Option with target directory. */
    TARGET_DIR("d", true, "target directory to use");

    /** option name. */
    private final String optionName;
    /** flag if it has arguments. */
    private final boolean hasArguments;
    /** description. */
    private final String description;

    /**
     * Command line option.
     * 
     * @param optionName
     *            option name
     * @param hasArguments
     *            flag if it has arguments
     * @param description
     *            description
     */
    private CliOption(final String optionName, final boolean hasArguments, final String description) {
	this.optionName = optionName;
	this.hasArguments = hasArguments;
	this.description = description;
    }

    /**
     * Get the optionName.
     * 
     * @return the optionName
     */
    public String getOptionName() {
	return optionName;
    }

    /**
     * Get the hasArguments.
     * 
     * @return the hasArguments
     */
    public boolean isHasArguments() {
	return hasArguments;
    }

    /**
     * Get the description.
     * 
     * @return the description
     */
    public String getDescription() {
	return description;
    }

    /**
     * Get the optionName.
     * 
     * @return Option
     */
    public Option getOption() {
	return new Option(optionName, hasArguments, description);
    }

    /**
     * Get all options.
     * 
     * @return all options
     */
    public static Options getOptions() {

	final Options result = new Options();

	for (final CliOption option : values()) {
	    result.addOption(option.getOption());
	}
	return result;
    }

}
