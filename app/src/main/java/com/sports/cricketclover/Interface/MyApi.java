package com.sports.cricketclover.Interface;

import com.sports.cricketclover.Model.Events.GamesModel;
import com.sports.cricketclover.Model.Series.SeriesModel;
import com.sports.cricketclover.Model.Standings.StandingsModel;
import com.sports.cricketclover.Model.Teams.SeriesTeamModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface MyApi {

    String BASE_URL = "https://dev132-cricket-live-scores-v1.p.rapidapi.com/";

    @Headers({"x-rapidapi-host: dev132-cricket-live-scores-v1.p.rapidapi.com",
            "x-rapidapi-key: 07e55202eemshd454005e3a79774p103cccjsn4b32f05d3a2f",
            "useQueryString: true"})
    @GET("matchseries.php")
    Call<GamesModel> getSeriesGames(@Query("seriesid") String id);

    @Headers({"x-rapidapi-host: dev132-cricket-live-scores-v1.p.rapidapi.com",
            "x-rapidapi-key: 07e55202eemshd454005e3a79774p103cccjsn4b32f05d3a2f",
            "useQueryString: true"})
    @GET("matches.php")
    Call<GamesModel> getAllGames(@Query("completedlimit") String climit, @Query("inprogresslimit") String ilimit, @Query("upcomingLimit") String ulimit);

    @Headers({"x-rapidapi-host: dev132-cricket-live-scores-v1.p.rapidapi.com",
            "x-rapidapi-key: 07e55202eemshd454005e3a79774p103cccjsn4b32f05d3a2f",
            "useQueryString: true"})
    @GET("seriesteams.php")
    Call<SeriesTeamModel> getTeams(@Query("seriesid") String id);

    @Headers({"x-rapidapi-host: dev132-cricket-live-scores-v1.p.rapidapi.com",
            "x-rapidapi-key: 07e55202eemshd454005e3a79774p103cccjsn4b32f05d3a2f",
            "useQueryString: true"})
    @GET("seriesstandings.php")
    Call<StandingsModel> getStandings(@Query("seriesid") String id);

    @Headers({"x-rapidapi-host: dev132-cricket-live-scores-v1.p.rapidapi.com",
            "x-rapidapi-key: 07e55202eemshd454005e3a79774p103cccjsn4b32f05d3a2f",
            "useQueryString: true"})
    @GET("series.php")
    Call<SeriesModel> getSeries();

}
