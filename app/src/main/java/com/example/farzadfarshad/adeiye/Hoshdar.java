package com.example.farzadfarshad.adeiye;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.PowerManager;
import android.os.SystemClock;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;


import com.activeandroid.query.Select;
import com.example.farzadfarshad.adeiye.Database.OghatDb;
import com.example.farzadfarshad.adeiye.Services.MyService;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class Hoshdar extends BroadcastReceiver {
    public Context context;
    MediaPlayer mediaPlayer;
    AlarmManager am;
    PendingIntent pi;

    @Override
    public void onReceive(Context context, Intent intent) {
        this.context = context;
        try {
            PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
            PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "");
            wl.acquire();
            // Put here YOUR code.
            CheckForazan();

            wl.release();
        } catch (Exception e) {
            Log.d("LogLog", e.toString());
            e.printStackTrace();
        }
    }

    private void CheckForazan() {
        SetAlarm(context);
    }


    public void SetAlarm(Context context) {
        /*am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent i = new Intent(context, Hoshdar.class);
        pi = PendingIntent.getBroadcast(context, 0, i, 0);
        am.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), 60000, pi);*/

        Toast.makeText(context, "salam farshad", Toast.LENGTH_SHORT).show();

        final Calendar calendar = Calendar.getInstance();



        calendar.set(Calendar.HOUR_OF_DAY , 20);
        calendar.set(Calendar.MINUTE , 41);
        calendar.set(Calendar.SECOND, 0);


        Intent myIntent = new Intent(context, MyService.class);
        pi = PendingIntent.getService(context, 0, myIntent, 0);

        AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);



        am.setExact(AlarmManager.RTC_WAKEUP , calendar.getTimeInMillis() , pi);

//        am.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pi);
       /* am.setRepeating(AlarmManager.RTC, calendar.getTimeInMillis()
                , 60 * 1000, pi);*/
    /*    am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent i = new Intent(context, Hoshdar.class);
        pi = PendingIntent.getBroadcast(context, 0, i, 0);
        am.setRepeating(AlarmManager.ELAPSED_REALTIME, SystemClock.elapsedRealtime(), 60000, pi);*/


        /*alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis()
                + (2 * 1000), pendingIntent);*/
        /*alarmManager.setRepeating(AlarmManager.RTC, SystemClock.elapsedRealtime()
                , 60 * 1000, pendingIntent);*/

    }


    public String giveDate() {
        Calendar cal = Calendar.getInstance();
//        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy");
        SimpleDateFormat sdf = new SimpleDateFormat("dd");
        return sdf.format(cal.getTime()).toLowerCase();
    }

    public String giveTime() {
        DateFormat df = new SimpleDateFormat("HH:mm");
        return df.format(Calendar.getInstance().getTime());
    }

    public void CancelAlarm(Context context) {
        try {
            pi.cancel();
            am.cancel(pi);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<OghatDb> getAll(String day) {
        return new Select()
                .from(OghatDb.class)
                .where("Day = ?", day)
                .execute();
    }
}