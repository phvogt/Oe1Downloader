
package at.or.vogt.oe1downloader.json.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "day", "broadcasts", "date", "dateISO", "dateOffset" })
public class Day {

    @JsonProperty("day")
    private Integer day;
    @JsonProperty("broadcasts")
    private List<Broadcast> broadcasts = null;
    @JsonProperty("date")
    private Long date;
    @JsonProperty("dateISO")
    private String dateISO;
    @JsonProperty("dateOffset")
    private Integer dateOffset;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("day")
    public Integer getDay() {
        return day;
    }

    @JsonProperty("day")
    public void setDay(final Integer day) {
        this.day = day;
    }

    @JsonProperty("broadcasts")
    public List<Broadcast> getBroadcasts() {
        return broadcasts == null ? null : new ArrayList<>(broadcasts);
    }

    @JsonProperty("broadcasts")
    public void setBroadcasts(final List<Broadcast> broadcasts) {
        this.broadcasts = broadcasts == null ? null : new ArrayList<>(broadcasts);
    }

    @JsonProperty("date")
    public Long getDate() {
        return date;
    }

    @JsonProperty("date")
    public void setDate(final Long date) {
        this.date = date;
    }

    @JsonProperty("dateISO")
    public String getDateISO() {
        return dateISO;
    }

    @JsonProperty("dateISO")
    public void setDateISO(final String dateISO) {
        this.dateISO = dateISO;
    }

    @JsonProperty("dateOffset")
    public Integer getDateOffset() {
        return dateOffset;
    }

    @JsonProperty("dateOffset")
    public void setDateOffset(final Integer dateOffset) {
        this.dateOffset = dateOffset;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(final String name, final Object value) {
        this.additionalProperties.put(name, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Day [super = ");
        builder.append(super.toString());
        builder.append(" day=");
        builder.append(day);
        builder.append(", broadcasts=");
        builder.append(broadcasts);
        builder.append(", date=");
        builder.append(date);
        builder.append(", dateISO=");
        builder.append(dateISO);
        builder.append(", dateOffset=");
        builder.append(dateOffset);
        builder.append(", additionalProperties=");
        builder.append(additionalProperties);
        builder.append("]");
        return builder.toString();
    }

}
