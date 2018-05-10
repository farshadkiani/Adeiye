package com.example.farzadfarshad.adeiye.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by farshad&farzad on 4/14/2018.
 */

public class MovieModelDetail {


    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("video")
    @Expose
    private String video;
    @SerializedName("picture")
    @Expose
    private String picture;
    @SerializedName("comment")
    @Expose
    private String comment;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }



}
