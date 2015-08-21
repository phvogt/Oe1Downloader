// (c) 2015 by Philipp Vogt
package at.or.vogt.oe1downloader;

import java.util.HashMap;
import java.util.Map;

/**
 * Counter for index of rules.
 */
public class RuleIndexCounter {

    final Map<RuleVO, Integer> _counters = new HashMap<>();

    /**
     * Gets the next index for a rule.
     * @param rule rule
     * @return index
     */
    public int getNextIndex(final RuleVO rule) {

        int result = rule.getMp3StartIndex();
        if (_counters.containsKey(rule)) {
            result = _counters.get(rule) + 1;
        }
        _counters.put(rule, result);

        return result;

    }

}
