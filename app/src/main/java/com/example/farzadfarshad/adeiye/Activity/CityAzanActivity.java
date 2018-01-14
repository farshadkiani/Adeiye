package com.example.farzadfarshad.adeiye.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.activeandroid.query.Select;
import com.example.farzadfarshad.adeiye.Database.AyeDb;
import com.example.farzadfarshad.adeiye.Database.OghatDb;
import com.example.farzadfarshad.adeiye.MainActivity;
import com.example.farzadfarshad.adeiye.Model.AzanModel.Example;
import com.example.farzadfarshad.adeiye.R;
import com.example.farzadfarshad.adeiye.RetrofitManager.ServerAPI;
import com.example.farzadfarshad.adeiye.RetrofitManager.ServerAPIAzan;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CityAzanActivity extends AppCompatActivity implements View.OnClickListener {


    @BindView(R.id.ostan_spn)
    Spinner ostan_spn;

    @BindView(R.id.city_spn)
    Spinner city_spn;

    @BindView(R.id.save_btn)
    Button save_btn;

    @BindView(R.id.include_view)
    View include_view;

    @BindView(R.id.sob_txt)
    TextView sob_txt;

    @BindView(R.id.toolo_txt)
    TextView toolo_txt;

    @BindView(R.id.zohr_txt)
    TextView zohr_txt;

    @BindView(R.id.asr_txt)
    TextView asr_txt;

    @BindView(R.id.ghoroob_txt)
    TextView ghoroob_txt;

    @BindView(R.id.maghreb_txt)
    TextView maghreb_txt;

    @BindView(R.id.nimeshab_txt)
    TextView nimeshab_txt;

    @BindView(R.id.esha_txt)
    TextView esha_txt;

    @BindView(R.id.ofogh_txt)
    TextView ofogh_txt;

    @BindView(R.id.pakhsh_txt)
    TextView pakhsh_txt;

    @BindView(R.id.play_img)
    ImageView play_img;


    @BindView(R.id.back_img)
    ImageView back_img;

    @BindView(R.id.image_azan)
    ImageView image_azan;

    String[] SPINNERLIST;

    String[] SPINNERLIST_city;

    String[] splitted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_azan);

        ButterKnife.bind(this);
        save_btn.setOnClickListener(this);
        back_img.setOnClickListener(this);
        image_azan.setOnClickListener(this);
        pakhsh_txt.setOnClickListener(this);

        initView();
    }

    private void initView() {
        play_img.setVisibility(View.GONE);
        SPINNERLIST = getResources().getStringArray(R.array.province);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, SPINNERLIST);
        ostan_spn.setAdapter(arrayAdapter);

        ostan_spn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                setCitySpinner(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        getDate();

        setViewOghat();

    }

    private void getDate() {
        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat df = new SimpleDateFormat("DD MM yyyy");
        String formattedDate = df.format(calendar.getTime());

        splitted = formattedDate.split("\\s+");
    }

    private void setViewOghat() {
        if (getAll().size() != 0) {
            include_view.setVisibility(View.VISIBLE);
            ofogh_txt.setText(getAll().get(0).getCity());
            setOghatKadr();
        }
    }

    private void setOghatKadr() {
        sob_txt.setText("| " + getAll(splitted[0]).get(0).getFajr());
        toolo_txt.setText("| " + getAll(splitted[0]).get(0).getSunrise());
        zohr_txt.setText("| " + getAll(splitted[0]).get(0).getDhuhr());
        asr_txt.setText("| " + getAll(splitted[0]).get(0).getAsr());
        ghoroob_txt.setText("| " + getAll(splitted[0]).get(0).getSunset());
        maghreb_txt.setText("| " + getAll(splitted[0]).get(0).getMaghreb());
        esha_txt.setText("| " + getAll(splitted[0]).get(0).getIsha());
        nimeshab_txt.setText(" " + getAll(splitted[0]).get(0).getMidnight());
    }


    private void setCitySpinner(int position) {
        switch (position) {
            case 0:
                SPINNERLIST_city = getResources().getStringArray(R.array.city0);
                break;
            case 1:
                SPINNERLIST_city = getResources().getStringArray(R.array.city1);
                break;
            case 2:
                SPINNERLIST_city = getResources().getStringArray(R.array.city2);
                break;
            case 3:
                SPINNERLIST_city = getResources().getStringArray(R.array.city3);
                break;
            case 4:
                SPINNERLIST_city = getResources().getStringArray(R.array.city4);
                break;
            case 5:
                SPINNERLIST_city = getResources().getStringArray(R.array.city5);
                break;
            case 6:
                SPINNERLIST_city = getResources().getStringArray(R.array.city6);
                break;
            case 7:
                SPINNERLIST_city = getResources().getStringArray(R.array.city7);
                break;
            case 8:
                SPINNERLIST_city = getResources().getStringArray(R.array.city8);
                break;
            case 9:
                SPINNERLIST_city = getResources().getStringArray(R.array.city9);
                break;
            case 10:
                SPINNERLIST_city = getResources().getStringArray(R.array.city10);
                break;
            case 11:
                SPINNERLIST_city = getResources().getStringArray(R.array.city11);
                break;
            case 12:
                SPINNERLIST_city = getResources().getStringArray(R.array.city12);
                break;
            case 13:
                SPINNERLIST_city = getResources().getStringArray(R.array.city13);
                break;
            case 14:
                SPINNERLIST_city = getResources().getStringArray(R.array.city14);
                break;
            case 15:
                SPINNERLIST_city = getResources().getStringArray(R.array.city15);
                break;
            case 16:
                SPINNERLIST_city = getResources().getStringArray(R.array.city16);
                break;
            case 17:
                SPINNERLIST_city = getResources().getStringArray(R.array.city17);
                break;
            case 18:
                SPINNERLIST_city = getResources().getStringArray(R.array.city18);
                break;
            case 19:
                SPINNERLIST_city = getResources().getStringArray(R.array.city19);
                break;
            case 20:
                SPINNERLIST_city = getResources().getStringArray(R.array.city20);
                break;
            case 21:
                SPINNERLIST_city = getResources().getStringArray(R.array.city21);
                break;
            case 22:
                SPINNERLIST_city = getResources().getStringArray(R.array.city22);
                break;
            case 23:
                SPINNERLIST_city = getResources().getStringArray(R.array.city23);
                break;
            case 24:
                SPINNERLIST_city = getResources().getStringArray(R.array.city24);
                break;
            case 25:
                SPINNERLIST_city = getResources().getStringArray(R.array.city25);
                break;
            case 26:
                SPINNERLIST_city = getResources().getStringArray(R.array.city26);
                break;
            case 27:
                SPINNERLIST_city = getResources().getStringArray(R.array.city27);
                break;
            case 28:
                SPINNERLIST_city = getResources().getStringArray(R.array.city28);
                break;
            case 29:
                SPINNERLIST_city = getResources().getStringArray(R.array.city29);
                break;
            case 30:
                SPINNERLIST_city = getResources().getStringArray(R.array.city30);
                break;
        }

        ArrayAdapter arrayAdapter_city = new ArrayAdapter<String>(getBaseContext()
                , android.R.layout.simple_dropdown_item_1line, SPINNERLIST_city);
        city_spn.setAdapter(arrayAdapter_city);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.save_btn:

                if (getAll().size() != 0) {
                    if (splitted[1] != getAll().get(0).getMonth()) {
                        OghatDb.delete(OghatDb.class, 1);
                        getdata();
                    } else if (!String.valueOf(ostan_spn.getSelectedItem()).equals(getAll().get(0).getProvince())) {
                        OghatDb.delete(OghatDb.class, 1);
                        getdata();
                    }
                } else
                    getdata();
                break;

            case R.id.back_img:
                onBackPressed();
                break;

            case R.id.image_azan:
                goToPakhshAzan();
                break;

            case R.id.pakhsh_txt:
                goToPakhshAzan();
                break;
        }
    }

    private void goToPakhshAzan() {
        Intent intent = new Intent(this, PakhshAzan.class);
        startActivity(intent);
    }

    private void getdata() {
        final ProgressDialog pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.show();
        ServerAPIAzan api = ServerAPIAzan.retrofit.create(ServerAPIAzan.class);
        api.downlloadAzan("calendarByCity?city=" + city_spn.getSelectedItem() + "&country=Iran&method=0&month=" + splitted[1]
                + "&year=" + splitted[2])
                .enqueue(new Callback<Example>() {
                    @Override
                    public void onResponse(Call<Example> call, Response<Example> response) {
                        try {
                            pDialog.dismiss();
                            if (response.isSuccessful()) {
                                for (int i = 0; i < response.body().getData().size(); i++) {
                                    OghatDb oghatDb = new OghatDb();
                                    oghatDb.fajr = response.body().getData().get(i).getTimings().getFajr().split("\\s+")[0];
                                    oghatDb.sunrise = response.body().getData().get(i).getTimings().getSunrise().split("\\s+")[0];
                                    oghatDb.dhuhr = response.body().getData().get(i).getTimings().getDhuhr().split("\\s+")[0];
                                    oghatDb.asr = response.body().getData().get(i).getTimings().getAsr().split("\\s+")[0];
                                    oghatDb.sunset = response.body().getData().get(i).getTimings().getSunset().split("\\s+")[0];
                                    oghatDb.maghreb = response.body().getData().get(i).getTimings().getMaghrib().split("\\s+")[0];
                                    oghatDb.isha = response.body().getData().get(i).getTimings().getIsha().split("\\s+")[0];
                                    oghatDb.imask = response.body().getData().get(i).getTimings().getImsak().split("\\s+")[0];
                                    oghatDb.midnight = response.body().getData().get(i).getTimings().getMidnight().split("\\s+")[0];
                                    oghatDb.month = splitted[0];
                                    oghatDb.day = response.body().getData().get(i).getDate().getReadable().split("\\s+")[0];
                                    oghatDb.province = String.valueOf(String.valueOf(ostan_spn.getSelectedItem()));
                                    oghatDb.city = String.valueOf(city_spn.getSelectedItem());

                                    oghatDb.save();
                                }
                                Toast.makeText(CityAzanActivity.this, getResources().getString(R.string.success_getdata_namaz)
                                        , Toast.LENGTH_SHORT).show();
                                setViewOghat();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call<Example> call, Throwable t) {
                        pDialog.dismiss();
                        Toast.makeText(CityAzanActivity.this, getResources().getString(R.string.error_server_namaz)
                                , Toast.LENGTH_SHORT).show();
                    }
                });
    }


    public static List<OghatDb> getAll() {
        return new Select()
                .from(OghatDb.class)
                .execute();
    }

    public static List<OghatDb> getAll(String day) {
        return new Select()
                .from(OghatDb.class)
                .where("Day = ?", day)
                .execute();
    }


}