package com.example.farzadfarshad.adeiye;

/**
 * Created by FARZAD&FARSHAD on 06/01/2018.
 */

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.PowerManager;
import android.widget.Toast;

public class MyBroadcastReceiver extends BroadcastReceiver {
    MediaPlayer mp;

    @Override
    public void onReceive(Context context, Intent intent) {
        PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "");
        wl.acquire();
        /*mp = MediaPlayer.create(context, R.raw.alrm);
        mp.start();*/
        Toast.makeText(context, "Alarm....", Toast.LENGTH_LONG).show();

        wl.release();
    }
}
