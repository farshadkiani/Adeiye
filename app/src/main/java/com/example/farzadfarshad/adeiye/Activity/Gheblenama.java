package com.example.farzadfarshad.adeiye.Activity;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.farzadfarshad.adeiye.R;
import com.example.farzadfarshad.adeiye.Tools.QiblaCompassView;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class Gheblenama extends Activity implements SensorEventListener {

    // define the display assembly compass picture
    private ImageView image;

    // record the compass picture angle turned
    private float currentDegree = 0f;

    // device sensor manager
    private SensorManager mSensorManager;

    TextView tvHeading;


    private static android.hardware.SensorListener sOrientationListener;
    private static float sQiblaDirection = 0f;
    private static boolean isTrackingOrientation = false;
    private static DecimalFormat sDecimalFormat;
    private static final String TAG = Gheblenama.class.getSimpleName();
    private static final String PATTERN = "#.###";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gheblenama);


        // our compass image
        image = (ImageView) findViewById(R.id.imageViewCompass);

        // TextView that will tell the user what degree is he heading
        tvHeading = (TextView) findViewById(R.id.tvHeading);

        // initialize your android device sensor capabilities
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);







        try {
            sDecimalFormat = new DecimalFormat(PATTERN);
        } catch (AssertionError ae) {
            Log.wtf(TAG, "Could not construct DecimalFormat", ae);
            Log.d(TAG, "Will try with Locale.US");
            NumberFormat format = NumberFormat.getInstance(Locale.US);
            if (format instanceof DecimalFormat) {
                sDecimalFormat = (DecimalFormat) format;
                sDecimalFormat.applyPattern(PATTERN);
            }
        }



        final QiblaCompassView qiblaCompassView = (QiblaCompassView) findViewById(R.id.qibla_compass);
        qiblaCompassView.setConstants(((TextView) findViewById(R.id.bearing_north)),
                getText(R.string.bearing_north),
                ((TextView) findViewById(R.id.bearing_qibla)),
                getText(R.string.bearing_qibla));
        sOrientationListener = new android.hardware.SensorListener() {
            @Override
            public void onSensorChanged(int s, float v[]) {
                float northDirection = v[SensorManager.DATA_X];
                qiblaCompassView.setDirections(northDirection, sQiblaDirection);
            }

            @Override
            public void onAccuracyChanged(int s, int a) {
            }
        };
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!isTrackingOrientation) {
            isTrackingOrientation = mSensorManager.registerListener(sOrientationListener,
                    SensorManager.SENSOR_ORIENTATION);
        }
        // for the system's orientation sensor registered listeners
      /*  mSensorManager.registerListener(this, mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION),
                SensorManager.SENSOR_DELAY_GAME);*/
    }

    @Override
    protected void onPause() {
        super.onPause();

        // to stop the listener and save battery
        mSensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        // get the angle around the z-axis rotated
        float degree = Math.round(event.values[0]);

        tvHeading.setText("Heading: " + Float.toString(degree) + " degrees");

        degree = 360 - degree;

        // create a rotation animation (reverse turn degree degrees)
        RotateAnimation ra = new RotateAnimation(
                currentDegree,
                -degree,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF,
                0.5f);

        // how long the animation will take place
        ra.setDuration(210);

        // set the animation after the end of the reservation status
        ra.setFillAfter(true);

        // Start the animation
        image.startAnimation(ra);
        currentDegree = -degree;

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // not in use
    }


}