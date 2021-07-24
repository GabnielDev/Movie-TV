package com.example.memberajv.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.memberajv.Model.Trailer;
import com.example.memberajv.R;

import java.util.List;

public class TrailerAdapter extends RecyclerView.Adapter<TrailerAdapter.TrailerViewHolder> {

    private Context context;
    private List<Trailer> trailerList;

    public TrailerAdapter(Context context, List<Trailer> trailerList) {
        this.context = context;
        this.trailerList = trailerList;
    }

    @NonNull
    @Override
    public TrailerAdapter.TrailerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_trailer, parent, false);
        return new TrailerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TrailerAdapter.TrailerViewHolder holder, int position) {
        holder.txtTitle.setText(trailerList.get(position).getName());


    }

    @Override
    public int getItemCount() {
        return trailerList.size();
    }

    public class TrailerViewHolder extends RecyclerView.ViewHolder {

        TextView txtTitle;

        public TrailerViewHolder(@NonNull final View itemView) {
            super(itemView);

            txtTitle = itemView.findViewById(R.id.txtTitle);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {

                        String videoId = trailerList.get(pos).getKey();
//                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://youtube/watch?v=" +videoId));
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:"+videoId));
                        intent.putExtra("VIDEO_ID", videoId);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                    }
                }
            });

        }
    }
}
