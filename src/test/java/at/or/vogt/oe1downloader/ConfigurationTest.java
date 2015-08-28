// (c) 2015 by Philipp Vogt
package at.or.vogt.oe1downloader;

import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.log4j.PropertyConfigurator;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Tests for {@link Configuration}.
 */
public class ConfigurationTest {

    static {
        PropertyConfigurator.configure("conf/log4j.properties");
    }

    /** Logger. */
    private final Logger logger = LoggerFactory.getLogger(ConfigurationTest.class);

    /**
     * Tests the properties.
     * @throws Exception if an error occurs
     */
    @Test
    public void testProperties() throws Exception {

        final String methodname = "logProperties(): ";

        final Configuration dut = new Configuration();
        final Properties props = dut.getProperties();

        final Set<Object> keys = props.keySet();
        for (final Object key : keys) {
            final Object value = props.get(key);
            logger.info(methodname + "key = {} / value = {}", key, value);
        }
    }

    /**
     * Tests the rules.
     * @throws Exception if an error occurs
     */
    @Test
    public void testRules() throws Exception {

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
