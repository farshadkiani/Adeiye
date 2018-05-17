package com.example.farzadfarshad.adeiye.PaskheAzan;

import android.media.MediaPlayer;

import com.example.farzadfarshad.adeiye.ContextModule;

import dagger.Module;
import dagger.Provides;

/**
 * Created by farshad&farzad on 5/11/2018.
 */


@Module(includes = ContextModule.class)
public class PakhsheAzanModule {


    @Provides
    MediaPlayer getMediaPlayer(){
        return new MediaPlayer();
    }




}
