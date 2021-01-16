package com.sports.cricketclover.Repository.Standings;

import androidx.lifecycle.MutableLiveData;

import com.sports.cricketclover.Interface.MyApi;
import com.sports.cricketclover.Model.Series.SeriesModel;
import com.sports.cricketclover.Service.RetrofitService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StandingsRepository {
    private static StandingsRepository standingsRepository;

    public static StandingsRepository getInstance(){
        if (standingsRepository == null){
            standingsRepository = new StandingsRepository();
        }
        return standingsRepository;
    }

    private MyApi newsApi;

    public StandingsRepository(){
        newsApi = RetrofitService.createService(MyApi.class);
    }

    public MutableLiveData<SeriesModel> getAllStandings(){
        MutableLiveData<SeriesModel> gamesData = new MutableLiveData<>();
        newsApi.getSeries().enqueue(new Callback<SeriesModel>() {
            @Override
            public void onResponse(Call<SeriesModel> call, Response<SeriesModel> response) {
                gamesData.setValue(response.body());
            }
            @Override
            public void onFailure(Call<SeriesModel> call, Throwable t) {
                gamesData.setValue(null);
            }
        });
        return gamesData;
    }
}