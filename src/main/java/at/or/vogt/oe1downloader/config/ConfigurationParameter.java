package at.or.vogt.oe1downloader.config;

/**
 * Parameter in configuration file.
 */
public enum ConfigurationParameter {

    /** Rules. */
    RULES("rules.", null),
    /** broadcast URL for JSON. */
    JSON_BROADCASTS_URL("json_broadcasts_url", "https://audioapi.orf.at/oe1/json/4.0/broadcasts?_o=oe1.orf.at"),
    /** base URL for MP3 downloads. */
    MP3_BASE_URL("mp3_base_url", "http://loopstream01.apa.at/?channel=oe1&shoutcast=0&id="),
    /** target directory for downloads. */
    TARGET_DIRECTORY("target_directory", "./mp3s"),
    /** user agent string to use for downloading. */
    USER_AGENT_STRING("user_agent_string",
            "Mozilla/5.0 (X11; Linux x86_64; rv:40.0) Gecko/20100101 Firefox/40.0 Iceweasel/40.0"),
    /** number of parallel downloads. */
    NUMBER_OF_PARALLEL_DOWNLOADS("number_of_parallel_downloads", "3"),
    /** download percentage for successful download. */
    DOWNLOAD_PERCENTAGE_FOR_SUCCESS("download_percentage_for_success", "100.0"),
    /** number of retries. */
    NUMBER_OF_RETRIES("number_of_retries", "3"),
    /** location to dump the json file to. */
    DUMP_JSON_LOCATION("dump_json_location", "");

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
