package com.example.farzadfarshad.adeiye.PdfShow;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by farshad&farzad on 5/18/2018.
 */

public class PdfModel {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("contents")
    @Expose
    private List<PdfModelDetail> contents = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<PdfModelDetail> getContents() {
        return contents;
    }

    public void setContents(List<PdfModelDetail> contents) {
        this.contents = contents;
    }

}
