package at.or.vogt.oe1downloader.config;

/**
 * Parameter in configuration file.
 */
public enum ConfigurationParameter {

    /** Rules. */
    RULES("rules.", null),
    /** base URL for JSON. */
    JSON_BASE_URL("json_base_url", "https://audioapi.orf.at/oe1/api/json/current/broadcasts?_s="),
    /** base URL for MP3 downloads. */
    MP3_BASE_URL("mp3_base_url", "http://loopstream01.apa.at/?channel=oe1&shoutcast=0&id="),
    /** target directory for downloads. */
    TARGET_DIRECTORY("target_directory", "./mp3s"),
    /** days to go back. */
    DAYSBACK("daysback", "7"),
    /** user agent string to use for downloading. */
    USER_AGENT_STRING("user_agent_string", "Mozilla/5.0 (X11; Linux x86_64; rv:40.0) Gecko/20100101 Firefox/40.0 Iceweasel/40.0"),
    /**
     * number of parallel downloads.
     */
    NUMBER_OF_PARALLEL_DOWNLOADS("number_of_parallel_downloads", "3"),
    /**
     * number of retries.
     */
    NUMBER_OF_RETRIES("number_of_retries", "3");

    /** name of parameter in configuration. */
    private final String name;

    /** default value. */
    private final String defaultValue;

    /**
     * Constructor.
     * @param name name of the parameter
     */
    private ConfigurationParameter(final String name, final String defaultValue) {
        this.name = name;
        this.defaultValue = defaultValue;
    }

    /**
     * Get the name.
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Get the defaultValue.
     * @return the defaultValue
     */
    public String getDefaultValue() {
        return defaultValue;
    }

}
