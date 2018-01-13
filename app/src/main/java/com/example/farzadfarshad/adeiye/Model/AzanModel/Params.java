package com.example.farzadfarshad.adeiye.Model.AzanModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by FARZAD&FARSHAD on 08/01/2018.
 */

public class Params {


    @SerializedName("Fajr")
    @Expose
    private Integer fajr;
    @SerializedName("Isha")
    @Expose
    private Integer isha;

    public Integer getFajr() {
        return fajr;
    }

    public void setFajr(Integer fajr) {
        this.fajr = fajr;
    }

    public Integer getIsha() {
        return isha;
    }

    public void setIsha(Integer isha) {
        this.isha = isha;
    }






}
