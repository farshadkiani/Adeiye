package com.example.farzadfarshad.adeiye.PdfShow;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.farzadfarshad.adeiye.Activity.FarajActivity;
import com.example.farzadfarshad.adeiye.DialogCustom;
import com.example.farzadfarshad.adeiye.Model.MovieModel;
import com.example.farzadfarshad.adeiye.Model.MovieModelDetail;
import com.example.farzadfarshad.adeiye.Movie.MovieAdapter;
import com.example.farzadfarshad.adeiye.Movie.PermissionHandler;
import com.example.farzadfarshad.adeiye.MyApplication;
import com.example.farzadfarshad.adeiye.R;
import com.example.farzadfarshad.adeiye.RetrofitManager.ApiClient;
import com.example.farzadfarshad.adeiye.RetrofitManager.ApiInterface;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PdfShowFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PdfShowFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PdfShowFragment extends Fragment implements PdfShowAdapter.ItemClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    @BindView(R.id.recycler_pdf)
    RecyclerView recycler_pdf;

    PdfShowAdapter pdfShowAdapter;

    ProgressDialog progressDialog;

    List<PdfModelDetail> pdfModelDetails;


    public PdfShowFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PdfShowFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PdfShowFragment newInstance(String param1, String param2) {
        PdfShowFragment fragment = new PdfShowFragment();
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
        View view = inflater.inflate(R.layout.fragment_pdf_show, container, false);
        ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView() {
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage(getResources().getString(R.string.get_data));
        progressDialog.show();

        recycler_pdf.setLayoutManager(new GridLayoutManager(getContext(), 2));


        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<PdfModel> call = apiService.getPdf();

        call.enqueue(new Callback<PdfModel>() {
            @Override
            public void onResponse(Call<PdfModel> call, Response<PdfModel> response) {
                progressDialog.dismiss();
                if (response.isSuccessful() &&
                        Integer.valueOf(response.body().getStatus()) == 200) {

                    initRecycler(response.body().getContents());

                }
            }

            @Override
            public void onFailure(Call<PdfModel> call, Throwable t) {

            }
        });
    }

    public void initRecycler(List<PdfModelDetail> contents) {
        this.pdfModelDetails = contents;
        pdfShowAdapter = new PdfShowAdapter(getContext(), contents, MyApplication.getInstance().getGlide());
        recycler_pdf.setAdapter(pdfShowAdapter);
        pdfShowAdapter.setClickListener(this);
    }

    @Override
    public void onItemClick(View view, final int position) {

        String path = getExternalFilesDir()
                + File.separator + "Adeiye" + File.separator + pdfModelDetails.get(position).getId() + ".Pdf";

        File futureStudioIconFile = new File(path);


        if (futureStudioIconFile.exists()) {
            Intent intent = new Intent(getContext() , PdfShowActivtiy.class);
            intent.putExtra("path", path);
            startActivity(intent);
        } else {
            final DialogCustom dialogCustom = new DialogCustom(getContext());
            dialogCustom.show();
            dialogCustom.setmyClick(new DialogCustom.myOnClickListener() {
                @Override
                public void onButtonClick() {
                    checkPermisson(pdfModelDetails.get(position).getFile(), dialogCustom
                            , pdfModelDetails.get(position).getId());
//                downloadFile(pdfModelDetails.get(position).getFile(), dialogCustom);
                }
            });
        }

    }

    private void downloadFile(String url, final int id) {
        final ProgressDialog pDialog = new ProgressDialog(getContext());
        pDialog.setMessage(getResources().getString(R.string.download_file_doa));
        pDialog.show();
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseBody> call = apiService.downlload(url);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    pDialog.dismiss();
                    boolean writtenToDisk = writeResponseBodyToDisk(response.body(), id);
                    if (writtenToDisk)
                        Toast.makeText(getContext(), "ok", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(getContext(), "no", Toast.LENGTH_SHORT).show();
                } else {
                    Log.d("salam", "server contact failed");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("salam", "error");
            }
        });
    }

    private boolean writeResponseBodyToDisk(ResponseBody body, int id) {
        try {
            // todo change the file location/name according to your needs
            File futureStudioIconFile = new File(getExternalFilesDir()
                    + File.separator + "Adeiye" + File.separator + id + ".Pdf");

            InputStream inputStream = null;
            OutputStream outputStream = null;


            try {
                byte[] fileReader = new byte[4096];

                long fileSize = body.contentLength();
                long fileSizeDownloaded = 0;

                inputStream = body.byteStream();
                outputStream = new FileOutputStream(futureStudioIconFile);

                while (true) {
                    int read = inputStream.read(fileReader);

                    if (read == -1) {
                        break;
                    }

                    outputStream.write(fileReader, 0, read);

                    fileSizeDownloaded += read;

                    Log.d("salam", "file download: " + fileSizeDownloaded + " of " + fileSize);
                }

                outputStream.flush();

                return true;
            } catch (IOException e) {
                return false;
            } finally {
                if (inputStream != null) {
                    inputStream.close();
                }

                if (outputStream != null) {
                    outputStream.close();
                }
            }
        } catch (IOException e) {
            return false;
        }
    }

    private File getExternalFilesDir() {
        return Environment.getExternalStorageDirectory();
    }


    public void checkPermisson(final String url, final DialogCustom dialogCustom, final int id) {
        String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
        new PermissionHandler().checkPermission(getActivity(), permissions, new PermissionHandler.OnPermissionResponse() {
            @Override
            public void onPermissionGranted() {
                dialogCustom.dismiss();
                downloadFile(url, id);
            }

            @Override
            public void onPermissionDenied() {
                Toast.makeText(getContext(), getResources().getString(R.string.take_permission)
                        , Toast.LENGTH_SHORT).show();
            }
        });

    }
}
