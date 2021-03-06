package com.example.memberajv.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class TV {

    @SerializedName("poster_path")
    private String poster_path;
    @SerializedName("popularity")
    private Double popularity;
    @SerializedName("id")
    private Integer id;
    @SerializedName("backdrop_path")
    private String backdrop_path;
    @SerializedName("vote_average")
    private Integer vote_average;
    @SerializedName("overview")
    private String overview;
    @SerializedName("first_air_date")
    private String first_air_date;
    @SerializedName("origin_country")
    private List<Integer> origin_country = new ArrayList<Integer>();
    @SerializedName("genre_ids")
    private List<Integer> genre_ids = new ArrayList<Integer>();
    @SerializedName("original_language")
    private String original_language;
    @SerializedName("vote_count")
    private Integer vote_count;
    @SerializedName("name")
    private String name;
    @SerializedName("original_name")
    private String original_name;

    public TV(String poster_path, Double popularity, Integer id, String backdrop_path, Integer vote_average, String overview, String first_air_date, List<Integer> origin_country, List<Integer> genre_ids, String original_language, Integer vote_count, String name, String original_name) {
        this.poster_path = poster_path;
        this.popularity = popularity;
        this.id = id;
        this.backdrop_path = backdrop_path;
        this.vote_average = vote_average;
        this.overview = overview;
        this.first_air_date = first_air_date;
        this.origin_country = origin_country;
        this.genre_ids = genre_ids;
        this.original_language = original_language;
        this.vote_count = vote_count;
        this.name = name;
        this.original_name = original_name;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public Double getPopularity() {
        return popularity;
    }

    public void setPopularity(Double popularity) {
        this.popularity = popularity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public Integer getVote_average() {
        return vote_average;
    }

    public void setVote_average(Integer vote_average) {
        this.vote_average = vote_average;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getFirst_air_date() {
        return first_air_date;
    }

    public void setFirst_air_date(String first_air_date) {
        this.first_air_date = first_air_date;
    }

    public List<Integer> getOrigin_country() {
        return origin_country;
    }

    public void setOrigin_country(List<Integer> origin_country) {
        this.origin_country = origin_country;
    }

    public List<Integer> getGenre_ids() {
        return genre_ids;
    }

    public void setGenre_ids(List<Integer> genre_ids) {
        this.genre_ids = genre_ids;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }

    public Integer getVote_count() {
        return vote_count;
    }

    public void setVote_count(Integer vote_count) {
        this.vote_count = vote_count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOriginal_name() {
        return original_name;
    }

    public void setOriginal_name(String original_name) {
        this.original_name = original_name;
    }
}
