package com.example.farzadfarshad.adeiye.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.farzadfarshad.adeiye.Model.SettingParentModel;
import com.example.farzadfarshad.adeiye.R;

import java.util.List;

/**
 * Created by farshad&farzad on 2/25/2018.
 */

public class SettingChildAdapter extends RecyclerView.Adapter<SettingChildAdapter.MyViewHolder> {

    private List<SettingParentModel> settingParentModelList;
    int pos;
    Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView child_txt;

        public MyViewHolder(View view) {
            super(view);
            child_txt = (TextView) view.findViewById(R.id.child_txt);
        }
    }


    public SettingChildAdapter(Context context, List<SettingParentModel> settingParentModelList, int position) {
        this.settingParentModelList = settingParentModelList;
        this.pos = position;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_setting__child_layout, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        SettingParentModel movie = settingParentModelList.get(position);
        holder.child_txt.setText(movie.getTitle());

    }

    @Override
    public int getItemCount() {
        return settingParentModelList.size();
    }



}
