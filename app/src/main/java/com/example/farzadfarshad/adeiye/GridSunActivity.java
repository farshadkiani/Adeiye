package com.example.farzadfarshad.adeiye;

import android.*;
import android.Manifest;
import android.app.ProgressDialog;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.farzadfarshad.adeiye.Adapter.SunGrideAdapter;
import com.example.farzadfarshad.adeiye.Interface.onImageClick;
import com.example.farzadfarshad.adeiye.Model.SunModel;
import com.example.farzadfarshad.adeiye.RetrofitManager.ServerAPI;

import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GridSunActivity extends AppCompatActivity implements View.OnClickListener {

    Toolbar toolbar;


    @BindView(R.id.play_img)
    ImageView play_img;

    @BindView(R.id.back_img)
    ImageView back_img;

    private final String android_version_names[] = {
            "Donut",
            "Eclair",
            "Froyo",
            "Gingerbread",
            "Honeycomb",
            "Ice Cream Sandwich",
            "Jelly Bean",
            "KitKat",
            "Lollipop",
            "Marshmallow"
    };

    private final String android_image_urls[] = {
            "android/images/donut.png",
            "android/images/eclair.png",
            "android/images/froyo.png",
            "android/images/ginger.png",
            "android/images/honey.png",
            "android/images/icecream.png",
            "android/images/jellybean.png",
            "android/images/kitkat.png",
            "android/images/lollipop.png",
            "android/images/marshmallow.png"
    };

    public static final int MY_PERMISSIONS_REQUEST_STORAGE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_sun);
        ButterKnife.bind(this);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        initViews();
    }

    private void initViews() {

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.card_recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 2);
        recyclerView.setLayoutManager(layoutManager);

        ArrayList<SunModel> androidVersions = prepareData();
        SunGrideAdapter adapter = new SunGrideAdapter(getApplicationContext(), androidVersions);
        recyclerView.setAdapter(adapter);

        adapter.setOnClickImage(new onImageClick() {
            @Override
            public void clickImage(String url) {
                checkPermissionStorage(url);
            }
        });

        play_img.setVisibility(View.GONE);
        back_img.setOnClickListener(this);

    }

    private void checkPermissionStorage(String url) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            //Prompt the user once explanation has been shown
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    MY_PERMISSIONS_REQUEST_STORAGE);
        } else
            download(url);
    }

    private ArrayList<SunModel> prepareData() {

        ArrayList<SunModel> android_version = new ArrayList<>();
        for (int i = 0; i < android_version_names.length; i++) {
            SunModel androidVersion = new SunModel();
            androidVersion.setSun_name(android_version_names[i]);
            androidVersion.setSun_image_url(android_image_urls[i]);
            android_version.add(androidVersion);
        }
        return android_version;
    }


    public void download(String url) {
        final ProgressDialog pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.show();
        final String[] separated = url.split("images/");

        ServerAPI api = ServerAPI.retrofit.create(ServerAPI.class);
        api.downlload(url).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    pDialog.dismiss();
                    File path = Environment.getExternalStorageDirectory();
                    File file = new File(path + "/" + "Adeiye", separated[1]);
                    FileOutputStream fileOutputStream = new FileOutputStream(file);
                    IOUtils.write(response.body().bytes(), fileOutputStream);
                    onBackPressed();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(GridSunActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_STORAGE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted. Do the
                    // contacts-related task you need to do.
                    if (ContextCompat.checkSelfPermission(this,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE)
                            == PackageManager.PERMISSION_GRANTED) {

//                        download(url);
                    }

                } else {

                    // Permission denied, Disable the functionality that depends on this permission.
                    Toast.makeText(this, "permission denied", Toast.LENGTH_LONG).show();
                }
                return;
            }

            // other 'case' lines to check for other permissions this app might request.
            // You can add here other case statements according to your requirement.
        }
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