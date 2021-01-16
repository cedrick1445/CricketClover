package com.sports.cricketclover.Model.Events;

import com.google.gson.annotations.SerializedName;

public class GamesModel {

    @SerializedName("matchList")
    private MatchModel matchList;

        public GamesModel(MatchModel matchList) {
            this.matchList = matchList;
        }

    public MatchModel getMatchList() {
        return matchList;
    }
}