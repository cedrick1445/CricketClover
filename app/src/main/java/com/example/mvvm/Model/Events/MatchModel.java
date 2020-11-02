package com.example.mvvm.Model.Events;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MatchModel {
    @SerializedName("matches")
    private List<MatchListModel> matches = null;

        public MatchModel(List<MatchListModel> matches) {
            this.matches = matches;
        }

    public List<MatchListModel> getMatches() {
        return matches;
    }
}
