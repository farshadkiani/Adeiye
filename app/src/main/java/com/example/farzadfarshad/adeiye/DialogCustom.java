package com.example.farzadfarshad.adeiye;


import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.farzadfarshad.adeiye.Activity.FarajActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by FARZAD&FARSHAD on 14/12/2017.
 */

public class DialogCustom extends android.app.Dialog {


    @BindView(R.id.dialog_btn)
    Button dialog_btn;

    @BindView(R.id.title_txt)
    TextView title_txt;

    @BindView(R.id.progress)
    ProgressBar progress;

    Context context;

    public DialogCustom(Context context, myOnClickListener myOnClickListener) {
        super(context);
        this.myListener = myOnClickListener;
        this.context = context;
    }

    public DialogCustom(Context context) {
        super(context);
        this.context = context;
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

        ButterKnife.bind(this);

        if (context instanceof FarajActivity) {
            dialog_btn.setVisibility(View.VISIBLE);
            progress.setVisibility(View.GONE);
            title_txt.setText(context.getResources().getString(R.string.download_file));
        } else if(context instanceof MainActivity){
            dialog_btn.setVisibility(View.VISIBLE);
            progress.setVisibility(View.GONE);
            title_txt.setText(context.getResources().getString(R.string.download_file));
        } else {
            dialog_btn.setVisibility(View.GONE);
            progress.setVisibility(View.GONE);
            title_txt.setText(context.getResources().getString(R.string.get_data));
        }

        dialog_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (myListener != null) {
                    myListener.onButtonClick();
                }

            }
        });

    }

    public void setmyClick(myOnClickListener myListener) {
        this.myListener = myListener;
    }
}
