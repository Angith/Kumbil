package com.kumbil.neha;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class CookMenuRVAdapter extends RecyclerView.Adapter<CookMenuRVAdapter.CookMenuRVViewHolder> {

    public ArrayList<CookMenu> cookMenus;
    public Context mContext;

    public CookMenuRVAdapter(ArrayList<CookMenu> data, Context context) {
        this.cookMenus = data;
        this.mContext = context;
    }

    @NonNull
    @Override
    public CookMenuRVViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.cook_menu_list_layout, viewGroup, false);
        return new CookMenuRVAdapter.CookMenuRVViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CookMenuRVViewHolder CookMenuRVViewHolder, int position) {
        final CookMenu cookMenu = cookMenus.get(position);

        CookMenuRVViewHolder.setDishName(cookMenu.getDishName());
        CookMenuRVViewHolder.setPrice(String.valueOf(cookMenu.getPrice()));
        CookMenuRVViewHolder.setDescription(cookMenu.getDescription());
    }

    @Override
    public int getItemCount() {
        return cookMenus == null? 0: cookMenus.size();
    }

    public class CookMenuRVViewHolder extends RecyclerView.ViewHolder{

        private TextView dishName;
        private TextView price;
        private TextView description;

        public CookMenuRVViewHolder(@NonNull View itemView) {
            super(itemView);
            dishName = itemView.findViewById(R.id.tvDishNameValue);
            price = itemView.findViewById(R.id.tvPriceValue);
            description = itemView.findViewById(R.id.tvDescriptionValue);
        }

        public void setDishName(String dish) {
            dishName.setText(dish);
        }

        public void setPrice(String pr) {
            price.setText(pr);
        }

        public void setDescription(String desc) {
            description.setText(desc);
        }
    }
}
