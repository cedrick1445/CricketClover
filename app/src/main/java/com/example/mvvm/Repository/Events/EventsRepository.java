package com.example.mvvm.Repository.Events;

import androidx.lifecycle.MutableLiveData;

import com.example.mvvm.Model.Events.GamesModel;
import com.example.mvvm.Interface.MyApi;
import com.example.mvvm.Service.RetrofitService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EventsRepository {
    private static EventsRepository newsEventsRepository;

    public static EventsRepository getInstance(){
        if (newsEventsRepository == null){
            newsEventsRepository = new EventsRepository();
        }
        return newsEventsRepository;
    }

    private MyApi newsApi;

    public EventsRepository(){
        newsApi = RetrofitService.createService(MyApi.class);
    }

    public MutableLiveData<GamesModel> getGamesEvents(){
        MutableLiveData<GamesModel> gamesData = new MutableLiveData<>();
        newsApi.getSeriesGames("2693").enqueue(new Callback<GamesModel>() {
            @Override
            public void onResponse(Call<GamesModel> call, Response<GamesModel> response) {
                gamesData.setValue(response.body());
            }
            @Override
            public void onFailure(Call<GamesModel> call, Throwable t) {
                gamesData.setValue(null);
            }
        });
        return gamesData;
    }

    public MutableLiveData<GamesModel> getAllGames(){
        MutableLiveData<GamesModel> allGamesData = new MutableLiveData<>();
        newsApi.getAllGames("10","0","0").enqueue(new Callback<GamesModel>() {
            @Override
            public void onResponse(Call<GamesModel> call, Response<GamesModel> response) {
                allGamesData.setValue(response.body());
            }
            @Override
            public void onFailure(Call<GamesModel> call, Throwable t) {
                allGamesData.setValue(null);
            }
        });
        return allGamesData;
    }
}