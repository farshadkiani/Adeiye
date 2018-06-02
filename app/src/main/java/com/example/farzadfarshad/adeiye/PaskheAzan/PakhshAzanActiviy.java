package com.example.farzadfarshad.adeiye.PaskheAzan;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.activeandroid.query.Select;
import com.example.farzadfarshad.adeiye.ContextModule;
import com.example.farzadfarshad.adeiye.Database.OghatDb;
import com.example.farzadfarshad.adeiye.Model.CheckAzan;
import com.example.farzadfarshad.adeiye.Movie.DaggerMovieFragmentComponent;
import com.example.farzadfarshad.adeiye.Movie.MovieFragmentComponent;
import com.example.farzadfarshad.adeiye.R;
import com.example.farzadfarshad.adeiye.Services.MyService;
import com.example.farzadfarshad.adeiye.Setting.PlayAzan;
import com.example.farzadfarshad.adeiye.Tools.SharedPreferencesTools;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PakhshAzanActiviy extends AppCompatActivity implements View.OnClickListener {

    @Inject
    MediaPlayer mediaPlayer;

    @BindView(R.id.stop_img)
    ImageView stop_img;

    @BindView(R.id.play_azan_img)
    ImageView play_azan_img;

    @BindView(R.id.time_sob__txt)
    TextView time_sob__txt;


    String[] splitted = new String[3];
    int day;
    List<OghatDb> oghatDbList;
    String[] splitted_time_azan_asr;
    String[] splitted_time_azan_sob;
    String[] splitted_time_azan_zohr;
    String[] splitted_time_azan;
    PendingIntent pi;

    SharedPreferencesTools sharedPreferencesTools;

    PlayAzan playAzan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pakhsh_azan_activiy);

        ButterKnife.bind(this);

        PakhsheAzanComponent component = DaggerPakhsheAzanComponent.builder()
                .contextModule(new ContextModule(this))
                .build();

        component.injectPakhsheAzan(this);

        spitedAzanTime();

        initView();

//        playSong(this);


        final Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, Integer.valueOf(splitted_time_azan[0]));
        calendar.set(Calendar.MINUTE, Integer.valueOf(splitted_time_azan[1]));
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.DAY_OF_MONTH, day);


        Intent myIntent = new Intent(this, MyService.class);
        pi = PendingIntent.getService(this, 0, myIntent, 0);

        AlarmManager am = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);


        am.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pi);
    }

    private void initView() {
       /* Intent myService = new Intent(this, MyService.class);
        stopService(myService);*/
        time_sob__txt.setText(oghatDbList.get(0).getFajr());
        stop_img.setOnClickListener(this);
        play_azan_img.setOnClickListener(this);
        sharedPreferencesTools = new SharedPreferencesTools(this);



        checkPakhshAzan();

//        mediaPlayer = new MediaPlayer();
    }

    private void checkPakhshAzan() {

        if (sharedPreferencesTools.getGhari().equals("ardabili")) {
            playAzan = new PlayAzan(this, "", mediaPlayer);
            playAzan.playFromAssest();
        }else if (sharedPreferencesTools.getGhari().equals("aghati")){
            playAzan = new PlayAzan(this, sharedPreferencesTools.getGhari(), mediaPlayer);
            playAzan.playFromSd();
        }

    }


    private void playSong(Context context) {
        try {
//            mediaPlayer.release();
//            mediaPlayer = new MediaPlayer();
            if (mediaPlayer.isPlaying()) {
                return;
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
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.stop_img) {
            if (mediaPlayer.isPlaying())
                mediaPlayer.stop();
            checkPakhshAzan();
        } else if (v.getId() == R.id.play_azan_img) {
//            playSong(this);
            checkPakhshAzan();
        }
    }


    public void spitedAzanTime() {

        getDate();

        oghatDbList = getAll(splitted[0]);

        getDateOghat();

        checkSobZohrAsr();

    }


    private void getDate() {
    /*    Calendar calendar = Calendar.getInstance();

        SimpleDateFormat df = new SimpleDateFormat("DD MM yyyy");
        String formattedDate = df.format(calendar.getTime());

        splitted = formattedDate.split("\\s+");*/


//        Calendar cal = Calendar.getInstance();
//        cal.setTime(date);
       /* year = cal.get(Calendar.YEAR);
        month = cal.get(Calendar.MONTH);
        day = cal.get(Calendar.DAY_OF_MONTH);

        splitted[0] = String.valueOf(day);
        splitted[1] = String.valueOf(month);
        splitted[2] = String.valueOf(year);*/

        DateFormat dateFormat = new SimpleDateFormat("dd MM yyyy");
        Date date = new Date();
        String Datanew = dateFormat.format(date);

        splitted[0] = dateFormat.format(date).split("\\s+")[0];
        splitted[1] = dateFormat.format(date).split("\\s+")[1];
        splitted[2] = dateFormat.format(date).split("\\s+")[2];
        day = Integer.valueOf(splitted[0]);
    }

    private void getDateOghat() {
        splitted_time_azan_asr = oghatDbList.get(0).getMaghreb().split(":");
        splitted_time_azan_zohr = oghatDbList.get(0).getDhuhr().split(":");
        splitted_time_azan_sob = oghatDbList.get(0).getImask().split(":");
    }


    private void checkSobZohrAsr() {
        CheckAzan checkAzan = new CheckAzan(this, giveTime());
        String decideSobZohrAsr = checkAzan.checkSobZohrAsr(splitted_time_azan_sob[0], splitted_time_azan_zohr[0]
                , splitted_time_azan_asr[0]);

        switch (decideSobZohrAsr) {
            case "sob":
                splitted_time_azan = oghatDbList.get(0).getImask().split(":");
                break;
            case "zohr":
                splitted_time_azan = oghatDbList.get(0).getDhuhr().split(":");
                break;
            case "asr":
                splitted_time_azan = oghatDbList.get(0).getMaghreb().split(":");
                break;
            case "sob_after":
                setAfterCurrentDate("sob_after");
                break;
            case "zohr_after":
                setAfterCurrentDate("zohr_after");
                break;
            case "asr_after":
                setAfterCurrentDate("asr_after");
                break;

        }
    }


    private void setAfterCurrentDate(String check) {
        splitted[0] = String.valueOf(++day);
        oghatDbList = getAll(splitted[0]);
        switch (check) {
            case "sob_after":
                splitted_time_azan = oghatDbList.get(0).getImask().split(":");
                break;
            case "zohr_after":
                splitted_time_azan = oghatDbList.get(0).getDhuhr().split(":");
                break;
            case "asr_after":
                splitted_time_azan = oghatDbList.get(0).getMaghreb().split(":");
                break;
        }
    }


    public static List<OghatDb> getAll(String day) {
        return new Select()
                .from(OghatDb.class)
                .where("Day = ?", day)
                .execute();
    }

    public String giveTime() {
//        DateFormat df = new SimpleDateFormat("HH:mm");
        DateFormat df = new SimpleDateFormat("HH");
        int convert = Integer.valueOf(df.format(Calendar.getInstance().getTime()));
        String time = String.valueOf(++convert);
        return time;
    }

    @Override
    public void onBackPressed() {
        if (mediaPlayer.isPlaying())
            mediaPlayer.stop();
        super.onBackPressed();
    }


}
