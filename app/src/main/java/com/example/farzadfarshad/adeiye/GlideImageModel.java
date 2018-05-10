package com.example.farzadfarshad.adeiye;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.example.farzadfarshad.adeiye.ContextModule;

import dagger.Module;
import dagger.Provides;

/**
 * Created by farshad&farzad on 4/16/2018.
 */

@Module(includes = ContextModule.class)
public class GlideImageModel {

    @Provides
    public Glide getGlide(Context context){
        return Glide.get(context);
    }
}
