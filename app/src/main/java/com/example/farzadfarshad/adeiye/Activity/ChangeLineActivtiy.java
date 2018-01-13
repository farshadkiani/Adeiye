package com.example.farzadfarshad.adeiye.Activity;

import android.app.Activity;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;

import com.cuboid.cuboidcirclebutton.CuboidButton;
import com.example.farzadfarshad.adeiye.R;
import com.example.farzadfarshad.adeiye.Tools.SharedPreferencesTools;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChangeLineActivtiy extends AppCompatActivity implements View.OnClickListener {


    @BindView(R.id.red_chb)
    CheckBox red_chb;

    @BindView(R.id.green_chb)
    CheckBox green_chb;

    @BindView(R.id.blue_chb)
    CheckBox blue_chb;

    @BindView(R.id.yellow_chb)
    CheckBox yellow_chb;

    @BindView(R.id.example_btn)
    CuboidButton example_btn;

    @BindView(R.id.done_btn)
    Button done_btn;

    byte select_color;

    SharedPreferencesTools sharedPreferencesTools;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_linee);
        ButterKnife.bind(this);
        done_btn.setOnClickListener(this);
        sharedPreferencesTools = new SharedPreferencesTools(this);
    }

    public void itemClicked(View v) {
        //code to check if this checkbox is checked!
        switch (v.getId()) {
            case R.id.red_chb:
                green_chb.setChecked(false);
                blue_chb.setChecked(false);
                yellow_chb.setChecked(false);
                example_btn.setCircle_border_color(getResources().getColor(R.color.colorAccent));
                select_color = 0 ;
                break;
            case R.id.green_chb:
                red_chb.setChecked(false);
                blue_chb.setChecked(false);
                yellow_chb.setChecked(false);
                example_btn.setCircle_border_color(getResources().getColor(R.color.green));
                select_color = 1 ;
                break;
            case R.id.blue_chb:
                red_chb.setChecked(false);
                green_chb.setChecked(false);
                yellow_chb.setChecked(false);
                example_btn.setCircle_border_color(getResources().getColor(R.color.abirooshan));
                select_color = 2 ;
                break;
            case R.id.yellow_chb:
                red_chb.setChecked(false);
                green_chb.setChecked(false);
                blue_chb.setChecked(false);
                example_btn.setCircle_border_color(getResources().getColor(R.color.yellow));
                select_color = 3 ;
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.done_btn:
                setSaveColor();
                finish();
                break;
        }
    }

    private void setSaveColor() {
        switch (select_color){
            case 0:
                sharedPreferencesTools.setColorAroundColor("red");
                break;
            case 1:
                sharedPreferencesTools.setColorAroundColor("green");
                break;
            case 2:
                sharedPreferencesTools.setColorAroundColor("blue");
                break;
            case 3:
                sharedPreferencesTools.setColorAroundColor("yellow");
                break;
        }
    }
}
