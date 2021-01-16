package com.sports.cricketclover.Fragments.Teams;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sports.cricketclover.Adapter.Team.TeamsAdapter;
import com.sports.cricketclover.Model.Teams.TeamsListModel;
import com.sports.cricketclover.R;
import com.sports.cricketclover.ViewModel.Teams.TeamsViewModels;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FragmentTeams extends Fragment {

    RecyclerView teams_recycler;
    public static ArrayList<TeamsListModel> teamsArrayList = new ArrayList<>();
    TeamsViewModels teamsViewModels;
    TeamsAdapter adapter;
    AVLoadingIndicatorView loadingBanner;
    TextView txtLoading;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_teams, container, false);

        teams_recycler = view.findViewById(R.id.teams_recycler);
        loadingBanner = view.findViewById(R.id.loadingBanner);
        txtLoading = view.findViewById(R.id.txtLoading);
        initObserver();
        initRecyclerView();
        return view;
    }

    private void initObserver() {
        teamsViewModels = ViewModelProviders.of(this).get(TeamsViewModels.class);
        teamsViewModels.init();
        teamsViewModels.getAllTeamsRepository().observe(this, newsResponse -> {
            try {
                List<TeamsListModel> newsArticles = newsResponse.getSeriesTeams().getTeams();
                teamsArrayList.addAll(newsArticles);
                Collections.shuffle(teamsArrayList);
                adapter.notifyDataSetChanged();
                loadingBanner.setVisibility(View.GONE);
                txtLoading.setVisibility(View.GONE);
            } catch (Exception e) {
                e.getMessage();
            }
        });
    }

    private void initRecyclerView(){
        GridLayoutManager grid = new GridLayoutManager(getContext(),2, GridLayoutManager.VERTICAL, false);
        teams_recycler.setLayoutManager(grid);
        adapter = new TeamsAdapter(getContext(), teamsArrayList);
        teams_recycler.setAdapter(adapter);
    }
}