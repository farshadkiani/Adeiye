package com.example.farzadfarshad.adeiye.Tools;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

import com.example.farzadfarshad.adeiye.R;

/**
 * Created by FARZAD&FARSHAD on 16/01/2018.
 */

public class SmileyView extends View {
    private Paint mCirclePaint;
    private Paint mEyeAndMouthPaint;
    private Paint paint;
    private float mCenterX;
    private float mCenterY;
    private float mRadius;
    private float x = 0;
    private float y = 360;
    private RectF mArcBounds = new RectF();


    float eyeRadius;
    float eyeOffsetX;
    float eyeOffsetY;

    float degree = 0 ;
    float degreey = 45 ;

    private Bitmap bitmap;
    private Resources resources;

    public SmileyView(Context context) {
        this(context, null, 0);

    }

    public SmileyView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SmileyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaints(context);
    }

    private void initPaints(Context context) {
        mCirclePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mCirclePaint.setStyle(Paint.Style.FILL);
        mCirclePaint.setColor(Color.YELLOW);
        mEyeAndMouthPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mEyeAndMouthPaint.setStyle(Paint.Style.STROKE);
        mEyeAndMouthPaint.setStrokeWidth(16 * getResources().getDisplayMetrics().density);
        mEyeAndMouthPaint.setStrokeCap(Paint.Cap.ROUND);
        mEyeAndMouthPaint.setColor(Color.BLACK);

        paint = new Paint();
        paint.setColor(Color.RED);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(16 * getResources().getDisplayMetrics().density);

        resources = context.getResources();
        bitmap = BitmapFactory
                .decodeResource(resources, R.drawable.lightimage);


    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int w = MeasureSpec.getSize(widthMeasureSpec);
        int h = MeasureSpec.getSize(heightMeasureSpec);
        int size = Math.min(w, h);
        setMeasuredDimension(size, size);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // draw face
        canvas.drawCircle(mCenterX, mCenterY, mRadius, mCirclePaint);
// draw eyes
        eyeRadius = mRadius / 5f;
        eyeOffsetX = mRadius / 3f;
        eyeOffsetY = mRadius / 3f;
        canvas.drawCircle(mCenterX - eyeOffsetX, mCenterY - eyeOffsetY, eyeRadius,
                mEyeAndMouthPaint);
        canvas.drawCircle(mCenterX + eyeOffsetX, mCenterY - eyeOffsetY, eyeRadius,
                mEyeAndMouthPaint);
// draw mouth
        float mouthInset = mRadius / 3f;
        mArcBounds.set(mouthInset, mouthInset, mRadius * 2 - mouthInset, mRadius * 2 -
                mouthInset);
        canvas.drawArc(mArcBounds, 45f, 90f, false, mEyeAndMouthPaint);


        canvas.drawCircle(x, y
                , 16 * getResources().getDisplayMetrics().density, paint);

        canvas.drawArc(mArcBounds , degree , degreey , true , paint);

        canvas.drawBitmap(bitmap, x , 200, paint);


        canvas.drawText("salam farshad" ,x , 200 , paint);

    }


    public void initAnimation() {
        ValueAnimator animation = ValueAnimator.ofFloat(0, 360);
        animation.setInterpolator(new AccelerateDecelerateInterpolator());
        animation.setDuration(2000);
        animation.setRepeatCount(ValueAnimator.INFINITE);
        animation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                x = (float) animation.getAnimatedValue();
//                y = (float) animation.getAnimatedValue();
                degree = (float) animation.getAnimatedValue();
//                degreey = (float) animation.getAnimatedValue();
                invalidate();
            }
        });
        animation.start();
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        mCenterX = w / 2f;
        mCenterY = h / 2f;
        mRadius = Math.min(w, h) / 2f;
      /*  x = mCenterX - eyeOffsetX - eyeRadius + (16 * getResources().getDisplayMetrics().density) / 2;
        y = mCenterY - eyeOffsetY - eyeRadius + (16 * getResources().getDisplayMetrics().density);*/

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);

        int action = event.getAction();

        switch (action) {
            case MotionEvent.ACTION_DOWN:
                initAnimation();
                break;
            case MotionEvent.ACTION_MOVE:
                initAnimation();
                break;
        }


        invalidate();
        return true;
    }
}


