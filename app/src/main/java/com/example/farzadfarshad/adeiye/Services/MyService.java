package com.example.farzadfarshad.adeiye.Services;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.PowerManager;
import android.widget.Toast;

import com.example.farzadfarshad.adeiye.Activity.FarajActivity;
import com.example.farzadfarshad.adeiye.Hoshdar;
import com.example.farzadfarshad.adeiye.PaskheAzan.PakhshAzanActiviy;
import com.example.farzadfarshad.adeiye.SplashActivity;

/**
 * Created by FARZAD&FARSHAD on 06/01/2018.
 */

public class MyService extends Service {

    Hoshdar alarm = new Hoshdar();

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //TODO do something useful
        PowerManager pm = (PowerManager) getApplicationContext().getSystemService(Context.POWER_SERVICE);
        PowerManager.WakeLock wakeLock = pm.newWakeLock((PowerManager.SCREEN_BRIGHT_WAKE_LOCK | PowerManager.FULL_WAKE_LOCK | PowerManager.ACQUIRE_CAUSES_WAKEUP), "TAG");
        wakeLock.acquire();
//        Toast.makeText(this, "salam farshad", Toast.LENGTH_SHORT).show();


        alarm.SetAlarm(this);

        return Service.START_NOT_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        //TODO for communication return IBinder implementation
        return null;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        Intent intent = new Intent(this, PakhshAzanActiviy.class);
        startActivity(intent);
    }
}
