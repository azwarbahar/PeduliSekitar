package com.example.coronavirus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.coronavirus.adapter.KasusAdapter;
import com.example.coronavirus.api.ApiRequestData;
import com.example.coronavirus.api.IndonesiaRetrosAPI;
import com.example.coronavirus.model.KasusModel;
import com.example.coronavirus.model.ListKasusModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailIndonesiaActivity extends AppCompatActivity {

    private ImageView btn_back;
    private TextView tv_confermed_negara88;
    private TextView tv_recovered_negara2;
    private TextView tv_confermed_negara6;
    private TextView tv_deaths_negara;
    private ProgressBar progesBar;
    private RecyclerView rv_kasus;
    private RecyclerView.Adapter adapter;


    private List<ListKasusModel> listKasusModels = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_indonesia);

        progesBar = findViewById(R.id.progesBar);
        btn_back = findViewById(R.id.btn_back);
        tv_confermed_negara88 = findViewById(R.id.tv_confermed_negara88);
        tv_recovered_negara2 = findViewById(R.id.tv_recovered_negara2);
        tv_confermed_negara6 = findViewById(R.id.tv_confermed_negara6);
        tv_deaths_negara = findViewById(R.id.tv_deaths_negara);
        rv_kasus = findViewById(R.id.rv_kasus);
        progesBar.setVisibility(View.VISIBLE);



        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailIndonesiaActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        Intent intent = getIntent();
        tv_confermed_negara88.setText(intent.getStringExtra("konfirmasi"));
        tv_recovered_negara2.setText(intent.getStringExtra("sembuh"));
        tv_confermed_negara6.setText(intent.getStringExtra("dirawat"));
        tv_deaths_negara.setText(intent.getStringExtra("meninggal"));

        LoadDataKasus();
    }

    private void LoadDataKasus(){
        ApiRequestData apiRequestData = IndonesiaRetrosAPI.getClient().create(ApiRequestData.class);
        Call<KasusModel> kasusModelCall = apiRequestData.getDatakasus();
        kasusModelCall.enqueue(new Callback<KasusModel>() {
            @Override
            public void onResponse(Call<KasusModel> call, Response<KasusModel> response) {
                if (response.isSuccessful()){
                    progesBar.setVisibility(View.GONE);
                    listKasusModels = response.body().getData();
                    for (int a = 0 ; a < listKasusModels.size(); a++){
                        Log.d("Cek", "Respon : "+listKasusModels.get(a).getKode_pasien());
                    }
                    rv_kasus.setLayoutManager(new LinearLayoutManager(DetailIndonesiaActivity.this));
                    adapter = new KasusAdapter(DetailIndonesiaActivity.this, listKasusModels);
                    rv_kasus.setAdapter(adapter);
//                    Toast.makeText(DetailIndonesiaActivity.this, "SUkses", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<KasusModel> call, Throwable t) {
                progesBar.setVisibility(View.GONE);
                Log.d("ERROR" ,"Respon : "+t.getMessage() );
            }
        });
    }

}
