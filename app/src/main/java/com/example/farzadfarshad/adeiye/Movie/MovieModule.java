package com.example.farzadfarshad.adeiye.Movie;

import android.app.ProgressDialog;
import android.content.Context;

import com.bumptech.glide.Glide;
import com.example.farzadfarshad.adeiye.ContextModule;
import com.example.farzadfarshad.adeiye.Model.MovieModelDetail;
import com.example.farzadfarshad.adeiye.RetrofitManager.ApiInterface;

import java.util.List;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by farshad&farzad on 4/24/2018.
 */

@Module(includes = ContextModule.class)
public class MovieModule {

    public static final String BASE_URL = " http://farshad.hotelphp.ir/";



    @Provides
    MovieAdapter movieAdapter(Context context , Glide glide){
        return new MovieAdapter(context , glide);
    }



    @Provides
    Glide getGlide(Context context){

         return Glide.get(context);
    }


    @Provides
    public ApiInterface ApiService(Retrofit retrofit) {
        return retrofit.create(ApiInterface.class);
    }

    @Provides
    public Retrofit retrofit() {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }

    @Provides
    public ProgressDialog progressDialog(Context context){
        return new ProgressDialog(context);
    }

}
