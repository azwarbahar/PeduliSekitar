package com.example.coronavirus;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coronavirus.model.SinglePetugasModel;

import java.util.List;

class PetugasAdapter extends RecyclerView.Adapter<PetugasAdapter.MyholderView> {
    private Context mContext;
    private List<SinglePetugasModel> models;

    public PetugasAdapter(Context mContext, List<SinglePetugasModel> models) {
        this.mContext = mContext;
        this.models = models;
    }

    @NonNull
    @Override
    public PetugasAdapter.MyholderView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_petugas, parent, false);
        return new MyholderView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PetugasAdapter.MyholderView holder, int position) {

        final SinglePetugasModel sp = models.get(position);
        holder.nama.setText(sp.getNama_petugas());
        holder.profesi.setText(sp.getProfesi());
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public class MyholderView extends RecyclerView.ViewHolder {

        TextView nama;
        TextView profesi;

        public MyholderView(@NonNull View itemView) {
            super(itemView);

            nama = itemView.findViewById(R.id.textView31);
            profesi = itemView.findViewById(R.id.textView40);

        }
    }
}
