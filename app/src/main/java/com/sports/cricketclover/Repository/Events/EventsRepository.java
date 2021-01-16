package com.sports.cricketclover.Repository.Events;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.sports.cricketclover.Model.Events.GamesModel;
import com.sports.cricketclover.Interface.MyApi;
import com.sports.cricketclover.Service.RetrofitService;

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
                Log.d("GetResponse" , response.body().getMatchList().getMatches().get(0).getCurrentMatchState());
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