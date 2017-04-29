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
@JsonPropertyOrder({ "alias", "title", "loopStreamId", "start", "startISO", "startOffset", "end", "endISO", "endOffset" })
public class Stream {

    @JsonProperty("alias")
    private String alias;
    @JsonProperty("title")
    private Object title;
    @JsonProperty("loopStreamId")
    private String loopStreamId;
    @JsonProperty("start")
    private Long start;
    @JsonProperty("startISO")
    private String startISO;
    @JsonProperty("startOffset")
    private Long startOffset;
    @JsonProperty("end")
    private Long end;
    @JsonProperty("endISO")
    private String endISO;
    @JsonProperty("endOffset")
    private Long endOffset;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("alias")
    public String getAlias() {
        return alias;
    }

    @JsonProperty("alias")
    public void setAlias(final String alias) {
        this.alias = alias;
    }

    @JsonProperty("title")
    public Object getTitle() {
        return title;
    }

    @JsonProperty("title")
    public void setTitle(final Object title) {
        this.title = title;
    }

    @JsonProperty("loopStreamId")
    public String getLoopStreamId() {
        return loopStreamId;
    }

    @JsonProperty("loopStreamId")
    public void setLoopStreamId(final String loopStreamId) {
        this.loopStreamId = loopStreamId;
    }

    @JsonProperty("start")
    public Long getStart() {
        return start;
    }

    @JsonProperty("start")
    public void setStart(final Long start) {
        this.start = start;
    }

    @JsonProperty("startISO")
    public String getStartISO() {
        return startISO;
    }

    @JsonProperty("startISO")
    public void setStartISO(final String startISO) {
        this.startISO = startISO;
    }

    @JsonProperty("startOffset")
    public Long getStartOffset() {
        return startOffset;
    }

    @JsonProperty("startOffset")
    public void setStartOffset(final Long startOffset) {
        this.startOffset = startOffset;
    }

    @JsonProperty("end")
    public Long getEnd() {
        return end;
    }

    @JsonProperty("end")
    public void setEnd(final Long end) {
        this.end = end;
    }

    @JsonProperty("endISO")
    public String getEndISO() {
        return endISO;
    }

    @JsonProperty("endISO")
    public void setEndISO(final String endISO) {
        this.endISO = endISO;
    }

    @JsonProperty("endOffset")
    public Long getEndOffset() {
        return endOffset;
    }

    @JsonProperty("endOffset")
    public void setEndOffset(final Long endOffset) {
        this.endOffset = endOffset;
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
        builder.append("Stream [super = ");
        builder.append(super.toString());
        builder.append(" alias=");
        builder.append(alias);
        builder.append(", title=");
        builder.append(title);
        builder.append(", loopStreamId=");
        builder.append(loopStreamId);
        builder.append(", start=");
        builder.append(start);
        builder.append(", startISO=");
        builder.append(startISO);
        builder.append(", startOffset=");
        builder.append(startOffset);
        builder.append(", end=");
        builder.append(end);
        builder.append(", endISO=");
        builder.append(endISO);
        builder.append(", endOffset=");
        builder.append(endOffset);
        builder.append(", additionalProperties=");
        builder.append(additionalProperties);
        builder.append("]");
        return builder.toString();
    }

}
