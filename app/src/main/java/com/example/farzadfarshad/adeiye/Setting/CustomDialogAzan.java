package com.example.farzadfarshad.adeiye.Setting;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Window;

import com.example.farzadfarshad.adeiye.R;

import butterknife.ButterKnife;

/**
 * Created by farshad&farzad on 5/20/2018.
 */

public class CustomDialogAzan extends Dialog {

    Context context;

    public CustomDialogAzan(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_azan);
        ButterKnife.bind(this);
    }


}
