// (c) 2015 by Philipp Vogt
package at.or.vogt.oe1downloader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Level;

/**
 * Helper class for the configuration.
 */
public class Configuration {

    /** file name for config properties. */
    private static final String CONFIG_FILENAME = "conf/config.properties";

    /** event logger. */
    private static final EventLogger EVENTLOGGER = new EventLogger();

    /** contains the properties. */
    private final Properties configFileProps;

    /**
     * Constructor.
     */
    public Configuration() {
        configFileProps = loadProperties();
    }

    /**
     * Loads the Properties.
     * @return the loaded properties
     */
    private Properties loadProperties() {

        EVENTLOGGER.log(Level.INFO, "loading properties from " + CONFIG_FILENAME + ".");

        final Properties result = new Properties();

        try (final FileInputStream in = new FileInputStream(new File(CONFIG_FILENAME))) {
            result.load(in);
        } catch (final IOException e) {
            final String message = "could not load " + CONFIG_FILENAME;
            EVENTLOGGER.log(Level.ERROR, message, e);
            throw new RuntimeException(message, e);
        }

        return result;
    }

    /**
     * Parses the properties in a map.
     * @param parameter parameter for the properties
     * @return the properties as a map
     */
    Map<String, String> getPropertyMap(final ConfigurationParameter parameter) {

        final String propertyPrefix = parameter.getName();
        final Map<String, String> result = new LinkedHashMap<String, String>();

        final List<String> keys = new ArrayList<String>(configFileProps.stringPropertyNames());
        Collections.sort(keys);
        for (final String key : keys) {
            if (key.startsWith(propertyPrefix)) {
                final String mapKey = key.substring(propertyPrefix.length());
                final String value = configFileProps.getProperty(key);
                result.put(mapKey, value);
            }

        }

        return result;
    }

    /**
     * Get the properties.
     * @return the configFileProps
     */
    Properties getProperties() {
        return configFileProps;
    }

    /**
     * Gets the property for the configuration parameter.
     * @param parameter to get
     * @param defaultValue default value to return if parameter is null
     * @return the configuration value
     */
    String getProperty(final ConfigurationParameter parameter, final String defaultValue) {

        final String result = configFileProps.getProperty(parameter.getName());

        if (result != null) {
            return result;
        } else {
            return defaultValue;
        }
    }

}
