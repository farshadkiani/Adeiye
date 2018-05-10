package com.example.farzadfarshad.adeiye;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.util.Log;


import com.activeandroid.query.Select;
import com.example.farzadfarshad.adeiye.Activity.FarajActivity;
import com.example.farzadfarshad.adeiye.Database.OghatDb;
import com.example.farzadfarshad.adeiye.Model.CheckAzan;
import com.example.farzadfarshad.adeiye.Services.MyService;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class Hoshdar extends BroadcastReceiver {
    public Context context;
    MediaPlayer mediaPlayer = new MediaPlayer();
    ;
    AlarmManager am;
    PendingIntent pi;
    int minute_app_adeiye = 59;
    int hour = 13;

    String[] splitted = new String[3];
    int year;
    int month;
    int day;
    List<OghatDb> oghatDbList;
    String[] splitted_time_azan_asr;
    String[] splitted_time_azan_sob;
    String[] splitted_time_azan_zohr;
    String[] splitted_time_azan;


    @Override
    public void onReceive(Context context, Intent intent) {
        this.context = context;
        try {
           /* PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
//            PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "");
            PowerManager.WakeLock wl = pm.newWakeLock((PowerManager.PARTIAL_WAKE_LOCK |PowerManager.SCREEN_BRIGHT_WAKE_LOCK |PowerManager.SCREEN_DIM_WAKE_LOCK | PowerManager.FULL_WAKE_LOCK | PowerManager.ACQUIRE_CAUSES_WAKEUP | PowerManager.ON_AFTER_RELEASE), "TAG");
            wl.acquire();*/
//            wl.release();
            // Put here YOUR code.




            CheckForazan();


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

        this.context = context;

//        playSong(context);

//        spitedAzanTime();

        final Calendar calendar = Calendar.getInstance();

       /* if (minute_app_adeiye == 60) {
            minute_app_adeiye = 0;
            if (hour == 24)
                hour = 0;
            else
                hour++;
        }*/


       /* int minute = Integer.valueOf(giveTime().split(":")[1]);

        calendar.set(Calendar.HOUR_OF_DAY, Integer.valueOf(giveTime().split(":")[0]));
        calendar.set(Calendar.MINUTE, ++minute);
        calendar.set(Calendar.SECOND, 0);*/
       

       
       
//// TODO: 5/10/2018  
        /*calendar.set(Calendar.HOUR_OF_DAY, Integer.valueOf(splitted_time_azan[0]));
        calendar.set(Calendar.MINUTE, Integer.valueOf(splitted_time_azan[1]));
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.DAY_OF_MONTH, day);


        Intent myIntent = new Intent(context, MyService.class);
        pi = PendingIntent.getService(context, 0, myIntent, 0);

        AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);


        am.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pi);
        */
        
        

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
//        DateFormat df = new SimpleDateFormat("HH:mm");
        DateFormat df = new SimpleDateFormat("HH");
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
        CheckAzan checkAzan = new CheckAzan(context, giveTime());
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
}