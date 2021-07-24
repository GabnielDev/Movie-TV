package com.example.memberajv.Fragment;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.memberajv.Adapter.AiringTodayAdapter;
import com.example.memberajv.Model.TV;
import com.example.memberajv.Model.TVResponse;
import com.example.memberajv.R;
import com.example.memberajv.Rest.ApiClient;
import com.example.memberajv.Rest.ApiInterface;
import com.example.memberajv.TV.InewsActivity;
import com.example.memberajv.TV.KompasActivity;
import com.example.memberajv.TV.NetActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class TVFragment extends Fragment {

    ImageSlider images_slider_tv;
    Button net_tv, kompas_tv, inews_tv;
    RecyclerView rvAiringToday;

    private final static String API_KEY = "e4b52bfade0e0539c2a8645f5b587ed0";

    public TVFragment() {

        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_tv, container, false);

        images_slider_tv = view.findViewById(R.id.imageslidertv);
        net_tv = view.findViewById(R.id.nettv);
        kompas_tv = view.findViewById(R.id.kompastv);
        inews_tv = view.findViewById(R.id.inewstv);

//        rvtvNasional = view.findViewById(R.id.rvtvNasional);
//        rvtvNasional.setLayoutManager(new LinearLayoutManager(getActivity()));

        //imageslider
        ArrayList<SlideModel> images = new ArrayList<>();
        images.add(new SlideModel("https://cdn.akurat.co/images/uploads/images/akurat_20201101040552_BVYhz0.jpg", null));
        images.add(new SlideModel(R.drawable.miracleincellland, null));
        images.add(new SlideModel(R.drawable.miracleincellland, null));
        images.add(new SlideModel(R.drawable.miracleincellland, null));
        images_slider_tv.setImageList(images, ScaleTypes.CENTER_CROP);

        //action nasional tv
        net_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), NetActivity.class));
            }
        });

        kompas_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), KompasActivity.class));
            }
        });

        inews_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), InewsActivity.class));
            }
        });

        //rv
//        airingtoday(view);

        return view;
    }

    private void airingtoday(final View view) {
        rvAiringToday = view.findViewById(R.id.rvAiringToday);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        rvAiringToday.setLayoutManager(layoutManager);

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

        Call<TVResponse> call = apiInterface.getAiringToday(API_KEY);
        call.enqueue(new Callback<TVResponse>() {
            @Override
            public void onResponse(Call<TVResponse> call, Response<TVResponse> response) {
                List<TV> tvs = response.body().getResults();
                rvAiringToday.setAdapter(new AiringTodayAdapter(tvs, view.getContext()));
            }

            @Override
            public void onFailure(Call<TVResponse> call, Throwable t) {

            }
        });

    }

}
