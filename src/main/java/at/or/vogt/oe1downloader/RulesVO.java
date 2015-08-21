// (c) 2015 by Philipp Vogt
package at.or.vogt.oe1downloader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import at.or.vogt.oe1downloader.json.Sendung;
import at.or.vogt.oe1downloader.json.Tag;

/**
 * Contains the rules.
 */
public class RulesVO {

    /** file name for config properties. */
    private static final String CONFIG_FILENAME = "conf/config.properties";
    /** prefix for rules in config. */
    private static final String CONFIG_RULES_PREFIX = "rules.";

    /** Logger. */
    private final Logger logger = LoggerFactory.getLogger(RulesVO.class);

    /** rules. */
    private final List<RuleVO> rules = new ArrayList<RuleVO>();

    /**
     * loads the rules.
     */
    public void loadRules() {
        final Properties configFileProps = loadProperties();

        final Map<String, String> parsedRules = parseRules(configFileProps);

        final Map<String, Map<String, String>> rulesMap = parseMap(parsedRules);

        final List<RuleVO> rulesList = convert(rulesMap);
        rules.clear();
        rules.addAll(rulesList);
    }

    /**
     * Loads the Properties.
     * @return the loaded properties
     */
    Properties loadProperties() {
        final Properties configFileProps = new Properties();
        try (final FileInputStream in = new FileInputStream(new File(CONFIG_FILENAME))) {
            configFileProps.load(in);
        } catch (final IOException e) {
            throw new RuntimeException("error loading " + CONFIG_FILENAME, e);
        }
        return configFileProps;
    }

    /**
     * Parses the rules in a map.
     * @param properties the properties
     * @return the parsed rules
     */
    Map<String, String> parseRules(final Properties properties) {

        final Map<String, String> result = new LinkedHashMap<String, String>();

        final Enumeration<Object> keyEnum = properties.keys();
        while (keyEnum.hasMoreElements()) {
            final String key = (String) keyEnum.nextElement();
            if (key.startsWith(CONFIG_RULES_PREFIX)) {
                final String mapKey = key.substring(CONFIG_RULES_PREFIX.length());
                final String value = properties.getProperty(key);
                result.put(mapKey, value);
            }

        }

        return result;
    }

    /**
     * Parses the map.
     * @param map map to parse
     * @return map with rule names and their values
     */
    Map<String, Map<String, String>> parseMap(final Map<String, String> map) {

        final Map<String, Map<String, String>> result = new TreeMap<String, Map<String, String>>();

        final Set<String> keys = map.keySet();
        for (final String key : keys) {
            final String value = map.get(key);
            final String[] keyparts = key.split("\\.");
            Map<String, String> ruleMap = result.get(keyparts[0]);
            if (ruleMap == null) {
                ruleMap = new TreeMap<String, String>();
                result.put(keyparts[0], ruleMap);
            }
            ruleMap.put(keyparts[1], value);

        }

        return result;
    }

    /**
     * Converts the map to a list of RuleVO
     * @param rules to convert
     * @return list of rules
     */
    List<RuleVO> convert(final Map<String, Map<String, String>> rulesMap) {

        final List<RuleVO> result = new ArrayList<RuleVO>();

        final Set<String> rulesNames = rulesMap.keySet();
        for (final String rulesName : rulesNames) {
            final Map<String, String> rulesProps = rulesMap.get(rulesName);
            final RuleVO rule = new RuleVO(rulesName, rulesProps.get("shortTitle"), rulesProps.get("time"),
                    Integer.valueOf(rulesProps.get("mp3StartIndex")), rulesProps.get("mp3postfix"));
            result.add(rule);
        }

        return result;
    }

    /**
     * Checks the tag for matching Sendungen and returns them as a list of
     * RecordVO.
     * @param tag Tag to check
     * @return list of RecordVO
     */
    public List<RecordVO> checkForRecords(final List<Tag> tage) {

        final List<RecordVO> result = new ArrayList<>();

        for (final Tag tag : tage) {
            final List<RecordVO> records = checkForRecords(tag);
            result.addAll(records);
        }

        return result;
    }

    /**
     * Checks the tag for matching Sendungen and returns them as a list of
     * RecordVO.
     * @param tag Tag to check
     * @return list of RecordVO
     */
    List<RecordVO> checkForRecords(final Tag tag) {

        final String methodname = "checkForRecords(): ";

        final List<RecordVO> result = new ArrayList<RecordVO>();

        final List<Sendung> sendungen = tag.getSendungen();
        for (final Sendung sendung : sendungen) {
            logger.debug(methodname + "sendung = {}", sendung);
            for (final RuleVO rule : rules) {
                logger.debug(methodname + "  rule = {}", rule);

                if (rule.matches(sendung)) {
                    final RecordVO record = new RecordVO(sendung, rule);
                    result.add(record);
                }
            }
        }

        return result;
    }

}
