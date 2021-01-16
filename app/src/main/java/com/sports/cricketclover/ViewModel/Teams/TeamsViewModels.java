package com.sports.cricketclover.ViewModel.Teams;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.sports.cricketclover.Model.Teams.SeriesTeamModel;
import com.sports.cricketclover.Repository.Teams.TeamsRepository;

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