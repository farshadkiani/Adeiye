package com.example.farzadfarshad.adeiye.Services;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.PowerManager;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.example.farzadfarshad.adeiye.Activity.FarajActivity;
import com.example.farzadfarshad.adeiye.Hoshdar;
import com.example.farzadfarshad.adeiye.PaskheAzan.PakhshAzanActiviy;
import com.example.farzadfarshad.adeiye.SplashActivity;

/**
 * Created by FARZAD&FARSHAD on 06/01/2018.
 */

public class MyService extends Service {


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //TODO do something useful
        /*PowerManager pm = (PowerManager) getApplicationContext().getSystemService(Context.POWER_SERVICE);
        PowerManager.WakeLock wakeLock = pm.newWakeLock((PowerManager.SCREEN_BRIGHT_WAKE_LOCK | PowerManager.FULL_WAKE_LOCK | PowerManager.ACQUIRE_CAUSES_WAKEUP), "TAG");
        wakeLock.acquire();*/
//        Toast.makeText(this, "salam farshad", Toast.LENGTH_SHORT).show();

        Intent intent1 = new Intent(this, PakhshAzanActiviy.class);
        startActivity(intent1);

        stopSelf();

        return Service.START_NOT_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public void onCreate() {
        super.onCreate();

        PowerManager pm = (PowerManager) getApplicationContext().getSystemService(Context.POWER_SERVICE);
        PowerManager.WakeLock wakeLock = pm.newWakeLock((PowerManager.SCREEN_BRIGHT_WAKE_LOCK | PowerManager.FULL_WAKE_LOCK | PowerManager.ACQUIRE_CAUSES_WAKEUP), "TAG");
        wakeLock.acquire();



    }

}
