// (c) 2015 by Philipp Vogt
package at.or.vogt.oe1downloader.json;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Represents a Tag.
 */
public class Tag {

    /** label for day. */
    private final String dayLabel;

    /** all Sendungen of the Tag. */
    private final List<Sendung> sendungen = new ArrayList<Sendung>();

    /**
     * Constructor.
     * @param jsonObj JSON object
     */
    public Tag(final JSONObject jsonObj) {

        dayLabel = jsonObj.getString("day_label");
        final JSONArray arr = jsonObj.getJSONArray("list");
        for (int i = 0; i < arr.length(); i++) {
            final JSONObject sendungObj = arr.getJSONObject(i);
            final Sendung sendung = new Sendung(sendungObj);
            sendungen.add(sendung);
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
     * Get the sendungen.
     * @return the sendungen
     */
    public List<Sendung> getSendungen() {
        return sendungen;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Tag [super = ");
        builder.append(super.toString());
        builder.append(" dayLabel=");
        builder.append(dayLabel);
        builder.append(", sendungen=");
        builder.append(sendungen);
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
        for (final Sendung sendung : sendungen) {
            builder.append("  sendung: time = ");
            builder.append(sendung.getTime());
            builder.append(" title = ");
            builder.append(sendung.getTitle());
            builder.append("\n");
        }
        return builder.toString();
    }

}
