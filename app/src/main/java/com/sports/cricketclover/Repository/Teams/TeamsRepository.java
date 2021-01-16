package com.sports.cricketclover.Repository.Teams;

import androidx.lifecycle.MutableLiveData;

import com.sports.cricketclover.Interface.MyApi;
import com.sports.cricketclover.Model.Teams.SeriesTeamModel;
import com.sports.cricketclover.Service.RetrofitService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TeamsRepository {
    private static TeamsRepository teamsRepository;

    public static TeamsRepository getInstance(){
        if (teamsRepository == null){
            teamsRepository = new TeamsRepository();
        }
        return teamsRepository;
    }

    private MyApi newsApi;

    public TeamsRepository(){
        newsApi = RetrofitService.createService(MyApi.class);
    }

    public MutableLiveData<SeriesTeamModel> getAllTeams(){
        MutableLiveData<SeriesTeamModel> gamesData = new MutableLiveData<>();
        newsApi.getTeams("2693").enqueue(new Callback<SeriesTeamModel>() {
            @Override
            public void onResponse(Call<SeriesTeamModel> call, Response<SeriesTeamModel> response) {
                gamesData.setValue(response.body());
            }
            @Override
            public void onFailure(Call<SeriesTeamModel> call, Throwable t) {
                gamesData.setValue(null);
            }
        });
        return gamesData;
    }
}
