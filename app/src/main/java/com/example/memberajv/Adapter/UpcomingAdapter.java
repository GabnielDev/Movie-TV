package com.example.memberajv.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.memberajv.DetailActivity;
import com.example.memberajv.Model.Movie;
import com.example.memberajv.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class UpcomingAdapter extends RecyclerView.Adapter<UpcomingAdapter.ViewHolder> {

    private List<Movie> movies;
    private Context context;

    public UpcomingAdapter(List<Movie> movies, Context context) {
        this.movies = movies;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_poster, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Picasso.get().load("https://image.tmdb.org/t/p/w300_and_h450_bestv2" +
                movies.get(position).getPosterPath()).into(holder.imgPoster);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("title", movies.get(position).getTitle());
                intent.putExtra("date", movies.get(position).getReleaseDate());
                intent.putExtra("vote", movies.get(position).getVoteAverage().toString());
                intent.putExtra("overview", movies.get(position).getOverview());
                intent.putExtra("poster", movies.get(position).getPosterPath());
                intent.putExtra("id", movies.get(position).getId());
                intent.putExtra("backposter", movies.get(position).getBackdropPath());
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imgPoster;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgPoster = itemView.findViewById(R.id.imgPoster);

        }
    }
}
