package com.example.coronavirus;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.coronavirus.api.ApiRequestData;
import com.example.coronavirus.api.LaporanRetrosAPI;
import com.example.coronavirus.model.LaporanModel;
import com.example.coronavirus.model.ResponLaporanModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailLaporanActivity extends AppCompatActivity {


    private List<LaporanModel> laporanModels = new ArrayList<>();

    private TextView tv_nama_detail;
    private TextView tv_telpon_detail;
    private TextView tv_tanggal_detail;
    private TextView tv_jam_detaul;
    private TextView tv_alamat_detail;
    private TextView tv_deskripsi_detail;
    private TextView tv_status_detail;

    private String id;
    private String nama;
    private String telpon;
    private String deskripsi;
    private String tanggal;
    private String jam;
    private String alamat;
    private String latitud;
    private String longitud;
    private String picture;
    private String status;

    private ImageView image_laporan;


    private View dialogView;
    private ProgressDialog pd;

    private Button btn_proses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_laporan);

        tv_nama_detail = findViewById(R.id.tv_nama_detail);
        tv_telpon_detail = findViewById(R.id.tv_telpon_detail);
        tv_tanggal_detail = findViewById(R.id.tv_tanggal_detail);
        tv_jam_detaul = findViewById(R.id.tv_jam_detaul);
        tv_alamat_detail = findViewById(R.id.tv_alamat_detail);
        tv_deskripsi_detail = findViewById(R.id.tv_deskripsi_detail);
        tv_status_detail = findViewById(R.id.tv_status_detail);

        image_laporan = findViewById(R.id.image_laporan);

        btn_proses = findViewById(R.id.btn_proses);

        btn_proses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadUpdate();
            }
        });

        pd = new ProgressDialog(this);

        image_laporan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showImageDialog();
            }
        });

        readDataSingle();

    }

    private void loadUpdate() {

        String sendStatus ;

        if (status.equals("1")){
            sendStatus = "0";
        } else {
            sendStatus = "1";
        }

        ApiRequestData apiRequestData = LaporanRetrosAPI.getClient().create(ApiRequestData.class);
        Call<ResponLaporanModel> responLaporanModelCall = apiRequestData.updateDataLaporan(id,sendStatus);
        responLaporanModelCall.enqueue(new Callback<ResponLaporanModel>() {
            @Override
            public void onResponse(Call<ResponLaporanModel> call, Response<ResponLaporanModel> response) {
                if (response.isSuccessful()){
                    Log.d("UPDATE", "pesan : "+response.body().getPesan());
                    Toast.makeText(DetailLaporanActivity.this, "Berhasil Update Laporan", Toast.LENGTH_SHORT).show();
                    readDataSingle();
                } else {
                    Toast.makeText(DetailLaporanActivity.this, "GAGAL", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponLaporanModel> call, Throwable t) {
                Log.d("Error", "Update : "+t);
            }
        });

    }

    private void showImageDialog() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(DetailLaporanActivity.this);
        LayoutInflater inflater = getLayoutInflater();
        dialogView = inflater.inflate(R.layout.llayout_image_zoom, null);
        dialog.setView(dialogView);
        dialog.setCancelable(true);

        ImageView imageView = dialogView.findViewById(R.id.img_show);
        Picasso.with(DetailLaporanActivity.this).load(picture).into(imageView);

        dialog.show();
    }

    private void initLaporan(List<LaporanModel> models) {

        for (int a = 0; a < laporanModels.size(); a++) {

            id = laporanModels.get(a).getId();
            nama = laporanModels.get(a).getName();
            telpon = laporanModels.get(a).getTelpon();
            deskripsi = laporanModels.get(a).getDeskripsi();
            tanggal = laporanModels.get(a).getTanggal();
            jam = laporanModels.get(a).getJam();
            alamat = laporanModels.get(a).getAlamat();
            latitud = laporanModels.get(a).getLatitude();
            longitud = laporanModels.get(a).getLongitude();
            picture = laporanModels.get(a).getPicture();
            status = laporanModels.get(a).getStatus();

            Picasso.with(DetailLaporanActivity.this).load(picture).into(image_laporan);

            tv_nama_detail.setText(nama);
            tv_telpon_detail.setText(telpon);
            tv_deskripsi_detail.setText(deskripsi);
            tv_tanggal_detail.setText(tanggal);
            tv_jam_detaul.setText(jam);
            tv_alamat_detail.setText(alamat);
            if (status.equals("0")) {
                tv_status_detail.setText("Belum Diproses");
                btn_proses.setBackgroundResource(R.drawable.bg_btn_lapor);
                btn_proses.setTextColor(this.getResources().getColor(R.color.putih));
                btn_proses.setText("Selesai");
            } else if (status.equals("1")) {
                tv_status_detail.setText("Sudah Diproses");
                btn_proses.setText("Batal");
                btn_proses.setBackgroundResource(R.drawable.bg_btn_batal);
                btn_proses.setTextColor(this.getResources().getColor(R.color.orange));
            }

        }

    }

    private void readDataSingle() {
        Bundle bundle = getIntent().getExtras();
        String id = bundle.getString("sendID");

        ApiRequestData apiRequestData = LaporanRetrosAPI.getClient().create(ApiRequestData.class);
        Call<ResponLaporanModel> laporanModelCall = apiRequestData.getDataLaporanSingle(id);
        laporanModelCall.enqueue(new Callback<ResponLaporanModel>() {
            @Override
            public void onResponse(Call<ResponLaporanModel> call, Response<ResponLaporanModel> response) {
                if (response.isSuccessful()) {

                    laporanModels = response.body().getResultDataLaporan();
                    Log.d("DetailSUcces", "Message : " + response.body().getKode());
                    initLaporan(laporanModels);
                } else {
                    Toast.makeText(DetailLaporanActivity.this, "Gagal", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponLaporanModel> call, Throwable t) {
                Log.d("Detail", "Respon : " + t);
            }
        });

    }


}
