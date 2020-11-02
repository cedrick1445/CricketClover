package com.example.mvvm.ViewModel.Events;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mvvm.Model.Events.GamesModel;
import com.example.mvvm.Repository.Events.EventsRepository;


public class EventsViewModels extends androidx.lifecycle.ViewModel {

    private MutableLiveData<GamesModel> getLiveGamesData;
    private MutableLiveData<GamesModel> getAllGamesData;

    private EventsRepository eventsRepository;

    public void init(){
        if (getLiveGamesData != null || getAllGamesData != null){
            return;
        }

        eventsRepository = EventsRepository.getInstance();

        getLiveGamesData = eventsRepository.getGamesEvents();
        getAllGamesData = eventsRepository.getAllGames();
    }

    public LiveData<GamesModel> getLiveGamesRepository() {
        return getLiveGamesData;
    }

    public LiveData<GamesModel> getAllGamesRepository() {
        return getAllGamesData;
    }
}