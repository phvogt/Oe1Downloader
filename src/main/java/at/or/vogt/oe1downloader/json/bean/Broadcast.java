
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
@JsonPropertyOrder({ "href", "station", "entity", "id", "broadcastDay", "programKey", "program", "programTitle", "title",
        "subtitle", "ressort", "state", "isOnDemand", "isGeoProtected", "start", "startISO", "startOffset", "scheduledStart",
        "scheduledStartISO", "scheduledStartOffset", "end", "endISO", "endOffset", "scheduledEnd", "scheduledEndISO",
        "scheduledEndOffset", "niceTime", "niceTimeISO", "niceTimeOffset" })
public class Broadcast {

    @JsonProperty("href")
    private String href;
    @JsonProperty("station")
    private String station;
    @JsonProperty("entity")
    private String entity;
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("broadcastDay")
    private Integer broadcastDay;
    @JsonProperty("programKey")
    private String programKey;
    @JsonProperty("program")
    private String program;
    @JsonProperty("programTitle")
    private String programTitle;
    @JsonProperty("title")
    private String title;
    @JsonProperty("subtitle")
    private String subtitle;
    @JsonProperty("ressort")
    private String ressort;
    @JsonProperty("state")
    private String state;
    @JsonProperty("isOnDemand")
    private Boolean isOnDemand;
    @JsonProperty("isGeoProtected")
    private Boolean isGeoProtected;
    @JsonProperty("start")
    private Long start;
    @JsonProperty("startISO")
    private String startISO;
    @JsonProperty("startOffset")
    private Integer startOffset;
    @JsonProperty("scheduledStart")
    private Long scheduledStart;
    @JsonProperty("scheduledStartISO")
    private String scheduledStartISO;
    @JsonProperty("scheduledStartOffset")
    private Integer scheduledStartOffset;
    @JsonProperty("end")
    private Long end;
    @JsonProperty("endISO")
    private String endISO;
    @JsonProperty("endOffset")
    private Integer endOffset;
    @JsonProperty("scheduledEnd")
    private Long scheduledEnd;
    @JsonProperty("scheduledEndISO")
    private String scheduledEndISO;
    @JsonProperty("scheduledEndOffset")
    private Integer scheduledEndOffset;
    @JsonProperty("niceTime")
    private Long niceTime;
    @JsonProperty("niceTimeISO")
    private String niceTimeISO;
    @JsonProperty("niceTimeOffset")
    private Integer niceTimeOffset;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * Constructor.
     */
    public Broadcast() {
    }

    /**
     * Constructor.
     * @param href href
     * @param station station
     * @param entity entity
     * @param id id
     * @param broadcastDay broadcastDay
     * @param programKey programKey
     * @param program program
     * @param programTitle program title
     * @param title title
     * @param subtitle subtitle
     * @param ressort ressort
     * @param state state
     * @param isOnDemand isOnDemand
     * @param isGeoProtected isGeoProtected
     * @param start start
     * @param startISO startISO
     * @param startOffset startOffset
     * @param scheduledStart scheduledStart
     * @param scheduledStartISO scheduledStartISO
     * @param scheduledStartOffset scheduledStartOffset
     * @param end end
     * @param endISO endISO
     * @param endOffset endOffset
     * @param scheduledEnd scheduledEnd
     * @param scheduledEndISO scheduledEndISO
     * @param scheduledEndOffset scheduledEndOffset
     * @param niceTime niceTime
     * @param niceTimeISO niceTimeISO
     * @param niceTimeOffset niceTimeOffset
     * @param additionalProperties additionalProperties
     */
    public Broadcast(final String href, final String station, final String entity, final Integer id, final Integer broadcastDay,
            final String programKey, final String program, final String programTitle, final String title, final String subtitle,
            final String ressort, final String state, final Boolean isOnDemand, final Boolean isGeoProtected, final Long start,
            final String startISO, final Integer startOffset, final Long scheduledStart, final String scheduledStartISO,
            final Integer scheduledStartOffset, final Long end, final String endISO, final Integer endOffset,
            final Long scheduledEnd, final String scheduledEndISO, final Integer scheduledEndOffset, final Long niceTime,
            final String niceTimeISO, final Integer niceTimeOffset, final Map<String, Object> additionalProperties) {
        this.href = href;
        this.station = station;
        this.entity = entity;
        this.id = id;
        this.broadcastDay = broadcastDay;
        this.programKey = programKey;
        this.program = program;
        this.programTitle = programTitle;
        this.title = title;
        this.subtitle = subtitle;
        this.ressort = ressort;
        this.state = state;
        this.isOnDemand = isOnDemand;
        this.isGeoProtected = isGeoProtected;
        this.start = start;
        this.startISO = startISO;
        this.startOffset = startOffset;
        this.scheduledStart = scheduledStart;
        this.scheduledStartISO = scheduledStartISO;
        this.scheduledStartOffset = scheduledStartOffset;
        this.end = end;
        this.endISO = endISO;
        this.endOffset = endOffset;
        this.scheduledEnd = scheduledEnd;
        this.scheduledEndISO = scheduledEndISO;
        this.scheduledEndOffset = scheduledEndOffset;
        this.niceTime = niceTime;
        this.niceTimeISO = niceTimeISO;
        this.niceTimeOffset = niceTimeOffset;
        this.additionalProperties = additionalProperties;
    }

    @JsonProperty("href")
    public String getHref() {
        return href;
    }

    @JsonProperty("href")
    public void setHref(final String href) {
        this.href = href;
    }

    @JsonProperty("station")
    public String getStation() {
        return station;
    }

    @JsonProperty("station")
    public void setStation(final String station) {
        this.station = station;
    }

    @JsonProperty("entity")
    public String getEntity() {
        return entity;
    }

    @JsonProperty("entity")
    public void setEntity(final String entity) {
        this.entity = entity;
    }

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(final Integer id) {
        this.id = id;
    }

    @JsonProperty("broadcastDay")
    public Integer getBroadcastDay() {
        return broadcastDay;
    }

    @JsonProperty("broadcastDay")
    public void setBroadcastDay(final Integer broadcastDay) {
        this.broadcastDay = broadcastDay;
    }

    @JsonProperty("programKey")
    public String getProgramKey() {
        return programKey;
    }

    @JsonProperty("programKey")
    public void setProgramKey(final String programKey) {
        this.programKey = programKey;
    }

    @JsonProperty("program")
    public String getProgram() {
        return program;
    }

    @JsonProperty("program")
    public void setProgram(final String program) {
        this.program = program;
    }

    @JsonProperty("programTitle")
    public String getProgramTitle() {
        return programTitle;
    }

    @JsonProperty("programTitle")
    public void setProgramTitle(final String programTitle) {
        this.programTitle = programTitle;
    }

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("title")
    public void setTitle(final String title) {
        this.title = title;
    }

    @JsonProperty("subtitle")
    public String getSubtitle() {
        return subtitle;
    }

    @JsonProperty("subtitle")
    public void setSubtitle(final String subtitle) {
        this.subtitle = subtitle;
    }

    @JsonProperty("ressort")
    public String getRessort() {
        return ressort;
    }

    @JsonProperty("ressort")
    public void setRessort(final String ressort) {
        this.ressort = ressort;
    }

    @JsonProperty("state")
    public String getState() {
        return state;
    }

    @JsonProperty("state")
    public void setState(final String state) {
        this.state = state;
    }

    @JsonProperty("isOnDemand")
    public Boolean getIsOnDemand() {
        return isOnDemand;
    }

    @JsonProperty("isOnDemand")
    public void setIsOnDemand(final Boolean isOnDemand) {
        this.isOnDemand = isOnDemand;
    }

    @JsonProperty("isGeoProtected")
    public Boolean getIsGeoProtected() {
        return isGeoProtected;
    }

    @JsonProperty("isGeoProtected")
    public void setIsGeoProtected(final Boolean isGeoProtected) {
        this.isGeoProtected = isGeoProtected;
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
    public Integer getStartOffset() {
        return startOffset;
    }

    @JsonProperty("startOffset")
    public void setStartOffset(final Integer startOffset) {
        this.startOffset = startOffset;
    }

    @JsonProperty("scheduledStart")
    public Long getScheduledStart() {
        return scheduledStart;
    }

    @JsonProperty("scheduledStart")
    public void setScheduledStart(final Long scheduledStart) {
        this.scheduledStart = scheduledStart;
    }

    @JsonProperty("scheduledStartISO")
    public String getScheduledStartISO() {
        return scheduledStartISO;
    }

    @JsonProperty("scheduledStartISO")
    public void setScheduledStartISO(final String scheduledStartISO) {
        this.scheduledStartISO = scheduledStartISO;
    }

    @JsonProperty("scheduledStartOffset")
    public Integer getScheduledStartOffset() {
        return scheduledStartOffset;
    }

    @JsonProperty("scheduledStartOffset")
    public void setScheduledStartOffset(final Integer scheduledStartOffset) {
        this.scheduledStartOffset = scheduledStartOffset;
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
    public Integer getEndOffset() {
        return endOffset;
    }

    @JsonProperty("endOffset")
    public void setEndOffset(final Integer endOffset) {
        this.endOffset = endOffset;
    }

    @JsonProperty("scheduledEnd")
    public Long getScheduledEnd() {
        return scheduledEnd;
    }

    @JsonProperty("scheduledEnd")
    public void setScheduledEnd(final Long scheduledEnd) {
        this.scheduledEnd = scheduledEnd;
    }

    @JsonProperty("scheduledEndISO")
    public String getScheduledEndISO() {
        return scheduledEndISO;
    }

    @JsonProperty("scheduledEndISO")
    public void setScheduledEndISO(final String scheduledEndISO) {
        this.scheduledEndISO = scheduledEndISO;
    }

    @JsonProperty("scheduledEndOffset")
    public Integer getScheduledEndOffset() {
        return scheduledEndOffset;
    }

    @JsonProperty("scheduledEndOffset")
    public void setScheduledEndOffset(final Integer scheduledEndOffset) {
        this.scheduledEndOffset = scheduledEndOffset;
    }

    @JsonProperty("niceTime")
    public Long getNiceTime() {
        return niceTime;
    }

    @JsonProperty("niceTime")
    public void setNiceTime(final Long niceTime) {
        this.niceTime = niceTime;
    }

    @JsonProperty("niceTimeISO")
    public String getNiceTimeISO() {
        return niceTimeISO;
    }

    @JsonProperty("niceTimeISO")
    public void setNiceTimeISO(final String niceTimeISO) {
        this.niceTimeISO = niceTimeISO;
    }

    @JsonProperty("niceTimeOffset")
    public Integer getNiceTimeOffset() {
        return niceTimeOffset;
    }

    @JsonProperty("niceTimeOffset")
    public void setNiceTimeOffset(final Integer niceTimeOffset) {
        this.niceTimeOffset = niceTimeOffset;
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
     * Short info.
     * @return short info
     */
    public String toStringShort() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Broadcast [super = ");
        builder.append(super.toString());
        builder.append(" title=");
        builder.append(title);
        builder.append(" programTitle=");
        builder.append(programTitle);
        builder.append(", subtitle=");
        builder.append(subtitle);
        builder.append(", start=");
        builder.append(start);
        builder.append(", scheduledStart=");
        builder.append(scheduledStart);
        builder.append(", end=");
        builder.append(end);
        builder.append(", scheduledEnd=");
        builder.append(scheduledEnd);
        builder.append("]");
        return builder.toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Broadcast [super = ");
        builder.append(super.toString());
        builder.append(" href=");
        builder.append(href);
        builder.append(", station=");
        builder.append(station);
        builder.append(", entity=");
        builder.append(entity);
        builder.append(", id=");
        builder.append(id);
        builder.append(", broadcastDay=");
        builder.append(broadcastDay);
        builder.append(", programKey=");
        builder.append(programKey);
        builder.append(", program=");
        builder.append(program);
        builder.append(", programTitle=");
        builder.append(programTitle);
        builder.append(", title=");
        builder.append(title);
        builder.append(", subtitle=");
        builder.append(subtitle);
        builder.append(", ressort=");
        builder.append(ressort);
        builder.append(", state=");
        builder.append(state);
        builder.append(", isOnDemand=");
        builder.append(isOnDemand);
        builder.append(", isGeoProtected=");
        builder.append(isGeoProtected);
        builder.append(", start=");
        builder.append(start);
        builder.append(", startISO=");
        builder.append(startISO);
        builder.append(", startOffset=");
        builder.append(startOffset);
        builder.append(", scheduledStart=");
        builder.append(scheduledStart);
        builder.append(", scheduledStartISO=");
        builder.append(scheduledStartISO);
        builder.append(", scheduledStartOffset=");
        builder.append(scheduledStartOffset);
        builder.append(", end=");
        builder.append(end);
        builder.append(", endISO=");
        builder.append(endISO);
        builder.append(", endOffset=");
        builder.append(endOffset);
        builder.append(", scheduledEnd=");
        builder.append(scheduledEnd);
        builder.append(", scheduledEndISO=");
        builder.append(scheduledEndISO);
        builder.append(", scheduledEndOffset=");
        builder.append(scheduledEndOffset);
        builder.append(", niceTime=");
        builder.append(niceTime);
        builder.append(", niceTimeISO=");
        builder.append(niceTimeISO);
        builder.append(", niceTimeOffset=");
        builder.append(niceTimeOffset);
        builder.append(", additionalProperties=");
        builder.append(additionalProperties);
        builder.append("]");
        return builder.toString();
    }

}
