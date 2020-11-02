package com.example.mvvm.Model.Events;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MatchListModel {
    @SerializedName("id")
    private Integer id;
    @SerializedName("matchTypeId")
    private Integer matchTypeId;
    @SerializedName("series")
    private SeriesModel series;
    @SerializedName("name")
    private String name;
    @SerializedName("status")
    private String status;
    @SerializedName("venue")
    private VenueModel venue;
    @SerializedName("homeTeam")
    private MatchTeamModel homeTeam;
    @SerializedName("awayTeam")
    private MatchTeamModel awayTeam;
    @SerializedName("currentMatchState")
    private String currentMatchState;
    @SerializedName("isMultiDay")
    private Boolean isMultiDay;
    @SerializedName("matchSummaryText")
    private String matchSummaryText;
    @SerializedName("scores")
    private ScoresModel scores;
    @SerializedName("liveStreams")
    private List<Object> liveStreams = null;
    @SerializedName("isLive")
    private Boolean isLive;
    @SerializedName("currentInningId")
    private Integer currentInningId;
    @SerializedName("isMatchDrawn")
    private Boolean isMatchDrawn;
    @SerializedName("isMatchAbandoned")
    private Boolean isMatchAbandoned;
    @SerializedName("winningTeamId")
    private Integer winningTeamId;
    @SerializedName("startDateTime")
    private String startDateTime;
    @SerializedName("endDateTime")
    private String endDateTime;
    @SerializedName("isWomensMatch")
    private Boolean isWomensMatch;
    @SerializedName("isGamedayEnabled")
    private Boolean isGamedayEnabled;
    @SerializedName("removeMatch")
    private Boolean removeMatch;

    public MatchListModel(Integer id, Integer matchTypeId, SeriesModel series, String name, String status, VenueModel venue, MatchTeamModel homeTeam, MatchTeamModel awayTeam, String currentMatchState, Boolean isMultiDay, String matchSummaryText, ScoresModel scores, List<Object> liveStreams, Boolean isLive, Integer currentInningId, Boolean isMatchDrawn, Boolean isMatchAbandoned, Integer winningTeamId, String startDateTime, String endDateTime, Boolean isWomensMatch, Boolean isGamedayEnabled, Boolean removeMatch) {
        this.id = id;
        this.matchTypeId = matchTypeId;
        this.series = series;
        this.name = name;
        this.status = status;
        this.venue = venue;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.currentMatchState = currentMatchState;
        this.isMultiDay = isMultiDay;
        this.matchSummaryText = matchSummaryText;
        this.scores = scores;
        this.liveStreams = liveStreams;
        this.isLive = isLive;
        this.currentInningId = currentInningId;
        this.isMatchDrawn = isMatchDrawn;
        this.isMatchAbandoned = isMatchAbandoned;
        this.winningTeamId = winningTeamId;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.isWomensMatch = isWomensMatch;
        this.isGamedayEnabled = isGamedayEnabled;
        this.removeMatch = removeMatch;
    }

    public Integer getId() {
        return id;
    }

    public Integer getMatchTypeId() {
        return matchTypeId;
    }

    public SeriesModel getSeries() {
        return series;
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }

    public VenueModel getVenue() {
        return venue;
    }

    public MatchTeamModel getHomeTeam() {
        return homeTeam;
    }

    public MatchTeamModel getAwayTeam() {
        return awayTeam;
    }

    public String getCurrentMatchState() {
        return currentMatchState;
    }

    public Boolean getMultiDay() {
        return isMultiDay;
    }

    public String getMatchSummaryText() {
        return matchSummaryText;
    }

    public ScoresModel getScores() {
        return scores;
    }

    public List<Object> getLiveStreams() {
        return liveStreams;
    }

    public Boolean getLive() {
        return isLive;
    }

    public Integer getCurrentInningId() {
        return currentInningId;
    }

    public Boolean getMatchDrawn() {
        return isMatchDrawn;
    }

    public Boolean getMatchAbandoned() {
        return isMatchAbandoned;
    }

    public Integer getWinningTeamId() {
        return winningTeamId;
    }

    public String getStartDateTime() {
        return startDateTime;
    }

    public String getEndDateTime() {
        return endDateTime;
    }

    public Boolean getWomensMatch() {
        return isWomensMatch;
    }

    public Boolean getGamedayEnabled() {
        return isGamedayEnabled;
    }

    public Boolean getRemoveMatch() {
        return removeMatch;
    }
}
