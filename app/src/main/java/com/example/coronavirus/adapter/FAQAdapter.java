package com.example.coronavirus.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coronavirus.R;
import com.example.coronavirus.model.FAQModel;

import java.util.List;

public class FAQAdapter extends RecyclerView.Adapter<FAQAdapter.HolderView> {

    private Context context;
    private List<FAQModel> list ;

    public FAQAdapter(Context context, List<FAQModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public FAQAdapter.HolderView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_faq,parent,false);
        return new FAQAdapter.HolderView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderView holder, final int position) {

        final FAQModel faqModel = list.get(position);


        holder.bind(faqModel);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean expend = faqModel.isExpanded();
                faqModel.setExpanded(!expend);
                notifyItemChanged(position);

            }
        });

    }



    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public class HolderView extends RecyclerView.ViewHolder {

        private TextView title;
        private TextView sub_title;
        private View subitem;

        public HolderView(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.title_faq);
            sub_title = itemView.findViewById(R.id.desk_faq);
            subitem = itemView.findViewById(R.id.sub_item);

        }

        private void bind(FAQModel faqModel) {
            boolean expanded = faqModel.isExpanded();

            subitem.setVisibility(expanded ? View.VISIBLE : View.GONE);

            title.setText(faqModel.getTitle_FAQ());
            sub_title.setText(faqModel.getSub_Title_FAQ());
        }
    }
}
