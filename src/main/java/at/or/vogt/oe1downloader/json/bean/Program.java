
package at.or.vogt.oe1downloader.json.bean;

import java.util.HashMap;
import java.util.List;
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
    "day",
    "broadcasts"
})
public class Program {

    @JsonProperty("day")
    private Long day;
    @JsonProperty("broadcasts")
    private List<Broadcast> broadcasts = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public Program() {
    }

    /**
     * 
     * @param broadcasts
     * @param day
     */
    public Program(Long day, List<Broadcast> broadcasts) {
        super();
        this.day = day;
        this.broadcasts = broadcasts;
    }

    @JsonProperty("day")
    public Long getDay() {
        return day;
    }

    @JsonProperty("day")
    public void setDay(Long day) {
        this.day = day;
    }

    @JsonProperty("broadcasts")
    public List<Broadcast> getBroadcasts() {
        return broadcasts;
    }

    @JsonProperty("broadcasts")
    public void setBroadcasts(List<Broadcast> broadcasts) {
        this.broadcasts = broadcasts;
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
        return new ToStringBuilder(this).append("day", day).append("broadcasts", broadcasts).append("additionalProperties", additionalProperties).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(broadcasts).append(additionalProperties).append(day).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Program) == false) {
            return false;
        }
        Program rhs = ((Program) other);
        return new EqualsBuilder().append(broadcasts, rhs.broadcasts).append(additionalProperties, rhs.additionalProperties).append(day, rhs.day).isEquals();
    }

}
