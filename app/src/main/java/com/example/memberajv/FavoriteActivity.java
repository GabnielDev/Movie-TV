package com.example.memberajv;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;

import com.example.memberajv.Adapter.FavoriteAdapter;
import com.example.memberajv.Adapter.TrailerAdapter;
import com.example.memberajv.Model.Movie;
import com.example.memberajv.Model.Trailer;
import com.example.memberajv.data.FavoriteDbHelper;

import java.util.ArrayList;
import java.util.List;

public class FavoriteActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener {

    FavoriteAdapter adapter;
    List<Movie> movieList;
    RecyclerView rvFavorite;
    private FavoriteDbHelper favoriteDbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);

        recyclerViews();

    }

    private void recyclerViews() {
        rvFavorite = findViewById(R.id.rvFavorite);
        movieList = new ArrayList<>();

        rvFavorite = findViewById(R.id.rvFavorite);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        rvFavorite.setLayoutManager(layoutManager);
        rvFavorite.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        getAllFavorite();


    }

    private void getAllFavorite() {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                movieList.clear();
                movieList.addAll(favoriteDbHelper.getAllFavorite());
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                adapter.notifyDataSetChanged();
            }
        }.execute();
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
    }
}