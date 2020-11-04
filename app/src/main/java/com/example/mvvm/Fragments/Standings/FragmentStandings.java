package com.example.mvvm.Fragments.Standings;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvvm.Adapter.Standings.StandingsAdapter;
import com.example.mvvm.Model.Standings.TeamStandingModel;
import com.example.mvvm.R;
import com.example.mvvm.ViewModel.Standings.StandingsViewModels;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.example.mvvm.Fragments.Teams.FragmentTeams.teamsArrayList;

public class FragmentStandings extends Fragment {

    RecyclerView recyclerView;
    StandingsViewModels standingsViewModels;
    public static ArrayList<TeamStandingModel> standingsArrayList = new ArrayList<>();
    StandingsAdapter adapter;
    ProgressBar loadingProgress;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_standings, container, false);
        init(view);
        initObserver();
        setupAdapter();
        return view;
    }

    private void init(View view){
        recyclerView = view.findViewById(R.id.standings_recycler);
        loadingProgress = view.findViewById(R.id.loadingProgress);
        GridLayoutManager grid = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(grid);
    }

    private void initObserver(){
        standingsViewModels = ViewModelProviders.of(this).get(StandingsViewModels.class);
        standingsViewModels.init();
        standingsViewModels.getAllStandingsRepository().observe(this, newsResponse -> {
            try {
                List<TeamStandingModel> standingsDetails = newsResponse.getTeams();
                standingsArrayList.addAll(standingsDetails);
                Collections.shuffle(teamsArrayList);
                adapter.notifyDataSetChanged();
                loadingProgress.setVisibility(View.GONE);
            } catch (Exception e) {
                e.getMessage();
            }
        });
    }

    private void setupAdapter(){

        if (standingsArrayList.size() > 0) {
            loadingProgress.setVisibility(View.GONE);
        } else {
            loadingProgress.setVisibility(View.VISIBLE);
        }

        adapter = new StandingsAdapter(getContext(), standingsArrayList);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}