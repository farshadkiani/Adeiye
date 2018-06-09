package com.example.farzadfarshad.adeiye.PdfShow;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.farzadfarshad.adeiye.R;
import com.necistudio.vigerpdf.VigerPDF;
import com.necistudio.vigerpdf.adapter.VigerAdapter;
import com.necistudio.vigerpdf.manage.OnResultListener;
//import com.necistudio.vigerpdf.VigerPDF;
//import com.necistudio.vigerpdf.adapter.VigerAdapter;
//import com.necistudio.vigerpdf.manage.OnResultListener;

import java.io.File;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PdfShowActivtiy extends AppCompatActivity implements View.OnClickListener{

    VigerPDF vigerPDF;
    private VigerAdapter adapter;
    private ArrayList<Bitmap> itemData;


    @BindView(R.id.toolbar_title)
    TextView toolbar_title;

    @BindView(R.id.play_img)
    ImageView play_img;

    @BindView(R.id.back_img)
    ImageView back_img;

    @BindView(R.id.viewPager)
    com.necistudio.vigerpdf.utils.ViewPagerZoomHorizontal viewPager;

    String nameShahid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pdf_show_activtiy);

        ButterKnife.bind(this);

        initView();

    }

    private void initView() {
        String path = getIntent().getStringExtra("path");
        nameShahid = getIntent().getStringExtra("shahid");
        toolbar_title.setText(nameShahid);
        play_img.setVisibility(View.GONE);
        back_img.setOnClickListener(this);
        vigerPDF = new VigerPDF(this);
        itemData = new ArrayList<>();
        fromFile(path);
    }


    private void fromFile(String path) {
//        adapter.notifyDataSetChanged();
        File file = new File(path);
        vigerPDF.cancle();
        vigerPDF.initFromFile(file, new OnResultListener() {
            @Override
            public void resultData(Bitmap data) {
                itemData.add(data);
//                adapter.notifyDataSetChanged();
                adapter = new VigerAdapter(getApplicationContext(), itemData);
                viewPager.setAdapter(adapter);
            }

            @Override
            public void progressData(int progress) {
//                Log.e("data", "" + progress);

            }

            @Override
            public void failed(Throwable t) {

            }

        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_img:
                onBackPressed();
                break;
        }
    }
}
