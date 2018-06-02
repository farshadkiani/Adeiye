package com.example.farzadfarshad.adeiye.Setting;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.pm.ActivityInfoCompat;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.farzadfarshad.adeiye.DialogCustom;
import com.example.farzadfarshad.adeiye.Movie.PermissionHandler;
import com.example.farzadfarshad.adeiye.R;
import com.example.farzadfarshad.adeiye.RetrofitManager.ApiClient;
import com.example.farzadfarshad.adeiye.RetrofitManager.ApiInterface;
import com.example.farzadfarshad.adeiye.RetrofitManager.ServerAPI;
import com.example.farzadfarshad.adeiye.Tools.SharedPreferencesTools;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by farshad&farzad on 5/20/2018.
 */

public class CustomDialogAzan extends Dialog
        implements View.OnClickListener {

    Activity context;

    @BindView(R.id.dl_moazen_img)
    ImageView dl_moazen_img;

    @BindView(R.id.dl_aghati_img)
    ImageView dl_aghati_img;

    @BindView(R.id.ardabii_rdb)
    RadioButton ardabii_rdb;

    @BindView(R.id.aghati_rdb)
    RadioButton aghati_rdb;

    @BindView(R.id.sabt_azan_btn)
    Button sabt_azan_btn;

    MediaPlayer mediaPlayer;

    PlayAzan playAzan;

    SharedPreferencesTools sharedPreferencesTools;

    public CustomDialogAzan(Activity context) {
        super(context);
        this.context = context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_azan);
        ButterKnife.bind(this);

        initView();
    }

    private void initView() {
        dl_moazen_img.setBackgroundResource(R.drawable.play);
        dl_moazen_img.setOnClickListener(this);
        dl_aghati_img.setOnClickListener(this);
        aghati_rdb.setOnClickListener(this);
        ardabii_rdb.setOnClickListener(this);
        sabt_azan_btn.setOnClickListener(this);
        mediaPlayer = new MediaPlayer();
        sharedPreferencesTools = new SharedPreferencesTools(context);
        checkAzanDownloaded();
    }

    private boolean checkAzanDownloaded() {
        File futureStudioIconFile = new File(getExternalFilesDir()
                + File.separator + "Adeiye" + File.separator + "aghati.mp3");
        if (futureStudioIconFile.exists()) {
            dl_aghati_img.setBackground(context.getResources().getDrawable(R.drawable.play));
            return true;
        } else {
            dl_aghati_img.setBackgroundResource(android.R.drawable.stat_sys_download);
            return false;
        }
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.dl_moazen_img) {
            play("azan.mp3");
        } else if (v.getId() == R.id.dl_aghati_img) {
            if (checkAzanDownloaded()) {
                if (playAzan == null)// for inke mediaplayer dobare sakhte nashavad check mikonam
                    playAzan = new PlayAzan(context, "aghati", mediaPlayer);
                if (playAzan.playFromSd())
                    dl_aghati_img.setBackground(context.getResources().getDrawable(R.drawable.quantum_ic_pause_circle_filled_grey600_36));
                else
                    dl_aghati_img.setBackground(context.getResources().getDrawable(R.drawable.play));

                return;
            }
            final DialogCustom dialogCustom = new DialogCustom(context);
            dialogCustom.show();
            dialogCustom.setmyClick(new DialogCustom.myOnClickListener() {
                @Override
                public void onButtonClick() {
                    checkPermisson("s/t8beo3dzwyif4k3/aghati.mp3?dl=1u", dialogCustom
                            , "aghati");
//                downloadFile(pdfModelDetails.get(position).getFile(), dialogCustom);
                }
            });
        } else if (v.getId() == R.id.aghati_rdb) {
            if (ardabii_rdb.isChecked())
                ardabii_rdb.setChecked(false);
            aghati_rdb.setChecked(true);
            if (checkAzanDownloaded())
                dl_aghati_img.setBackground(context.getResources().getDrawable(R.drawable.play));
        } else if (v.getId() == R.id.ardabii_rdb) {
            if (aghati_rdb.isChecked())
                aghati_rdb.setChecked(false);
            ardabii_rdb.setChecked(true);
        } else if (v.getId() == R.id.sabt_azan_btn){

            if (aghati_rdb.isChecked())
                sharedPreferencesTools.setGhari("aghati");
            else if (ardabii_rdb.isChecked())
                sharedPreferencesTools.setGhari("ardabili");

            dismiss();

        }

    }

    private void play(String file_name) {
        try {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.stop();
                dl_moazen_img.setBackgroundResource(R.drawable.play);
                return;
            } else
                dl_moazen_img.setBackgroundResource(R.drawable.quantum_ic_pause_circle_filled_grey600_36);
            mediaPlayer.reset();
            AssetFileDescriptor descriptor = context.getAssets().openFd(file_name);
            mediaPlayer.setDataSource(descriptor.getFileDescriptor()
                    , descriptor.getStartOffset(), descriptor.getLength());
            descriptor.close();

            mediaPlayer.prepare();
            mediaPlayer.setVolume(1f, 1f);
            mediaPlayer.setLooping(false);
            mediaPlayer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void downloadFile(String url, final String name) {
        final ProgressDialog pDialog = new ProgressDialog(getContext());
        pDialog.setMessage(context.getResources().getString(R.string.download_file_doa));
        pDialog.show();
        ServerAPI apiService = ServerAPI.retrofit.create(ServerAPI.class);
        Call<ResponseBody> call = apiService.downlload(url);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    pDialog.dismiss();
                    boolean writtenToDisk = writeResponseBodyToDisk(response.body(), name);
                    if (writtenToDisk) {
                        Toast.makeText(getContext(), "ok", Toast.LENGTH_SHORT).show();
                        if (name.equals("aghati"))
                            dl_aghati_img.setBackground(context.getResources().getDrawable(R.drawable.play));
                    } else
                        Toast.makeText(getContext(), "no", Toast.LENGTH_SHORT).show();
                } else {
                    Log.d("salam", "server contact failed");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("salam", "error");
            }
        });
    }

    private boolean writeResponseBodyToDisk(ResponseBody body, String name) {
        try {
            // todo change the file location/name according to your needs
            File futureStudioIconFile = new File(getExternalFilesDir()
                    + File.separator + "Adeiye" + File.separator + name + ".mp3");

            InputStream inputStream = null;
            OutputStream outputStream = null;


            try {
                byte[] fileReader = new byte[4096];

                long fileSize = body.contentLength();
                long fileSizeDownloaded = 0;

                inputStream = body.byteStream();
                outputStream = new FileOutputStream(futureStudioIconFile);

                while (true) {
                    int read = inputStream.read(fileReader);

                    if (read == -1) {
                        break;
                    }

                    outputStream.write(fileReader, 0, read);

                    fileSizeDownloaded += read;

                    Log.d("salam", "file download: " + fileSizeDownloaded + " of " + fileSize);
                }

                outputStream.flush();

                return true;
            } catch (IOException e) {
                return false;
            } finally {
                if (inputStream != null) {
                    inputStream.close();
                }

                if (outputStream != null) {
                    outputStream.close();
                }
            }
        } catch (IOException e) {
            return false;
        }
    }

    private File getExternalFilesDir() {
        return Environment.getExternalStorageDirectory();
    }


    public void checkPermisson(final String url, final DialogCustom dialogCustom, final String name) {
        String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
        new PermissionHandler().checkPermission(context, permissions, new PermissionHandler.OnPermissionResponse() {
            @Override
            public void onPermissionGranted() {
                dialogCustom.dismiss();
                downloadFile(url, name);
            }

            @Override
            public void onPermissionDenied() {
                Toast.makeText(getContext(), context.getResources().getString(R.string.take_permission)
                        , Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public void cancel() {
        super.cancel();
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.release();
            mediaPlayer.stop();
        }
    }
}