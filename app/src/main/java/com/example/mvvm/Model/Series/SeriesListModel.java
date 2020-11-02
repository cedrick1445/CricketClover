package com.example.mvvm.Model.Series;

import com.google.gson.annotations.SerializedName;

public class SeriesListModel {
    @SerializedName("id")
    private Integer id;
    @SerializedName("name")
    private String name;
    @SerializedName("statisticsProvider")
    private String statisticsProvider;
    @SerializedName("shieldImageUrl")
    private String shieldImageUrl;
    @SerializedName("status")
    private String status;
    @SerializedName("startDateTime")
    private String startDateTime;
    @SerializedName("endDateTime")
    private String endDateTime;

    public SeriesListModel(Integer id, String name, String statisticsProvider, String shieldImageUrl, String status, String startDateTime, String endDateTime) {
        this.id = id;
        this.name = name;
        this.statisticsProvider = statisticsProvider;
        this.shieldImageUrl = shieldImageUrl;
        this.status = status;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getStatisticsProvider() {
        return statisticsProvider;
    }

    public String getShieldImageUrl() {
        return shieldImageUrl;
    }

    public String getStatus() {
        return status;
    }

    public String getStartDateTime() {
        return startDateTime;
    }

    public String getEndDateTime() {
        return endDateTime;
    }
}
