package com.example.coronavirus.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coronavirus.R;
import com.example.coronavirus.model.WebsiteModle;

import java.util.ArrayList;

public class WebsiteAdapter extends RecyclerView.Adapter<WebsiteAdapter.MyHolderView> {

    private Context context;
    ArrayList<WebsiteModle> websiteModles;

    public WebsiteAdapter(Context context, ArrayList<WebsiteModle> websiteModles) {
        this.context = context;
        this.websiteModles = websiteModles;
    }

    @NonNull
    @Override
    public WebsiteAdapter.MyHolderView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_website,parent,false);
        return new WebsiteAdapter.MyHolderView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WebsiteAdapter.MyHolderView holder, int position) {

        final String link = websiteModles.get(position).getLinkWeb();
        holder.tv_hotline.setText("Hotline : "+websiteModles.get(position).getHotlineWeb());
        holder.title_website.setText(websiteModles.get(position).getTitleWeb());
        holder.tv_link.setText(link);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(link));
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return websiteModles.size();
    }

    public class MyHolderView extends RecyclerView.ViewHolder {

        private TextView title_website;
        private TextView tv_link;
        private TextView tv_hotline;

        public MyHolderView(@NonNull View itemView) {
            super(itemView);

            tv_link = itemView.findViewById(R.id.tv_link);
            title_website = itemView.findViewById(R.id.title_website);
            tv_hotline = itemView.findViewById(R.id.tv_hotline);

        }
    }
}
