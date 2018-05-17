package com.example.farzadfarshad.adeiye;

/**
 * Created by FARZAD&FARSHAD on 10/10/2017.
 */

import android.app.Application;
import android.content.Context;
import android.graphics.Typeface;
import android.support.multidex.MultiDex;
import android.text.TextUtils;
import android.widget.TextView;

import com.activeandroid.ActiveAndroid;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import com.example.farzadfarshad.adeiye.Tools.SharedPreferencesTools;
import com.example.farzadfarshad.adeiye.Tools.TypefaceUtil;
import com.nostra13.universalimageloader.core.ImageLoader;

import javax.inject.Inject;

public class MyApplication extends Application {


    public static final String TAG = MyApplication.class
            .getSimpleName();

    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;

    @Inject
    public Glide glide;

    private static MyApplication mInstance;


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }


    @Override
    public void onCreate() {
        super.onCreate();

        mInstance = this;

        ActiveAndroid.initialize(this);


        DaggerComponent component = DaggerDaggerComponent.builder()
                .contextModule(new ContextModule(this))
                .build();

        component.injectMyApplication(this);


        TypefaceUtil.overrideFont(getApplicationContext(), "SERIF", "Fonts/IranNastaliq.ttf");
//        glide = component.getGlide();

    }


    public static synchronized MyApplication getInstance() {
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        return mRequestQueue;
    }

    /*public ImageLoader getImageLoader() {
        getRequestQueue();
        if (mImageLoader == null) {
            mImageLoader = new ImageLoader(this.mRequestQueue,
                    new LruBitmapCache());
        }
        return this.mImageLoader;
    }*/

    public <T> void addToRequestQueue(Request<T> req, String tag) {
        // set the default tag if tag is empty
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }

    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }

    public Glide getGlide(){
        return glide;
    }



}
