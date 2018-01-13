package com.example.farzadfarshad.adeiye.Interface;

/**
 * Created by FARZAD&FARSHAD on 20/12/2017.
 */


import com.example.farzadfarshad.adeiye.MapModel.Example;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

public interface RetrofitMaps {

    @GET("api/place/nearbysearch/json?sensor=true&key=AIzaSyDN7RJFmImYAca96elyZlE5s_fhX-MMuhk")
    Call<Example> getNearbyPlaces(@Query("type") String type, @Query("location") String location, @Query("radius") int radius);
}
