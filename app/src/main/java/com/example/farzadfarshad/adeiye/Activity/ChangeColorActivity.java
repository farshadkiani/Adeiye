package com.example.farzadfarshad.adeiye.Activity;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.cuboid.cuboidcirclebutton.CuboidButton;
import com.example.farzadfarshad.adeiye.R;
import com.example.farzadfarshad.adeiye.Tools.SharedPreferencesTools;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChangeColorActivity extends AppCompatActivity implements View.OnClickListener {


    @BindView(R.id.relative1)
    RelativeLayout relative1;

    @BindView(R.id.relative2)
    RelativeLayout relative2;

    @BindView(R.id.relative3)
    RelativeLayout relative3;

    @BindView(R.id.relative4)
    RelativeLayout relative4;

    @BindView(R.id.select_img1)
    ImageView select_img1;
    @BindView(R.id.select_img2)
    ImageView select_img2;
    @BindView(R.id.select_img3)
    ImageView select_img3;
    @BindView(R.id.select_img4)
    ImageView select_img4;


    @BindView(R.id.doaahd_btn1)
    CuboidButton doaahd_btn1;

    @BindView(R.id.doaahd_btn2)
    CuboidButton doaahd_btn2;

    @BindView(R.id.doaahd_btn3)
    CuboidButton doaahd_btn3;

    @BindView(R.id.doaahd_btn4)
    CuboidButton doaahd_btn4;


    @BindView(R.id.done_btn)
    Button done_btn;

    boolean[] check_select = new boolean[4];

    SharedPreferencesTools sharedPreferencesTools;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_color);
        ButterKnife.bind(this);
        sharedPreferencesTools = new SharedPreferencesTools(this);
        relative1.setOnClickListener(this);
        relative2.setOnClickListener(this);
        relative3.setOnClickListener(this);
        relative4.setOnClickListener(this);

        doaahd_btn1.setOnClickListener(this);
        doaahd_btn2.setOnClickListener(this);
        doaahd_btn3.setOnClickListener(this);
        doaahd_btn4.setOnClickListener(this);

        done_btn.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.relative1:
                selectOne();

                break;
            case R.id.relative2:
                selectTwo();

                break;
            case R.id.relative3:
                selectThree();

                break;
            case R.id.relative4:
                selectFour();

                break;
            case R.id.doaahd_btn1:
                selectOne();
                break;
            case R.id.doaahd_btn2:
                selectTwo();
                break;
            case R.id.doaahd_btn3:
                selectThree();
                break;
            case R.id.doaahd_btn4:
                selectFour();
                break;

            case R.id.done_btn:
                checkSetColor();
                break;
        }
    }

    private void checkSetColor() {
        if (check_select[0])
            sharedPreferencesTools.setColorApp("narenji");
        else if (check_select[1])
            sharedPreferencesTools.setColorApp("ghermez");
        else if (check_select[2])
            sharedPreferencesTools.setColorApp("sabz");
        else if (check_select[3])
            sharedPreferencesTools.setColorApp("blueprimary");
        finish();
    }

    private void selectFour() {
        if (check_select[3]) {
            select_img4.setImageDrawable(getResources().getDrawable(R.drawable.circleshapegray));
            check_select[3] = false;
        } else {
            select_img4.setImageDrawable(getResources().getDrawable(R.drawable.tikbackground));
            check_select[3] = true;
        }
        select_img1.setImageDrawable(getResources().getDrawable(R.drawable.circleshapegray));
        select_img2.setImageDrawable(getResources().getDrawable(R.drawable.circleshapegray));
        select_img3.setImageDrawable(getResources().getDrawable(R.drawable.circleshapegray));
        check_select[0] = false;
        check_select[1] = false;
        check_select[2] = false;
    }

    private void selectThree() {
        if (check_select[2]) {
            select_img3.setImageDrawable(getResources().getDrawable(R.drawable.circleshapegray));
            check_select[2] = false;
        } else {
            select_img3.setImageDrawable(getResources().getDrawable(R.drawable.tikbackground));
            check_select[2] = true;
        }
        select_img1.setImageDrawable(getResources().getDrawable(R.drawable.circleshapegray));
        select_img2.setImageDrawable(getResources().getDrawable(R.drawable.circleshapegray));
        select_img4.setImageDrawable(getResources().getDrawable(R.drawable.circleshapegray));

        check_select[0] = false;
        check_select[1] = false;
        check_select[3] = false;
    }

    private void selectTwo() {
        if (check_select[1]) {
            select_img2.setImageDrawable(getResources().getDrawable(R.drawable.circleshapegray));
            check_select[1] = false;
        } else {
            select_img2.setImageDrawable(getResources().getDrawable(R.drawable.tikbackground));
            check_select[1] = true;
        }
        select_img1.setImageDrawable(getResources().getDrawable(R.drawable.circleshapegray));
        select_img3.setImageDrawable(getResources().getDrawable(R.drawable.circleshapegray));
        select_img4.setImageDrawable(getResources().getDrawable(R.drawable.circleshapegray));

        check_select[0] = false;
        check_select[2] = false;
        check_select[3] = false;
    }

    private void selectOne() {
        if (check_select[0]) {
            select_img1.setImageDrawable(getResources().getDrawable(R.drawable.circleshapegray));
            check_select[0] = false;
        } else {
            select_img1.setImageDrawable(getResources().getDrawable(R.drawable.tikbackground));
            check_select[0] = true;
        }
        select_img2.setImageDrawable(getResources().getDrawable(R.drawable.circleshapegray));
        select_img3.setImageDrawable(getResources().getDrawable(R.drawable.circleshapegray));
        select_img4.setImageDrawable(getResources().getDrawable(R.drawable.circleshapegray));

        check_select[1] = false;
        check_select[2] = false;
        check_select[3] = false;
    }
}
