package com.example.mvvm.ViewModel.Standings;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mvvm.Model.Events.GamesModel;
import com.example.mvvm.Model.Standings.StandingsModel;
import com.example.mvvm.Model.Teams.SeriesTeamModel;
import com.example.mvvm.Repository.Series.SeriesRepository;
import com.example.mvvm.Repository.Standings.StandingsRepository;
import com.example.mvvm.Repository.Teams.TeamsRepository;

public class StandingsViewModels extends androidx.lifecycle.ViewModel {

    private MutableLiveData<StandingsModel> getAllSeries;

    private SeriesRepository seriesRepository;

    public void init(){
        if (getAllSeries != null){
            return;
        }

        seriesRepository = SeriesRepository.getInstance();
        getAllSeries = seriesRepository.getAllSeries();
    }

    public LiveData<StandingsModel> getAllStandingsRepository() {
        return getAllSeries;
    }
}