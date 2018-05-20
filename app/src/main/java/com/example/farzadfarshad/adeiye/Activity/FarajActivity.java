package com.example.farzadfarshad.adeiye.Activity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.activeandroid.query.Select;
import com.example.farzadfarshad.adeiye.Adapter.DoaAdapter;
import com.example.farzadfarshad.adeiye.Database.AyeDb;
import com.example.farzadfarshad.adeiye.Database.DoaDb;
import com.example.farzadfarshad.adeiye.DialogCustom;
import com.example.farzadfarshad.adeiye.GridSunActivity;
import com.example.farzadfarshad.adeiye.Movie.PermissionHandler;
import com.example.farzadfarshad.adeiye.R;
import com.example.farzadfarshad.adeiye.RetrofitManager.ServerAPI;
import com.example.farzadfarshad.adeiye.Setting.SettingActivity;
import com.karumi.expandableselector.ExpandableItem;
import com.karumi.expandableselector.ExpandableSelector;
import com.karumi.expandableselector.OnExpandableItemClickListener;
import com.yalantis.contextmenu.lib.ContextMenuDialogFragment;
import com.yalantis.contextmenu.lib.MenuObject;
import com.yalantis.contextmenu.lib.MenuParams;
import com.yalantis.contextmenu.lib.interfaces.OnMenuItemClickListener;
import com.yalantis.contextmenu.lib.interfaces.OnMenuItemLongClickListener;

import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FarajActivity extends AppCompatActivity implements View.OnClickListener
        , OnMenuItemClickListener, OnMenuItemLongClickListener {

    List<AyeDb> arrayList = new ArrayList<>();

    @BindView(R.id.recycler_view)
    RecyclerView recycler_view;

    @BindView(R.id.toolbar_title)
    TextView toolbar_title;

    @BindView(R.id.back_img)
    ImageView back_img;

    @BindView(R.id.play_img)
    ImageView play_img;

/*    @BindView(R.id.es_sizes)
    ExpandableSelector sizesExpandableSelector;*/

    @BindView(R.id.include_player)
    View include_player;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.player_imageview)
    ImageView player_imageview;

    @BindView(R.id.pause_img)
    ImageView pause_img;

    @BindView(R.id.player_seekbar)
    SeekBar player_seekbar;

    @BindView(R.id.time)
    TextView time_txt;

    MediaPlayer mediaPlayer;

    int length_mediaplayer;

    DoaAdapter doaAdapter;

    private ContextMenuDialogFragment mMenuDialogFragment;
    private FragmentManager fragmentManager;


    //audio
    private double timeElapsed = 0, finalTime = 0;
    private int forwardTime = 2000, backwardTime = 2000;
    private Handler durationHandler = new Handler();

    //
    byte color_adapter = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faraj);
        ButterKnife.bind(this);
        player_imageview.setOnClickListener(this);
        pause_img.setOnClickListener(this);
        fragmentManager = getSupportFragmentManager();

        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setHomeButtonEnabled(false);
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
        arrayList = getAll();

        initView((byte) 0);

        initMenuFragment();
    }

    private void initView(byte color) {
        mediaPlayer = new MediaPlayer();
        doaAdapter = new DoaAdapter(this, arrayList, color);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recycler_view.setLayoutManager(mLayoutManager);
        doaAdapter.notifyDataSetChanged();
        recycler_view.setAdapter(doaAdapter);
        toolbar_title.setText(getResources().getString(R.string.doa_faraj));
        back_img.setOnClickListener(this);
        play_img.setOnClickListener(this);
        /*initializeSizesExpandableSelector();
        sizesExpandableSelector.setOnExpandableItemClickListener(new OnExpandableItemClickListener() {
            @Override
            public void onExpandableItemClickListener(int index, View view) {
                //Do something here
                if (index == 0)
                    sizesExpandableSelector.collapse();
                else if (index == 1)
                    Toast.makeText(FarajActivity.this,getResources().getString(R.string.add_favorite_list),Toast.LENGTH_SHORT).show();
                else if (index == 2){
                    //go to setting
                }

            }
        });*/
      /*  recycler_view.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0 && sizesExpandableSelector.isShown())
                    sizesExpandableSelector.setVisibility(View.GONE);
                if (dy < 0)
                    sizesExpandableSelector.setVisibility(View.VISIBLE);
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {

                *//*if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    sizesExpandableSelector.setVisibility(View.VISIBLE);
                }*//*
                super.onScrollStateChanged(recyclerView, newState);
            }
        });*/

    }

    public static List<AyeDb> getAll() {
        return new Select()
                .from(AyeDb.class)
                .where("NameDoa = ?", "Faraj")
                .execute();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.back_img:
                onBackPressed();
                break;

            case R.id.play_img:
                if (fragmentManager.findFragmentByTag(ContextMenuDialogFragment.TAG) == null) {
                    mMenuDialogFragment.show(fragmentManager, ContextMenuDialogFragment.TAG);
                }

                break;

            case R.id.player_imageview:
                player_imageview.setVisibility(View.GONE);
                pause_img.setVisibility(View.VISIBLE);
                if (length_mediaplayer != 0) {
                    mediaPlayer.seekTo(length_mediaplayer);
                    mediaPlayer.start();
                } else {
                    File path = Environment.getExternalStorageDirectory();
                    Uri myUri = Uri.parse(path + "/" + "Adeiye" + "/" + "Faraj.mp3");
                    mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

                    try {
                        mediaPlayer.setDataSource(getApplicationContext(), myUri);
                        mediaPlayer.prepare();
                        finalTime = mediaPlayer.getDuration();
                        player_seekbar.setMax((int) finalTime);
                        player_seekbar.setClickable(false);
                        durationHandler.postDelayed(updateSeekBarTime, 100);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    mediaPlayer.start();

                    timeElapsed = mediaPlayer.getCurrentPosition();
                    player_seekbar.setProgress((int) timeElapsed);
                    durationHandler.postDelayed(updateSeekBarTime, 100);
                }
                break;

            case R.id.pause_img:
                pause();
                break;

        }
    }

    // pause mp3 song
    public void pause() {
        mediaPlayer.pause();
        length_mediaplayer = mediaPlayer.getCurrentPosition();
        player_imageview.setVisibility(View.VISIBLE);
        pause_img.setVisibility(View.GONE);
    }


    //handler to change seekBarTime
    private Runnable updateSeekBarTime = new Runnable() {
        public void run() {
            //get current position
            timeElapsed = mediaPlayer.getCurrentPosition();
            //set seekbar progress
            player_seekbar.setProgress((int) timeElapsed);
            //set time remaing
            double timeRemaining = finalTime - timeElapsed;
            time_txt.setText(String.format("%d:%d", TimeUnit.MILLISECONDS.toMinutes((long) timeRemaining)
                    , TimeUnit.MILLISECONDS.toSeconds((long) timeRemaining) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long) timeRemaining))));
            durationHandler.postDelayed(this, 100);
        }
    };

    private void givemargin() {
       /* RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)sizesExpandableSelector.getLayoutParams();
        params.setMargins(30, 20, 20, 150); //substitute parameters for left, top, right, bottom
        sizesExpandableSelector.setLayoutParams(params);*/
    }

    private void initializeSizesExpandableSelector() {
 /*       List<ExpandableItem> expandableItems = new ArrayList<>();
        expandableItems.add(new ExpandableItem(R.drawable.plus));
        expandableItems.add(new ExpandableItem(R.drawable.favorites));
        expandableItems.add(new ExpandableItem(R.drawable.setting));
        sizesExpandableSelector.showExpandableItems(expandableItems);*/
    }

    public static void setMargins(View v, int l, int t, int r, int b) {
        if (v.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) v.getLayoutParams();
            p.setMargins(l, t, r, b);
            v.requestLayout();
        }
    }

    private void initMenuFragment() {
        MenuParams menuParams = new MenuParams();
        menuParams.setActionBarSize((int) getResources().getDimension(R.dimen.tool_bar_height));
        menuParams.setMenuObjects(getMenuObjects());
        menuParams.setClosableOutside(false);
        mMenuDialogFragment = ContextMenuDialogFragment.newInstance(menuParams);
        mMenuDialogFragment.setItemClickListener(this);
        mMenuDialogFragment.setItemLongClickListener(this);
    }


    private List<MenuObject> getMenuObjects() {
        // You can use any [resource, bitmap, drawable, color] as image:
        // item.setResource(...)
        // item.setBitmap(...)
        // item.setDrawable(...)
        // item.setColor(...)
        // You can set image ScaleType:
        // item.setScaleType(ScaleType.FIT_XY)
        // You can use any [resource, drawable, color] as background:
        // item.setBgResource(...)
        // item.setBgDrawable(...)
        // item.setBgColor(...)
        // You can use any [color] as text color:
        // item.setTextColor(...)
        // You can set any [color] as divider color:
        // item.setDividerColor(...)

        List<MenuObject> menuObjects = new ArrayList<>();

        MenuObject close = new MenuObject();
        close.setResource(R.drawable.exit_nav);

        MenuObject send = new MenuObject(getResources().getString(R.string.play));
        send.setResource(R.drawable.play_nav);

        MenuObject like = new MenuObject(getResources().getString(R.string.nav_setting));
        Bitmap b = BitmapFactory.decodeResource(getResources(), R.drawable.settinges);
        like.setBitmap(b);

        MenuObject addFr = new MenuObject(getResources().getString(R.string.add_favorite_list));
        BitmapDrawable bd = new BitmapDrawable(getResources(),
                BitmapFactory.decodeResource(getResources(), R.drawable.star_nav));
        addFr.setDrawable(bd);

        MenuObject addFav = new MenuObject(getResources().getString(R.string.dark));
        addFav.setResource(R.drawable.dark);

        /*MenuObject block = new MenuObject("Block user");
        block.setResource(R.drawable.ic_sun);*/

        menuObjects.add(close);
        menuObjects.add(send);
        menuObjects.add(like);
        menuObjects.add(addFr);
        menuObjects.add(addFav);
//        menuObjects.add(block);
        return menuObjects;
    }

    @Override
    public void onMenuItemLongClick(View clickedView, int position) {


    }

    @Override
    public void onMenuItemClick(View clickedView, int position) {

        if (position == 1) {
            checkPermisson();
        } else if (position == 2) {
            Intent intent = new Intent(this, SettingActivity.class);
            startActivity(intent);
            finish();
        } else if (position == 3) {
        } else if (position == 4) {
            if (color_adapter == 0) {
                initView((byte) 1);
                color_adapter = 1;
            } else {
                initView((byte) 0);
                color_adapter = 0;
            }
        }


    }

    public void checkPermisson() {
        String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
        new PermissionHandler().checkPermission(this, permissions, new PermissionHandler.OnPermissionResponse() {
            @Override
            public void onPermissionGranted() {
                File path = Environment.getExternalStorageDirectory();
                File file = new File(path + "/" + "Adeiye" + "/" + "Faraj.mp3");
                if (file.exists() && !include_player.isShown())
                    include_player.setVisibility(View.VISIBLE);
                else {
                    final DialogCustom dialogCustom = new DialogCustom(FarajActivity.this);
                    dialogCustom.show();
                    dialogCustom.setmyClick(new DialogCustom.myOnClickListener() {
                        @Override
                        public void onButtonClick() {
                            download("s/peanpyovcyf6lid/doafaraj.mp3?dl=1u", dialogCustom);
                        }
                    });
//                givemargin();
                }
            }

            @Override
            public void onPermissionDenied() {
                Toast.makeText(FarajActivity.this, getResources().getString(R.string.take_permission)
                        , Toast.LENGTH_SHORT).show();
            }
        });

    }


   /* @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.context_menu:
                if (fragmentManager.findFragmentByTag(ContextMenuDialogFragment.TAG) == null) {
                    mMenuDialogFragment.show(fragmentManager, ContextMenuDialogFragment.TAG);
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }*/

    @Override
    public void onBackPressed() {
        if (mMenuDialogFragment != null && mMenuDialogFragment.isAdded()) {
            mMenuDialogFragment.dismiss();
        } else {
            if (mediaPlayer.isPlaying())
                mediaPlayer.stop();
            finish();
        }
    }

    public void download(String url, final DialogCustom dialogCustom) {
        dialogCustom.dismiss();
        final ProgressDialog pDialog = new ProgressDialog(this);
        pDialog.setMessage(getResources().getString(R.string.download_file_doa));
        pDialog.show();
//        final String[] separated = url.split("images/");

        ServerAPI api = ServerAPI.retrofit.create(ServerAPI.class);
        api.downlload(url).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    pDialog.dismiss();
                    File path = Environment.getExternalStorageDirectory();
                    File file = new File(path + "/" + "Adeiye");
                    if (!file.exists())
                        file.mkdirs();
                    File file1 = new File(file, "Faraj.mp3");
//                    file1.createNewFile();
                    FileOutputStream fileOutputStream = new FileOutputStream(file1);
                    IOUtils.write(response.body().bytes(), fileOutputStream);
                    include_player.setVisibility(View.VISIBLE);
//                    onBackPressed();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(FarajActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Intent intent = new Intent("PERMISSION_RECEIVER");
        intent.putExtra("requestCode", requestCode);
        intent.putExtra("permissions", permissions);
        intent.putExtra("grantResults", grantResults);
        sendBroadcast(intent);
    }


}