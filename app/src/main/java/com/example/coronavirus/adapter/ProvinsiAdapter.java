package com.example.coronavirus.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coronavirus.R;
import com.example.coronavirus.model.PropertisModel;

import java.util.ArrayList;

public class ProvinsiAdapter extends RecyclerView.Adapter<ProvinsiAdapter.MyholderView> {

    private Context context;
    private ArrayList<PropertisModel> list ;

    public ProvinsiAdapter(Context context, ArrayList<PropertisModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ProvinsiAdapter.MyholderView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_negara,parent,false);
        return new ProvinsiAdapter.MyholderView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProvinsiAdapter.MyholderView holder, int position) {

        holder.tv_negara.setText(list.get(position).getProvinsi());
        holder.tv_convirmed_item.setText(list.get(position).getKasus_Terkonfirmasi_Akumulatif());
        holder.tv_recovered_item.setText(list.get(position).getKasus_Sembuh_Akumulatif());
        holder.tv_deaths_item.setText(list.get(position).getKasus_Meninggal_Akumulatif());
//        holder.tv_lastupdate_item.setText(list.get(position).getPembaruan().substring(0,10) + " / " + list.get(position).getPembaruan().substring(11,16));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyholderView extends RecyclerView.ViewHolder {

        TextView tv_negara;
        TextView tv_convirmed_item;
        TextView tv_recovered_item;
        TextView tv_deaths_item;
        TextView tv_lastupdate_item;

        public MyholderView(@NonNull View itemView) {
            super(itemView);

            tv_negara =itemView.findViewById(R.id.tv_negara);
            tv_convirmed_item =itemView.findViewById(R.id.tv_convirmed_item);
            tv_recovered_item =itemView.findViewById(R.id.tv_recovered_item);
            tv_deaths_item =itemView.findViewById(R.id.tv_deaths_item);
            tv_lastupdate_item =itemView.findViewById(R.id.tv_lastupdate_item);

        }
    }
}
