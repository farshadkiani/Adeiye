package com.example.farzadfarshad.adeiye.Setting;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;

import com.example.farzadfarshad.adeiye.R;

import java.io.File;

/**
 * Created by farshad&farzad on 5/27/2018.
 */

public class PlayAzan {

    private String name;
    private Context context;
    private MediaPlayer mediaPlayer;

    public PlayAzan(Context context, String filename, MediaPlayer mediaPlayer) {
        this.context = context;
        this.name = filename;
        setMediaPlayer(mediaPlayer);
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void setMediaPlayer(MediaPlayer mediaPlayer) {
        if (this.mediaPlayer == null)
            this.mediaPlayer = mediaPlayer;
    }

    public void setName(String name) {
        this.name = name;
    }


    public boolean playFromSd() {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
            return false;
        }
        mediaPlayer = MediaPlayer.create(context
                , Uri.parse(Environment.getExternalStorageDirectory().getPath() + "/Adeiye/" + name + ".mp3"));
        mediaPlayer.setLooping(false);
        mediaPlayer.start();
        return true;
    }

    public boolean playFromAssest(){
        try {
//            mediaPlayer.release();
//            mediaPlayer = new MediaPlayer();
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.stop();
                return false;
              /*  mediaPlayer.stop();
                mediaPlayer.release();
                mediaPlayer = new MediaPlayer();*/
            }
            mediaPlayer.reset();
            AssetFileDescriptor descriptor = context.getAssets().openFd("azan.mp3");
            mediaPlayer.setDataSource(descriptor.getFileDescriptor(), descriptor.getStartOffset(), descriptor.getLength());
            descriptor.close();

            mediaPlayer.prepare();
            mediaPlayer.setVolume(1f, 1f);
            mediaPlayer.setLooping(false);
            mediaPlayer.start();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


}
