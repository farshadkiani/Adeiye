package com.example.farzadfarshad.adeiye.Model;

/**
 * Created by FARZAD&FARSHAD on 12/11/2017.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FirstWebService {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("contents")
    @Expose
    private Contents contents;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Contents getContents() {
        return contents;
    }

    public void setContents(Contents contents) {
        this.contents = contents;
    }

}
