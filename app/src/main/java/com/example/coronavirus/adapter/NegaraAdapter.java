package com.example.coronavirus.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coronavirus.R;
import com.example.coronavirus.model.LocationModel;

import java.util.List;

public class NegaraAdapter extends RecyclerView.Adapter<NegaraAdapter.MyHolderView> {


    private Context context;
    private List<LocationModel> list ;

    public NegaraAdapter(Context context, List<LocationModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public NegaraAdapter.MyHolderView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_negara,parent,false);
        return new NegaraAdapter.MyHolderView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final NegaraAdapter.MyHolderView holder, final int position) {

//        RecVieDataNegaraModel recVieDataNegaraModel = list2.get(position);
        holder.tv_negara.setText(list.get(position).getProvinceState()+", "+list.get(position).getCountryRegion());
        holder.tv_convirmed_item.setText(list.get(position).getConfirmed());
        holder.tv_recovered_item.setText(list.get(position).getRecovered());
        holder.tv_deaths_item.setText(list.get(position).getDeaths());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyHolderView extends RecyclerView.ViewHolder {
        TextView tv_negara;
        TextView tv_convirmed_item;
        TextView tv_recovered_item;
        TextView tv_deaths_item;
        TextView tv_lastupdate_item;
        public MyHolderView(@NonNull View itemView) {
            super(itemView);

            tv_negara =itemView.findViewById(R.id.tv_negara);
            tv_convirmed_item =itemView.findViewById(R.id.tv_convirmed_item);
            tv_recovered_item =itemView.findViewById(R.id.tv_recovered_item);
            tv_deaths_item =itemView.findViewById(R.id.tv_deaths_item);
            tv_lastupdate_item =itemView.findViewById(R.id.tv_lastupdate_item);

        }
    }



}
