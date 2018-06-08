package com.example.farzadfarshad.adeiye.PdfShow;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.example.farzadfarshad.adeiye.CustomView.CircleView;
import com.example.farzadfarshad.adeiye.DialogCustom;
import com.example.farzadfarshad.adeiye.Movie.PermissionHandler;
import com.example.farzadfarshad.adeiye.R;
import java.io.File;
import java.util.List;

/**
 * Created by farshad&farzad on 5/18/2018.
 */

public class PdfShowAdapter extends RecyclerView.Adapter<PdfShowAdapter.ViewHolder> {

    private List<PdfModelDetail> mData;
    private LayoutInflater mInflater;
    private PdfShowAdapter.ItemClickListener mClickListener;
    private Context context;
    private Glide glide;

    public PdfShowAdapter(Context context, Glide glide) {
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
        this.glide = glide;
    }

    // data is passed into the constructor

    public PdfShowAdapter(Context context, List<PdfModelDetail> data, Glide glide) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.context = context;
        this.glide = glide;
    }

    public void SwapData(List<PdfModelDetail> data) {
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
    public void onBindViewHolder(PdfShowAdapter.ViewHolder holder, final int position) {
        String animal = mData.get(position).getTitle();
        holder.myTextView.setText(animal);

        holder.imaage_circleview.setLabelText(animal);
        holder.imaage_circleview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String path = getExternalFilesDir()
                        + File.separator + "Adeiye" + File.separator + mData.get(position).getId() + ".Pdf";

                File futureStudioIconFile = new File(path);


                if (futureStudioIconFile.exists()) {
                    Intent intent = new Intent(context, PdfShowActivtiy.class);
                    intent.putExtra("path", path);
                    context.startActivity(intent);
                } else {
                    final DialogCustom dialogCustom = new DialogCustom(context);
                    dialogCustom.show();
                    dialogCustom.setmyClick(new DialogCustom.myOnClickListener() {
                        @Override
                        public void onButtonClick() {
                            checkPermisson(mData.get(position).getFile(), dialogCustom
                                    , mData.get(position).getId());
//                downloadFile(pdfModelDetails.get(position).getFile(), dialogCustom);
                        }

                    });
                }
            }

        });

        /*glide.with(context).load(mData.get(position).getId())
                .error(context.getResources().getDrawable(R.drawable.icon_shahid))
                .into(holder.film_img);*/

    }

    // total number of cells
    @Override
    public int getItemCount() {
        return mData.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView myTextView;
//        ImageView film_img;
        CircleView imaage_circleview;

        ViewHolder(View itemView) {
            super(itemView);
            myTextView = (TextView) itemView.findViewById(R.id.textView3);
//            film_img = (ImageView) itemView.findViewById(R.id.imageView);
            imaage_circleview = (CircleView) itemView.findViewById(R.id.imaage_circleview);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null)
                mClickListener.onItemClick(view, getAdapterPosition());
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

    private File getExternalFilesDir() {
        return Environment.getExternalStorageDirectory();
    }


    public void checkPermisson(final String url, final DialogCustom dialogCustom, final int id) {
        String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
        new PermissionHandler().checkPermission((Activity) context, permissions, new PermissionHandler.OnPermissionResponse() {
            @Override
            public void onPermissionGranted() {
                dialogCustom.dismiss();
//                downloadFile(url, id);
            }

            @Override
            public void onPermissionDenied() {
                Toast.makeText(context, context.getResources().getString(R.string.take_permission)
                        , Toast.LENGTH_SHORT).show();
            }
        });

    }




}