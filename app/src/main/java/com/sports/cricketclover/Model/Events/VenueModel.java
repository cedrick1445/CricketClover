package com.sports.cricketclover.Model.Events;

import com.google.gson.annotations.SerializedName;

public class VenueModel {
    @SerializedName("name")
    private String name;
    @SerializedName("shortName")
    private String shortName;

    public VenueModel(String name, String shortName) {
        this.name = name;
        this.shortName = shortName;
    }

    public String getName() {
        return name;
    }

    public String getShortName() {
        return shortName;
    }
}
