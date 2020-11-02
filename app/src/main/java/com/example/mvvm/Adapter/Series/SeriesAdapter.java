package com.example.mvvm.Adapter.Series;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvvm.Model.Series.SeriesListModel;
import com.example.mvvm.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SeriesAdapter extends RecyclerView.Adapter<SeriesAdapter.SeriesViewHolder> {
    CountDownTimer countDownTimer;
    Context context;
    List<SeriesListModel> seriesListModelList;
    SeriesListModel seriesListModel;
    int flag;
    public class SeriesViewHolder extends RecyclerView.ViewHolder {
        TextView seriesName;
        TextView status;
        TextView startDate;
        TextView seriesId;
        TextView endDate;
        ImageView seriesLogo;
        public SeriesViewHolder(@NonNull View itemView) {
            super(itemView);
            seriesName = itemView.findViewById(R.id.series_name);
            seriesId = itemView.findViewById(R.id.series_id);
            status = itemView.findViewById(R.id.series_status);
            startDate = itemView.findViewById(R.id.series_start);
            endDate = itemView.findViewById(R.id.series_end);
            seriesLogo = itemView.findViewById(R.id.series_logo);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                }
            });
        }
    }

    public SeriesAdapter(Context context, List<SeriesListModel> seriesListModelList){
        this.context = context;
        this.seriesListModelList = seriesListModelList;
        flag = 0;
    }
    @NonNull
    @Override
    public SeriesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.series_list, parent, false);
        return new SeriesViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull SeriesViewHolder holder, int position) {
        seriesListModel = seriesListModelList.get(position);
        holder.seriesName.setText(seriesListModel.getName());
        holder.seriesId.setText(seriesListModel.getId().toString());
        holder.status.setText(seriesListModel.getStatus());
        holder.startDate.setText("Start: "+ seriesListModel.getStartDateTime());
        holder.endDate.setText("End: "+seriesListModel.getEndDateTime());
        Picasso.get().load(seriesListModel.getShieldImageUrl()).into(holder.seriesLogo);
    }

    @Override
    public int getItemCount() {
        return seriesListModelList.size();
    }
}
