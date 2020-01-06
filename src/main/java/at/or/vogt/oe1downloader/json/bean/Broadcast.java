
package at.or.vogt.oe1downloader.json.bean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "href", "station", "entity", "id", "broadcastDay", "programKey", "program", "programTitle",
        "title", "subtitle", "ressort", "state", "isOnDemand", "isGeoProtected", "isAdFree", "start", "startISO",
        "startOffset", "scheduledStart", "scheduledStartISO", "scheduledStartOffset", "end", "endISO", "endOffset",
        "scheduledEnd", "scheduledEndISO", "scheduledEndOffset", "niceTime", "niceTimeISO", "niceTimeOffset",
        "description", "pressRelease", "akm", "tags", "moderator", "url", "urlText", "images", "categories", "items",
        "streams", "marks" })
public class Broadcast {

    @JsonProperty("href")
    private String href;
    @JsonProperty("station")
    private String station;
    @JsonProperty("entity")
    private String entity;
    @JsonProperty("id")
    private Long id;
    @JsonProperty("broadcastDay")
    private Long broadcastDay;
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
    @JsonProperty("isAdFree")
    private Boolean isAdFree;
    @JsonProperty("start")
    private Long start;
    @JsonProperty("startISO")
    private String startISO;
    @JsonProperty("startOffset")
    private Long startOffset;
    @JsonProperty("scheduledStart")
    private Long scheduledStart;
    @JsonProperty("scheduledStartISO")
    private String scheduledStartISO;
    @JsonProperty("scheduledStartOffset")
    private Long scheduledStartOffset;
    @JsonProperty("end")
    private Long end;
    @JsonProperty("endISO")
    private String endISO;
    @JsonProperty("endOffset")
    private Long endOffset;
    @JsonProperty("scheduledEnd")
    private Long scheduledEnd;
    @JsonProperty("scheduledEndISO")
    private String scheduledEndISO;
    @JsonProperty("scheduledEndOffset")
    private Long scheduledEndOffset;
    @JsonProperty("niceTime")
    private Long niceTime;
    @JsonProperty("niceTimeISO")
    private String niceTimeISO;
    @JsonProperty("niceTimeOffset")
    private Long niceTimeOffset;
    @JsonProperty("description")
    private Object description;
    @JsonProperty("pressRelease")
    private String pressRelease;
    @JsonProperty("akm")
    private String akm;
    @JsonProperty("tags")
    private List<Object> tags = null;
    @JsonProperty("moderator")
    private Object moderator;
    @JsonProperty("url")
    private String url;
    @JsonProperty("urlText")
    private String urlText;
    @JsonProperty("images")
    private List<Image> images = null;
    @JsonProperty("categories")
    private List<Object> categories = null;
    @JsonProperty("items")
    private List<Object> items = null;
    @JsonProperty("streams")
    private List<Stream> streams = null;
    @JsonProperty("marks")
    private List<Mark> marks = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     *
     */
    public Broadcast() {
    }

    /**
     *
     * @param endOffset
     * @param niceTimeISO
     * @param akm
     * @param isOnDemand
     * @param moderator
     * @param niceTimeOffset
     * @param description
     * @param programKey
     * @param program
     * @param title
     * @param scheduledEndOffset
     * @param scheduledStartISO
     * @param ressort
     * @param station
     * @param end
     * @param href
     * @param id
     * @param state
     * @param scheduledEndISO
     * @param categories
     * @param niceTime
     * @param scheduledStart
     * @param images
     * @param scheduledEnd
     * @param urlText
     * @param streams
     * @param start
     * @param marks
     * @param isAdFree
     * @param pressRelease
     * @param url
     * @param tags
     * @param programTitle
     * @param isGeoProtected
     * @param scheduledStartOffset
     * @param startOffset
     * @param broadcastDay
     * @param subtitle
     * @param endISO
     * @param items
     * @param entity
     * @param startISO
     */
    public Broadcast(final String href, final String station, final String entity, final Long id,
            final Long broadcastDay, final String programKey, final String program, final String programTitle,
            final String title, final String subtitle, final String ressort, final String state,
            final Boolean isOnDemand, final Boolean isGeoProtected, final Boolean isAdFree, final Long start,
            final String startISO, final Long startOffset, final Long scheduledStart, final String scheduledStartISO,
            final Long scheduledStartOffset, final Long end, final String endISO, final Long endOffset,
            final Long scheduledEnd, final String scheduledEndISO, final Long scheduledEndOffset, final Long niceTime,
            final String niceTimeISO, final Long niceTimeOffset, final Object description, final String pressRelease,
            final String akm, final List<Object> tags, final Object moderator, final String url, final String urlText,
            final List<Image> images, final List<Object> categories, final List<Object> items,
            final List<Stream> streams, final List<Mark> marks) {
        super();
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
        this.isAdFree = isAdFree;
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
        this.description = description;
        this.pressRelease = pressRelease;
        this.akm = akm;
        this.tags = tags;
        this.moderator = moderator;
        this.url = url;
        this.urlText = urlText;
        this.images = images;
        this.categories = categories;
        this.items = items;
        this.streams = streams;
        this.marks = marks;
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
    public Long getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(final Long id) {
        this.id = id;
    }

    @JsonProperty("broadcastDay")
    public Long getBroadcastDay() {
        return broadcastDay;
    }

    @JsonProperty("broadcastDay")
    public void setBroadcastDay(final Long broadcastDay) {
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

    @JsonProperty("isAdFree")
    public Boolean getIsAdFree() {
        return isAdFree;
    }

    @JsonProperty("isAdFree")
    public void setIsAdFree(final Boolean isAdFree) {
        this.isAdFree = isAdFree;
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
    public Long getScheduledStartOffset() {
        return scheduledStartOffset;
    }

    @JsonProperty("scheduledStartOffset")
    public void setScheduledStartOffset(final Long scheduledStartOffset) {
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
    public Long getEndOffset() {
        return endOffset;
    }

    @JsonProperty("endOffset")
    public void setEndOffset(final Long endOffset) {
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
    public Long getScheduledEndOffset() {
        return scheduledEndOffset;
    }

    @JsonProperty("scheduledEndOffset")
    public void setScheduledEndOffset(final Long scheduledEndOffset) {
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
    public Long getNiceTimeOffset() {
        return niceTimeOffset;
    }

    @JsonProperty("niceTimeOffset")
    public void setNiceTimeOffset(final Long niceTimeOffset) {
        this.niceTimeOffset = niceTimeOffset;
    }

    @JsonProperty("description")
    public Object getDescription() {
        return description;
    }

    @JsonProperty("description")
    public void setDescription(final Object description) {
        this.description = description;
    }

    @JsonProperty("pressRelease")
    public String getPressRelease() {
        return pressRelease;
    }

    @JsonProperty("pressRelease")
    public void setPressRelease(final String pressRelease) {
        this.pressRelease = pressRelease;
    }

    @JsonProperty("akm")
    public String getAkm() {
        return akm;
    }

    @JsonProperty("akm")
    public void setAkm(final String akm) {
        this.akm = akm;
    }

    @JsonProperty("tags")
    public List<Object> getTags() {
        return tags;
    }

    @JsonProperty("tags")
    public void setTags(final List<Object> tags) {
        this.tags = tags;
    }

    @JsonProperty("moderator")
    public Object getModerator() {
        return moderator;
    }

    @JsonProperty("moderator")
    public void setModerator(final Object moderator) {
        this.moderator = moderator;
    }

    @JsonProperty("url")
    public String getUrl() {
        return url;
    }

    @JsonProperty("url")
    public void setUrl(final String url) {
        this.url = url;
    }

    @JsonProperty("urlText")
    public String getUrlText() {
        return urlText;
    }

    @JsonProperty("urlText")
    public void setUrlText(final String urlText) {
        this.urlText = urlText;
    }

    @JsonProperty("images")
    public List<Image> getImages() {
        return images;
    }

    @JsonProperty("images")
    public void setImages(final List<Image> images) {
        this.images = images;
    }

    @JsonProperty("categories")
    public List<Object> getCategories() {
        return categories;
    }

    @JsonProperty("categories")
    public void setCategories(final List<Object> categories) {
        this.categories = categories;
    }

    @JsonProperty("items")
    public List<Object> getItems() {
        return items;
    }

    @JsonProperty("items")
    public void setItems(final List<Object> items) {
        this.items = items;
    }

    @JsonProperty("streams")
    public List<Stream> getStreams() {
        return streams;
    }

    @JsonProperty("streams")
    public void setStreams(final List<Stream> streams) {
        this.streams = streams;
    }

    @JsonProperty("marks")
    public List<Mark> getMarks() {
        return marks;
    }

    @JsonProperty("marks")
    public void setMarks(final List<Mark> marks) {
        this.marks = marks;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(final String name, final Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("href", href)
                .append("station", station)
                .append("entity", entity)
                .append("id", id)
                .append("broadcastDay", broadcastDay)
                .append("programKey", programKey)
                .append("program", program)
                .append("programTitle", programTitle)
                .append("title", title)
                .append("subtitle", subtitle)
                .append("ressort", ressort)
                .append("state", state)
                .append("isOnDemand", isOnDemand)
                .append("isGeoProtected", isGeoProtected)
                .append("isAdFree", isAdFree)
                .append("start", start)
                .append("startISO", startISO)
                .append("startOffset", startOffset)
                .append("scheduledStart", scheduledStart)
                .append("scheduledStartISO", scheduledStartISO)
                .append("scheduledStartOffset", scheduledStartOffset)
                .append("end", end)
                .append("endISO", endISO)
                .append("endOffset", endOffset)
                .append("scheduledEnd", scheduledEnd)
                .append("scheduledEndISO", scheduledEndISO)
                .append("scheduledEndOffset", scheduledEndOffset)
                .append("niceTime", niceTime)
                .append("niceTimeISO", niceTimeISO)
                .append("niceTimeOffset", niceTimeOffset)
                .append("description", description)
                .append("pressRelease", pressRelease)
                .append("akm", akm)
                .append("tags", tags)
                .append("moderator", moderator)
                .append("url", url)
                .append("urlText", urlText)
                .append("images", images)
                .append("categories", categories)
                .append("items", items)
                .append("streams", streams)
                .append("marks", marks)
                .append("additionalProperties", additionalProperties)
                .toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(endOffset)
                .append(niceTimeISO)
                .append(akm)
                .append(isOnDemand)
                .append(moderator)
                .append(niceTimeOffset)
                .append(description)
                .append(programKey)
                .append(program)
                .append(title)
                .append(scheduledEndOffset)
                .append(scheduledStartISO)
                .append(ressort)
                .append(station)
                .append(end)
                .append(href)
                .append(id)
                .append(state)
                .append(scheduledEndISO)
                .append(categories)
                .append(niceTime)
                .append(scheduledStart)
                .append(images)
                .append(scheduledEnd)
                .append(urlText)
                .append(streams)
                .append(start)
                .append(marks)
                .append(isAdFree)
                .append(pressRelease)
                .append(url)
                .append(tags)
                .append(programTitle)
                .append(isGeoProtected)
                .append(scheduledStartOffset)
                .append(startOffset)
                .append(broadcastDay)
                .append(subtitle)
                .append(additionalProperties)
                .append(endISO)
                .append(items)
                .append(entity)
                .append(startISO)
                .toHashCode();
    }

    @Override
    public boolean equals(final Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Broadcast) == false) {
            return false;
        }
        final Broadcast rhs = ((Broadcast) other);
        return new EqualsBuilder()
                .append(endOffset, rhs.endOffset)
                .append(niceTimeISO, rhs.niceTimeISO)
                .append(akm, rhs.akm)
                .append(isOnDemand, rhs.isOnDemand)
                .append(moderator, rhs.moderator)
                .append(niceTimeOffset, rhs.niceTimeOffset)
                .append(description, rhs.description)
                .append(programKey, rhs.programKey)
                .append(program, rhs.program)
                .append(title, rhs.title)
                .append(scheduledEndOffset, rhs.scheduledEndOffset)
                .append(scheduledStartISO, rhs.scheduledStartISO)
                .append(ressort, rhs.ressort)
                .append(station, rhs.station)
                .append(end, rhs.end)
                .append(href, rhs.href)
                .append(id, rhs.id)
                .append(state, rhs.state)
                .append(scheduledEndISO, rhs.scheduledEndISO)
                .append(categories, rhs.categories)
                .append(niceTime, rhs.niceTime)
                .append(scheduledStart, rhs.scheduledStart)
                .append(images, rhs.images)
                .append(scheduledEnd, rhs.scheduledEnd)
                .append(urlText, rhs.urlText)
                .append(streams, rhs.streams)
                .append(start, rhs.start)
                .append(marks, rhs.marks)
                .append(isAdFree, rhs.isAdFree)
                .append(pressRelease, rhs.pressRelease)
                .append(url, rhs.url)
                .append(tags, rhs.tags)
                .append(programTitle, rhs.programTitle)
                .append(isGeoProtected, rhs.isGeoProtected)
                .append(scheduledStartOffset, rhs.scheduledStartOffset)
                .append(startOffset, rhs.startOffset)
                .append(broadcastDay, rhs.broadcastDay)
                .append(subtitle, rhs.subtitle)
                .append(additionalProperties, rhs.additionalProperties)
                .append(endISO, rhs.endISO)
                .append(items, rhs.items)
                .append(entity, rhs.entity)
                .append(startISO, rhs.startISO)
                .isEquals();
    }

}
