package com.example.coronavirus;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.coronavirus.api.ApiRequestData;
import com.example.coronavirus.api.LaporanRetrosAPI;
import com.example.coronavirus.model.SinglePetugasModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private Button btn_login;
    private TextView tv_back, tv_salah;

    private TextView initid;
    private TextView initnama;
    private TextView initprofesi;
    private TextView inituser;
    private TextView initpass;

    private EditText input_username;
    private EditText input_password;

    private ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        pd = new ProgressDialog(this);

        input_password = findViewById(R.id.input_password);
        input_username = findViewById(R.id.input_username);

        initid = findViewById(R.id.initid);
        initnama = findViewById(R.id.initnama);
        initprofesi = findViewById(R.id.initprofesi);
        inituser = findViewById(R.id.inituser);
        initpass = findViewById(R.id.initpass);


        tv_back = findViewById(R.id.tv_back);
        tv_salah = findViewById(R.id.tv_salah);

        tv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, MulaiActivity.class);
                startActivity(intent);
            }
        });

        btn_login = findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String cekinituser = input_username.getText().toString();
                String cekinitpass = input_password.getText().toString();
                if (cekinituser.isEmpty()) {
                    input_username.setError("Lengkapi");
                } else if (cekinitpass.isEmpty()) {
                    input_password.setError("Lengkapi");
                } else {
                    cekLogin();
                }


            }
        });
    }

    private void cekLogin() {

        pd.setMessage("Proses ... ");
        pd.setCancelable(true);
        pd.show();

        String username = input_username.getText().toString().trim();
        String password = input_password.getText().toString().trim();

        ApiRequestData apiRequestData = LaporanRetrosAPI.getClient().create(ApiRequestData.class);
        Call<List<SinglePetugasModel>> listCall = apiRequestData.getSinglePetugas(username, password);
        listCall.enqueue(new Callback<List<SinglePetugasModel>>() {
            @Override
            public void onResponse(Call<List<SinglePetugasModel>> call, Response<List<SinglePetugasModel>> response) {
                pd.hide();
                if (response.isSuccessful()) {
                    if (!response.body().isEmpty()) {

                        tv_salah.setText("");
                        for (int i = 0; i < response.body().size(); i++) {

                            initid.setText(response.body().get(i).getId_petugas());
                            initnama.setText(response.body().get(i).getNama_petugas());
                            initprofesi.setText(response.body().get(i).getProfesi());
                            inituser.setText(response.body().get(i).getUsername());
                            initpass.setText(response.body().get(i).getPassword());

                        }

                        Bundle bundle = new Bundle();
                        bundle.putString("ID", (String) initid.getText());
                        bundle.putString("NAMA", (String) initnama.getText());
                        bundle.putString("PROFESI", (String) initprofesi.getText());
                        bundle.putString("USER", (String) inituser.getText());
                        bundle.putString("PASS", (String) initpass.getText());

                        Toast.makeText(LoginActivity.this, "Berhasil Login!", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(LoginActivity.this, PetugasActivity.class);
                        intent.putExtras(bundle);
                        startActivity(intent);

                    } else {
                        tv_salah.setText("Username atau Password salah!");
                    }
                }
            }

            @Override
            public void onFailure(Call<List<SinglePetugasModel>> call, Throwable t) {
                pd.hide();
                Log.d("Cek error", "Respon : " + t);
            }
        });
    }
}
