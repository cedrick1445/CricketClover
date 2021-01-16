package com.sports.cricketclover.Model.Teams;

import com.google.gson.annotations.SerializedName;

public class SeriesTeamModel {
    @SerializedName("seriesTeams")
    private TeamsModel seriesTeams;

    public SeriesTeamModel(TeamsModel seriesTeams) {
        this.seriesTeams = seriesTeams;
    }

    public TeamsModel getSeriesTeams() {
        return seriesTeams;
    }
}
