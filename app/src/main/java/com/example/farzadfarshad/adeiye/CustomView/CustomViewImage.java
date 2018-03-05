package com.example.farzadfarshad.adeiye.CustomView;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.example.farzadfarshad.adeiye.R;

/**
 * Created by FARZAD&FARSHAD on 28/01/2018.
 */

public class CustomViewImage extends android.support.v7.widget.AppCompatImageView {

    private Bitmap bitmap;
    private Resources resources;
    private Paint paint;

    private int left = 10;
    private int top = 20;
    private byte count = 0;

    Handler mHandler = new Handler();


    public CustomViewImage(Context context) {
        super(context);
        init(context);
    }

    public CustomViewImage(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CustomViewImage(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10);
        paint.setColor(Color.GREEN);
        resources = context.getResources();
        bitmap = BitmapFactory
                .decodeResource(resources, R.drawable.lightimage);
        bitmap = Bitmap.createScaledBitmap(bitmap, 55, 80, true);
    }

    @Override
    protected void onDraw(final Canvas canvas) {

        int viewWidthHalf = this.getMeasuredWidth() / 2;
        int viewHeightHalf = this.getMeasuredHeight() / 2;
        int z = getLeft();
        int f = getTop();
        int e = getPaddingLeft();
        int u = getPaddingTop();

        switch (count) {
            case 0:
                left = 10;
                top = 20;
                break;
            case 1:
                left = 2;
                top = 5;
                break;
            case 2:
                left = 15;
                top = 25;
                break;
        }
        canvas.drawBitmap(bitmap, left, top, paint);

        mHandler.postDelayed(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                count++;
                if (count == 3)
                    count = 0;
                invalidate();
            }
        }, 5000);


        super.onDraw(canvas);
    }
}
