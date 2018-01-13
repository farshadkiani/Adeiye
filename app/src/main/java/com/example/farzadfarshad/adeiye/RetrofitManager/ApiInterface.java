package com.example.farzadfarshad.adeiye.RetrofitManager;

import com.example.farzadfarshad.adeiye.Model.UrlImage;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * Created by FARZAD&FARSHAD on 12/11/2017.
 */

public interface ApiInterface {
    @GET("get")
    Call<UrlImage> getUrlImage();


    @GET
    Call<ResponseBody> downlload(@Url String fileUrl);

}
