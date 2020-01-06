
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
    "category",
    "copyright",
    "versions",
    "text",
    "hashCode",
    "mode",
    "alt"
})
public class Image {

    @JsonProperty("category")
    private String category;
    @JsonProperty("copyright")
    private String copyright;
    @JsonProperty("versions")
    private List<Version> versions = null;
    @JsonProperty("text")
    private String text;
    @JsonProperty("hashCode")
    private Long hashCode;
    @JsonProperty("mode")
    private String mode;
    @JsonProperty("alt")
    private String alt;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public Image() {
    }

    /**
     * 
     * @param mode
     * @param copyright
     * @param versions
     * @param hashCode
     * @param alt
     * @param text
     * @param category
     */
    public Image(String category, String copyright, List<Version> versions, String text, Long hashCode, String mode, String alt) {
        super();
        this.category = category;
        this.copyright = copyright;
        this.versions = versions;
        this.text = text;
        this.hashCode = hashCode;
        this.mode = mode;
        this.alt = alt;
    }

    @JsonProperty("category")
    public String getCategory() {
        return category;
    }

    @JsonProperty("category")
    public void setCategory(String category) {
        this.category = category;
    }

    @JsonProperty("copyright")
    public String getCopyright() {
        return copyright;
    }

    @JsonProperty("copyright")
    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    @JsonProperty("versions")
    public List<Version> getVersions() {
        return versions;
    }

    @JsonProperty("versions")
    public void setVersions(List<Version> versions) {
        this.versions = versions;
    }

    @JsonProperty("text")
    public String getText() {
        return text;
    }

    @JsonProperty("text")
    public void setText(String text) {
        this.text = text;
    }

    @JsonProperty("hashCode")
    public Long getHashCode() {
        return hashCode;
    }

    @JsonProperty("hashCode")
    public void setHashCode(Long hashCode) {
        this.hashCode = hashCode;
    }

    @JsonProperty("mode")
    public String getMode() {
        return mode;
    }

    @JsonProperty("mode")
    public void setMode(String mode) {
        this.mode = mode;
    }

    @JsonProperty("alt")
    public String getAlt() {
        return alt;
    }

    @JsonProperty("alt")
    public void setAlt(String alt) {
        this.alt = alt;
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
        return new ToStringBuilder(this).append("category", category).append("copyright", copyright).append("versions", versions).append("text", text).append("hashCode", hashCode).append("mode", mode).append("alt", alt).append("additionalProperties", additionalProperties).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(mode).append(copyright).append(versions).append(hashCode).append(alt).append(text).append(additionalProperties).append(category).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Image) == false) {
            return false;
        }
        Image rhs = ((Image) other);
        return new EqualsBuilder().append(mode, rhs.mode).append(copyright, rhs.copyright).append(versions, rhs.versions).append(hashCode, rhs.hashCode).append(alt, rhs.alt).append(text, rhs.text).append(additionalProperties, rhs.additionalProperties).append(category, rhs.category).isEquals();
    }

}
