package com.example.memberajv;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.memberajv.Adapter.TrailerAdapter;
import com.example.memberajv.Model.Movie;
import com.example.memberajv.Model.Trailer;
import com.example.memberajv.Model.TrailerResponse;
import com.example.memberajv.Rest.ApiClient;
import com.example.memberajv.Rest.ApiInterface;
import com.example.memberajv.data.FavoriteDbHelper;
import com.github.ivbaranov.mfb.MaterialFavoriteButton;
import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.picasso.transformations.BlurTransformation;
import jp.wasabeef.picasso.transformations.CropCircleTransformation;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity {

    public TextView txtTitle, txtOverview, txtDate, txtVote, txtGenres;
    public ImageView imgPoster, imgBackPoster;
    RecyclerView rvTrailer;
    TrailerAdapter adapter;
    List<Trailer> trailerList;
//    MaterialFavoriteButton favoriteButton;
    SharedPreferences sharedPreferences;
    private FavoriteDbHelper favoriteDbHelper;
    private Movie favorite;
    private final AppCompatActivity activity = DetailActivity.this;


    private final static String API_KEY = "e4b52bfade0e0539c2a8645f5b587ed0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        //initiation
        txtTitle = findViewById(R.id.txtTitle);
        txtDate = findViewById(R.id.txtDate);
        txtOverview = findViewById(R.id.txtDescrip);
        txtVote = findViewById(R.id.txtrating);
        imgPoster = findViewById(R.id.imgPoster);
        imgBackPoster = findViewById(R.id.imgBackPoster);
//        favoriteButton = findViewById(R.id.favoriteButton);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        final Integer id = getIntent().getIntExtra("id", 0);
        String title = getIntent().getStringExtra("title");
        String date = getIntent().getStringExtra("date");
        String overview = getIntent().getStringExtra("overview");
        String vote = getIntent().getStringExtra("vote");

        Log.d("Test", "onCreate: " + id);

        txtTitle.setText(title);
        txtDate.setText(date);
        txtOverview.setText(overview);
        txtVote.setText(vote);

        Picasso.get()
                .load("https://image.tmdb.org/t/p/w300" + getIntent().getStringExtra("poster"))
                .into(imgPoster);

        Picasso.get()
                .load("https://image.tmdb.org/t/p/w500" +getIntent().getStringExtra("backposter"))
                .transform(new BlurTransformation(getApplicationContext(), 10, 1))
                .into(imgBackPoster);

//        favoriteButton.setOnFavoriteChangeListener(new MaterialFavoriteButton.OnFavoriteChangeListener() {
//            @Override
//            public void onFavoriteChanged(MaterialFavoriteButton buttonView, boolean favorite) {
//                if (favorite) {
//                    SharedPreferences.Editor editor = getSharedPreferences("com.example.memberajv.DetailActivity", MODE_PRIVATE).edit();
//                    editor.putBoolean("Favorite Added", true);
//                    editor.commit();
//                    saveFavorite();
//                    Snackbar.make(buttonView, "Added to Favorite", Snackbar.LENGTH_SHORT).show();
//                } else {
//                    int movie_id = getIntent().getExtras().getInt("id");
//                    favoriteDbHelper = new FavoriteDbHelper(DetailActivity.this);
//                    favoriteDbHelper.deleteFavorite(movie_id);
//                    SharedPreferences.Editor editor = getSharedPreferences("com.example.memberajv.DetailActivity", MODE_PRIVATE).edit();
//                    editor.putBoolean("Favorite Remove", true);
//                    editor.commit();
//                    Snackbar.make(buttonView, "Removed from Favorite", Snackbar.LENGTH_SHORT).show();
//                }
//            }
//        });

        recyclerViews();
        loadJSON(id);

    }

//    public void watchYoutubeVideo() {
//        int movie_id = getIntent().getExtras().getInt("id");
//
//        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
//        Call<TrailerResponse> call = apiInterface.getMovieTrailer(movie_id, API_KEY);
//
//        String videoId = trailerList.get(movie_id).getKey();
//        Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://youtube.com/watch?v=" +videoId));
//            startActivity(webIntent);
//    }

    private void recyclerViews() {
        trailerList = new ArrayList<>();
        adapter = new TrailerAdapter(this, trailerList);

        rvTrailer = findViewById(R.id.rvTrailer);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        rvTrailer.setLayoutManager(layoutManager);
        rvTrailer.setAdapter(adapter);
        adapter.notifyDataSetChanged();


    }

    private void loadJSON(Integer movie_id) {
        try {
            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

            Call<TrailerResponse> call = apiInterface.getMovieTrailer(movie_id, API_KEY);
            call.enqueue(new Callback<TrailerResponse>() {
                @Override
                public void onResponse(Call<TrailerResponse> call, Response<TrailerResponse> response) {
                    List<Trailer> trailers = response.body().getResults();
                    rvTrailer.setAdapter(new TrailerAdapter(getApplicationContext(), trailers));
                    Log.d("Trailer", "onResponse: "+trailers);
                    rvTrailer.smoothScrollToPosition(0);
                }

                @Override
                public void onFailure(Call<TrailerResponse> call, Throwable t) {
                    Log.d("Error", t.getMessage());
                    Toast.makeText(DetailActivity.this, "Error fetching trailer data", Toast.LENGTH_SHORT).show();
                }
            });

        } catch (Exception e) {
            Log.d("Error", e.getMessage());
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    public void saveFavorite() {
        favoriteDbHelper = new FavoriteDbHelper(activity);
        favorite = new Movie();

        int movie_id = getIntent().getExtras().getInt("id");
        String rate = getIntent().getExtras().getString("vote");
        String poster = getIntent().getExtras().getString("poster");

        favorite.setId(movie_id);
        favorite.setOriginalTitle(txtTitle.getText().toString().trim());
        favorite.setPosterPath(poster);
        favorite.setVoteAverage(Double.parseDouble(rate));
        favorite.setOverview(txtOverview.getText().toString().trim());

        favoriteDbHelper.addFavorite(favorite);


    }

}
