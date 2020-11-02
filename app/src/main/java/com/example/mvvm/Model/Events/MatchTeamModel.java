package com.example.mvvm.Model.Events;

import com.google.gson.annotations.SerializedName;

public class MatchTeamModel {
    @SerializedName("isBatting")
    private Boolean isBatting;
    @SerializedName("id")
    private Integer id;
    @SerializedName("name")
    private String name;
    @SerializedName("shortName")
    private String shortName;
    @SerializedName("logoUrl")
    private String logoUrl;
    @SerializedName("backgroundImageUrl")
    private String backgroundImageUrl;
    @SerializedName("teamColour")
    private String teamColour;

    public MatchTeamModel(Boolean isBatting, Integer id, String name, String shortName, String logoUrl, String backgroundImageUrl, String teamColour) {
        this.isBatting = isBatting;
        this.id = id;
        this.name = name;
        this.shortName = shortName;
        this.logoUrl = logoUrl;
        this.backgroundImageUrl = backgroundImageUrl;
        this.teamColour = teamColour;
    }

    public Boolean getBatting() {
        return isBatting;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getShortName() {
        return shortName;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public String getBackgroundImageUrl() {
        return backgroundImageUrl;
    }

    public String getTeamColour() {
        return teamColour;
    }
}
