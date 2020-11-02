package com.example.mvvm.Model.Standings;

import com.google.gson.annotations.SerializedName;

public class TeamStandingModel {
    @SerializedName("id")
    private Integer id;
    @SerializedName("name")
    private String name;
    @SerializedName("shortName")
    private String shortName;
    @SerializedName("groupName")
    private String groupName;
    @SerializedName("logoUrl")
    private String logoUrl;
    @SerializedName("position")
    private Integer position;
    @SerializedName("played")
    private Integer played;
    @SerializedName("won")
    private Integer won;
    @SerializedName("wonOutRight")
    private Integer wonOutRight;
    @SerializedName("wonInning")
    private Integer wonInning;
    @SerializedName("drawn")
    private Integer drawn;
    @SerializedName("tied")
    private Integer tied;
    @SerializedName("lost")
    private Integer lost;
    @SerializedName("lostOutRight")
    private Integer lostOutRight;
    @SerializedName("lostInning")
    private Integer lostInning;
    @SerializedName("noResult")
    private Integer noResult;
    @SerializedName("bonus")
    private Double bonus;
    @SerializedName("points")
    private Double points;
    @SerializedName("netRunRate")
    private Double netRunRate;
    @SerializedName("quotient")
    private Integer quotient;
    @SerializedName("penalty")
    private Integer penalty;
    @SerializedName("runsMade")
    private Integer runsMade;
    @SerializedName("runsConceded")
    private Integer runsConceded;
    @SerializedName("ballsFaced")
    private Integer ballsFaced;
    @SerializedName("ballsBowled")
    private Integer ballsBowled;
    @SerializedName("deductedPoints")
    private Integer deductedPoints;

    public TeamStandingModel(Integer id, String name, String shortName, String groupName, String logoUrl, Integer position, Integer played, Integer won, Integer wonOutRight, Integer wonInning, Integer drawn, Integer tied, Integer lost, Integer lostOutRight, Integer lostInning, Integer noResult, Double bonus, Double points, Double netRunRate, Integer quotient, Integer penalty, Integer runsMade, Integer runsConceded, Integer ballsFaced, Integer ballsBowled, Integer deductedPoints) {
        this.id = id;
        this.name = name;
        this.shortName = shortName;
        this.groupName = groupName;
        this.logoUrl = logoUrl;
        this.position = position;
        this.played = played;
        this.won = won;
        this.wonOutRight = wonOutRight;
        this.wonInning = wonInning;
        this.drawn = drawn;
        this.tied = tied;
        this.lost = lost;
        this.lostOutRight = lostOutRight;
        this.lostInning = lostInning;
        this.noResult = noResult;
        this.bonus = bonus;
        this.points = points;
        this.netRunRate = netRunRate;
        this.quotient = quotient;
        this.penalty = penalty;
        this.runsMade = runsMade;
        this.runsConceded = runsConceded;
        this.ballsFaced = ballsFaced;
        this.ballsBowled = ballsBowled;
        this.deductedPoints = deductedPoints;
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

    public String getGroupName() {
        return groupName;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public Integer getPosition() {
        return position;
    }

    public Integer getPlayed() {
        return played;
    }

    public Integer getWon() {
        return won;
    }

    public Integer getWonOutRight() {
        return wonOutRight;
    }

    public Integer getWonInning() {
        return wonInning;
    }

    public Integer getDrawn() {
        return drawn;
    }

    public Integer getTied() {
        return tied;
    }

    public Integer getLost() {
        return lost;
    }

    public Integer getLostOutRight() {
        return lostOutRight;
    }

    public Integer getLostInning() {
        return lostInning;
    }

    public Integer getNoResult() {
        return noResult;
    }

    public Double getBonus() {
        return bonus;
    }

    public Double getPoints() {
        return points;
    }

    public Double getNetRunRate() {
        return netRunRate;
    }

    public Integer getQuotient() {
        return quotient;
    }

    public Integer getPenalty() {
        return penalty;
    }

    public Integer getRunsMade() {
        return runsMade;
    }

    public Integer getRunsConceded() {
        return runsConceded;
    }

    public Integer getBallsFaced() {
        return ballsFaced;
    }

    public Integer getBallsBowled() {
        return ballsBowled;
    }

    public Integer getDeductedPoints() {
        return deductedPoints;
    }
}
