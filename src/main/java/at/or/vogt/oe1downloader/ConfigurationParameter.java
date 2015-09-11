// (c) 2015 by Philipp Vogt
package at.or.vogt.oe1downloader;

/**
 * Parameter in configuration file.
 */
public enum ConfigurationParameter {

    /** Rules. */
    RULES("rules."), /** base URL for JSON. */
    JSON_BASE_URL("json_base_url"), /** target directory for downloads. */
    TARGET_DIRECTORY("target_directory"), /** days to go back. */
    DAYSBACK("daysback"), /** user agent string to use for downloading. */
    USER_AGENT_STRING("user_agent_string"), /** number of parallel downloads. */
    NUMBER_OF_PARALLEL_DOWNLOADS("number_of_parallel_downloads");

    /** name of parameter in configuration. */
    private final String name;

    /**
     * Constructor.
     * @param name name of the parameter
     */
    private ConfigurationParameter(final String name) {
        this.name = name;
    }

    /**
     * Get the name.
     * @return the name
     */
    public String getName() {
        return name;
    }

}
