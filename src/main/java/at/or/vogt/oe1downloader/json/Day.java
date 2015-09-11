// (c) 2015 by Philipp Vogt
package at.or.vogt.oe1downloader.json;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Represents a Day.
 */
public class Day {

    /** label for day. */
    private final String dayLabel;

    /** all Shows of the Day. */
    private final List<Show> shows = new ArrayList<Show>();

    /**
     * Constructor.
     * @param jsonObj JSON object
     */
    public Day(final JSONObject jsonObj) {

        dayLabel = jsonObj.getString("day_label");
        final JSONArray arr = jsonObj.getJSONArray("list");
        for (int i = 0; i < arr.length(); i++) {
            final JSONObject showObj = arr.getJSONObject(i);
            final Show show = new Show(showObj);
            shows.add(show);
        }

    }

    /**
     * Get the dayLabel.
     * @return the dayLabel
     */
    public String getDayLabel() {
        return dayLabel;
    }

    /**
     * Get the shows.
     * @return the shows
     */
    public List<Show> getShows() {
        return shows;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Day [super = ");
        builder.append(super.toString());
        builder.append(" dayLabel=");
        builder.append(dayLabel);
        builder.append(", shows=");
        builder.append(shows);
        builder.append("]");
        return builder.toString();
    }

    /**
     * Short String representation.
     * @return short String representation
     */
    public String toStringShort() {
        final StringBuilder builder = new StringBuilder();

        builder.append("result.dayLabel = ");
        builder.append(dayLabel);
        builder.append("\n");
        for (final Show show : shows) {
            builder.append("  show: time = ");
            builder.append(show.getTime());
            builder.append(" title = ");
            builder.append(show.getTitle());
            builder.append("\n");
        }
        return builder.toString();
    }

}
