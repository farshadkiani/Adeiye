package com.example.farzadfarshad.adeiye.RetrofitManager;

import com.example.farzadfarshad.adeiye.Model.AzanModel.Example;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by FARZAD&FARSHAD on 01/01/2018.
 */

public interface ServerAPIAzan {

    @GET
    Call<Example> downlloadAzan(@Url String url);

    Retrofit retrofit =
            new Retrofit.Builder()
                    .baseUrl("http://api.aladhan.com/") // REMEMBER TO END with /
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
}
