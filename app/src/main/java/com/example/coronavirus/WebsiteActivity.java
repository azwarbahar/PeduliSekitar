package com.example.coronavirus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.coronavirus.adapter.WebsiteAdapter;
import com.example.coronavirus.model.WebsiteModle;

import java.util.ArrayList;

public class WebsiteActivity extends AppCompatActivity {

    private String[] datatitle;
    private String[] datalink;
    private String[] datahotlin;

    private RecyclerView rv_website;
    private ArrayList<WebsiteModle> websiteModles;

    private ImageView btn_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_website);

        btn_back = findViewById(R.id.btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WebsiteActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        websiteModles = tambahitem();
        rv_website = findViewById(R.id.rv_website);
        WebsiteAdapter adapter = new WebsiteAdapter(WebsiteActivity.this,websiteModles);
        rv_website.setLayoutManager(new LinearLayoutManager(WebsiteActivity.this));
        rv_website.setAdapter(adapter);
    }


    private ArrayList<WebsiteModle> tambahitem(){

        datatitle = getResources().getStringArray(R.array.titel_web);
        datalink = getResources().getStringArray(R.array.link_web);
        datahotlin = getResources().getStringArray(R.array.hotlen_web);


        ArrayList<WebsiteModle> modleArrayList = new ArrayList<>();

        for (int a =0; a < datatitle.length; a++){
            WebsiteModle modle = new WebsiteModle();

            modle.setTitleWeb(datatitle[a]);
            modle.setLinkWeb(datalink[a]);
            modle.setHotlineWeb(datahotlin[a]);

            modleArrayList.add(modle);
        }

        return modleArrayList;
    }

}
