package com.example.farzadfarshad.adeiye.Movie;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.farzadfarshad.adeiye.ContextModule;
import com.example.farzadfarshad.adeiye.Model.MovieModel;
import com.example.farzadfarshad.adeiye.Model.MovieModelDetail;
import com.example.farzadfarshad.adeiye.R;
import com.example.farzadfarshad.adeiye.RetrofitManager.ApiClient;
import com.example.farzadfarshad.adeiye.RetrofitManager.ApiInterface;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MovieFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MovieFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MovieFragment extends Fragment implements MovieAdapter.ItemClickListener, View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    @Inject
    MovieAdapter adapter;

    @Inject
    ApiInterface apiService;

    @Inject
    ProgressDialog pDialog;

    List<MovieModelDetail> movieModelDetailList;



    @BindView(R.id.search_etx)
    EditText search_etx;

    @BindView(R.id.rvNumbers)
    RecyclerView recyclerView;


    public MovieFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_movie, container, false);

        ButterKnife.bind(this, view);
       /* final ProgressDialog pDialog = new ProgressDialog(getContext());
        pDialog.setMessage(getResources().getString(R.string.get_data));
        pDialog.show();*/

        MovieFragmentComponent component = DaggerMovieFragmentComponent.builder()
                .contextModule(new ContextModule(getContext()))
                .build();

        component.injectMovieComponent(this);


        pDialog.setMessage(getResources().getString(R.string.get_data));
        pDialog.show();


        // set up the RecyclerView
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
//        adapter = new MovieAdapter();
        adapter.setClickListener(this);

        search_etx.setOnClickListener(this);


        /*ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);*/

        Call<MovieModel> call = apiService.getMovies();

        call.enqueue(new Callback<MovieModel>() {
            @Override
            public void onResponse(Call<MovieModel> call, Response<MovieModel> response) {
                pDialog.dismiss();
                if (response.isSuccessful() &&
                        Integer.valueOf(response.body().getStatus()) == 200) {

                    initView(response.body().getContents());

                }
            }

            @Override
            public void onFailure(Call<MovieModel> call, Throwable t) {

            }
        });

        return view;
    }

    private void initView(List<MovieModelDetail> contents) {
//        adapter = new MovieAdapter(getContext(), contents , MyApplication.getInstance().getGlide());
        adapter.SwapData(contents);
        adapter.setClickListener(this);
        movieModelDetailList = contents;

        recyclerView.setAdapter(adapter);
    }


    @Override
    public void onItemClick(View view, int position) {

//        Toast.makeText(getContext(), movieModelDetailList.get(position).getTitle(), Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(getContext(), MovieFilmActivity.class);
        intent.putExtra("url", movieModelDetailList.get(position).getVideo());
        startActivity(intent);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.search_etx) {
            search_etx.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    final int DRAWABLE_LEFT = 0;
                    final int DRAWABLE_TOP = 1;
                    final int DRAWABLE_RIGHT = 2;
                    final int DRAWABLE_BOTTOM = 3;
                    if (event.getAction() == MotionEvent.ACTION_UP) {

                        if (event.getRawX() >=
                                (search_etx.getRight() - search_etx.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {

                            Toast.makeText(getContext(), "salam", Toast.LENGTH_SHORT).show();

                            return true;
                        }
                        if (event.getRawX() <=
                                (search_etx.getLeft() + 2*(search_etx.getCompoundDrawables()[DRAWABLE_LEFT].getBounds().width()))) {

                            Toast.makeText(getContext(), "salam farshad", Toast.LENGTH_SHORT).show();

                            return true;
                        }


                    }
                    return true;
                }
            });
        }
    }
}
