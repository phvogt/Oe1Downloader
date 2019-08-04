package at.or.vogt.oe1downloader.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import at.or.vogt.oe1downloader.EventLogger;

/**
 * Helper class for the configuration.
 */
public class Configuration {

    /** file name for config properties. */
    private static String CONFIG_FILENAME = "conf/config.properties";

    /** event logger. */
    private static final Logger EVENTLOGGER = EventLogger.getLogger();

    /** Logger. */
    private final Logger logger = LoggerFactory.getLogger(Configuration.class);

    /** contains the properties. */
    Properties configFileProps = new Properties();

    /**
     * Gets the configuration.
     * @return configuration.
     */
    public static Configuration getConfiguration() {

        final Configuration result = new Configuration();
        result.loadProperties(CONFIG_FILENAME);

        return result;
    }

    /**
     * Loads the Properties.
     * @param configFilename file name of configuration file
     * @return the loaded properties
     */
    Properties loadProperties(final String configFilename) {

        logger.info("loading properties from " + configFilename + ".");

        try (final FileInputStream in = new FileInputStream(new File(configFilename))) {
            if (configFileProps == null) {
                configFileProps = new Properties();
            }
            configFileProps.load(in);
        } catch (final IOException e) {
            final String message = "could not load " + configFilename;
            logger.error(message, e);
            EVENTLOGGER.error(message);
            throw new RuntimeException(message, e);
        }

        return configFileProps == null ? null : (Properties) configFileProps.clone();
    }

    /**
     * Parses the properties in a map.
     * @param parameter parameter for the properties
     * @return the properties as a map
     */
    public Map<String, String> getPropertyMap(final ConfigurationParameter parameter) {

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
        return configFileProps == null ? null : (Properties) configFileProps.clone();
    }

    /**
     * Gets the property for the configuration parameter.
     * @param parameter to get
     * @return the configuration value
     */
    public String getProperty(final ConfigurationParameter parameter) {

        final String result = configFileProps.getProperty(parameter.getName());

        if (result != null) {
            return result;
        } else {
            return parameter.getDefaultValue();
        }
    }

    /**
     * Override the configuration file name. Used for testing.
     * @param configFilename configuration file name
     */
    public static void setConfigFilename(final String configFilename) {
        CONFIG_FILENAME = configFilename;
    }

    /**
     * Get the configuration file name. Used for testing.
     * @return the configuration file name
     */
    public static String gtConfigFilename() {
        return CONFIG_FILENAME;
    }

}
