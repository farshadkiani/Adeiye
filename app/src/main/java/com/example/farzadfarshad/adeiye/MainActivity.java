package com.example.farzadfarshad.adeiye;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Handler;
import android.os.SystemClock;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.example.farzadfarshad.adeiye.Activity.ChangeColorActivity;
import com.example.farzadfarshad.adeiye.Activity.ChangeLineActivtiy;
import com.example.farzadfarshad.adeiye.Activity.CircleActivity;
import com.example.farzadfarshad.adeiye.Activity.CityAzanActivity;
import com.example.farzadfarshad.adeiye.Activity.Gheblenama;
import com.example.farzadfarshad.adeiye.Activity.Login;
import com.example.farzadfarshad.adeiye.Activity.QiblaActivity;
import com.example.farzadfarshad.adeiye.Activity.noteActivity;
import com.example.farzadfarshad.adeiye.Fragments.DoaFragment;
import com.example.farzadfarshad.adeiye.Services.MyService;
import com.example.farzadfarshad.adeiye.Tools.SharedPreferencesTools;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    @BindView(R.id.promos_img)
    ImageView promos_img;

    @BindView(R.id.sliding_img)
    ImageView sliding_img;

    @BindView(R.id.toolbar_title)
    TextView toolbar_title;

    private ViewFlipper mViewFlipper;


    Toolbar toolbar;
    static int i, j;

    private NavigationView navigationView;
    private DrawerLayout drawer;
    private View navHeader;
    private ImageView imgNavHeaderBg;
    private TextView txtName, txtWebsite;

    // index to identify current nav menu item
    public static int navItemIndex = 0;

    // tags used to attach the fragments
    private static final String TAG_HOME = "home";
    private static final String TAG_PHOTOS = "photos";
    private static final String TAG_MOVIES = "movies";
    private static final String TAG_NOTIFICATIONS = "notifications";
    private static final String TAG_AZAN = "azan";
    private static final String TAG_SETTINGS = "settings";
    public static String CURRENT_TAG = TAG_HOME;

    // toolbar titles respected to selected nav menu item
    private String[] activityTitles;

    // flag to load home fragment when user presses back key
    private boolean shouldLoadHomeFragOnBackPress = true;
    private Handler mHandler;

    public SharedPreferencesTools sharedPreferencesTools;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);

        ButterKnife.bind(this);
        sharedPreferencesTools = new SharedPreferencesTools(this);

        sliding_img.setOnClickListener(this);


        final Calendar calendar = Calendar.getInstance();
        /*cal.setTimeInMillis(System.currentTimeMillis());
        cal.add(Calendar.MINUTE, 1);*/
//        calendar.setTimeInMillis(SystemClock.elapsedRealtime());
        /*calendar.set(Calendar.MONTH , 1);
        calendar.set(Calendar.YEAR , 2018);
        calendar.set(Calendar.DAY_OF_MONTH , 11);*/

        calendar.set(Calendar.HOUR_OF_DAY, 20);
        calendar.set(Calendar.MINUTE, 35);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.AM_PM, Calendar.PM);
        /*Intent intent = new Intent(this, MyBroadcastReceiver.class);
        PendingIntent pintent = PendingIntent.getService(this, 0, intent, 0);
        AlarmManager alarm = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        // schedule for every 30 seconds
        alarm.setRepeating(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), 30 * 1000, pintent);*/

        //ToDo salam
        /*Intent intent = new Intent(this, MyService.class);
        final PendingIntent pendingIntent = PendingIntent.getService(
                this.getApplicationContext(), 0, intent, 0);
        final AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);*/


        /*alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis()
                + (2 * 1000), pendingIntent);*/
        /*alarmManager.setRepeating(AlarmManager.RTC, SystemClock.elapsedRealtime()
                , 60 * 1000, pendingIntent);*/

        //ToDo salam
//        alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);


//        alarmManager.setAndAllowWhileIdle(AlarmManager.RTC_WAKEUP , SystemClock.elapsedRealtime() , pendingIntent);


//        alarmManager.setWindow(AlarmManager.RTC_WAKEUP , System.currentTimeMillis() , 60 * 1000, pendingIntent);

//        alarmManager.setExact(AlarmManager.RTC_WAKEUP , System.currentTimeMillis() , pendingIntent);


//        startService(intent);

        /*Intent intent1 = new Intent(this, MyService.class);
        startService(intent1);*/


        /*Timer repeatTask = new Timer();
        repeatTask.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
// Here do something
// This task will run every 10 sec repeat
               *//* alarmManager.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, cal.getTimeInMillis()
                        , 60 * 1000, pendingIntent);*//*

                alarmManager.setExact(AlarmManager.RTC_WAKEUP , System.currentTimeMillis() , pendingIntent);
            }
        }, 0, 60000);*/


        mHandler = new Handler();

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);


        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        // Navigation view header
        navHeader = navigationView.getHeaderView(0);

        /*txtName = (TextView) navHeader.findViewById(R.id.name);
        txtWebsite = (TextView) navHeader.findViewById(R.id.website);
        imgNavHeaderBg = (ImageView) navHeader.findViewById(R.id.img_header_bg);*/

        // load toolbar titles from string resources
        activityTitles = getResources().getStringArray(R.array.nav_item_activity_titles);


        mViewFlipper = (ViewFlipper) navHeader.findViewById(R.id.view_flipper);
        mViewFlipper.setAutoStart(true);
        mViewFlipper.setFlipInterval(5000);
        mViewFlipper.startFlipping();


        // load nav menu header data
        loadNavHeader();

        // initializing navigation menu
        setUpNavigationView();

        if (savedInstanceState == null) {
            navItemIndex = 0;
            CURRENT_TAG = TAG_HOME;
//            loadHomeFragment();
        }


        final ImageView circular = (ImageView) toolbar.findViewById(R.id.profile_image);
        circular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Display popup attached to the button as a position anchor
                displayPopupWindow(v);

                Animation animation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.rotate_around_center_point);
                circular.startAnimation(animation);
            }
        });


//        Picasso.with(this).load("http://adeiyeapp.gigfa.com/downloadone.jpg").into(image);
        Uri imageUri = Uri.parse("http://adeiyeapp.gigfa.com/downloadone.jpg");
//        Picasso.with(this).load(imageUri).into(image);


//your image url
       /* String url = "http://wwwns.akamai.com/media_resources/globe_emea.png";

        ImageLoader imageLoader = ImageLoader.getInstance();
        DisplayImageOptions options = new DisplayImageOptions.Builder().cacheInMemory(true)
                .cacheOnDisc(true)
                .build();

//download and display image from url
        imageLoader.displayImage(url, image, options);*/


       /* Glide.with(this).load("http://farshad.hotelphp.ir/image/speech.png")
                .thumbnail(0.5f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .error(getResources().getDrawable(R.drawable.ic_sun))
                .into(image_test);*/


        final ImageView imageView = (ImageView) toolbar.findViewById(R.id.sun_img);
        i = 0;
        j = 180;
        final RotateAnimation rotate = new RotateAnimation(i, j, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(30000);
        rotate.setInterpolator(new LinearInterpolator());
        rotate.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                imageView.clearAnimation();
                if (i == 0)
                    i = 180;
                else
                    i = 0;
                if (j == 180)
                    j = 0;
                else
                    j = 180;
                RotateAnimation rotate = new RotateAnimation(i, j, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                rotate.setDuration(30000);
                rotate.setInterpolator(new LinearInterpolator());
                rotate.setAnimationListener(this);
                imageView.startAnimation(rotate);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
//                imageView.clearAnimation();
//                imageView.startAnimation(rotate);
            }
        });
        imageView.startAnimation(rotate);
    }


    private void displayPopupWindow(View anchorView) {
        PopupWindow popup = new PopupWindow(MainActivity.this);
        View layout = getLayoutInflater().inflate(R.layout.popup_content, null);


        popup.setContentView(layout);
        // Set content width and height
        popup.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        popup.setWidth(WindowManager.LayoutParams.WRAP_CONTENT);
        // Closes the popup window when touch outside of it - when looses focus
        popup.setOutsideTouchable(true);
        popup.setFocusable(true);
        // Show anchored to button
        popup.setBackgroundDrawable(new BitmapDrawable());
        popup.showAsDropDown(anchorView);



        TextView tvCaption = (TextView) layout.findViewById(R.id.tvCaption);
        tvCaption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, GridSunActivity.class);
                startActivity(intent);
            }
        });

        TextView tvCaption1 = (TextView) layout.findViewById(R.id.tvCaption1);
        tvCaption1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ChangeLineActivtiy.class);
                startActivity(intent);
            }
        });

        TextView tvCaption2 = (TextView) layout.findViewById(R.id.tvCaption2);
        tvCaption2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
/*
                Intent intent = new Intent(MainActivity.this, QiblaActivity.class);
                startActivity(intent);*/

                Intent intent = new Intent(MainActivity.this, CircleActivity.class);
                startActivity(intent);

            }
        });
    }


    private void loadNavHeader() {
        // name, website
//        txtName.setText(getResources().getString(R.string.app_name));
//        txtWebsite.setText("www.androidhive.info");

        // loading header background image
      /*  Glide.with(this).load(urlNavHeaderBg)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imgNavHeaderBg);

        // Loading profile image
        Glide.with(this).load(urlProfileImg)
                .crossFade()
                .thumbnail(0.5f)
                .bitmapTransform(new CircleTransform(this))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imgProfile);*/

        // showing dot next to notifications label
        navigationView.getMenu().getItem(3).setActionView(R.layout.menu_dot);
        View view = navigationView.getMenu().getItem(3).getActionView();
        SwitchCompat switchCompat = (SwitchCompat) view.findViewById(R.id.switcher);

        if (sharedPreferencesTools.getNotificationApp())
            switchCompat.setChecked(true);
        else
            switchCompat.setChecked(false);

        switchCompat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    sharedPreferencesTools.setNotificationApp(true);
                else
                    sharedPreferencesTools.setNotificationApp(false);
            }
        });
    }


    private void loadHomeFragment() {
        // selecting appropriate nav menu item
        selectNavMenu();

        // set toolbar title
        setToolbarTitle();

        // if user select the current navigation menu again, don't do anything
        // just close the navigation drawer
        if (getSupportFragmentManager().findFragmentByTag(CURRENT_TAG) != null) {
            drawer.closeDrawers();

            return;
        }
        // Sometimes, when fragment has huge data, screen seems hanging
        // when switching between navigation menus
        // So using runnable, the fragment is loaded with cross fade effect
        // This effect can be seen in GMail app
        Runnable mPendingRunnable = new Runnable() {
            @Override
            public void run() {
                // update the main content by replacing fragments
                Fragment fragment = getHomeFragment();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
                        android.R.anim.fade_out);
                fragmentTransaction.replace(R.id.fragment_place, fragment, CURRENT_TAG);
                fragmentTransaction.commitAllowingStateLoss();
            }
        };

        // If mPendingRunnable is not null, then add to the message queue
        if (mPendingRunnable != null) {
            mHandler.post(mPendingRunnable);
        }


        //Closing drawer on item click
        drawer.closeDrawers();

        // refresh toolbar menu
        invalidateOptionsMenu();
    }


    private Fragment getHomeFragment() {
        switch (navItemIndex) {
            case 0:
                // home
                DoaFragment homeFragment = new DoaFragment();
                return homeFragment;
            case 1:
                // photos
                DoaFragment photosFragment = new DoaFragment();
                return photosFragment;
            case 2:
                // movies fragment
                DoaFragment moviesFragment = new DoaFragment();
                return moviesFragment;
            case 3:
                // notifications fragment
                DoaFragment notificationsFragment = new DoaFragment();
                return notificationsFragment;
/*
            case 4:
                // settings fragment
                DoaFragment settingsFragment = new DoaFragment();
                return settingsFragment;*/
            default:
                return new DoaFragment();
        }
    }

    private void setToolbarTitle() {
        getSupportActionBar().setTitle(activityTitles[navItemIndex]);
    }

    private void selectNavMenu() {
        navigationView.getMenu().getItem(navItemIndex).setChecked(true);
        navigationView.getMenu().getItem(3).setActionView(R.layout.menu_dot);


    }


    private void setUpNavigationView() {
        //Setting Navigation View Item Selected Listener to handle the item click of the navigation menu
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            // This method will trigger on item Click of navigation menu
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                //Check to see which item was being clicked and perform appropriate action
                switch (menuItem.getItemId()) {
                    //Replacing the main content with ContentFragment Which is our Inbox View;
                    case R.id.nav_home:
                        navItemIndex = 0;
                        CURRENT_TAG = TAG_HOME;
                        break;
                    case R.id.nav_photos:
                        navItemIndex = 1;
                        CURRENT_TAG = TAG_PHOTOS;
                        break;
                    case R.id.nav_movies:
                        navItemIndex = 2;
                        CURRENT_TAG = TAG_MOVIES;
                        break;
                    case R.id.nav_notifications:
                        navItemIndex = 3;
                        CURRENT_TAG = TAG_NOTIFICATIONS;
                        break;
                    case R.id.nav_azan:
                        navItemIndex = 4;
                        CURRENT_TAG = TAG_AZAN;
                        startActivity(new Intent(MainActivity.this, CityAzanActivity.class));
                        drawer.closeDrawers();
                        return true;
                    case R.id.nav_settings:
                        // launch new intent instead of loading fragment
                        navItemIndex = 5;
                        startActivity(new Intent(MainActivity.this, MapsActivity.class));
                        drawer.closeDrawers();
                        return true;
                    case R.id.nav_about_us:
                        // launch new intent instead of loading fragment
                        startActivity(new Intent(MainActivity.this, Gheblenama.class));
                        drawer.closeDrawers();
                        return true;
                   /* case R.id.nav_privacy_policy:
                        // launch new intent instead of loading fragment
                        startActivity(new Intent(MainActivity.this, Login.class));
                        drawer.closeDrawers();
                        return true;*/
                    default:
                        navItemIndex = 0;
                }

                //Checking if the item is in checked state or not, if not make it in checked state
                if (menuItem.isChecked()) {
                    menuItem.setChecked(false);
                } else {
                    menuItem.setChecked(true);
                }
                menuItem.setChecked(true);

                loadHomeFragment();

                return true;
            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sliding_img:
                drawer.openDrawer(Gravity.RIGHT);
                break;
            case R.id.promos_img:
                break;
        }
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(Gravity.RIGHT))
            drawer.closeDrawers();
        else
            super.onBackPressed();
    }

    public void setTitleToolbar(String tag){
        switch (tag){

            case "doa":
                toolbar_title.setText("دعاها");
                break;

            case "zekrrooz":
                toolbar_title.setText("ذکر روز");
                break;

            case "ziarat":
                toolbar_title.setText("زیارت");
                break;

            case "adeiye":
                toolbar_title.setText(getResources().getString(R.string.app_name));
                break;

        }

    }
}