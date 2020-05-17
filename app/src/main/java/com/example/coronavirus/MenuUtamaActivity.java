package com.example.coronavirus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.coronavirus.api.ApiRequestData;
import com.example.coronavirus.api.IndonesiaRetrosAPI;
import com.example.coronavirus.api.LaporanRetrosAPI;
import com.example.coronavirus.model.IndonesiaModel;
import com.example.coronavirus.model.JumlahLaporanModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MenuUtamaActivity extends AppCompatActivity {


    private long backPressedTime;
    private ProgressBar progesBar;
    private TextView tv_jumlah_laporan;
    private TextView tv_positiv;
    private TextView tv_sembuh;
    private TextView tv_meninggal;
    private RelativeLayout rl_lapor;
    private CardView cv_indo, cv_earth, cv_faq, cv_tentang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_utama);

        tv_jumlah_laporan = findViewById(R.id.tv_jumlah_laporan);
        tv_positiv = findViewById(R.id.tv_positiv);
        tv_sembuh = findViewById(R.id.tv_sembuh);
        tv_meninggal = findViewById(R.id.tv_meninggal);
        rl_lapor = findViewById(R.id.rl_lapor);
        cv_indo = findViewById(R.id.cv_indo);
        cv_earth = findViewById(R.id.cv_earth);
        cv_faq = findViewById(R.id.cv_faq);
        cv_tentang = findViewById(R.id.cv_tentang);
        progesBar = findViewById(R.id.progesBar);
        progesBar.setVisibility(View.VISIBLE);
        loadData();
        negaraLoad();

        cv_indo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuUtamaActivity.this, MapsIndoActivity.class);
                startActivity(intent);
            }
        });

        cv_faq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MenuUtamaActivity.this, FAQActivity.class);
                startActivity(intent);
            }
        });

        cv_tentang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuUtamaActivity.this, TentangActivity.class);
                startActivity(intent);
            }
        });

        cv_earth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuUtamaActivity.this, MapsActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
            }
        });

//        //Exit When Back and Set no History
//        Intent intent = new Intent(Intent.ACTION_MAIN);
//        intent.addCategory(Intent.CATEGORY_HOME);
//        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//***Change Here***
//        startActivity(intent);
//        finish();
//        System.exit(0);


        rl_lapor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuUtamaActivity.this, FormLaporanActivity.class);
                startActivity(intent);
            }
        });


    }

    private void negaraLoad() {

        ApiRequestData apiRequestData = IndonesiaRetrosAPI.getClient().create(ApiRequestData.class);
        Call<IndonesiaModel> indonesiaModelCall = apiRequestData.getDataIndo();
        indonesiaModelCall.enqueue(new Callback<IndonesiaModel>() {
            @Override
            public void onResponse(Call<IndonesiaModel> call, Response<IndonesiaModel> response) {
                if (response.isSuccessful()) {
                    progesBar.setVisibility(View.GONE);
                    assert response.body() != null;
                    String mati = response.body().getMeninggal();
                    String sembuh = response.body().getSembuh();
                    String rawat = response.body().getPerawatan();
                    String jumlahkasus = response.body().getJumlahKasus();

                    tv_sembuh.setText(sembuh);
                    tv_meninggal.setText(mati);
                    tv_positiv.setText(jumlahkasus);


                }
            }

            @Override
            public void onFailure(Call<IndonesiaModel> call, Throwable t) {
                progesBar.setVisibility(View.GONE);
            }
        });

    }

    private void loadData() {
        ApiRequestData apiRequestData = LaporanRetrosAPI.getClient().create(ApiRequestData.class);
        Call<JumlahLaporanModel> getjumLapo = apiRequestData.getjumlahlapor();
        getjumLapo.enqueue(new Callback<JumlahLaporanModel>() {
            @Override
            public void onResponse(Call<JumlahLaporanModel> call, Response<JumlahLaporanModel> response) {
                if (response.isSuccessful()) {
                    String jumalh = String.valueOf(response.body().getValue());
                    tv_jumlah_laporan.setText(jumalh);
                }
            }

            @Override
            public void onFailure(Call<JumlahLaporanModel> call, Throwable t) {

            }
        });
    }

    @Override
    public void onBackPressed() {


        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            super.onBackPressed();
            return;
        } else {
            Toast.makeText(this, "Klik sekali lagi untuk keluar", Toast.LENGTH_SHORT).show();
        }
        backPressedTime = System.currentTimeMillis();
    }
}
