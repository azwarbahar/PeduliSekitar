package com.example.coronavirus.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coronavirus.R;
import com.example.coronavirus.model.ListKasusModel;

import java.util.List;

public class KasusAdapter extends RecyclerView.Adapter<KasusAdapter.MyHolderView> {

    private Context context;
     private List<ListKasusModel> list ;

    public KasusAdapter(Context context, List<ListKasusModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public KasusAdapter.MyHolderView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_kasus,parent,false);
        return new KasusAdapter.MyHolderView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KasusAdapter.MyHolderView holder, int position) {


        if (list.get(position).getStatus_pasien() == null){
            holder.tv_status.setText("null");
        } else {
            if (list.get(position).getStatus_pasien().equals("1")){
                holder.tv_status.setBackgroundColor(Color.parseColor("#0A460A"));
                holder.tv_status.setText("Sembuh");
            } else if (list.get(position).getStatus_pasien().equals("2")){
                holder.tv_status.setBackgroundColor(Color.parseColor("#474709"));
                holder.tv_status.setText("Masih Dirawat");
            } else  if (list.get(position).getStatus_pasien().equals("0")){
                holder.tv_status.setBackgroundColor(Color.parseColor("#6D0808"));
                holder.tv_status.setText("Meninggal");
            }
        }



        holder.tv_klaster.setText(list.get(position).getProvinsi());
        holder.tv_umur.setText(list.get(position).getUmur());

        if (list.get(position).getJenis_kelamin() == null){
            holder.tv_gendre.setText("Null");
        } else {
            if (list.get(position).getJenis_kelamin().equals("P")){
                holder.tv_gendre.setText("Perempuan");
            } else if (list.get(position).getJenis_kelamin().equals("L")){
                holder.tv_gendre.setText("Laki - laki");
            }
        }
        holder.tv_wn.setText(list.get(position).getDetail_wn());


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyHolderView extends RecyclerView.ViewHolder {

        TextView tv_klaster;
        TextView tv_umur;
        TextView tv_gendre;
        TextView tv_status;
        TextView tv_wn;

        public MyHolderView(@NonNull View itemView) {
            super(itemView);

            tv_klaster = itemView.findViewById(R.id.tv_klaster);
            tv_umur = itemView.findViewById(R.id.tv_umur);
            tv_gendre = itemView.findViewById(R.id.tv_gendre);
            tv_status = itemView.findViewById(R.id.tv_status);
            tv_wn = itemView.findViewById(R.id.tv_wn);

        }
    }
}
