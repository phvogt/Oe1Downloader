// (c) 2015 by Philipp Vogt
package at.or.vogt.oe1downloader.config;

import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.log4j.PropertyConfigurator;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Tests for {@link Configuration}.
 */
public class ConfigurationTest {

    static {
        PropertyConfigurator.configure("src/test/resources/log4j.properties");
    }

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
        Assert.assertNotNull(props);

        final Set<Object> keys = props.keySet();
        Assert.assertTrue(keys.size() > 0);
        for (final Object key : keys) {
            final Object value = props.get(key);
            logger.info(methodname + "key = {} / value = {}", key, value);
        }
    }

    /**
     * Tests {@link Configuration#getProperty(ConfigurationParameter, String)}.
     */
    @Test
    public void testGetProperty() {

        final Configuration dut = Configuration.getConfiguration();
        final String result = dut.getProperty(ConfigurationParameter.DAYSBACK);
        Assert.assertNotNull(result);
        Assert.assertNotEquals(ConfigurationParameter.DAYSBACK.getDefaultValue(), result);
    }

    /**
     * Tests {@link Configuration#getProperty(ConfigurationParameter, String)}
     * with empty property.
     * @throws Exception if an error occurs
     */
    @Test
    public void testGetPropertyEmpty() throws Exception {

        final Configuration dut = new Configuration();
        dut.loadProperties("src/test/resources/configEmpty.properties");
        final String result = dut.getProperty(ConfigurationParameter.DAYSBACK);
        Assert.assertNotNull(result);
        Assert.assertEquals(ConfigurationParameter.DAYSBACK.getDefaultValue(), result);
    }

    /**
     * Tests {@link Configuration#getPropertyMap(ConfigurationParameter)}.
     */
    @Test
    public void testGetPropertyMap() {

        final Configuration dut = Configuration.getConfiguration();
        final Map<String, String> result = dut.getPropertyMap(ConfigurationParameter.RULES);
        Assert.assertNotNull(result);
        Assert.assertTrue(result.keySet().size() > 0);
    }

    /**
     * Tests property loading with an error.
     */
    @Test(expected = RuntimeException.class)
    public void testLoadPropertiesError() {

        final Configuration dut = new Configuration();
        dut.loadProperties("asdf.properties");
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

}
