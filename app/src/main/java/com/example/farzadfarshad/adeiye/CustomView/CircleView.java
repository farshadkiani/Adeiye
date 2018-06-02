package com.example.farzadfarshad.adeiye.CustomView;


import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;


import com.example.farzadfarshad.adeiye.R;

/**
 * Created by farshad&farzad on 6/1/2018.
 */

public class CircleView extends View {

    //circle and text colors
    private int circleCol, labelCol, cicleColSmaller, circleImgSmaller;
    //label text
    private String circleText;
    //paint for drawing custom view
    private Paint circlePaint;
    //paint for drawing custom view
    private Paint circlePaintSmaller;

    int viewWidthHalf;
    int viewHeightHalf;

    int viewWidthSmaller;
    int viewHeightSmaller;

    int radius = 0;
    int radiusSmaller = 0;

    Context context;

    private Bitmap bitmap;

    private final int maxWidth = 80;
    private final int maxHeight = 80;

    public CircleView(Context context, AttributeSet attrs) {
        super(context);
        this.context = context;
        //get the attributes specified in attrs.xml using the name we included
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs,
                R.styleable.LovelyView, 0, 0);
        try {
            //get the text and colors specified using the names in attrs.xml
            circleText = a.getString(R.styleable.LovelyView_circleLabel);
            circleCol = a.getInteger(R.styleable.LovelyView_circleColor, 0);//0 is default
            labelCol = a.getInteger(R.styleable.LovelyView_labelColor, 0);
            cicleColSmaller = a.getInteger(R.styleable.LovelyView_circleColorSmaller, 0);
            circleImgSmaller = a.getResourceId(R.styleable.LovelyView_circleImageSmaller, 0);
            bitmap = BitmapFactory.decodeResource(getResources(), circleImgSmaller);
        } finally {
            a.recycle();
        }

        initView();

    }

    private void initView() {

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //paint object for drawing in onDraw
        circlePaint = new Paint();
        //paint object for drawing in onDraw
        circlePaintSmaller = new Paint();
        //get half of the width and height as we are working with a circle
        viewWidthHalf = this.getMeasuredWidth() / 2;
        viewHeightHalf = this.getMeasuredHeight() / 2;


        //get the radius as half of the width or height, whichever is smaller
        //subtract ten so that it has some space around it
        if (viewWidthHalf > viewHeightHalf) {
            radius = viewHeightHalf - 100;
        } else {
            radius = viewWidthHalf - 100;
        }
        // get width and height smaller circle for first and not change when call animation
        if (viewWidthSmaller != viewWidthHalf && viewWidthSmaller == 0) {
            viewWidthSmaller = this.getMeasuredWidth() / 2;
            viewHeightSmaller = this.getMeasuredHeight() / 2;
            viewWidthSmaller += radius/2;
            viewHeightSmaller -= radius;
            radiusSmaller = radius / 2;
        }

        circlePaint.setStyle(Paint.Style.FILL);
        circlePaint.setAntiAlias(true);

        circlePaintSmaller.setStyle(Paint.Style.FILL);
        circlePaintSmaller.setAntiAlias(true);
        //set the paint color using the circle color specified
        circlePaint.setColor(circleCol);
        //set the paint color using the circle color specified
        circlePaintSmaller.setColor(cicleColSmaller);

        canvas.drawCircle(viewWidthHalf, viewHeightHalf, radius, circlePaint);
        //set image
        imageIcon(canvas, circlePaint, viewWidthHalf, viewHeightHalf);
        //set the text color using the color specified
        circlePaint.setColor(labelCol);
        //set text properties
        circlePaint.setTextAlign(Paint.Align.CENTER);
        circlePaint.setTextSize(50);
        //draw circle
        canvas.drawCircle(viewWidthSmaller, viewHeightSmaller, radiusSmaller, circlePaintSmaller);

        if (viewWidthHalf == viewWidthSmaller)
            //draw the text using the string attribute and chosen properties
            canvas.drawText(circleText, viewWidthHalf, viewHeightHalf, circlePaint);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int desiredWidth = 200;
        int desiredHeight = 600;

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int width;
        int height;

        //Measure Width
        if (widthMode == MeasureSpec.EXACTLY) {
            //Must be this size
            width = widthSize;
        } else if (widthMode == MeasureSpec.AT_MOST) {
            //Can't be bigger than...
            width = Math.max(desiredWidth, widthSize);
        } else {
            //Be whatever you want
            width = desiredWidth;
        }

        //Measure Height
        if (heightMode == MeasureSpec.EXACTLY) {
            //Must be this size
            height = heightSize;
        } else if (heightMode == MeasureSpec.AT_MOST) {
            //Can't be bigger than...
            height = Math.max(desiredHeight, heightSize);
        } else {
            //Be whatever you want
            height = desiredHeight;
        }

        //MUST CALL THIS
        setMeasuredDimension(width, height);
    }

    public int getCircleColor() {
        return circleCol;
    }

    public int getLabelColor() {
        return labelCol;
    }

    public String getLabelText() {
        return circleText;
    }

    public void setCircleColor(int newColor) {
        //update the instance variable
        circleCol = newColor;
        //redraw the view
        invalidate();
        requestLayout();
    }

    public void setLabelColor(int newColor) {
        //update the instance variable
        labelCol = newColor;
        //redraw the view
        invalidate();
        requestLayout();
    }

    public void setLabelText(String newLabel) {
        //update the instance variable
        circleText = newLabel;
        //redraw the view
        invalidate();
        requestLayout();
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (event.getActionMasked() == MotionEvent.ACTION_DOWN) {
            setCircleColor(Color.GREEN);
            setLabelText("salam");
            setLabelColor(Color.MAGENTA);
            setAnimation();
        }
        return super.onTouchEvent(event);
    }

    public void setAnimation() {

        // change value x for smaller circle
        ValueAnimator widthAnimator = ValueAnimator.ofInt(viewWidthSmaller, viewWidthHalf);
        widthAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int animatedValue = (int) animation.getAnimatedValue();
                viewWidthSmaller = animatedValue;

                invalidate();
                requestLayout();
            }
        });
        widthAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        widthAnimator.start();

        //change value y for smaller circle
        ValueAnimator heightAnimator = ValueAnimator.ofInt(viewHeightSmaller, viewHeightHalf);
        heightAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int animatedValue = (int) animation.getAnimatedValue();
                viewHeightSmaller = animatedValue;

                invalidate();
                requestLayout();
            }
        });
        heightAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        heightAnimator.start();

        // change radius for smaller circle
        ValueAnimator radiusAnimator = ValueAnimator.ofInt(radiusSmaller, radius);
        radiusAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int animatedValue = (int) animation.getAnimatedValue();
                radiusSmaller = animatedValue;

                invalidate();
                requestLayout();
            }
        });
        radiusAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        radiusAnimator.start();


    }


    public void imageIcon(Canvas canvas, Paint p, int p1, int p2) {
        Bitmap b2 = getCroppedBitmap(bitmap);
        canvas.drawBitmap(b2, p1 - b2.getWidth() * 0.5f, p2 - b2.getHeight() * 0.5f, null);
    }

    private Bitmap scaleBitmap(Bitmap bm) {
        int width = bm.getWidth();
        int height = bm.getHeight();
        if (width > height) {
            float ratio = (float) width / maxWidth;
            width = maxWidth;
            height = (int) (height / ratio);
        } else if (height > width) {
            float ratio = (float) height / maxHeight;
            height = maxHeight;
            width = (int) (width / ratio);
        } else {
            height = maxHeight;
            width = maxWidth;
        }

        bm = Bitmap.createScaledBitmap(bm, width, height, true);
        return bm;
    }

    public Bitmap getCroppedBitmap(Bitmap bitmap) {
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
                bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        // canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
        canvas.drawCircle(bitmap.getWidth() / 2, bitmap.getHeight() / 2,
                bitmap.getWidth() / 2, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        Bitmap _bmp = Bitmap.createScaledBitmap(output, 2 * radius, 2 * radius, true);
        return _bmp;
//        return output;
    }
}
