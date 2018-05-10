package com.example.farzadfarshad.adeiye;



import com.bumptech.glide.Glide;

import javax.inject.Singleton;

import dagger.Component;


/**
 * Created by farshad&farzad on 4/16/2018.
 */

@Component(modules = {GlideImageModel.class})
public interface DaggerComponent {

    void injectMyApplication(MyApplication myApplication);

//    Glide getGlide();
}
