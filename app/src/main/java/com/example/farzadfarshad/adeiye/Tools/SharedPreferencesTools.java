package com.example.farzadfarshad.adeiye.Tools;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by FARZAD&FARSHAD on 28/12/2017.
 */

public class SharedPreferencesTools {

    public static final String MyPREFERENCES = "MyPrefs";
    SharedPreferences sharedpreferences;
    SharedPreferences.Editor editor;
    public static String COLOR_AROUND = "Color_Around";
    public static String COLOR_APP = "Color_App";
    public static String NOTIFICATION_APP = "NOTIFICATION_App";
    public static String SOB = "Sob";
    public static String ZOHR = "Zohr";
    public static String ASR = "Asr";
    public static String SHOWOGHAT = "ShowOghat";

    public SharedPreferencesTools(Context context) {
        sharedpreferences = context.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        editor = sharedpreferences.edit();
    }

    public void setColorAroundColor(String color) {
        editor.putString(COLOR_AROUND, color);
        editor.commit();
    }

    public void setColorApp(String colorApp) {
        editor.putString(COLOR_APP, colorApp);
        editor.commit();
    }

    public String getColorAround() {
        return sharedpreferences.getString(COLOR_AROUND, "");
    }

    public String getColorApp() {
        return sharedpreferences.getString(COLOR_APP, "");
    }


    public void setNotificationApp(boolean check) {
        editor.putBoolean(NOTIFICATION_APP, check);
        editor.commit();
    }

    public boolean getNotificationApp() {
        return sharedpreferences.getBoolean(NOTIFICATION_APP, true);
    }

    public void setSobAzan(boolean sob) {
        editor.putBoolean(SOB, sob);
        editor.commit();
    }

    public boolean getSOB() {
        return sharedpreferences.getBoolean(SOB, false);
    }


    public void setZohrAzan(boolean zohr) {
        editor.putBoolean(ZOHR, zohr);
        editor.commit();
    }

    public boolean getZohr() {
        return sharedpreferences.getBoolean(ZOHR, false);
    }


    public void setAsrAzan(boolean asr) {
        editor.putBoolean(ASR, asr);
        editor.commit();
    }


    public boolean getAsr() {
        return sharedpreferences.getBoolean(ASR, false);
    }

    public void setShowOghat(boolean showghat) {
        editor.putBoolean(SHOWOGHAT , showghat);
        editor.commit();
    }

    public boolean getShowOghat() {
        return sharedpreferences.getBoolean(SHOWOGHAT , false);
    }
}
