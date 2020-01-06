
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
    "alias",
    "title",
    "loopStreamId",
    "start",
    "startISO",
    "startOffset",
    "end",
    "endISO",
    "endOffset"
})
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

    /**
     * No args constructor for use in serialization
     * 
     */
    public Stream() {
    }

    /**
     * 
     * @param loopStreamId
     * @param endOffset
     * @param startOffset
     * @param start
     * @param alias
     * @param end
     * @param title
     * @param endISO
     * @param startISO
     */
    public Stream(String alias, Object title, String loopStreamId, Long start, String startISO, Long startOffset, Long end, String endISO, Long endOffset) {
        super();
        this.alias = alias;
        this.title = title;
        this.loopStreamId = loopStreamId;
        this.start = start;
        this.startISO = startISO;
        this.startOffset = startOffset;
        this.end = end;
        this.endISO = endISO;
        this.endOffset = endOffset;
    }

    @JsonProperty("alias")
    public String getAlias() {
        return alias;
    }

    @JsonProperty("alias")
    public void setAlias(String alias) {
        this.alias = alias;
    }

    @JsonProperty("title")
    public Object getTitle() {
        return title;
    }

    @JsonProperty("title")
    public void setTitle(Object title) {
        this.title = title;
    }

    @JsonProperty("loopStreamId")
    public String getLoopStreamId() {
        return loopStreamId;
    }

    @JsonProperty("loopStreamId")
    public void setLoopStreamId(String loopStreamId) {
        this.loopStreamId = loopStreamId;
    }

    @JsonProperty("start")
    public Long getStart() {
        return start;
    }

    @JsonProperty("start")
    public void setStart(Long start) {
        this.start = start;
    }

    @JsonProperty("startISO")
    public String getStartISO() {
        return startISO;
    }

    @JsonProperty("startISO")
    public void setStartISO(String startISO) {
        this.startISO = startISO;
    }

    @JsonProperty("startOffset")
    public Long getStartOffset() {
        return startOffset;
    }

    @JsonProperty("startOffset")
    public void setStartOffset(Long startOffset) {
        this.startOffset = startOffset;
    }

    @JsonProperty("end")
    public Long getEnd() {
        return end;
    }

    @JsonProperty("end")
    public void setEnd(Long end) {
        this.end = end;
    }

    @JsonProperty("endISO")
    public String getEndISO() {
        return endISO;
    }

    @JsonProperty("endISO")
    public void setEndISO(String endISO) {
        this.endISO = endISO;
    }

    @JsonProperty("endOffset")
    public Long getEndOffset() {
        return endOffset;
    }

    @JsonProperty("endOffset")
    public void setEndOffset(Long endOffset) {
        this.endOffset = endOffset;
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
        return new ToStringBuilder(this).append("alias", alias).append("title", title).append("loopStreamId", loopStreamId).append("start", start).append("startISO", startISO).append("startOffset", startOffset).append("end", end).append("endISO", endISO).append("endOffset", endOffset).append("additionalProperties", additionalProperties).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(loopStreamId).append(endOffset).append(startOffset).append(start).append(alias).append(end).append(additionalProperties).append(title).append(endISO).append(startISO).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Stream) == false) {
            return false;
        }
        Stream rhs = ((Stream) other);
        return new EqualsBuilder().append(loopStreamId, rhs.loopStreamId).append(endOffset, rhs.endOffset).append(startOffset, rhs.startOffset).append(start, rhs.start).append(alias, rhs.alias).append(end, rhs.end).append(additionalProperties, rhs.additionalProperties).append(title, rhs.title).append(endISO, rhs.endISO).append(startISO, rhs.startISO).isEquals();
    }

}
