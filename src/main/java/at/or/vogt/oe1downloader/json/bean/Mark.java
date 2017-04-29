package at.or.vogt.oe1downloader.json.bean;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "type", "timestamp", "timestampISO", "timestampOffset" })
public class Mark {

    @JsonProperty("type")
    private String type;
    @JsonProperty("timestamp")
    private Long timestamp;
    @JsonProperty("timestampISO")
    private String timestampISO;
    @JsonProperty("timestampOffset")
    private Long timestampOffset;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(final String type) {
        this.type = type;
    }

    @JsonProperty("timestamp")
    public Long getTimestamp() {
        return timestamp;
    }

    @JsonProperty("timestamp")
    public void setTimestamp(final Long timestamp) {
        this.timestamp = timestamp;
    }

    @JsonProperty("timestampISO")
    public String getTimestampISO() {
        return timestampISO;
    }

    @JsonProperty("timestampISO")
    public void setTimestampISO(final String timestampISO) {
        this.timestampISO = timestampISO;
    }

    @JsonProperty("timestampOffset")
    public Long getTimestampOffset() {
        return timestampOffset;
    }

    @JsonProperty("timestampOffset")
    public void setTimestampOffset(final Long timestampOffset) {
        this.timestampOffset = timestampOffset;
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
        builder.append("Mark [super = ");
        builder.append(super.toString());
        builder.append(" type=");
        builder.append(type);
        builder.append(", timestamp=");
        builder.append(timestamp);
        builder.append(", timestampISO=");
        builder.append(timestampISO);
        builder.append(", timestampOffset=");
        builder.append(timestampOffset);
        builder.append(", additionalProperties=");
        builder.append(additionalProperties);
        builder.append("]");
        return builder.toString();
    }

}
