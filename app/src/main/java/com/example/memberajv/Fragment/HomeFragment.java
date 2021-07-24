package com.example.memberajv.Fragment;


import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Handler;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.memberajv.Adapter.NowplayingAdapter;
import com.example.memberajv.Adapter.PopularAdapter;
import com.example.memberajv.Adapter.TopratedAdapter;
import com.example.memberajv.Adapter.UpcomingAdapter;
import com.example.memberajv.DetailActivity;
import com.example.memberajv.Model.Movie;
import com.example.memberajv.Model.MovieResponse;
import com.example.memberajv.R;
import com.example.memberajv.Rest.ApiClient;
import com.example.memberajv.Rest.ApiInterface;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.IndicatorView.draw.data.Indicator;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

import jp.co.recruit_lifestyle.android.widget.WaveSwipeRefreshLayout;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private final static String API_KEY = "e4b52bfade0e0539c2a8645f5b587ed0";

    ImageSlider images_slider_home;
    RecyclerView rvToprated, rvNowplaying, rvPopular, rvUpcoming;
    SwipeRefreshLayout refreshLayout;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_home, container, false);

        refreshItem(view);

        //initiation
        images_slider_home = view.findViewById(R.id.imagesliderhome);
        refreshLayout = view.findViewById(R.id.refreshLayout);
        refreshLayout.setColorSchemeResources(R.color.colorAccent, R.color.colorPrimary);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshItem(view);
                onItemLoad();

            }

//            void refreshItem() {
//                nowplaying(view);
//                upcoming(view);
//                toprated(view);
//                popular(view);
//                onItemLoad();
//            }
//
//            void onItemLoad() {
//                refreshLayout.setRefreshing(false);
//            }



        });


        imagesslider();

        return view;
    }

    void refreshItem(View view) {
        nowplaying(view);
        upcoming(view);
        toprated(view);
        popular(view);
    }

    void onItemLoad() {
        refreshLayout.setRefreshing(false);
    }

    private void imagesslider() {
        ArrayList<SlideModel> images = new ArrayList<>();
        images.add(new SlideModel(R.drawable.miracleincellland, null));
        images.add(new SlideModel(R.drawable.miracleincellland, null));
        images.add(new SlideModel(R.drawable.miracleincellland, null));
        images.add(new SlideModel(R.drawable.miracleincellland, null));

        images_slider_home.setImageList(images, ScaleTypes.CENTER_CROP);
    }

    private void nowplaying(final View view) {
        rvNowplaying = view.findViewById(R.id.rvNowplaying);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        rvNowplaying.setLayoutManager(layoutManager);

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<MovieResponse> call = apiService.getNowPlaying(API_KEY);
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                final List<Movie> movies = response.body().getResults();
                rvNowplaying.setAdapter(new NowplayingAdapter(movies, view.getContext()));

            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {

            }
        });

    }

    private void upcoming(final View view) {
        rvUpcoming = view.findViewById(R.id.rvUpcoming);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        rvUpcoming.setLayoutManager(layoutManager);

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

        Call<MovieResponse> call = apiInterface.getUpcoming(API_KEY);
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                final List<Movie> movies = response.body().getResults();
                rvUpcoming.setAdapter(new UpcomingAdapter(movies, view.getContext()));

            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {

            }
        });

    }

    private void toprated(final View view) {
        rvToprated = view.findViewById(R.id.rvToprated);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        rvToprated.setLayoutManager(layoutManager);

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<MovieResponse> call = apiService.getTopRatedMovies(API_KEY);
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                final List<Movie> movies = response.body().getResults();
                rvToprated.setAdapter(new TopratedAdapter(movies, view.getContext()));

            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {

            }
        });

    }

    private void popular(final View view) {
        rvPopular = view.findViewById(R.id.rvPopular);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        rvPopular.setLayoutManager(layoutManager);

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<MovieResponse> call = apiService.getPopular(API_KEY);
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                final List<Movie> movies = response.body().getResults();
                rvPopular.setAdapter(new PopularAdapter(movies, view.getContext()));

            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {

            }
        });

    }


}
