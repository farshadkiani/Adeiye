package com.example.farzadfarshad.adeiye.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import com.example.farzadfarshad.adeiye.R;
import com.example.farzadfarshad.adeiye.Tools.SmileyView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CircleActivity extends AppCompatActivity {


    @BindView(R.id.smile)
    com.example.farzadfarshad.adeiye.Tools.SmileyView smileyView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle);
        ButterKnife.bind(this);

    }
}
