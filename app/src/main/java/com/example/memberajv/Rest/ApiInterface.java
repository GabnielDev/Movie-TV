package com.example.memberajv.Rest;

import com.example.memberajv.Model.MovieResponse;
import com.example.memberajv.Model.TVResponse;
import com.example.memberajv.Model.TrailerResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("movie/top_rated")
    Call<MovieResponse> getTopRatedMovies(@Query("api_key") String apiKey);

    @GET("movie/{id}")
    Call<MovieResponse> getMovieDetails(@Path("id") int id, @Query("api_key") String apiKey);

    @GET("movie/now_playing")
    Call<MovieResponse> getNowPlaying(@Query("api_key") String apiKey);

    @GET("movie/popular")
    Call<MovieResponse> getPopular(@Query("api_key") String apiKey);

    @GET("tv/airing_today")
    Call<TVResponse> getAiringToday (@Query("api_key") String apiKey);

    @GET("movie/upcoming")
    Call<MovieResponse> getUpcoming(@Query("api_key") String apiKey);

    @GET("movie/{movie_id}/videos")
    Call<TrailerResponse> getMovieTrailer(@Path("movie_id") int id,
                                          @Query("api_key") String apiKey);

}
