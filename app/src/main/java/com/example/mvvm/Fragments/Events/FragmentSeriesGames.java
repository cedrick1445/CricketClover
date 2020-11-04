package com.example.mvvm.Fragments.Events;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvvm.Adapter.Events.GamesAdapter;
import com.example.mvvm.Model.Events.MatchListModel;
import com.example.mvvm.R;
import com.example.mvvm.ViewModel.Events.EventsViewModels;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FragmentSeriesGames extends Fragment {
    public static ArrayList<MatchListModel> articleArrayList = new ArrayList<>();
    EventsViewModels eventsViewModels;
    GamesAdapter adapter;

    LinearLayout loadingLayout;
    RecyclerView recyclerView;

    public FragmentSeriesGames() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_series_games, container, false);

        initComponents(view);
        initObserver();
        initRecyclerView();
        return view;
    }

    private void initObserver(){
        eventsViewModels = ViewModelProviders.of(this).get(EventsViewModels.class);
        eventsViewModels.init();
        eventsViewModels.getLiveGamesRepository().observe(this, newsResponse -> {
            try {
                List<MatchListModel> newsArticles = newsResponse.getMatchList().getMatches();
                articleArrayList.addAll(newsArticles);
                Collections.shuffle(articleArrayList);
                adapter.notifyDataSetChanged();
                loadingLayout.setVisibility(View.GONE);
            } catch (Exception e) {
                e.getMessage();
            }
        });
    }

    private void initComponents(View view){
        loadingLayout = view.findViewById(R.id.loadingLayout);
        recyclerView = view.findViewById(R.id.games_recycler);
    }

    private void initRecyclerView(){
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(llm);

        if (articleArrayList.size() > 0) {
            loadingLayout.setVisibility(View.GONE);
        } else {
            loadingLayout.setVisibility(View.VISIBLE);
        }
        adapter = new GamesAdapter(getContext(), articleArrayList);
        recyclerView.setAdapter(adapter);
    }
}