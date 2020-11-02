package com.example.mvvm.Model.Standings;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class StandingsModel {
    @SerializedName("metaData")
    private MetaModel metaData;
    @SerializedName("teams")
    private List<TeamStandingModel> teams = null;

    public StandingsModel(MetaModel metaData, List<TeamStandingModel> teams) {
        this.metaData = metaData;
        this.teams = teams;
    }

    public MetaModel getMetaData() {
        return metaData;
    }

    public List<TeamStandingModel> getTeams() {
        return teams;
    }
}
