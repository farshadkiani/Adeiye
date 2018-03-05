package com.example.farzadfarshad.adeiye.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by farshad&farzad on 2/24/2018.
 */

public class Contents {
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("aaye")
    @Expose
    private String aaye;
    @SerializedName("hadis")
    @Expose
    private String hadis;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAaye() {
        return aaye;
    }

    public void setAaye(String aaye) {
        this.aaye = aaye;
    }

    public String getHadis() {
        return hadis;
    }

    public void setHadis(String hadis) {
        this.hadis = hadis;
    }
}
