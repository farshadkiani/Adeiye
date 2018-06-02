package com.example.farzadfarshad.adeiye.Setting;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import com.example.farzadfarshad.adeiye.R;
import com.example.farzadfarshad.adeiye.Tools.SharedPreferencesTools;
import org.adw.library.widgets.discreteseekbar.DiscreteSeekBar;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by farshad&farzad on 5/12/2018.
 */

public class ItemArrayAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
        implements AdapterView.OnItemSelectedListener, View.OnClickListener {
    private static final int TYPE_ONE = 1;
    private static final int TYPE_TWO = 2;

    private ArrayList<Item> itemList;

    SharedPreferencesTools sharedPreferencesTools;

    String font_name = "bnazanin.ttf";

    int size ;

    Context context;

    TextView title;

    clickAzanInterface clickAzanInterface;

    clickAzanShive clickAzanShive;

    // Constructor of the class
    public ItemArrayAdapter(ArrayList<Item> itemList, Context context, TextView title ) {
        this.itemList = itemList;
        this.context = context;
        this.title = title;
        sharedPreferencesTools = new SharedPreferencesTools(context);
    }

    // get the size of the list
    @Override
    public int getItemCount() {
        return itemList == null ? 0 : itemList.size();
    }

    // determine which layout to use for the row
    @Override
    public int getItemViewType(int position) {
        Item item = itemList.get(position);
        if (item.getType() == Item.ItemType.ONE_ITEM) {
            return TYPE_ONE;
        } else if (item.getType() == Item.ItemType.TWO_ITEM) {
            return TYPE_TWO;
        } else {
            return -1;
        }
    }


    // specify the row layout file and click for each row
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ONE) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout_text, parent, false);
            return new ViewHolderOne(view);
        } else if (viewType == TYPE_TWO) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout_two, parent, false);
            return new ViewHolderTwo(view);
        } else {
            throw new RuntimeException("The type has to be ONE or TWO");
        }
    }

    // load data in each row element
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int listPosition) {
        switch (holder.getItemViewType()) {
            case TYPE_ONE:
                initLayoutOne((ViewHolderOne) holder, listPosition);
                break;
            case TYPE_TWO:
                initLayoutTwo((ViewHolderTwo) holder, listPosition);
                break;
            default:
                break;
        }
    }

    private void initLayoutOne(ViewHolderOne holder, int pos) {
//        holder.font_txt.setText(itemList.get(pos).getName());


        Typeface face = Typeface.createFromAsset(context.getAssets(),
                "Fonts/" + sharedPreferencesTools.getFont());

        // Spinner click listener
        holder.spinner.setOnItemSelectedListener(this);
        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("arial_regular");
        categories.add("bkoodk");
        categories.add("bnazanin");
        categories.add("IranNastaliq");
        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, categories);
        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        holder.spinner.setAdapter(dataAdapter);

        holder.sabt_font_btn.setOnClickListener(this);

        holder.font_txt.setTypeface(face);
        holder.font_txt.setTextSize(sharedPreferencesTools.getFontSize());
        holder.sabt_font_btn.setTypeface(face);
        holder.sabt_font_btn.setTextSize(sharedPreferencesTools.getFontSize());
        holder.select_font_txt.setTypeface(face);
        holder.select_font_txt.setTextSize(sharedPreferencesTools.getFontSize());

        title.setTypeface(face);
        title.setTextSize(sharedPreferencesTools.getFontSize());

        holder.seekar_size.setProgress(sharedPreferencesTools.getFontSize());

//        holder.seekar_size.setNumericTransformer(new O);
        holder.seekar_size.setNumericTransformer(new DiscreteSeekBar.NumericTransformer() {
            @Override
            public int transform(int value) {
                size = value;
                return value;
            }
        });
    }

    private void initLayoutTwo(ViewHolderTwo holder, int pos) {
//        holder.tvLeft.setText(itemList.get(pos).getName());
//        holder.tvRight.setText(itemList.get(pos).getName());
        Typeface face = Typeface.createFromAsset(context.getAssets(),
                "Fonts/" + sharedPreferencesTools.getFont());
        holder.moazen.setTypeface(face);
        holder.moazen.setTextSize(sharedPreferencesTools.getFontSize());

        holder.moazen_dropdown_txt.setTypeface(face);
        holder.moazen_dropdown_txt.setTextSize(sharedPreferencesTools.getFontSize());

        holder.shive_txt.setTypeface(face);
        holder.shive_txt.setTextSize(sharedPreferencesTools.getFontSize());

        holder.calculate_txt.setTypeface(face);
        holder.calculate_txt.setTextSize(sharedPreferencesTools.getFontSize());

        holder.shive_lny.setOnClickListener(this);

        holder.linear_ly.setOnClickListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        font_name = parent.getItemAtPosition(position).toString() + ".ttf";
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.sabt_font_btn) {
            sharedPreferencesTools.setFont(font_name);
            sharedPreferencesTools.setFontSize(size);
            notifyDataSetChanged();
            SettingActivity.notif();
        } else if (v.getId() == R.id.shive_lny){

            if (clickAzanShive != null){
                clickAzanShive.clicShive();
            }

        } else if(v.getId() == R.id.linear_ly){

            if (clickAzanInterface != null){
                clickAzanInterface.click();
            }

        }
    }


    // Static inner class to initialize the views of rows
    static class ViewHolderOne extends RecyclerView.ViewHolder {
        public TextView font_txt;
        public Spinner spinner;
        public Button sabt_font_btn;
        public TextView select_font_txt;
        public DiscreteSeekBar seekar_size;

        public ViewHolderOne(View itemView) {
            super(itemView);
            font_txt = (TextView) itemView.findViewById(R.id.font_txt);
            spinner = (Spinner) itemView.findViewById(R.id.spinner);
            sabt_font_btn = (Button) itemView.findViewById(R.id.sabt_font_btn);
            select_font_txt = (TextView) itemView.findViewById(R.id.select_font_txt);
            seekar_size = (DiscreteSeekBar) itemView.findViewById(R.id.seekbar);
        }
    }

    static class ViewHolderTwo extends RecyclerView.ViewHolder {
        public TextView moazen, shive_txt , calculate_txt , moazen_dropdown_txt;
        LinearLayout linear_ly;
        LinearLayout shive_lny;

        public ViewHolderTwo(View itemView) {
            super(itemView);
            moazen = (TextView) itemView.findViewById(R.id.moazen_txt);
            moazen_dropdown_txt = (TextView) itemView.findViewById(R.id.moazen_dropdown_txt);
            shive_txt = (TextView) itemView.findViewById(R.id.shive_txt);
            calculate_txt = (TextView) itemView.findViewById(R.id.calculate_txt);
            linear_ly = (LinearLayout) itemView.findViewById(R.id.linear_ly);
            shive_lny  = (LinearLayout) itemView.findViewById(R.id.shive_lny);
        }
    }

    public void setclicAzan(clickAzanInterface clickAzanInterface){
        this.clickAzanInterface = clickAzanInterface;
    }

    public void setClickAzanShive(clickAzanShive clickAzanShive)
    {
        this.clickAzanShive = clickAzanShive;
    }
}