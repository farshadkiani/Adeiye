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
import com.example.farzadfarshad.adeiye.R;
import com.example.farzadfarshad.adeiye.Services.MyService;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PakhshAzan extends AppCompatActivity implements View.OnClickListener {


    @BindView(R.id.sob_che)
    CheckBox sob_che;

    @BindView(R.id.sabt_txt)
    TextView sabt_txt;


    String[] splitted;

    String[] splitted_time_azan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pakhsh_azan);
        ButterKnife.bind(this);

        sabt_txt.setOnClickListener(this);


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
            spitedAzanTime();
            callServie();
        }

    }

    private void callServie() {
        Calendar calendar = Calendar.getInstance();


        calendar.set(Calendar.HOUR_OF_DAY, Integer.valueOf(splitted_time_azan[0]));
        calendar.set(Calendar.MINUTE, Integer.valueOf(splitted_time_azan[1]));
        calendar.set(Calendar.SECOND, 0);


        Intent intent = new Intent(this, MyService.class);
        final PendingIntent pendingIntent = PendingIntent.getService(
                this.getApplicationContext(), 0, intent, 0);
        final AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
    }


    private void getDate() {
        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat df = new SimpleDateFormat("DD MM yyyy");
        String formattedDate = df.format(calendar.getTime());

        splitted = formattedDate.split("\\s+");
    }


    public static List<OghatDb> getAll(String day) {
        return new Select()
                .from(OghatDb.class)
                .where("Day = ?", day)
                .execute();
    }

    public void spitedAzanTime() {

        getDate();

        List<OghatDb> oghatDbList = getAll(splitted[0]);

        splitted_time_azan = oghatDbList.get(0).getAsr().split(":");

    }

}
