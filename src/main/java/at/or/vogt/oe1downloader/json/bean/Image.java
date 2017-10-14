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
@JsonPropertyOrder({ "category", "copyright", "versions", "text", "hashCode", "mode", "alt" })
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

    @JsonProperty("category")
    public String getCategory() {
        return category;
    }

    @JsonProperty("category")
    public void setCategory(final String category) {
        this.category = category;
    }

    @JsonProperty("copyright")
    public String getCopyright() {
        return copyright;
    }

    @JsonProperty("copyright")
    public void setCopyright(final String copyright) {
        this.copyright = copyright;
    }

    @JsonProperty("versions")
    public List<Version> getVersions() {
        return versions == null ? null : new ArrayList<>(versions);
    }

    @JsonProperty("versions")
    public void setVersions(final List<Version> versions) {
        this.versions = versions == null ? null : new ArrayList<>(versions);
    }

    @JsonProperty("text")
    public String getText() {
        return text;
    }

    @JsonProperty("text")
    public void setText(final String text) {
        this.text = text;
    }

    @JsonProperty("hashCode")
    public Long getHashCode() {
        return hashCode;
    }

    @JsonProperty("hashCode")
    public void setHashCode(final Long hashCode) {
        this.hashCode = hashCode;
    }

    @JsonProperty("mode")
    public String getMode() {
        return mode;
    }

    @JsonProperty("mode")
    public void setMode(final String mode) {
        this.mode = mode;
    }

    @JsonProperty("alt")
    public String getAlt() {
        return alt;
    }

    @JsonProperty("alt")
    public void setAlt(final String alt) {
        this.alt = alt;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(final String name, final Object value) {
        this.additionalProperties.put(name, value);
    }

}
