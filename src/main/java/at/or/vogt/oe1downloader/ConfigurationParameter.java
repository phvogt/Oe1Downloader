// (c) 2015 by Philipp Vogt
package at.or.vogt.oe1downloader;

/**
 * Parameter in configuration
 */
public enum ConfigurationParameter {

    /** Rules */
    RULES("rules."), /** base URL for JSON. */
    JSON_BASE_URL("json_base_url"), /** target directory for downloads. */
    TARGET_DIRECTORY("target_directory");

    /** name of parameter in configuration. */
    private final String name;

    /**
     * Constructor.
     * @param name
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
