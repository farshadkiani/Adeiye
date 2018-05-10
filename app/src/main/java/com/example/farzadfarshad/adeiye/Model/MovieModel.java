package com.example.farzadfarshad.adeiye.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by farshad&farzad on 4/14/2018.
 */

public class MovieModel {


    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("contents")
    @Expose
    private List<MovieModelDetail> contents = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<MovieModelDetail> getContents() {
        return contents;
    }

    public void setContents(List<MovieModelDetail> contents) {
        this.contents = contents;
    }


}
