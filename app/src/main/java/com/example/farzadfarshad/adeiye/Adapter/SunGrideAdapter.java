package com.example.farzadfarshad.adeiye.Adapter;

/**
 * Created by FARZAD&FARSHAD on 12/10/2017.
 */

import android.content.Context;
import android.os.Environment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.farzadfarshad.adeiye.Interface.onImageClick;
import com.example.farzadfarshad.adeiye.MainActivity;
import com.example.farzadfarshad.adeiye.R;
import com.example.farzadfarshad.adeiye.Model.SunModel;

import java.io.File;
import java.util.ArrayList;

public class SunGrideAdapter extends RecyclerView.Adapter<SunGrideAdapter.ViewHolder> {
    private ArrayList<SunModel> android;
    private Context context;
    private int position_selected = -1;
    private onImageClick onImageClick;
    File file;

    public SunGrideAdapter(Context context, ArrayList<SunModel> android) {
        this.android = android;
        this.context = context;
    }

    @Override
    public SunGrideAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_layout_sun, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final SunGrideAdapter.ViewHolder viewHolder, final int i) {
        final String[] separated = android.get(i).getSun_image_url().split("images/");
        File path = Environment.getExternalStorageDirectory();
        file = new File(path + "/" + "Adeiye", separated[1]);

        if (file.exists())
            viewHolder.tv_android.setText(context.getResources().getString(R.string.select));
        else
            viewHolder.tv_android.setText(android.get(i).getSun_name());

        Glide.with(context).load("http://api.learn2crack.com/" + android.get(i).getSun_image_url())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .error(context.getResources().getDrawable(R.drawable.ic_sun))
                .into(viewHolder.img_android);


        viewHolder.img_android.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewHolder.select_img.setImageDrawable(context.getResources().getDrawable(R.drawable.tikbackground));
                position_selected = i;
                notifyDataSetChanged();
            }
        });


        //for inke yeki beshe entekhab kard na chanta
        if (position_selected == i) {
            viewHolder.select_img.setImageDrawable(context.getResources().getDrawable(R.drawable.tikbackground));
            if (!file.exists()) {
                viewHolder.downloadimage_txt.setVisibility(View.VISIBLE);
                Animation animation = AnimationUtils.loadAnimation(context, R.anim.bottom_up);
                viewHolder.downloadimage_txt.startAnimation(animation);
                viewHolder.tv_android.setVisibility(View.GONE);
            }
        } else {
            viewHolder.select_img.setImageDrawable(context.getResources().getDrawable(R.drawable.circleshapegray));
            viewHolder.downloadimage_txt.setVisibility(View.GONE);
            viewHolder.tv_android.setVisibility(View.VISIBLE);
        }

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onImageClick != null && position_selected == i && !file.exists())
                    onImageClick.clickImage(android.get(i).getSun_image_url());
            }
        });
    }

    @Override
    public int getItemCount() {
        return android.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_android;
        private TextView downloadimage_txt;
        private ImageView img_android;
        private ImageView select_img;

        public ViewHolder(View view) {
            super(view);

            tv_android = (TextView) view.findViewById(R.id.tv_android);
            downloadimage_txt = (TextView) view.findViewById(R.id.downloadimage_txt);
            img_android = (ImageView) view.findViewById(R.id.img_android);
            select_img = (ImageView) view.findViewById(R.id.select_img);
        }
    }

    public void setOnClickImage(onImageClick onClickImage) {
        this.onImageClick = onClickImage;
    }

}
