package com.sports.cricketclover.Model.Events;

import com.google.gson.annotations.SerializedName;

public class SeriesModel {

    @SerializedName("id")
    private Integer id;
    @SerializedName("name")
    private String name;
    @SerializedName("shortName")
    private String shortName;

    public SeriesModel(Integer id, String name, String shortName) {
        this.id = id;
        this.name = name;
        this.shortName = shortName;
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
}
