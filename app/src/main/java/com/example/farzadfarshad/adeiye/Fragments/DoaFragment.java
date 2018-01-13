package com.example.farzadfarshad.adeiye.Fragments;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.icu.util.ULocale;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.activeandroid.query.Select;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.cuboid.cuboidcirclebutton.CuboidButton;
import com.example.farzadfarshad.adeiye.Activity.FarajActivity;
import com.example.farzadfarshad.adeiye.Activity.Login;
import com.example.farzadfarshad.adeiye.Database.AyeDb;
import com.example.farzadfarshad.adeiye.Database.DoaDb;
import com.example.farzadfarshad.adeiye.DialogCustom;
import com.example.farzadfarshad.adeiye.MapsActivity;
import com.example.farzadfarshad.adeiye.Model.UrlImage;
import com.example.farzadfarshad.adeiye.MyApplication;
import com.example.farzadfarshad.adeiye.R;
import com.example.farzadfarshad.adeiye.RetrofitManager.ApiClient;
import com.example.farzadfarshad.adeiye.RetrofitManager.ApiInterface;
import com.example.farzadfarshad.adeiye.Tools.SharedPreferencesTools;
import com.example.farzadfarshad.adeiye.Tools.SolarCalendar;

import org.json.JSONArray;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit.Call;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link DoaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DoaFragment extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    @BindView(R.id.faraj_btn)
    com.example.farzadfarshad.adeiye.Tools.CuboidButton faraj_btn;

    @BindView(R.id.komeil_btn)
    CuboidButton komeil_btn;

    @BindView(R.id.zekr_txt)
    TextView zekr_rooz_txt;

    @BindView(R.id.date)
    TextView date;

    @BindView(R.id.date_year_txt)
    TextView date_year_txt;

    @BindView(R.id.date_milady)
    TextView date_milady;

    @BindView(R.id.date_month)
    TextView date_month;

    @BindView(R.id.titlehadis_txt)
    TextView titlehadis_txt;

    @BindView(R.id.hsdis_txt)
    TextView hsdis_txt;

    @BindView(R.id.image_img)
    ImageView image_img;

    DialogCustom dialogCustom;

    SharedPreferencesTools sharedPreferencesTools;

    static int i, j;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public DoaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DoaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DoaFragment newInstance(String param1, String param2) {
        DoaFragment fragment = new DoaFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_doa, container, false);

        ButterKnife.bind(this, view);
        faraj_btn.setOnClickListener(this);
        komeil_btn.setOnClickListener(this);

        initView();


        ImageView day_img = (ImageView) view.findViewById(R.id.day_img);

       /* Glide.with(this).load("http://farshad.hotelphp.ir/image/speech.png")
                .thumbnail(0.5f)
                .crossFade()
                .error(getResources().getDrawable(R.drawable.ic_sun))
                .into(day_img);*/

        Glide.with(this).load("http://192.168.43.73/Adeiye/img/downloadtwo.png")
                .thumbnail(0.5f)
                .crossFade()
                .error(getResources().getDrawable(R.drawable.ic_sun))
                .into(day_img);


        /*ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<UrlImage> call = apiService.getUrlImage();*/
        /*call.enqueue(new Callback<UrlImage>() {
            @Override
            public void onResponse(Call<UrlImage> call, Response<UrlImage> response) {
//                List<Movie> movies = response.body().getResults();
                if (response.isSuccessful())
                    Toast.makeText(getContext(), response.body().getUrl(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<UrlImage> call, Throwable t) {
                // Log error here since request failed
//                Log.e(TAG, t.toString());
                Toast.makeText(getContext(), "failar", Toast.LENGTH_SHORT).show();
            }
        });*/

        // Tag used to cancel the request
        String tag_json_arry = "json_array_req";
        //        String url = "https://api.androidhive.info/volley/person_array.json";
        String url = "http://192.168.43.73/get.php";

        final ProgressDialog pDialog = new ProgressDialog(getContext());
        pDialog.setMessage("Loading...");
        pDialog.show();

        JsonArrayRequest req = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        pDialog.hide();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                pDialog.hide();
            }
        });

        // Adding request to request queue
        MyApplication.getInstance().addToRequestQueue(req, tag_json_arry);


        rotateImage();

        return view;
    }

    private void rotateImage() {
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
                image_img.clearAnimation();
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
                image_img.startAnimation(rotate);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
//                imageView.clearAnimation();
//                imageView.startAnimation(rotate);
            }
        });
        image_img.startAnimation(rotate);
    }

    private void initView() {
        sharedPreferencesTools = new SharedPreferencesTools(getActivity().getBaseContext());
        setZekrRooz();
    }

    private void setZekrRooz() {
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);

        SimpleDateFormat df = new SimpleDateFormat("dd MMMM yyyy");
        String formattedDate = df.format(calendar.getTime());

        SolarCalendar solarCalendar = new SolarCalendar();
//        String date1 = solarCalendar.getCurrentShamsidate();
//        Toast.makeText(getContext() , date1 , Toast.LENGTH_SHORT).show();

        switch (day) {
            case Calendar.SATURDAY:
                zekr_rooz_txt.setText(getResources().getString(R.string.zekr_saturday));
                date.setText("شنبه");
                date_milady.setText(formattedDate);
                date_month.setText(solarCalendar.date + " " + solarCalendar.strMonth);
                date_year_txt.setText("" + solarCalendar.date);
                break;
            case Calendar.SUNDAY:
                // Current day is Sunday
                zekr_rooz_txt.setText(getResources().getString(R.string.zekr_sunday));
                date.setText("یکشنبه");
                date_milady.setText(formattedDate);
                date_month.setText(solarCalendar.date + " " + solarCalendar.strMonth);
                date_year_txt.setText("" + solarCalendar.date);
                break;
            case Calendar.MONDAY:
                // Current day is Monday
                zekr_rooz_txt.setText(getResources().getString(R.string.zekr_monday));
                date.setText("دوشنبه");
                date_milady.setText(formattedDate);
                date_month.setText(solarCalendar.date + " " + solarCalendar.strMonth);
                date_year_txt.setText("" + solarCalendar.date);
                break;
            case Calendar.TUESDAY:
                zekr_rooz_txt.setText(getResources().getString(R.string.zekr_thurzday));
                date.setText("سه شنبه");
                date_milady.setText(formattedDate);
                date_month.setText(solarCalendar.date + " " + solarCalendar.strMonth);
                date_year_txt.setText("" + solarCalendar.date);
                break;
            case Calendar.WEDNESDAY:
                zekr_rooz_txt.setText(getResources().getString(R.string.zekr_wednesday));
                date.append("چهارشنبه");
                date_milady.setText(formattedDate);
                date_month.setText(solarCalendar.date + " " + solarCalendar.strMonth);
                date_year_txt.setText("" + solarCalendar.date);
                break;
            case Calendar.THURSDAY:
                zekr_rooz_txt.setText(getResources().getString(R.string.zekr_tuesday));
                date.setText("پنج شنبه");
                date_milady.setText(formattedDate);
                date_month.setText(solarCalendar.date + " " + solarCalendar.strMonth);
                date_year_txt.setText("" + solarCalendar.date);
                break;
            case Calendar.FRIDAY:
                zekr_rooz_txt.setText(getResources().getString(R.string.zekr_friday));
                date.setText("جمعه");
                date_milady.setText(formattedDate);
                date_month.setText(solarCalendar.date + " " + solarCalendar.strMonth);
                date_year_txt.setText("" + solarCalendar.date);
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.faraj_btn:

                dialogCustom = new DialogCustom(getActivity());
                dialogCustom.show();

                saveDoaFaraj();
                break;
            case R.id.komeil_btn:
                Intent intent = new Intent(getActivity(), MapsActivity.class);
                startActivity(intent);
                break;
        }
    }

    private void saveDoaFaraj() {

        if (getAll("Faraj").size() == 0) {


            AyeDb ayeDb = new AyeDb();
            ayeDb.matn = "بِسْمِ اللهِ الرَّحْمنِ الرَّحِیمِ";
            ayeDb.mani = "به نام خداوند بخشنده مهربان";
            ayeDb.nameDoa = "Faraj";
            ayeDb.save();

            AyeDb ayeDb1 = new AyeDb();
            ayeDb1.matn = "اِلهى عَظُمَ الْبَلاَّءُ وَبَرِحَ الْخَفاَّءُ وَانْكَشَفَ الْغِطاَّءُ وَانْقَطَعَ الرَّجاَّءُِ";
            ayeDb1.mani = "خدايا بلاء عظيم گشته و درون آشكار شد و پرده از كارها برداشته شد و اميد قطع شد";
            ayeDb1.nameDoa = "Faraj";
            ayeDb1.save();

            AyeDb ayeDb2 = new AyeDb();
            ayeDb2.matn = "وَضاقَتِ الاْرْضُ وَمُنِعَتِ السَّماَّءُ واَنْتَ الْمُسْتَعانُ وَاِلَيْكَِ";
            ayeDb2.mani = "و زمين تنگ شد و از ريزش رحمت آسمان جلوگيرى شد و تويى ياور و شكوه بسوى تو است";
            ayeDb2.nameDoa = "Faraj";
            ayeDb2.save();

            AyeDb ayeDb3 = new AyeDb();
            ayeDb3.matn = "الْمُشْتَكى وَعَلَيْكَ الْمُعَوَّلُ فِى الشِّدَّةِ وَالرَّخاَّءِ اَللّهُمَّ صَلِّ عَلىِ";
            ayeDb3.mani = "و اعتماد و تكيه ما چه در سختى و چه در آسانى بر تو است خدايا درود فرست بر";
            ayeDb3.nameDoa = "Faraj";
            ayeDb3.save();

            AyeDb ayeDb4 = new AyeDb();
            ayeDb4.matn = "مُحَمَّدٍ وَ الِ مُحَمَّدٍ اُولِى الاْمْرِ الَّذينَ فَرَضْتَ عَلَيْنا طاعَتَهُمْ";
            ayeDb4.mani = "محمد و آل محمد آن زمامدارانى كه پيرويشان را بر ما واجب كردى و بدين سبب مقام";
            ayeDb4.nameDoa = "Faraj";
            ayeDb4.save();

            AyeDb ayeDb5 = new AyeDb();
            ayeDb5.matn = "وَعَرَّفْتَنا بِذلِكَ مَنْزِلَتَهُمْ فَفَرِّجْ عَنا بِحَقِّهِمْ فَرَجاً عاجِلا قَريباً كَلَمْحِِ";
            ayeDb5.mani = "و منزلتشان را به ما شناساندى به حق ايشان به ما گشايشى ده فورى و نزديك مانند";
            ayeDb5.nameDoa = "Faraj";
            ayeDb5.save();

            AyeDb ayeDb6 = new AyeDb();
            ayeDb6.matn = "الْبَصَرِ اَوْ هُوَ اَقْرَبُ يا مُحَمَّدُ يا عَلِىُّ يا عَلِىُّ يا مُحَمَّدُ اِكْفِيانىِ";
            ayeDb6.mani = "چشم بر هم زدن يا نزديكتر اى محمد اى على اى على اى محمد مرا كفايت كنيد";
            ayeDb6.nameDoa = "Faraj";
            ayeDb6.save();

            AyeDb ayeDb7 = new AyeDb();
            ayeDb7.matn = "فَاِنَّكُما كافِيانِ وَانْصُرانى فَاِنَّكُما ناصِرانِ يا مَوْلانا يا صاحِبَ";
            ayeDb7.mani = "كه شماييد كفايت كننده ام و مرا يارى كنيد كه شماييد ياور من اى سرور ما اى صاحب";
            ayeDb7.nameDoa = "Faraj";
            ayeDb7.save();

            AyeDb ayeDb8 = new AyeDb();
            ayeDb8.matn = "الزَّمانِ الْغَوْثَ الْغَوْثَ الْغَوْثَ اَدْرِكْنى اَدْرِكْنى اَدْرِكْنى السّاعَةَ";
            ayeDb8.mani = "الزمان فرياد، فرياد، فرياد، درياب مرا درياب مرا درياب مرا همين ساعت";
            ayeDb8.nameDoa = "Faraj";
            ayeDb8.save();

            AyeDb ayeDb9 = new AyeDb();
            ayeDb9.matn = "السّاعَةَ السّاعَةَ الْعَجَلَ الْعَجَلَ الْعَجَلَ يا اَرْحَمَ الرّاحِمينَ بِحَقِّ";
            ayeDb9.mani = "همين ساعت هم اكنون زود زود زود اى خدا اى مهربانترين مهربانان به حق";
            ayeDb9.nameDoa = "Faraj";
            ayeDb9.save();

            AyeDb ayeDb10 = new AyeDb();
            ayeDb10.matn = "مُحَمَّدٍ وَآلِهِ الطّاهِرينَِ";
            ayeDb10.mani = "محمد و آل پاكيزه اش";
            ayeDb10.nameDoa = "Faraj";
            ayeDb10.save();
        }

        Intent intent = new Intent(getActivity(), FarajActivity.class);
        startActivity(intent);

        dialogCustom.dismiss();

    }

    public static List<AyeDb> getAll(String name) {
        return new Select()
                .from(AyeDb.class)
                .where("NameDoa = ?", name)
                .execute();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!sharedPreferencesTools.getColorAround().equals(""))
            switch (sharedPreferencesTools.getColorAround()) {
                case "red":
                    faraj_btn.setCircle_border_color(getResources().getColor(R.color.colorAccent));
                    break;
                case "blue":
                    faraj_btn.setCircle_border_color(getResources().getColor(R.color.abirooshan));
                    break;
                case "green":
                    faraj_btn.setCircle_border_color(getResources().getColor(R.color.green));
                    break;
                case "yellow":
                    faraj_btn.setCircle_border_color(getResources().getColor(R.color.yellow));
                    break;
            }

        if (!sharedPreferencesTools.getColorApp().equals(""))
            switch (sharedPreferencesTools.getColorApp()) {
                case "narenji":
                    faraj_btn.setCircle_color(ContextCompat.getColor(getActivity(), R.color.narengi));
                    break;
                case "ghermez":
                    faraj_btn.setCircle_color(ContextCompat.getColor(getActivity(), R.color.ghermez));
                    break;
                case "sabz":
                    faraj_btn.setCircle_color(ContextCompat.getColor(getActivity(), R.color.sabz));
                    break;
                case "blueprimary":
                    faraj_btn.setCircle_color(ContextCompat.getColor(getActivity(), R.color.colorPrimary));
                    break;
            }
    }
}