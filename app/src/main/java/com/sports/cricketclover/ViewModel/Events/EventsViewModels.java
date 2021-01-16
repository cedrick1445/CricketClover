package com.sports.cricketclover.ViewModel.Events;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.sports.cricketclover.Model.Events.GamesModel;
import com.sports.cricketclover.Repository.Events.EventsRepository;


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