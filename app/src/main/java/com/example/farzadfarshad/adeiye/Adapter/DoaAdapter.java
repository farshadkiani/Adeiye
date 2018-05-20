package com.example.farzadfarshad.adeiye.Adapter;

/**
 * Created by FARZAD&FARSHAD on 15/12/2017.
 */

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.farzadfarshad.adeiye.Database.AyeDb;
import com.example.farzadfarshad.adeiye.Model.DoaModel;
import com.example.farzadfarshad.adeiye.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Ravi Tamada on 18/05/16.
 */
public class DoaAdapter extends RecyclerView.Adapter<DoaAdapter.MyViewHolder> {

    private Context mContext;
    private List<AyeDb> doaList;
    byte checkColor = 0;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.matn_txt)
        TextView matn_txt;
        @BindView(R.id.mani_txt)
        TextView mani_txt;
        @BindView(R.id.card_view)
        CardView card_view;



        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }


    public DoaAdapter(Context mContext, List<AyeDb> doaList , byte checkColor) {
        this.mContext = mContext;
        this.doaList = doaList;
        this.checkColor = checkColor;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_layout_doa, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        AyeDb doaModel = doaList.get(position);
        holder.matn_txt.setText(doaModel.getMatn());
        holder.mani_txt.setText(doaModel.getMani());

        switch (checkColor){
            case 1:
                holder.card_view.setBackgroundColor(mContext.getResources().getColor(R.color.black));
                holder.mani_txt.setTextColor(mContext.getResources().getColor(R.color.White));
                holder.matn_txt.setTextColor(mContext.getResources().getColor(R.color.White));
                break;
            case 0:
                holder.card_view.setBackgroundColor(mContext.getResources().getColor(R.color.White));
                holder.mani_txt.setTextColor(mContext.getResources().getColor(R.color.black));
                holder.matn_txt.setTextColor(mContext.getResources().getColor(R.color.black));
                break;
        }

    }

    @Override
    public int getItemCount() {
        return doaList.size();
    }
}