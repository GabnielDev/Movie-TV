package com.example.memberajv.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.memberajv.Model.TV;
import com.example.memberajv.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AiringTodayAdapter  extends RecyclerView.Adapter<AiringTodayAdapter.ViewHolder> {

    private List<TV> tv;
    private Context context;

    public AiringTodayAdapter(List<TV> tv, Context context) {
        this.tv = tv;
        this.context = context;
    }

    @NonNull
    @Override
    public AiringTodayAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_poster, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AiringTodayAdapter.ViewHolder holder, int position) {
        Picasso.get().load("https://image.tmdb.org/t/p/w300_and_h450_bestv2" +
                tv.get(position).getPoster_path()).into(holder.imgPoster);
    }

    @Override
    public int getItemCount() {
        return tv.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imgPoster;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgPoster = itemView.findViewById(R.id.imgPoster);

        }
    }
}
