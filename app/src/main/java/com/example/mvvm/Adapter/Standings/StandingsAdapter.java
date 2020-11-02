package com.example.mvvm.Adapter.Standings;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvvm.Model.Standings.TeamStandingModel;
import com.example.mvvm.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class StandingsAdapter extends RecyclerView.Adapter<StandingsAdapter.StandingsViewHolder> {
    Context context;
    List<TeamStandingModel> teamStandingModelList;
    TeamStandingModel teamStandingModel;
    public class StandingsViewHolder extends RecyclerView.ViewHolder {
        TextView teamName;
        TextView winLose;
        TextView tied;
        ImageView teamLogo;
        public StandingsViewHolder(@NonNull View itemView) {
            super(itemView);
            teamName = itemView.findViewById(R.id.standings_team_name);
            winLose = itemView.findViewById(R.id.standings_win_loss);
            tied = itemView.findViewById(R.id.standings_tied);
            teamLogo = itemView.findViewById(R.id.standings_team_logo);
        }
    }

    public StandingsAdapter(Context context, List<TeamStandingModel> teamStandingModelList){
        this.context = context;
        this.teamStandingModelList = teamStandingModelList;
    }

    @NonNull
    @Override
    public StandingsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.standings_list, parent, false);
        return new StandingsViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull StandingsViewHolder holder, int position) {
        teamStandingModel = teamStandingModelList.get(position);
        holder.teamName.setText(teamStandingModel.getName());
        holder.winLose.setText("WIN: " + teamStandingModel.getWon() + "- LOSE: " + teamStandingModel.getLost()+ " (D: " + teamStandingModel.getTied() + ")");
        holder.teamName.setText(teamStandingModel.getName());
        Picasso.get().load(teamStandingModel.getLogoUrl()).into(holder.teamLogo);
    }

    @Override
    public int getItemCount() {
        return teamStandingModelList.size();
    }


}
