package com.example.farzadfarshad.adeiye.Setting;

import android.app.Dialog;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.farzadfarshad.adeiye.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by farshad&farzad on 5/20/2018.
 */

public class CustomDialogAzan extends Dialog
        implements View.OnClickListener {

    Context context;

    @BindView(R.id.dl_moazen_img)
    ImageView dl_moazen_img;

    @BindView(R.id.dl_aghati_img)
    ImageView dl_aghati_img;

    @BindView(R.id.ardabii_rdb)
    RadioButton ardabii_rdb;

    @BindView(R.id.aghati_rdb)
    RadioButton aghati_rdb;

    MediaPlayer mediaPlayer;

    public CustomDialogAzan(Context context) {
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
        mediaPlayer = new MediaPlayer();
    }


    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.dl_moazen_img) {
            play("azan.mp3");
        } else if (v.getId() == R.id.dl_aghati_img) {
            download();
        } else if (v.getId() == R.id.aghati_rdb) {
            if (ardabii_rdb.isChecked())
                ardabii_rdb.setChecked(false);
            aghati_rdb.setChecked(true);
        }else if (v.getId() == R.id.ardabii_rdb){
            if (aghati_rdb.isChecked())
                aghati_rdb.setChecked(false);
            ardabii_rdb.setChecked(true);
        }

    }

    private void download() {

    }

    private void play(String file_name) {

        try {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.stop();
                dl_moazen_img.setBackgroundResource(R.drawable.play);
                return;
            } else
                dl_moazen_img.setBackgroundResource(R.drawable.alrahman);
            mediaPlayer.reset();
            AssetFileDescriptor descriptor = context.getAssets().openFd(file_name);
            mediaPlayer.setDataSource(descriptor.getFileDescriptor(), descriptor.getStartOffset(), descriptor.getLength());
            descriptor.close();

            mediaPlayer.prepare();
            mediaPlayer.setVolume(1f, 1f);
            mediaPlayer.setLooping(false);
            mediaPlayer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}