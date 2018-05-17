package com.example.farzadfarshad.adeiye.Adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.farzadfarshad.adeiye.Model.SettingParentModel;
import com.example.farzadfarshad.adeiye.R;
import com.example.farzadfarshad.adeiye.Tools.SharedPreferencesTools;

import java.util.List;

import butterknife.OnClick;

/**
 * Created by farshad&farzad on 2/25/2018.
 */

public class SettingParentAdapter extends RecyclerView.Adapter<SettingParentAdapter.MyViewHolder> {

    private List<SettingParentModel> settingParentModelList;
    public onClick click;
    int pos;
    Context context;
    SharedPreferencesTools sharedPreferencesTools;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView row_recycler_txt;
        public LinearLayout linear_recycler;

        public MyViewHolder(View view) {
            super(view);
            row_recycler_txt = (TextView) view.findViewById(R.id.row_recycler_txt);
            linear_recycler = (LinearLayout) view.findViewById(R.id.linear_recycler);
        }
    }


    public SettingParentAdapter(Context context, List<SettingParentModel> settingParentModelList, int position) {
        this.settingParentModelList = settingParentModelList;
        this.pos = position;
        this.context = context;
        sharedPreferencesTools = new SharedPreferencesTools(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_setting_layout, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        SettingParentModel movie = settingParentModelList.get(position);
        holder.row_recycler_txt.setText(movie.getTitle());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (click != null)
                    click.click(position);
            }
        });



        if (position == pos) {
            holder.row_recycler_txt.setBackgroundColor(context.getResources().getColor(R.color.White));
            holder.row_recycler_txt.setTextColor(context.getResources().getColor(R.color.black));
            holder.linear_recycler.setBackgroundColor(context.getResources().getColor(R.color.White));
        } else {
            holder.row_recycler_txt.setBackgroundColor(context.getResources().getColor(R.color.black));
            holder.row_recycler_txt.setTextColor(context.getResources().getColor(R.color.White));
            holder.linear_recycler.setBackgroundColor(context.getResources().getColor(R.color.black));
        }
        setFontText(holder.row_recycler_txt);
    }

    @Override
    public int getItemCount() {
        return settingParentModelList.size();
    }

    public void setClickItem(onClick onClick) {
        click = onClick;
    }

    public interface onClick {
        void click(int position);
    }

    public void setFontText(TextView textView) {
        Typeface face = Typeface.createFromAsset(context.getAssets(),
                "Fonts/" + sharedPreferencesTools.getFont());
        textView.setTypeface(face);

    }
}
