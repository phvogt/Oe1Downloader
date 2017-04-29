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

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "href", "station", "entity", "id", "broadcastDay", "programKey", "program", "title", "subtitle", "ressort",
        "state", "isOnDemand", "isGeoProtected", "start", "startISO", "startOffset", "scheduledStart", "scheduledStartISO",
        "scheduledStartOffset", "end", "endISO", "endOffset", "scheduledEnd", "scheduledEndISO", "scheduledEndOffset", "niceTime",
        "niceTimeISO", "niceTimeOffset", "description", "pressRelease", "akm", "tags", "moderator", "url", "images", "items",
        "streams", "marks" })
public class ShowInfo {

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
    private Object pressRelease;
    @JsonProperty("akm")
    private String akm;
    @JsonProperty("tags")
    private List<Object> tags = null;
    @JsonProperty("moderator")
    private String moderator;
    @JsonProperty("url")
    private String url;
    @JsonProperty("images")
    private List<Image> images = null;
    @JsonProperty("items")
    private List<Object> items = null;
    @JsonProperty("streams")
    private List<Stream> streams = null;
    @JsonProperty("marks")
    private List<Mark> marks = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

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
    public Object getPressRelease() {
        return pressRelease;
    }

    @JsonProperty("pressRelease")
    public void setPressRelease(final Object pressRelease) {
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
    public String getModerator() {
        return moderator;
    }

    @JsonProperty("moderator")
    public void setModerator(final String moderator) {
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

    @JsonProperty("images")
    public List<Image> getImages() {
        return images;
    }

    @JsonProperty("images")
    public void setImages(final List<Image> images) {
        this.images = images;
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

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("ShowInfo [super = ");
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
        builder.append(", description=");
        builder.append(description);
        builder.append(", pressRelease=");
        builder.append(pressRelease);
        builder.append(", akm=");
        builder.append(akm);
        builder.append(", tags=");
        builder.append(tags);
        builder.append(", moderator=");
        builder.append(moderator);
        builder.append(", url=");
        builder.append(url);
        builder.append(", images=");
        builder.append(images);
        builder.append(", items=");
        builder.append(items);
        builder.append(", streams=");
        builder.append(streams);
        builder.append(", marks=");
        builder.append(marks);
        builder.append(", additionalProperties=");
        builder.append(additionalProperties);
        builder.append("]");
        return builder.toString();
    }

}
