package com.example.mvvm.ViewModel.Teams;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mvvm.Model.Events.GamesModel;
import com.example.mvvm.Model.Teams.SeriesTeamModel;
import com.example.mvvm.Repository.Events.EventsRepository;
import com.example.mvvm.Repository.Teams.TeamsRepository;

public class TeamsViewModels extends androidx.lifecycle.ViewModel {

    private MutableLiveData<SeriesTeamModel> getAllTeams;

    private TeamsRepository teamsRepository;

    public void init(){
        if (getAllTeams != null){
            return;
        }

        teamsRepository = TeamsRepository.getInstance();
        getAllTeams = teamsRepository.getAllTeams();
    }

    public LiveData<SeriesTeamModel> getAllTeamsRepository() {
        return getAllTeams;
    }
}