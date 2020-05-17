package com.example.coronavirus;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ProfilePrtugasActivity extends AppCompatActivity {

    private TextView tvnama;
    private TextView tvprofesi;
    private TextView tvuser;
    private TextView tvpass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_prtugas);

        tvnama = findViewById(R.id.tvnama);
        tvprofesi = findViewById(R.id.tvprofesi);
        tvuser = findViewById(R.id.tvuser);
        tvpass = findViewById(R.id.tvpass);

        getBundle();

    }

    private void getBundle() {

        Bundle bundle = getIntent().getExtras();
        assert bundle != null;

        String id = bundle.getString("ID");
        String nama = bundle.getString("NAMA");
        String profesi = bundle.getString("PROFESI");
        String user = bundle.getString("USER");
        String pass = bundle.getString("PASS");

        tvnama.setText(nama);
        tvprofesi.setText("Profesi : "+profesi);
        tvuser.setText("Username : "+user);
        tvpass.setText("Password : "+pass);

    }
}
