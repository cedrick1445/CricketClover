package com.example.mvvm.ViewModel.Series;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mvvm.Model.Series.SeriesModel;
import com.example.mvvm.Repository.Standings.StandingsRepository;

public class SeriesViewModels extends androidx.lifecycle.ViewModel {

    private MutableLiveData<SeriesModel> getAllStandings;

    private StandingsRepository standingsRepository;

    public void init(){
        if (getAllStandings != null){
            return;
        }

        standingsRepository = StandingsRepository.getInstance();
        getAllStandings = standingsRepository.getAllStandings();
    }

    public LiveData<SeriesModel> getAllStandingsRepository() {
        return getAllStandings;
    }
}