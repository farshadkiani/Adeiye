package com.example.farzadfarshad.adeiye.Setting;


import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.farzadfarshad.adeiye.Adapter.SettingChildAdapter;
import com.example.farzadfarshad.adeiye.Adapter.SettingParentAdapter;
import com.example.farzadfarshad.adeiye.Model.SettingParentModel;
import com.example.farzadfarshad.adeiye.R;
import com.example.farzadfarshad.adeiye.Tools.SharedPreferencesTools;
import com.example.farzadfarshad.adeiye.Tools.TypefaceUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SettingActivity extends AppCompatActivity
        implements View.OnClickListener {

    private List<SettingParentModel> movieList = new ArrayList<>();
    public static SettingParentAdapter mAdapter;
    private SettingChildAdapter settingChildAdapter;

    @BindView(R.id.toolbar_title)
    TextView toolbar_title;

    @BindView(R.id.play_img)
    ImageView play_img;

    @BindView(R.id.back_img)
    ImageView back_img;

    @BindView(R.id.relative_right)
    RecyclerView recyclerViewRight;

    @BindView(R.id.relative_left)
    RecyclerView recyclerViewLeft;

    SharedPreferencesTools sharedPreferencesTools;

    ArrayList<Item> itemList = new ArrayList<Item>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);
        initView(0);
    }

    private void initView(int position) {
        sharedPreferencesTools = new SharedPreferencesTools(this);
        setFontText(sharedPreferencesTools.getFont());
        toolbar_title.setText(getResources().getString(R.string.nav_setting));
        play_img.setVisibility(View.GONE);
        back_img.setOnClickListener(this);


        mAdapter = new SettingParentAdapter(this, movieList, position);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerViewRight.setLayoutManager(mLayoutManager);
        recyclerViewRight.setItemAnimator(new DefaultItemAnimator());
        recyclerViewRight.setAdapter(mAdapter);

        prepareDateParent();


        itemList.add(new Item("Item " + 0, Item.ItemType.ONE_ITEM));
        ItemArrayAdapter itemArrayAdapter = new ItemArrayAdapter(itemList, SettingActivity.this
                , toolbar_title);
        recyclerViewLeft.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerViewLeft.setItemAnimator(new DefaultItemAnimator());
        recyclerViewLeft.setAdapter(itemArrayAdapter);

        mAdapter.setClickItem(new SettingParentAdapter.onClick() {
            @Override
            public void click(int position) {
                // Initializing list view with the custom adapter
                ArrayList<Item> itemList = new ArrayList<Item>();
                switch (position) {
                    case 0:
                        itemList.add(new Item("Item " + 0, Item.ItemType.ONE_ITEM));
                        break;
                    case 1:
                        itemList.add(new Item("Item " + 1, Item.ItemType.TWO_ITEM));
                        break;
                }

                ItemArrayAdapter itemArrayAdapter = new ItemArrayAdapter(itemList, getBaseContext(), toolbar_title);
                recyclerViewLeft.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                recyclerViewLeft.setItemAnimator(new DefaultItemAnimator());
                recyclerViewLeft.setAdapter(itemArrayAdapter);


                //for background list white and black when slect item
                mAdapter = new SettingParentAdapter(SettingActivity.this, movieList, position);
                mAdapter.setClickItem(this);
                recyclerViewRight.setAdapter(mAdapter);


                itemArrayAdapter.setclicAzan(new clickAzanInterface() {
                    @Override
                    public void click() {
                        CustomDialogAzan customDialogAzan = new CustomDialogAzan(SettingActivity.this);
                        customDialogAzan.show();
                    }
                });


               /* settingChildAdapter = new SettingChildAdapter(SettingActivity.this, movieList, 0);
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                recyclerViewLeft.setLayoutManager(mLayoutManager);
                recyclerViewLeft.setItemAnimator(new DefaultItemAnimator());
                recyclerViewLeft.setAdapter(settingChildAdapter);
                settingChildAdapter.notifyDataSetChanged();
               *//* mAdapter = new SettingParentAdapter(SettingActivity.this, movieList, position);
                mAdapter.notifyDataSetChanged();*//*
                movieList.clear();
                initView(position);*/
            }
        });


    }

    private void prepareDateParent() {
        SettingParentModel settingParentModel = new SettingParentModel(getResources().getString(R.string.text));
        movieList.add(settingParentModel);

        settingParentModel = new SettingParentModel(getResources().getString(R.string.azan));
        movieList.add(settingParentModel);

        settingParentModel = new SettingParentModel("door");
        movieList.add(settingParentModel);

        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_img:
                onBackPressed();
                break;
        }
    }

    public void setFontText(String fontText) {
        Typeface face = Typeface.createFromAsset(getAssets(),
                "Fonts/" + fontText);
        toolbar_title.setTypeface(face);
    }

    public static void notif() {
        mAdapter.notifyDataSetChanged();
    }


}
