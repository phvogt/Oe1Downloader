package at.or.vogt.oe1downloader.config;

import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import at.or.vogt.oe1downloader.TestParameters;

/**
 * Tests for {@link Configuration}.
 */
class ConfigurationTest {

    /** Logger. */
    private final Logger logger = LoggerFactory.getLogger(ConfigurationTest.class);

    @BeforeEach
    public void before() {
        Configuration.setConfigFilename(TestParameters.TEST_CONFIG_FILENAME);
    }

    /**
     * Tests the properties.
     */
    @Test
    void testProperties() {

        final String methodname = "testProperties(): ";
        logger.info("{}start", methodname);

        final Configuration dut = Configuration.getConfiguration();
        final Properties props = dut.getProperties();
        Assertions.assertNotNull(props);

        final Set<Object> keys = props.keySet();
        Assertions.assertTrue(keys.size() > 0);
        for (final Object key : keys) {
            final Object value = props.get(key);
            logger.info("{}key = {} / value = {}", methodname, key, value);
        }
    }

    /**
     * Tests {@link Configuration#getProperty(ConfigurationParameter)}.
     */
    @Test
    void testGetProperty() {

        final Configuration dut = Configuration.getConfiguration();
        final String result = dut.getProperty(ConfigurationParameter.DUMP_JSON_LOCATION);
        Assertions.assertNotNull(result);
        Assertions.assertNotEquals(ConfigurationParameter.DUMP_JSON_LOCATION.getDefaultValue(), result);
    }

    /**
     * Tests {@link Configuration#getProperty(ConfigurationParameter)} with empty
     * property.
     * @throws Exception if an error occurs
     */
    @Test
    void testGetPropertyEmpty() throws Exception {

        final Configuration dut = new Configuration();
        dut.loadProperties("src/test/resources/configEmpty.properties");
        final String result = dut.getProperty(ConfigurationParameter.DUMP_JSON_LOCATION);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(ConfigurationParameter.DUMP_JSON_LOCATION.getDefaultValue(), result);
    }

    /**
     * Tests {@link Configuration#getPropertyMap(ConfigurationParameter)}.
     */
    @Test
    void testGetPropertyMap() {

        final Configuration dut = Configuration.getConfiguration();
        final Map<String, String> result = dut.getPropertyMap(ConfigurationParameter.RULES);
        Assertions.assertNotNull(result);
        Assertions.assertTrue(result.size() > 0);
    }

    /**
     * Tests property loading with an error.
     */
    @Test
    void testLoadPropertiesError() {

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
    void testRules() {

        final String methodname = "testRules(): ";
        logger.info("{}start", methodname);

        final Configuration dut = new Configuration();

        final Map<String, String> rulesMap = dut.getPropertyMap(ConfigurationParameter.RULES);

        final Set<String> keys = rulesMap.keySet();
        for (final String key : keys) {
            final String value = rulesMap.get(key);
            logger.info("{}key = {} / value = {}", methodname, key, value);
        }
    }

    @Test
    void testLoadProperties() throws Exception {
        final String methodname = "testLoadProperties(): ";
        logger.info("{}start", methodname);

        final Configuration dut = new Configuration();
        dut.configFileProps = null;

        final Properties result = dut.loadProperties("src/test/resources/config.properties");

        Assertions.assertNotNull(result);
    }

    @Test
    void testGetPropertyConfigurationParameterStringArray() throws Exception {

        final String methodname = "testGetPropertyConfigurationParameterStringArray(): ";
        logger.info("{}start", methodname);

        final Configuration dut = new Configuration();
        dut.configFileProps = null;
        dut.loadProperties("src/test/resources/config.properties");

        final String result = dut
                .getProperty(ConfigurationParameter.MP3_BASE_URL, "1578309254805",
                        "2020-01-02_0459_tl_51_7DaysThu1_1161013.mp3");

        logger.info("{}result = {}", methodname, result);
        Assertions
                .assertEquals(
                        "https://loopstream01.apa.at/?channel=oe1&shoutcast=0&player=radiothek_v1&referer=oe1.orf.at&_=1578309254805&userid=00000000-1111-2222-aaaa-333333333333&id=2020-01-02_0459_tl_51_7DaysThu1_1161013.mp3",
                        result);
    }

}
