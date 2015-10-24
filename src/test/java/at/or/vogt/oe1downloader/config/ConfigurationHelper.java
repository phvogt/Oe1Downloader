package at.or.vogt.oe1downloader.config;

/**
 * Helper for configuration tests
 */
public class ConfigurationHelper {

    /**
     * Gets the test configuration (src/test/resources/config.properties).
     * @return the configuratino
     */
    public static Configuration getTestConfiguration() {

        final Configuration result = new Configuration();
        result.loadProperties("src/test/resources/config.properties");

        return result;
    }

}
