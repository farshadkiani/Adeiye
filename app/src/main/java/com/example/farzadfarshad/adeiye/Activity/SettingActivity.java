package com.example.farzadfarshad.adeiye.Activity;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.farzadfarshad.adeiye.Adapter.SettingChildAdapter;
import com.example.farzadfarshad.adeiye.Adapter.SettingParentAdapter;
import com.example.farzadfarshad.adeiye.Model.SettingParentModel;
import com.example.farzadfarshad.adeiye.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SettingActivity extends AppCompatActivity implements View.OnClickListener{

    private List<SettingParentModel> movieList = new ArrayList<>();
    private RecyclerView recyclerViewRight;
    private RecyclerView recyclerViewLeft;
    private SettingParentAdapter mAdapter;
    private SettingChildAdapter settingChildAdapter;

    @BindView(R.id.toolbar_title)
    TextView toolbar_title;


    @BindView(R.id.play_img)
    ImageView play_img;


    @BindView(R.id.back_img)
    ImageView back_img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);
        initView(0);
    }

    private void initView(int position) {

        toolbar_title.setText(getResources().getString(R.string.nav_setting));
        play_img.setVisibility(View.GONE);
        back_img.setOnClickListener(this);


        recyclerViewRight = (RecyclerView) findViewById(R.id.relative_right);
        recyclerViewLeft = (RecyclerView) findViewById(R.id.relative_left);

        mAdapter = new SettingParentAdapter(this, movieList, position);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerViewRight.setLayoutManager(mLayoutManager);
        recyclerViewRight.setItemAnimator(new DefaultItemAnimator());
        recyclerViewRight.setAdapter(mAdapter);

        prepareDateParent();


        mAdapter.setClickItem(new SettingParentAdapter.onClick() {
            @Override
            public void click(int position) {
                settingChildAdapter = new SettingChildAdapter(SettingActivity.this, movieList, 0);
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                recyclerViewLeft.setLayoutManager(mLayoutManager);
                recyclerViewLeft.setItemAnimator(new DefaultItemAnimator());
                recyclerViewLeft.setAdapter(settingChildAdapter);
                settingChildAdapter.notifyDataSetChanged();
               /* mAdapter = new SettingParentAdapter(SettingActivity.this, movieList, position);
                mAdapter.notifyDataSetChanged();*/
                movieList.clear();
                initView(position);
            }
        });


    }

    private void prepareDateParent() {
        SettingParentModel settingParentModel = new SettingParentModel("masjed");
        movieList.add(settingParentModel);

        settingParentModel = new SettingParentModel("home");
        movieList.add(settingParentModel);

        settingParentModel = new SettingParentModel("door");
        movieList.add(settingParentModel);

        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back_img:
                onBackPressed();
                break;
        }
    }
}
