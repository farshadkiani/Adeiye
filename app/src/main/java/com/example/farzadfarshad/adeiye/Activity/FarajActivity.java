package com.example.farzadfarshad.adeiye.Activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
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
import android.widget.TextView;
import android.widget.Toast;

import com.activeandroid.query.Select;
import com.example.farzadfarshad.adeiye.Adapter.DoaAdapter;
import com.example.farzadfarshad.adeiye.Database.AyeDb;
import com.example.farzadfarshad.adeiye.Database.DoaDb;
import com.example.farzadfarshad.adeiye.R;
import com.karumi.expandableselector.ExpandableItem;
import com.karumi.expandableselector.ExpandableSelector;
import com.karumi.expandableselector.OnExpandableItemClickListener;
import com.yalantis.contextmenu.lib.ContextMenuDialogFragment;
import com.yalantis.contextmenu.lib.MenuObject;
import com.yalantis.contextmenu.lib.MenuParams;
import com.yalantis.contextmenu.lib.interfaces.OnMenuItemClickListener;
import com.yalantis.contextmenu.lib.interfaces.OnMenuItemLongClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FarajActivity extends AppCompatActivity implements View.OnClickListener
        ,OnMenuItemClickListener, OnMenuItemLongClickListener {

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

    DoaAdapter doaAdapter;

    private ContextMenuDialogFragment mMenuDialogFragment;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faraj);
        ButterKnife.bind(this);
        fragmentManager = getSupportFragmentManager();

        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setHomeButtonEnabled(false);
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
        arrayList = getAll();

        initView();

        initMenuFragment();
    }

    private void initView() {
        doaAdapter = new DoaAdapter(this, arrayList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recycler_view.setLayoutManager(mLayoutManager);
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


        }
    }

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

    public static void setMargins (View v, int l, int t, int r, int b) {
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
        close.setResource(R.drawable.icn_close);

        MenuObject send = new MenuObject(getResources().getString(R.string.play));
        send.setResource(R.drawable.play);

        MenuObject like = new MenuObject(getResources().getString(R.string.nav_setting));
        Bitmap b = BitmapFactory.decodeResource(getResources(), R.drawable.settinges);
        like.setBitmap(b);

        MenuObject addFr = new MenuObject(getResources().getString(R.string.add_favorite_list));
        BitmapDrawable bd = new BitmapDrawable(getResources(),
                BitmapFactory.decodeResource(getResources(), R.drawable.favorites));
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

        if (position == 1){
            if (!include_player.isShown()) {
                include_player.setVisibility(View.VISIBLE);
//                givemargin();
            }
        }



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
            finish();
        }
    }




}