package com.sports.cricketclover.Model.Teams;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TeamsModel {
    @SerializedName("teams")
    private List<TeamsListModel> teams = null;

    public TeamsModel(List<TeamsListModel> teams) {
        this.teams = teams;
    }

    public List<TeamsListModel> getTeams() {
        return teams;
    }
}
