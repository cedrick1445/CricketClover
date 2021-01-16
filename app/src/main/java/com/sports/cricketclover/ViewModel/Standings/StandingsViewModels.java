package com.sports.cricketclover.ViewModel.Standings;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.sports.cricketclover.Model.Standings.StandingsModel;
import com.sports.cricketclover.Repository.Series.SeriesRepository;

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