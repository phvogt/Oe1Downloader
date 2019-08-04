package at.or.vogt.oe1downloader.config;

import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Tests for {@link Configuration}.
 */
public class ConfigurationTest {

    /** Logger. */
    private final Logger logger = LoggerFactory.getLogger(ConfigurationTest.class);

    /**
     * Tests the properties.
     */
    @Test
    public void testProperties() {

        final String methodname = "testProperties(): ";

        final Configuration dut = Configuration.getConfiguration();
        final Properties props = dut.getProperties();
        Assertions.assertNotNull(props);

        final Set<Object> keys = props.keySet();
        Assertions.assertTrue(keys.size() > 0);
        for (final Object key : keys) {
            final Object value = props.get(key);
            logger.info(methodname + "key = {} / value = {}", key, value);
        }
    }

    /**
     * Tests {@link Configuration#getProperty(ConfigurationParameter)}.
     */
    @Test
    public void testGetProperty() {

        final Configuration dut = Configuration.getConfiguration();
        final String result = dut.getProperty(ConfigurationParameter.DAYSBACK);
        Assertions.assertNotNull(result);
        Assertions.assertNotEquals(ConfigurationParameter.DAYSBACK.getDefaultValue(), result);
    }

    /**
     * Tests {@link Configuration#getProperty(ConfigurationParameter)} with empty
     * property.
     * @throws Exception if an error occurs
     */
    @Test
    public void testGetPropertyEmpty() throws Exception {

        final Configuration dut = new Configuration();
        dut.loadProperties("src/test/resources/configEmpty.properties");
        final String result = dut.getProperty(ConfigurationParameter.DAYSBACK);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(ConfigurationParameter.DAYSBACK.getDefaultValue(), result);
    }

    /**
     * Tests {@link Configuration#getPropertyMap(ConfigurationParameter)}.
     */
    @Test
    public void testGetPropertyMap() {

        final Configuration dut = Configuration.getConfiguration();
        final Map<String, String> result = dut.getPropertyMap(ConfigurationParameter.RULES);
        Assertions.assertNotNull(result);
        Assertions.assertTrue(result.keySet().size() > 0);
    }

    /**
     * Tests property loading with an error.
     */
    @Test
    public void testLoadPropertiesError() {

        final Configuration dut = new Configuration();
        final RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            dut.loadProperties("asdf.properties");
        });
        Assertions.assertEquals("could not load asdf.properties", exception.getMessage());
    }

    /**
     * Tests the rules.
     */
    @Test
    public void testRules() {

        final Configuration dut = new Configuration();

        final Map<String, String> rulesMap = dut.getPropertyMap(ConfigurationParameter.RULES);
        final String methodname = "logMap(): ";

        final Set<String> keys = rulesMap.keySet();
        for (final String key : keys) {
            final String value = rulesMap.get(key);
            logger.info(methodname + "key = {} / value = {}", key, value);
        }
    }

    @Test
    public void testLoadProperties() throws Exception {
        final String methodname = "testLoadProperties(): ";
        logger.info(methodname);

        final Configuration dut = new Configuration();
        dut.configFileProps = null;

        final Properties result = dut.loadProperties("src/test/resources/config.properties");

        Assertions.assertNotNull(result);
    }

}
