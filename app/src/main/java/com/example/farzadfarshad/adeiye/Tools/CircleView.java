package com.example.farzadfarshad.adeiye.Tools;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.example.farzadfarshad.adeiye.R;

/**
 * Created by FARZAD&FARSHAD on 15/01/2018.
 */

public class CircleView extends View{

    Paint paint;

    public CircleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }


    public CircleView(Context context) {
        super(context);
        init(context);
    }


    private void init(Context context) {
        paint = new Paint();
        paint.setColor(Color.CYAN);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(4);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.BLUE);
        canvas.drawCircle(200, 200, 100, paint);
        Bitmap bMap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_sun);
        
        canvas.drawLine(getWidth()/2 , getHeight()/2 , 100 , 0 , paint);
    }
}
