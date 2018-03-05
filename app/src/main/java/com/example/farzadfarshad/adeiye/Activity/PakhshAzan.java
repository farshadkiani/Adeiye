package com.example.farzadfarshad.adeiye.Activity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.activeandroid.query.Select;
import com.example.farzadfarshad.adeiye.Database.OghatDb;
import com.example.farzadfarshad.adeiye.Model.CheckAzan;
import com.example.farzadfarshad.adeiye.R;
import com.example.farzadfarshad.adeiye.Services.MyService;
import com.example.farzadfarshad.adeiye.Tools.SharedPreferencesTools;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PakhshAzan extends AppCompatActivity implements View.OnClickListener {


    @BindView(R.id.sob_che)
    CheckBox sob_che;

    @BindView(R.id.zohr_che)
    CheckBox zohr_che;

    @BindView(R.id.mahgreb_che)
    CheckBox mahgreb_che;

    @BindView(R.id.sabt_txt)
    TextView sabt_txt;

    List<OghatDb> oghatDbList;


    String[] splitted = new String[3];

    String[] splitted_time_azan_asr;
    String[] splitted_time_azan_sob;
    String[] splitted_time_azan_zohr;
    String[] splitted_time_azan;

    int year;
    int day;
    int month;

    SharedPreferencesTools sharedPreferencesTools;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pakhsh_azan);
        ButterKnife.bind(this);
        sharedPreferencesTools = new SharedPreferencesTools(this);

        sabt_txt.setOnClickListener(this);

        CheckboxChecked();


    }

    private void CheckboxChecked() {
        if (sharedPreferencesTools.getSOB())
            sob_che.setChecked(true);
        if (sharedPreferencesTools.getZohr())
            zohr_che.setChecked(true);
        if (sharedPreferencesTools.getAsr())
            mahgreb_che.setChecked(true);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.sabt_txt:
                checkToStartServise();
                break;
        }
    }

    private void checkToStartServise() {
        if (sob_che.isChecked()) {
            sharedPreferencesTools.setSobAzan(true);

        }

        if (zohr_che.isChecked()) {
            sharedPreferencesTools.setZohrAzan(true);

        }

        if (mahgreb_che.isChecked()) {
            sharedPreferencesTools.setAsrAzan(true);

        }
        if (sharedPreferencesTools.getSOB() || sharedPreferencesTools.getZohr() || sharedPreferencesTools.getAsr()) {
            spitedAzanTime();
            callServie();
            finish();
        }

    }

    private void callServie() {
        Calendar calendar = Calendar.getInstance();


        calendar.set(Calendar.HOUR_OF_DAY, Integer.valueOf(splitted_time_azan[0]));
        calendar.set(Calendar.MINUTE, Integer.valueOf(splitted_time_azan[1]));
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.DAY_OF_MONTH, day);


       /* int minute = Integer.valueOf(giveTime().split(":")[1]);

        calendar.set(Calendar.HOUR_OF_DAY, Integer.valueOf(giveTime().split(":")[0]));
        calendar.set(Calendar.MINUTE, ++minute);
        calendar.set(Calendar.SECOND, 0);*/


        Intent intent = new Intent(this, MyService.class);
        final PendingIntent pendingIntent = PendingIntent.getService(
                this.getApplicationContext(), 0, intent, 0);
        final AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
    }


    private void getDate() {
    /*    Calendar calendar = Calendar.getInstance();

        SimpleDateFormat df = new SimpleDateFormat("DD MM yyyy");
        String formattedDate = df.format(calendar.getTime());

        splitted = formattedDate.split("\\s+");*/


        Calendar cal = Calendar.getInstance();
//        cal.setTime(date);
        year = cal.get(Calendar.YEAR);
        month = cal.get(Calendar.MONTH);
        day = cal.get(Calendar.DAY_OF_MONTH);

        splitted[0] = String.valueOf(day);
        splitted[1] = String.valueOf(month);
        splitted[2] = String.valueOf(year);
    }


    public static List<OghatDb> getAll(String day) {
        return new Select()
                .from(OghatDb.class)
                .where("Day = ?", day)
                .execute();
    }

    public void spitedAzanTime() {

        getDate();

        oghatDbList = getAll(splitted[0]);



        getDateOghat();

        checkSobZohrAsr();
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
        splitted[0] = String.valueOf(day++);
        oghatDbList = getAll(splitted[0]);
        switch (check){
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

    public String giveTime() {
        DateFormat df = new SimpleDateFormat("HH");
        return df.format(Calendar.getInstance().getTime());
    }

}