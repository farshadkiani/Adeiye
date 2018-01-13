package com.example.farzadfarshad.adeiye;


import android.content.Context;
import android.os.Bundle;
import android.view.Window;

/**
 * Created by FARZAD&FARSHAD on 14/12/2017.
 */

public class DialogCustom extends android.app.Dialog{


    public DialogCustom(Context context , myOnClickListener myOnClickListener) {
        super(context);
        this.myListener = myOnClickListener ;
    }

    public DialogCustom(Context context) {
        super(context);
    }

    public myOnClickListener myListener;

    // This is my interface //
    public interface myOnClickListener {
        void onButtonClick();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_layout);



    }
}
