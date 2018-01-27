package com.example.farzadfarshad.adeiye.Tools;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.CycleInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;

import com.example.farzadfarshad.adeiye.R;

/**
 * Created by FARZAD&FARSHAD on 16/01/2018.
 */

public class SmileyView extends View {
    private Paint mCirclePaint;
    private Paint mEyeAndMouthPaint;
    private Paint paintArc;
    private Paint paint;
    private float mCenterX;
    private float mCenterY;
    private float mRadius;
    private float x = 0;
    private float y = 360;
    private RectF mArcBounds = new RectF();
    private RectF mArcRect = new RectF();
    private Rect bitampRect = new Rect();


    float eyeRadius;
    float eyeOffsetX;
    float eyeOffsetY;

    float left_Bitmap = -100;
    float top_Bitampp = -20;

    float degree = 90f;
    float degreey = 100f;

    private Bitmap bitmap;
    Matrix matrix;
    float px;
    float py;
    private Resources resources;


    private final RectF tempRectF1 = new RectF();
    private final RectF tempRectF2 = new RectF();

    Canvas canvas = new Canvas();

    float rotate = 20;

    Handler mHandler = new Handler();

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
        mCirclePaint.setColor(Color.GREEN);
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

        paintArc = new Paint();
        paintArc.setStrokeWidth(50);
        paintArc.setColor(Color.YELLOW);
        paintArc.setAntiAlias(true);
        paintArc.setStyle(Paint.Style.STROKE);
        paintArc.setStrokeCap(Paint.Cap.ROUND);

        resources = context.getResources();
        bitmap = BitmapFactory
                .decodeResource(resources, R.drawable.light);

        Rect rect = new Rect(1000, 0, 300, 200);
        matrix = new Matrix();
        px = rect.exactCenterX();
        py = rect.exactCenterY();
        matrix.postTranslate(-bitmap.getWidth() / 2, -bitmap.getHeight() / 2);

        left_Bitmap = -bitmap.getWidth() / 2 ;
        top_Bitampp = -bitmap.getHeight() / 2 ;


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
//        canvas.drawCircle(mCenterX, mCenterY, mRadius, mCirclePaint);
        // draw eyes
        eyeRadius = mRadius / 5f;
        eyeOffsetX = mRadius / 3f;
        eyeOffsetY = mRadius / 3f;
     /*   canvas.drawCircle(mCenterX - eyeOffsetX, mCenterY - eyeOffsetY, eyeRadius,
                mEyeAndMouthPaint);*/
       /* canvas.drawCircle(mCenterX + eyeOffsetX, mCenterY - eyeOffsetY, eyeRadius,
                mEyeAndMouthPaint);*/
        // draw mouth
        float mouthInset = mRadius / 3f;
        mArcBounds.set(mouthInset, mouthInset, mRadius * 2 - mouthInset, mRadius * 2 -
                mouthInset);
//        canvas.drawArc(mArcBounds, 45f, 90f, false, mEyeAndMouthPaint);


//        canvas.drawCircle(x, y
//                , 16 * getResources().getDisplayMetrics().density, paint);


        mArcRect.set(10, -4000, 10 * getWidth(), 4000);
//        canvas.drawArc(mArcRect, degree, degreey, false, paintArc);

//        canvas.drawLine(0, 0, 100, getHeight() + getHeight(), paintArc);



 /*       Matrix matrix = new Matrix();
        matrix.setScale(50,50,50,50);
        matrix.postScale(50, 90);
        matrix.postRotate(45);
        canvas.drawBitmap(bitmap , matrix , paint);
        matrix.reset();*/

//        canvas.drawBitmap(bitmap, matrix, new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG | Paint.FILTER_BITMAP_FLAG));

        mHandler.postDelayed(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                left_Bitmap += 0.1;
                top_Bitampp += 0.8;
                invalidate();
            }
        }, 1000 / 25);
//        matrix.reset();


//        canvas.drawText("salam farshad", x, 200, paint);

        this.canvas = canvas;


        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10);
        paint.setColor(Color.GREEN);

        final Path path = new Path();
        int midX = 20 + ((40 - 20) / 2);
        int midY = 50 + ((100 - 50) / 2);
        float xDiff = midX - 20;
        float yDiff = midY - 50;
        double angle = (Math.atan2(yDiff, xDiff) * (180 / Math.PI)) - 90;
        double angleRadians = Math.toRadians(angle);
        float pointX = (float) (midX + 20 * Math.cos(angleRadians));
        float pointY = (float) (midY + 20 * Math.sin(angleRadians));

        path.moveTo(0, 0);
        path.cubicTo(20, 500, pointX, pointY, 40, 100);
//        canvas.drawPath(path, paint);


        canvas.drawLine(0, 0, 140, getHeight(), paint);

        canvas.drawBitmap(bitmap, left_Bitmap, top_Bitampp, paint);


    }


    public void initAnimation() {
        ValueAnimator animation = ValueAnimator.ofFloat(0, -40);
        animation.setInterpolator(new LinearInterpolator());
        animation.setDuration(4000);
        animation.setRepeatCount(ValueAnimator.INFINITE);
        animation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                x = getTranslationX();
                y = getRotationY();

//                matrix.postRotate(20);

                matrix.setRotate((float) animation.getAnimatedValue(), 0, mCenterY);


//                matrix.setRotate((float) animation.getAnimatedValue());
//                matrix.postRotate((float) animation.getAnimatedValue(),getWidth()/2 , getHeight()/2);
//                matrix.preRotate((float) animation.getAnimatedValue(),getWidth()/2 , getHeight()/2);

//                matrix.reset();

               /* degree = (float) animation.getAnimatedValue();
                degreey = (float) animation.getAnimatedValue();*/
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

    @Override
    protected void dispatchDraw(Canvas canvas) {
        canvas.save();
        canvas.rotate(-20, getWidth() / 2f, getHeight() / 2f);
        super.dispatchDraw(canvas);
        canvas.restore();
    }

}