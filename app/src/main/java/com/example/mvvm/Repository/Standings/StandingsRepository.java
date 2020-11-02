package com.example.mvvm.Repository.Standings;

import androidx.lifecycle.MutableLiveData;

import com.example.mvvm.Interface.MyApi;
import com.example.mvvm.Model.Series.SeriesModel;
import com.example.mvvm.Service.RetrofitService;

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