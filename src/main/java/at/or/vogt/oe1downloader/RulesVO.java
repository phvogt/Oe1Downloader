// (c) 2015 by Philipp Vogt
package at.or.vogt.oe1downloader;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.log4j.Level;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import at.or.vogt.oe1downloader.json.Sendung;
import at.or.vogt.oe1downloader.json.Tag;

/**
 * Contains the rules.
 */
public class RulesVO {

    /** event logger. */
    private static final EventLogger eventLogger = new EventLogger();

    /** Logger. */
    private final Logger logger = LoggerFactory.getLogger(RulesVO.class);

    /** rules. */
    private final List<RuleVO> rules = new ArrayList<RuleVO>();

    /**
     * loads the rules.
     */
    public void loadRules() {

        final Configuration config = new Configuration();

        eventLogger.log(Level.INFO, "loading rules.");

        final Map<String, String> parsedRules = config.getPropertyMap(ConfigurationParameter.RULES);

        final Map<String, Map<String, String>> rulesMap = parseMap(parsedRules);

        final List<RuleVO> rulesList = convert(rulesMap);
        rules.clear();
        rules.addAll(rulesList);
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
     * @param tage Tag to check
     * @param indexCounter filename index counter
     * @return list of RecordVO
     */
    public List<RecordVO> checkForRecords(final List<Tag> tage, final RuleIndexCounter indexCounter) {

        final List<RecordVO> result = new ArrayList<>();

        for (final Tag tag : tage) {
            final List<RecordVO> records = checkForRecords(tag, indexCounter);
            result.addAll(records);
        }

        return result;
    }

    /**
     * Checks the tag for matching Sendungen and returns them as a list of
     * RecordVO.
     * @param tag Tag to check
     * @param indexCounter filename index counter
     * @return list of RecordVO
     */
    List<RecordVO> checkForRecords(final Tag tag, final RuleIndexCounter indexCounter) {

        final String methodname = "checkForRecords(): ";

        final List<RecordVO> result = new ArrayList<RecordVO>();

        final List<Sendung> sendungen = tag.getSendungen();
        for (final Sendung sendung : sendungen) {
            logger.debug(methodname + "sendung = {}", sendung);
            for (final RuleVO rule : rules) {
                logger.debug(methodname + "  rule = {}", rule);

                if (rule.matches(sendung)) {

                    eventLogger.log(Level.INFO,
                            "will get " + sendung.getDayLabel() + " " + sendung.getTime() + " " + sendung.getTitle());

                    final RecordVO record = new RecordVO(sendung, rule, indexCounter);
                    result.add(record);
                }
            }
        }

        return result;
    }

}
