package com.example.farzadfarshad.adeiye.Model;

import android.content.Context;

import com.activeandroid.query.Select;
import com.example.farzadfarshad.adeiye.Activity.ChangeColorActivity;
import com.example.farzadfarshad.adeiye.Database.OghatDb;
import com.example.farzadfarshad.adeiye.Tools.SharedPreferencesTools;

import java.util.List;

/**
 * Created by farshad&farzad on 2/12/2018.
 */

public class CheckAzan {

    String currentTime;
    SharedPreferencesTools sharedPreferencesTools;

    public CheckAzan(Context context, String time) {
        this.currentTime = time;
        sharedPreferencesTools = new SharedPreferencesTools(context);
    }

    public String checkSobZohrAsr(String sob, String zohr, String asr) {

        if (Integer.valueOf(currentTime) > Integer.valueOf(asr)) {
            if (sharedPreferencesTools.getSOB())
                return "sob_after";
            else if (sharedPreferencesTools.getZohr())
                return "zohr_after";
            else if (sharedPreferencesTools.getAsr())
                return "asr_after";
        } else if (Integer.valueOf(currentTime) > Integer.valueOf(zohr)) {
            if (sharedPreferencesTools.getAsr())
                return "asr";
            else if (sharedPreferencesTools.getSOB())
                return "sob_after";
            else if (sharedPreferencesTools.getZohr())
                return "zohr_after";
        } else if (Integer.valueOf(currentTime) > Integer.valueOf(sob)) {
            if (sharedPreferencesTools.getZohr())
                return "zohr";
            else if (sharedPreferencesTools.getAsr())
                return "asr";
            else if (sharedPreferencesTools.getSOB())
                return "sob_after";
        }


        return null;
    }


    public void checkAfterTimeAzan(){



    }


}
