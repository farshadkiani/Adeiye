package com.example.farzadfarshad.adeiye.Setting;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Window;

import com.example.farzadfarshad.adeiye.R;

import butterknife.ButterKnife;

/**
 * Created by farshad&farzad on 5/29/2018.
 */

public class CustomDialogShiveAzan extends Dialog{

    public CustomDialogShiveAzan(@NonNull Context context) {
        super(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_azan_shive);
        ButterKnife.bind(this);

    }
}
