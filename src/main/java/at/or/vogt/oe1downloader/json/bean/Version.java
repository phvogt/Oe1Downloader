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
@JsonPropertyOrder({ "width", "hashCode", "path" })
public class Version {

    @JsonProperty("width")
    private Long width;
    @JsonProperty("hashCode")
    private Long hashCode;
    @JsonProperty("path")
    private String path;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("width")
    public Long getWidth() {
        return width;
    }

    @JsonProperty("width")
    public void setWidth(final Long width) {
        this.width = width;
    }

    @JsonProperty("hashCode")
    public Long getHashCode() {
        return hashCode;
    }

    @JsonProperty("hashCode")
    public void setHashCode(final Long hashCode) {
        this.hashCode = hashCode;
    }

    @JsonProperty("path")
    public String getPath() {
        return path;
    }

    @JsonProperty("path")
    public void setPath(final String path) {
        this.path = path;
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
        builder.append("Version [super = ");
        builder.append(super.toString());
        builder.append(" width=");
        builder.append(width);
        builder.append(", hashCode=");
        builder.append(hashCode);
        builder.append(", path=");
        builder.append(path);
        builder.append(", additionalProperties=");
        builder.append(additionalProperties);
        builder.append("]");
        return builder.toString();
    }

}
