package com.example.farzadfarshad.adeiye.CustomView;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by FARZAD&FARSHAD on 07/01/2018.
 */

public class CustomTextView extends TextView {

    public CustomTextView(Context context) {
        super(context);
        Typeface face = Typeface.createFromAsset(context.getResources().getAssets(), "bkoodk.ttf");
        this.setTypeface(face);
    }

    public CustomTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        Typeface face = Typeface.createFromAsset(context.getResources().getAssets(), "bkoodk.ttf");
        this.setTypeface(face);
    }

    public CustomTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        Typeface face = Typeface.createFromAsset(context.getResources().getAssets(), "bkoodk.ttf");
        this.setTypeface(face);
    }
}
