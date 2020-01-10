package com.kumbil.neha;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class CustomerMenuRVAdapter extends RecyclerView.Adapter<CustomerMenuRVAdapter.CustomerMenuRVViewHolder> {

    public ArrayList<CustomerMenu> cmList;
    private Context mContext;
    private ClickListener listener;

    public CustomerMenuRVAdapter(ArrayList<CustomerMenu> data, Context context, ClickListener listener) {
        this.cmList = data;
        this.mContext = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CustomerMenuRVViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.customer_menu_layout, viewGroup, false);
        return new CustomerMenuRVViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomerMenuRVViewHolder CustomerMenuRVViewHolder, int position) {
        final CustomerMenu cm = cmList.get(position);
        CustomerMenuRVViewHolder.setPrice(String.valueOf(cm.getPrice()));
        CustomerMenuRVViewHolder.setDishName(cm.getDishName());
        CustomerMenuRVViewHolder.setDescription(cm.getDescription());
    }

    @Override
    public int getItemCount() {
        return cmList == null? 0: cmList.size();
    }

    public class CustomerMenuRVViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView dishName;
        private TextView price;
        private TextView description;
        private Button btnBuy;
        private WeakReference<ClickListener> listenerRef;
        public CustomerMenuRVViewHolder(View view) {
            super(view);
            dishName = itemView.findViewById(R.id.tvDishNameValue);
            price = itemView.findViewById(R.id.tvPriceValue);
            description = itemView.findViewById(R.id.tvDescriptionValue);
            btnBuy = itemView.findViewById(R.id.btnBuy);
            listenerRef = new WeakReference<>(listener);

            btnBuy.setOnClickListener(this);
        }

        public void setPrice(String amount) {
            price.setText(amount);
        }

        public void setDishName(String dName) {
            dishName.setText(dName);
        }

        public void setDescription(String desc) {
            description.setText(desc);
        }

        @Override
        public void onClick(View v) {
            listenerRef.get().onPositionClicked(getAdapterPosition(), true);
        }
    }
}
