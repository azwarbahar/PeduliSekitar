package com.example.coronavirus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.coronavirus.adapter.FAQAdapter;
import com.example.coronavirus.model.FAQModel;

import java.util.ArrayList;

public class FAQActivity extends AppCompatActivity {


    private String[] titellist;
    private String[] subtitellist;

    private RecyclerView rv_faq;
    private ArrayList<FAQModel> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq);

        rv_faq = findViewById(R.id.rv_faq);

        arrayList = tambahItem();

        FAQAdapter faqAdapter = new FAQAdapter(FAQActivity.this, arrayList);
        rv_faq.setLayoutManager(new LinearLayoutManager(FAQActivity.this));
        rv_faq.setAdapter(faqAdapter);

    }

    private ArrayList<FAQModel> tambahItem() {

        titellist = getResources().getStringArray(R.array.list_title_faq);
        subtitellist = getResources().getStringArray(R.array.list_desk_faq);

        ArrayList<FAQModel> listnya = new ArrayList<>();

        for (int a = 0; a < titellist.length; a++) {
            FAQModel faqModel = new FAQModel();

            faqModel.setTitle_FAQ(titellist[a]);
            faqModel.setSub_Title_FAQ(subtitellist[a]);

            listnya.add(faqModel);
        }
        return listnya;
    }

}
