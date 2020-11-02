package com.example.mvvm.Fragments.Series;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvvm.Adapter.Series.SeriesAdapter;
import com.example.mvvm.Model.Series.SeriesListModel;
import com.example.mvvm.R;
import com.example.mvvm.ViewModel.Series.SeriesViewModels;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FragmentSeries extends Fragment {

    RecyclerView recyclerView;
    public static ArrayList<SeriesListModel> seriesArrayList = new ArrayList<>();
    SeriesViewModels seriesViewModels;
    SeriesAdapter adapter;
    LinearLayout loadingLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_series, container, false);
        init(view);
        initObserver();
        setupAdapter();
        return view;
    }
    
    private void init(View view){
        recyclerView = view.findViewById(R.id.series_recycler);
        loadingLayout = view.findViewById(R.id.loadingLayout);
    }

    private void initObserver(){
        seriesArrayList.clear();
        seriesViewModels = ViewModelProviders.of(this).get(SeriesViewModels.class);
        seriesViewModels.init();
        seriesViewModels.getAllStandingsRepository().observe(this, newsResponse -> {
            try {
                List<SeriesListModel> standingsDetails = newsResponse.getSeriesList().getSeries();
                seriesArrayList.addAll(standingsDetails);
                Collections.shuffle(seriesArrayList);
                adapter.notifyDataSetChanged();
                loadingLayout.setVisibility(View.GONE);
            } catch (Exception e) {
                e.getMessage();
            }
        });
    }
    
    private void setupAdapter(){
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(llm);

        if (seriesArrayList.size() > 0) {
            loadingLayout.setVisibility(View.GONE);
        } else {
            loadingLayout.setVisibility(View.VISIBLE);
        }
        
        adapter = new SeriesAdapter(getContext(), seriesArrayList);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
