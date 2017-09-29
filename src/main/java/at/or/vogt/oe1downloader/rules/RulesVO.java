package at.or.vogt.oe1downloader.rules;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import at.or.vogt.oe1downloader.EventLogger;
import at.or.vogt.oe1downloader.config.Configuration;
import at.or.vogt.oe1downloader.config.ConfigurationParameter;
import at.or.vogt.oe1downloader.download.RecordVO;
import at.or.vogt.oe1downloader.json.bean.Day;
import at.or.vogt.oe1downloader.json.bean.Show;

/**
 * Contains the rules.
 */
public class RulesVO {

    /** event logger. */
    private static final Logger EVENTLOGGER = EventLogger.getLogger();

    /** Logger. */
    private final Logger logger = LoggerFactory.getLogger(RulesVO.class);

    /** rules. */
    private final List<RuleVO> rules = new ArrayList<RuleVO>();

    /**
     * Gets the rules.
     * @return rules
     */
    public static RulesVO getRulesVO() {
        final RulesVO result = new RulesVO();
        result.loadRules();
        return result;
    }

    /**
     * loads the rules.
     */
    public void loadRules() {

        final Configuration config = Configuration.getConfiguration();

        EVENTLOGGER.info("loading rules.");

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
     * Converts the map to a list of RuleVO.
     * @param rulesMap rules to convert
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
     * Checks the day for matching Shows and returns them as a list of RecordVO.
     * @param days Day to check
     * @param indexCounter filename index counter
     * @return list of RecordVO
     */
    public List<RecordVO> checkForRecords(final List<Day> days, final RuleIndexCounter indexCounter) {

        final List<RecordVO> result = new ArrayList<>();

        for (final Day day : days) {
            final List<RecordVO> records = checkForRecords(day, indexCounter);
            result.addAll(records);
        }

        return result;
    }

    /**
     * Checks the day for matching Shows and returns them as a list of RecordVO.
     * @param day Day to check
     * @param indexCounter filename index counter
     * @return list of RecordVO
     */
    List<RecordVO> checkForRecords(final Day day, final RuleIndexCounter indexCounter) {

        final String methodname = "checkForRecords(): ";

        final List<RecordVO> result = new ArrayList<RecordVO>();

        final List<Show> shows = day.getBroadcasts().stream().map(b -> new Show(b)).collect(Collectors.toList());
        for (final Show show : shows) {
            logger.debug(methodname + "show = {}", show);

            rules.stream().filter((r) -> matches(r, show)).findFirst().ifPresent(rule -> {
                EVENTLOGGER.info("will get {} {}/{}",
                        show.getScheduledEnd().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")), show.getProgramTitle(),
                        show.getTitle());
                final String index = String.format("%02d", indexCounter.getNextIndex(rule));
                final RecordVO record = new RecordVO(show.getDay(), show.getTitle(), show.getScheduledStart(), index,
                        rule.getMp3postfix(), show.getHref());
                result.add(record);
            });
        }

        return result;
    }

    /**
     * Checks if the rule matches.
     * @param show the Show to match
     * @return true if it matched, otherwise false
     */
    static boolean matches(final RuleVO rule, final Show show) {

        if (StringUtils.isNotEmpty(rule.getShortTitle())
                && !(show.getTitle().contains(rule.getShortTitle()) || show.getProgramTitle().contains(rule.getShortTitle()))) {
            return false;
        }
        if (StringUtils.isNotEmpty(rule.getTime())
                && !show.getScheduledStart().format(DateTimeFormatter.ofPattern("HH:mm")).equals(rule.getTime())) {
            return false;
        }

        return true;
    }

}
