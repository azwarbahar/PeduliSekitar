package com.example.coronavirus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MulaiActivity extends AppCompatActivity {

    private Button btn_mulai, btn_masuk_petugas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mulai);

        btn_mulai = findViewById(R.id.btn_mulai);
        btn_masuk_petugas = findViewById(R.id.btn_masuk_petugas);

        btn_mulai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MulaiActivity.this, MenuUtamaActivity.class);
                startActivity(intent);
            }
        });

        btn_masuk_petugas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MulaiActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

    }
}
