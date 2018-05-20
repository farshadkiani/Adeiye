package com.example.farzadfarshad.adeiye.PdfShow;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.farzadfarshad.adeiye.Model.MovieModelDetail;
import com.example.farzadfarshad.adeiye.Movie.MovieAdapter;
import com.example.farzadfarshad.adeiye.R;

import java.util.List;

/**
 * Created by farshad&farzad on 5/18/2018.
 */

public class PdfShowAdapter extends RecyclerView.Adapter<PdfShowAdapter.ViewHolder> {

    private List<PdfModelDetail> mData ;
    private LayoutInflater mInflater;
    private PdfShowAdapter.ItemClickListener mClickListener;
    private Context context;
    private Glide glide;

    public PdfShowAdapter(Context context , Glide glide){
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
        this.glide = glide;
    }

    // data is passed into the constructor

    public PdfShowAdapter(Context context, List<PdfModelDetail> data , Glide glide) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.context = context;
        this.glide = glide;
    }

    public void SwapData(List<PdfModelDetail> data){
        this.mData = data;
        notifyDataSetChanged();
    }

    // inflates the cell layout from xml when needed
    @Override
    public PdfShowAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.pdf_item_layout, parent, false);
        return new PdfShowAdapter.ViewHolder(view);
    }

    // binds the data to the textview in each cell
    @Override
    public void onBindViewHolder(PdfShowAdapter.ViewHolder holder, int position) {
        String animal = mData.get(position).getTitle();
        holder.myTextView.setText(animal);

        glide.with(context).load(mData.get(position).getId())
                .error(context.getResources().getDrawable(R.drawable.icon_shahid))
                .into(holder.film_img);

    }

    // total number of cells
    @Override
    public int getItemCount() {
        return mData.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView myTextView;
        ImageView film_img;

        ViewHolder(View itemView) {
            super(itemView);
            myTextView = (TextView) itemView.findViewById(R.id.textView3);
            film_img = (ImageView) itemView.findViewById(R.id.imageView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // convenience method for getting data at click position
//    String getItem(int id) {
//        return mData[id];
//    }

    // allows clicks events to be caught
    public void setClickListener(PdfShowAdapter.ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}