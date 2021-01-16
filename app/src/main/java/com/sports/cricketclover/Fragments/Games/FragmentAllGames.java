package com.sports.cricketclover.Fragments.Games;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.sports.cricketclover.Adapter.Events.AllGamesAdapter;
import com.sports.cricketclover.Model.Events.MatchListModel;
import com.sports.cricketclover.R;
import com.sports.cricketclover.ViewModel.Events.EventsViewModels;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FragmentAllGames extends Fragment {

    public static ArrayList<MatchListModel> allGameArrayList = new ArrayList<>();
    EventsViewModels eventsViewModels;
    AllGamesAdapter adapter;

    RecyclerView recyclerView;
    AVLoadingIndicatorView loadingData;
    TextView txtLoading;

    public FragmentAllGames() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_all_games, container, false);

        loadingData = view.findViewById(R.id.loadingData);
        txtLoading = view.findViewById(R.id.txtLoading);

        eventsViewModels = ViewModelProviders.of(this).get(EventsViewModels.class);
        eventsViewModels.init();
        eventsViewModels.getAllGamesRepository().observe(this, allGamesResponse -> {
            try {
                List<MatchListModel> newsArticles = allGamesResponse.getMatchList().getMatches();
                allGameArrayList.addAll(newsArticles);
                Collections.shuffle(allGameArrayList);
                adapter.notifyDataSetChanged();
                loadingData.setVisibility(View.GONE);
                txtLoading.setVisibility(View.GONE);
            } catch (Exception e) {
                e.getMessage();
            }
        });

        initRecyclerView(view);
        return view;
    }

    private void initRecyclerView(View view){
        recyclerView = view.findViewById(R.id.games_all_recycler);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(llm);

        if (adapter == null) {
            if (allGameArrayList.size() > 0) {
                loadingData.setVisibility(View.GONE);
                txtLoading.setVisibility(View.GONE);
            } else {
                loadingData.setVisibility(View.VISIBLE);
                txtLoading.setVisibility(View.VISIBLE);
            }

            adapter = new AllGamesAdapter(getContext(), allGameArrayList);
            recyclerView.setAdapter(adapter);
        } else {
            adapter.notifyDataSetChanged();
        }
    }
}