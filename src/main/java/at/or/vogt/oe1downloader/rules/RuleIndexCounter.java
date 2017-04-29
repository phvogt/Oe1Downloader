package at.or.vogt.oe1downloader.rules;

import java.util.HashMap;
import java.util.Map;

/**
 * Counter for index of rules.
 */
public class RuleIndexCounter {

    /** internal map with a counter for each index. */
    private final Map<RuleVO, Integer> counters = new HashMap<>();

    /**
     * Gets the next index for a rule.
     * @param rule rule
     * @return index
     */
    public int getNextIndex(final RuleVO rule) {

        int result = rule.getMp3StartIndex();
        if (counters.containsKey(rule)) {
            result = counters.get(rule) + 1;
        }
        counters.put(rule, result);

        return result;

    }

}
