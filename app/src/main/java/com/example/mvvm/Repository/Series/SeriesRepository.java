package com.example.mvvm.Repository.Series;

import androidx.lifecycle.MutableLiveData;

import com.example.mvvm.Interface.MyApi;
import com.example.mvvm.Model.Events.GamesModel;
import com.example.mvvm.Model.Standings.StandingsModel;
import com.example.mvvm.Model.Teams.SeriesTeamModel;
import com.example.mvvm.Service.RetrofitService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SeriesRepository {
    private static SeriesRepository standingsRepository;

    public static SeriesRepository getInstance(){
        if (standingsRepository == null){
            standingsRepository = new SeriesRepository();
        }
        return standingsRepository;
    }

    private MyApi newsApi;

    public SeriesRepository(){
        newsApi = RetrofitService.createService(MyApi.class);
    }

    public MutableLiveData<StandingsModel> getAllSeries(){
        MutableLiveData<StandingsModel> gamesData = new MutableLiveData<>();
        newsApi.getStandings("2693").enqueue(new Callback<StandingsModel>() {
            @Override
            public void onResponse(Call<StandingsModel> call, Response<StandingsModel> response) {
                gamesData.setValue(response.body());
            }
            @Override
            public void onFailure(Call<StandingsModel> call, Throwable t) {
                gamesData.setValue(null);
            }
        });
        return gamesData;
    }
}
