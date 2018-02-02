package com.example.farzadfarshad.adeiye.CustomView;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.TextView;
import com.example.farzadfarshad.adeiye.R;

/**
 * Created by FARZAD&FARSHAD on 07/01/2018.
 */

public class CustomTextView extends TextView {

    Paint paint;
    private Bitmap bitmap;
    private Resources resources;
    float left_Bitmap;
    float top_Bitampp;
    boolean moveImage = true;

    Handler mHandler = new Handler();

    float centerX;
    float centerY;

    public CustomTextView(Context context) {
        super(context);

        init(context);
    }

    public CustomTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        Typeface face = Typeface.createFromAsset(context.getResources().getAssets(), "Fonts/bkoodk.ttf");
        this.setTypeface(face);
        style(context, attrs);
        init(context);
    }

    public CustomTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        Typeface face = Typeface.createFromAsset(context.getResources().getAssets(), "Fonts/bkoodk.ttf");
        this.setTypeface(face);
        style(context, attrs);
        init(context);
    }

    private void style(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.CustomFontTextView);
        int cf = a.getInteger(R.styleable.CustomFontTextView_fontName, 0);
        this.moveImage = a.getBoolean(R.styleable.CustomFontTextView_moveImage, true);
        int fontName = 0;
        switch (cf) {
            case 1:
                fontName = R.string.bkoodk;
                break;
            case 2:
                fontName = R.string.arial_regular;
                break;
            case 3:
                fontName = R.string.bnazanin;
                break;
            default:
                fontName = R.string.bkoodk;
                break;
        }


        Typeface tf = Typeface.createFromAsset(context.getAssets(),
                "Fonts/" + getResources().getString(fontName) + ".ttf");
        setTypeface(tf);
        a.recycle();
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

        left_Bitmap = -14f;
        top_Bitampp = getHeight() / 2 + getY();

        setX(left_Bitmap);
        setY(top_Bitampp);


//        setBackground(new BitmapDrawable(getResources(), bitmap));


    }

    @Override
    protected void onDraw(Canvas canvas) {
        setGravity(Gravity.CENTER);

        setText(getText());

        top_Bitampp = this.getHeight()/4;
//        top_Bitampp = this.getHeight()/5;


        if (moveImage)
            canvas.drawBitmap(bitmap, left_Bitmap, top_Bitampp, paint);

        mHandler.postDelayed(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                if (left_Bitmap == getRight() - 20)
                    left_Bitmap = 8f;
                left_Bitmap += 1;
                invalidate();
            }
        }, 5000);

        super.onDraw(canvas);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        centerX = w / 2;
        centerY = h / 2 - getY();
        super.onSizeChanged(w, h, oldw, oldh);
    }
}