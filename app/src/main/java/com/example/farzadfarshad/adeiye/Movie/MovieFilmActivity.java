package com.example.farzadfarshad.adeiye.Movie;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

import com.example.farzadfarshad.adeiye.R;

public class MovieFilmActivity extends AppCompatActivity {

    VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_film);

        videoView = (VideoView) findViewById(R.id.videoView);

        String url = getIntent().getStringExtra("url");

        Uri uri = Uri.parse(url);
        MediaController mediaController = new MediaController(this);
        videoView.setVideoURI(uri);
        videoView.setMediaController(mediaController);
        videoView.start();
    }
}
