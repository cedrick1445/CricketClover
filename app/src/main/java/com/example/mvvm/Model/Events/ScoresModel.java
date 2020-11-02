package com.example.mvvm.Model.Events;

import com.google.gson.annotations.SerializedName;

public class ScoresModel {
    @SerializedName("homeScore")
    private String homeScore;
    @SerializedName("homeOvers")
    private String homeOvers;
    @SerializedName("awayScore")
    private String awayScore;
    @SerializedName("awayOvers")
    private String awayOvers;

    public ScoresModel(String homeScore, String homeOvers, String awayScore, String awayOvers) {
        this.homeScore = homeScore;
        this.homeOvers = homeOvers;
        this.awayScore = awayScore;
        this.awayOvers = awayOvers;
    }

    public String getHomeScore() {
        return homeScore;
    }

    public String getHomeOvers() {
        return homeOvers;
    }

    public String getAwayScore() {
        return awayScore;
    }

    public String getAwayOvers() {
        return awayOvers;
    }
}
