package com.example.coronavirus;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.coronavirus.api.ApiRequestData;
import com.example.coronavirus.api.IndonesiaRetrosAPI;
import com.example.coronavirus.api.RetroServerAPI;
import com.example.coronavirus.model.IndonesiaModel;
import com.example.coronavirus.model.ResponModel;
import com.razerdp.widget.animatedpieview.AnimatedPieView;
import com.razerdp.widget.animatedpieview.AnimatedPieViewConfig;
import com.razerdp.widget.animatedpieview.data.SimplePieInfo;

import java.text.NumberFormat;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private AnimatedPieView pieView;
    private View dialogView;
    ImageView btn_maps,btn_about;
    private LinearLayout ll_dunia;
    private LinearLayout ll_hospital;
    private LinearLayout ll_indo;
    private ProgressBar progesBar;

    TextView tv_recovered, tv_confirmed, tv_deaths, tv_total, tv_confermed_negara88,tvweb, save;
    TextView tv_lastUpdate, tv_confermed_negara6, tv_recovered_negara2, tv_deaths_negara;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvweb = findViewById(R.id.tvweb);
        save = findViewById(R.id.save);
        ll_dunia = findViewById(R.id.ll_dunia);
        btn_about = findViewById(R.id.btn_about);
        progesBar = findViewById(R.id.progesBar);
        tv_confermed_negara88 = findViewById(R.id.tv_confermed_negara88);
        ll_hospital = findViewById(R.id.ll_hospital);
        ll_indo = findViewById(R.id.ll_indo);
        pieView = findViewById(R.id.pieView);
        tv_confirmed = findViewById(R.id.tv_confirmed);
        tv_deaths = findViewById(R.id.tv_deaths);
        tv_recovered = findViewById(R.id.tv_recovered);
        tv_total = findViewById(R.id.tv_total);
        tv_lastUpdate = findViewById(R.id.tv_lastUpdate);
        tv_confermed_negara6 = findViewById(R.id.tv_confermed_negara6);
        tv_recovered_negara2 = findViewById(R.id.tv_recovered_negara2);
        tv_deaths_negara = findViewById(R.id.tv_deaths_negara);
        btn_maps = findViewById(R.id.btn_maps);
        progesBar.setVisibility(View.VISIBLE);


        save.setText("#StaySave");

        tvweb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, WebsiteActivity.class);
                startActivity(intent);
            }
        });

        btn_maps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, WebsiteActivity.class);
                startActivity(intent);
            }
        });

        btn_about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AboutActivity.class);
                startActivity(intent);
            }
        });

        ll_hospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
                alertDialog.setTitle("Maaf");
                alertDialog.setCancelable(true);
                LayoutInflater inflater = getLayoutInflater();
                dialogView = inflater.inflate(R.layout.dialog_rs_rujukan, null);
                alertDialog.setView(dialogView);
                alertDialog.setIcon(R.drawable.ic_lock_black_24dp);

                Button btn_unduh =dialogView.findViewById(R.id.btn_unduh);
                btn_unduh.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse("https://covid19.kemkes.go.id/download/KEPMENKES_169_2020_Penetapan_RS_Rujukan_Penanggulangan_Penyakit_Infeksi_Emerging_Tertentu.pdf.pdf"));
                        startActivity(intent);
                    }
                });

                alertDialog.show();
            }
        });


        tv_lastUpdate.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
        tv_lastUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DetailIndonesiaActivity.class);
                intent.putExtra("konfirmasi",tv_confermed_negara88.getText());
                intent.putExtra("sembuh",tv_recovered_negara2.getText());
                intent.putExtra("dirawat",tv_confermed_negara6.getText());
                intent.putExtra("meninggal",tv_deaths_negara.getText());
                startActivity(intent);

            }
        });

//        tv_source = findViewById(R.id.tv_source);


        ll_indo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MapsIndoActivity.class);
                startActivity(intent);
            }
        });

        ll_dunia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MapsActivity.class);
                startActivity(intent);
            }
        });

        if (isOnline()) {
            Loaddata();
            NegaraLoad();
        } else {
            try {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
                alertDialog.setTitle("Info");
                alertDialog.setCancelable(false);
                alertDialog.setMessage("Jaringan Tidak Ada, Cek Koneksi Internet dan Segera Kembali!");
                alertDialog.setIcon(android.R.drawable.ic_dialog_alert);

                alertDialog.show();
            } catch (Exception e) {
                Log.d("TAG", "Show Dialog: " + e.getMessage());
            }
        }

    }

    public boolean isOnline() {
        ConnectivityManager conMgr = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = conMgr.getActiveNetworkInfo();

        if (netInfo == null || !netInfo.isConnected() || !netInfo.isAvailable()) {
            Toast.makeText(MainActivity.this, "No Internet connection!", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    private void NegaraLoad() {

        ApiRequestData apiRequestData = IndonesiaRetrosAPI.getClient().create(ApiRequestData.class);
        Call<IndonesiaModel> indonesiaModelCall = apiRequestData.getDataIndo();
        indonesiaModelCall.enqueue(new Callback<IndonesiaModel>() {
            @Override
            public void onResponse(Call<IndonesiaModel> call, Response<IndonesiaModel> response) {
                if (response.isSuccessful()){
                    progesBar.setVisibility(View.GONE);
                    assert response.body() != null;
                    String mati = response.body().getMeninggal();
                    String sembuh = response.body().getSembuh();
                    String rawat = response.body().getPerawatan();
                    String jumlahkasus = response.body().getJumlahKasus();

                    tv_confermed_negara88.setText(jumlahkasus);
                    tv_confermed_negara6.setText(rawat);
                    tv_deaths_negara.setText(mati);
                    tv_recovered_negara2.setText(sembuh);

                }
            }

            @Override
            public void onFailure(Call<IndonesiaModel> call, Throwable t) {
                progesBar.setVisibility(View.GONE);
            }
        });

//        ApiRequestData apiRequestData = RetroServerAPI.getClient().create(ApiRequestData.class);
//        Call<NegaraResponseModel> negaraResponseModelCall = apiRequestData.getDataNegara();
//        negaraResponseModelCall.enqueue(new Callback<NegaraResponseModel>() {
//            @Override
//            public void onResponse(Call<NegaraResponseModel> call, Response<NegaraResponseModel> response) {
//                if (response.isSuccessful()) {
//                    NegaraResponseModel.NegaraConfermed negaraConfermed = response.body().getCountryRegion();
//                    NegaraResponseModel.NegaraRecofered negaraRecofered = response.body().getRecovered();
//                    NegaraResponseModel.NegaraDeaths negaraDeaths = response.body().getDeaths();
//                    String tgl = response.body().getLastUpdate();
//                    tv_lastUpdate.setText("Last Update : " + tgl.substring(0, 10) + " / " + tgl.substring(11, 16));
//
//                    long confir = negaraConfermed.getValue();
//                    long recof = negaraRecofered.getValue();
//                    long deat = negaraDeaths.getValue();
//
//                    tv_confermed_negara6.setText(String.valueOf(confir));
//                    tv_recovered_negara2.setText(String.valueOf(recof));
//                    tv_deaths_negara.setText(String.valueOf(deat));
//
//                }
//            }
//
//            @Override
//            public void onFailure(Call<NegaraResponseModel> call, Throwable t) {
//
//            }
//        });
    }

    private void Loaddata() {
        ApiRequestData apiRequestData = RetroServerAPI.getClient().create(ApiRequestData.class);
        Call<ResponModel> responModelCall = apiRequestData.getResponData();
        responModelCall.enqueue(new Callback<ResponModel>() {
            @Override
            public void onResponse(Call<ResponModel> call, Response<ResponModel> response) {
                if (response.isSuccessful()) {

                    ResponModel.Confirmed confirmed = response.body().getConfirmed();
                    ResponModel.Deaths deaths = response.body().getDeaths();
                    ResponModel.Recofered recofered = response.body().getRecovered();
//                    tv_source.setText("API : " + response.body().getSource());
                    long confirm = confirmed.getValue();
                    long recof = recofered.getValue();
                    long deat = deaths.getValue();
                    tv_confirmed.setText(NumberFormat.getNumberInstance(Locale.getDefault()).format(confirmed.getValue()));
                    long total = confirm + recof + deat;
                    tv_total.setText(NumberFormat.getNumberInstance(Locale.getDefault()).format(total));
                    tv_recovered.setText(NumberFormat.getNumberInstance(Locale.getDefault()).format(recofered.getValue()));
                    tv_deaths.setText(NumberFormat.getNumberInstance(Locale.getDefault()).format(deaths.getValue()));

                    final AnimatedPieViewConfig pieViewConfig = new AnimatedPieViewConfig();
                    pieViewConfig.duration(1000);
                    pieViewConfig.strokeMode(true);
                    pieViewConfig.strokeWidth(30);
                    pieViewConfig.drawText(true);
                    pieViewConfig.addData(new SimplePieInfo(confirmed.getValue(), Color.YELLOW, "Confirmed"));
                    pieViewConfig.addData(new SimplePieInfo(recofered.getValue(), Color.GREEN, "Recovered"));
                    pieViewConfig.addData(new SimplePieInfo(deaths.getValue(), Color.RED, "Deaths"));
                    pieView.applyConfig(pieViewConfig);
                    pieView.start();
                }
            }

            @Override
            public void onFailure(Call<ResponModel> call, Throwable t) {
                Toast.makeText(MainActivity.this, "ERROR", Toast.LENGTH_SHORT).show();
                Log.d("RESPON ", "Respon : " + t.getMessage());
            }
        });
    }
}
