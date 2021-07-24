package com.example.memberajv.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TVResponse {

    @SerializedName("page")
    private int page;
    @SerializedName("result")
    private List<TV> results;
    @SerializedName("total_results")
    private int total_results;
    @SerializedName("total_pages")
    private int total_pages;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<TV> getResults() {
        return results;
    }

    public void setResults(List<TV> results) {
        this.results = results;
    }

    public int getTotal_results() {
        return total_results;
    }

    public void setTotal_results(int total_results) {
        this.total_results = total_results;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }
}
