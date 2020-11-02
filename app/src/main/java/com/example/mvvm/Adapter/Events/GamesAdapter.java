package com.example.mvvm.Adapter.Events;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvvm.Model.Events.MatchListModel;
import com.example.mvvm.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class GamesAdapter extends RecyclerView.Adapter<GamesAdapter.GamesViewHolder> {
    Context context;
    List<MatchListModel> matchList;
    MatchListModel match;
    public class GamesViewHolder extends RecyclerView.ViewHolder {

        TextView series;
        TextView venue;
        TextView homeName;
        TextView homeScore;
        TextView awayName;
        TextView awayScore;
        TextView date;
        TextView status;
        CircleImageView homeLogo;
        CircleImageView awayLogo;
        public GamesViewHolder(@NonNull View itemView) {
            super(itemView);
            series = itemView.findViewById(R.id.games_series);
            venue = itemView.findViewById(R.id.games_venue);
            homeName = itemView.findViewById(R.id.games_home_name);
            homeScore = itemView.findViewById(R.id.games_home_score);
            awayName = itemView.findViewById(R.id.games_away_name);
            awayScore = itemView.findViewById(R.id.games_away_score);
            date = itemView.findViewById(R.id.games_date);
            status = itemView.findViewById(R.id.games_status);
            homeLogo = itemView.findViewById(R.id.games_home_logo);
            awayLogo = itemView.findViewById(R.id.games_away_logo);
        }
    }

    public GamesAdapter(Context context, List<MatchListModel> matchList){
        this.context = context;
        this.matchList = matchList;
    }
    @NonNull
    @Override
    public GamesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.games_list,parent,false);
        return new GamesViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull GamesViewHolder holder, int position) {
        match = matchList.get(position);

        if(match.getStatus().equalsIgnoreCase("COMPLETED")){
            holder.series.setText("Series: " + match.getSeries().getName());
            holder.venue.setText("Venue: " + match.getVenue().getName());
            holder.homeName.setText(match.getHomeTeam().getName());
            holder.homeScore.setText(match.getScores().getHomeScore());
            holder.awayScore.setText(match.getScores().getAwayScore());
            holder.awayName.setText(match.getAwayTeam().getName());
            holder.date.setText("Date Started "+ match.getStartDateTime().substring(0,10));
            holder.status.setText(match.getStatus());
            Picasso.get().load(match.getHomeTeam().getLogoUrl()).into(holder.homeLogo);
            Picasso.get().load(match.getAwayTeam().getLogoUrl()).into(holder.awayLogo);
        }
        else {

            if(match.getHomeTeam().getName().equalsIgnoreCase("unknown")){
                holder.series.setText("Series: " + match.getSeries().getName());
                holder.venue.setText("Venue: " + match.getVenue().getName());
                holder.homeName.setText(match.getHomeTeam().getName());
                holder.awayName.setText(match.getAwayTeam().getName());
                holder.date.setText("Date Started " + match.getStartDateTime().substring(0,10));
                holder.status.setText(match.getStatus());
                holder.homeLogo.setImageResource(R.drawable.no_image);
                holder.awayLogo.setImageResource(R.drawable.no_image);
            }else{
                holder.series.setText("Series: " + match.getSeries().getName());
                holder.venue.setText("Venue: " + match.getVenue().getName());
                holder.homeName.setText(match.getHomeTeam().getName());
                holder.awayName.setText(match.getAwayTeam().getName());
                holder.date.setText("Date Started " +  match.getStartDateTime().substring(0,10));
                holder.status.setText(match.getStatus());
                Picasso.get().load(match.getHomeTeam().getLogoUrl()).into(holder.homeLogo);
                Picasso.get().load(match.getAwayTeam().getLogoUrl()).into(holder.awayLogo);
            }
        }

    }

    @Override
    public int getItemCount() {
        return matchList.size();
    }


}
