
package at.or.vogt.oe1downloader.json.bean;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "type",
    "timestamp",
    "timestampISO",
    "timestampOffset"
})
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

    /**
     * No args constructor for use in serialization
     * 
     */
    public Mark() {
    }

    /**
     * 
     * @param timestampOffset
     * @param timestampISO
     * @param type
     * @param timestamp
     */
    public Mark(String type, Long timestamp, String timestampISO, Long timestampOffset) {
        super();
        this.type = type;
        this.timestamp = timestamp;
        this.timestampISO = timestampISO;
        this.timestampOffset = timestampOffset;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("timestamp")
    public Long getTimestamp() {
        return timestamp;
    }

    @JsonProperty("timestamp")
    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    @JsonProperty("timestampISO")
    public String getTimestampISO() {
        return timestampISO;
    }

    @JsonProperty("timestampISO")
    public void setTimestampISO(String timestampISO) {
        this.timestampISO = timestampISO;
    }

    @JsonProperty("timestampOffset")
    public Long getTimestampOffset() {
        return timestampOffset;
    }

    @JsonProperty("timestampOffset")
    public void setTimestampOffset(Long timestampOffset) {
        this.timestampOffset = timestampOffset;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("type", type).append("timestamp", timestamp).append("timestampISO", timestampISO).append("timestampOffset", timestampOffset).append("additionalProperties", additionalProperties).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(timestampISO).append(timestampOffset).append(additionalProperties).append(type).append(timestamp).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Mark) == false) {
            return false;
        }
        Mark rhs = ((Mark) other);
        return new EqualsBuilder().append(timestampISO, rhs.timestampISO).append(timestampOffset, rhs.timestampOffset).append(additionalProperties, rhs.additionalProperties).append(type, rhs.type).append(timestamp, rhs.timestamp).isEquals();
    }

}
