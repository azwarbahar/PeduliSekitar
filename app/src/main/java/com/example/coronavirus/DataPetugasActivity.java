package com.example.coronavirus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.coronavirus.api.ApiRequestData;
import com.example.coronavirus.api.LaporanRetrosAPI;
import com.example.coronavirus.model.ResponLaporanModel;
import com.example.coronavirus.model.SinglePetugasModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataPetugasActivity extends AppCompatActivity {

    private RecyclerView.Adapter adapter;
    private RecyclerView rv_petugas;
    private List<SinglePetugasModel> petugasModels;
    private FloatingActionButton fb_tambah;
    private View dialogView;
    private ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_petugas);
        fb_tambah = findViewById(R.id.fb_tambah);
        pd = new ProgressDialog(this);
        fb_tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogAdd();
            }
        });

        rv_petugas = findViewById(R.id.rv_petugas);
        readDataAll();

    }

    private void showDialogAdd() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(DataPetugasActivity.this);
        LayoutInflater inflater = getLayoutInflater();
        dialogView = inflater.inflate(R.layout.input_dialog_petugas, null);
        dialog.setView(dialogView);
        dialog.setCancelable(true);
        dialog.setTitle("Tambah Petugas");

        dialog.setPositiveButton("Simpan", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(final DialogInterface dialog, int which) {

                EditText nama = dialogView.findViewById(R.id.nama_dialog);
                EditText profesi = dialogView.findViewById(R.id.profesi_dialog);
                EditText username = dialogView.findViewById(R.id.username_dialog);
                EditText password = dialogView.findViewById(R.id.password_dialog);

                ApiRequestData apiRequestData = LaporanRetrosAPI.getClient().create(ApiRequestData.class);
                Call<ResponLaporanModel> call = apiRequestData.addPetugas(nama.getText().toString(),
                        profesi.getText().toString(),
                        username.getText().toString(),
                        password.getText().toString());

                call.enqueue(new Callback<ResponLaporanModel>() {
                    @Override
                    public void onResponse(Call<ResponLaporanModel> call, Response<ResponLaporanModel> response) {
                        String kode = response.body().getKode();
                        if (kode.equals("1")){
                            Toast.makeText(DataPetugasActivity.this, "Berhasil Menambahkan", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                            readDataAll();
                        } else {
                            Toast.makeText(DataPetugasActivity.this, "GAGAL", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                            readDataAll();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponLaporanModel> call, Throwable t) {
                        dialog.dismiss();
                        readDataAll();
                    }
                });

            }
        });

        dialog.setNegativeButton("Batal", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                readDataAll();
            }
        });

        dialog.show();
    }

    private void readDataAll() {
        ApiRequestData apiRequestData = LaporanRetrosAPI.getClient().create(ApiRequestData.class);
        Call<ResponLaporanModel> laporanModelCall = apiRequestData.getPetugasAll();
        laporanModelCall.enqueue(new Callback<ResponLaporanModel>() {
            @Override
            public void onResponse(Call<ResponLaporanModel> call, Response<ResponLaporanModel> response) {
                if (response.isSuccessful()){
                    String kode = response.body().getKode();
                    if (kode.equals("1")){
                        petugasModels = response.body().getResult();
                        rv_petugas.setLayoutManager(new LinearLayoutManager(DataPetugasActivity.this));
                        adapter = new PetugasAdapter(DataPetugasActivity.this, petugasModels);
                        rv_petugas.setAdapter(adapter);
                    }
                } else {
                    Toast.makeText(DataPetugasActivity.this, "GAGAL", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponLaporanModel> call, Throwable t) {
                Log.d("Error data", "Respon : "+t);
            }
        });
    }
}
