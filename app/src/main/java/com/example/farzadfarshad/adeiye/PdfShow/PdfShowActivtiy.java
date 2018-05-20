package com.example.farzadfarshad.adeiye.PdfShow;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.farzadfarshad.adeiye.R;
import com.necistudio.vigerpdf.VigerPDF;
import com.necistudio.vigerpdf.adapter.VigerAdapter;
import com.necistudio.vigerpdf.manage.OnResultListener;

import java.io.File;
import java.util.ArrayList;

public class PdfShowActivtiy extends AppCompatActivity {

    VigerPDF vigerPDF;
    private ViewPager viewPager;
    private VigerAdapter adapter;
    private ArrayList<Bitmap> itemData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pdf_show_activtiy);
        String path = getIntent().getStringExtra("path");
        vigerPDF = new VigerPDF(this);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
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
}
