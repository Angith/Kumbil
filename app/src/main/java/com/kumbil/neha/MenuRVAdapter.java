package com.kumbil.neha;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class MenuRVAdapter extends RecyclerView.Adapter<MenuRVAdapter.MenuRVViewHolder> {

    public ArrayList<CookMenu> cookMenus;
    public Context mContext;

    public MenuRVAdapter(ArrayList<CookMenu> data, Context context) {
        this.cookMenus = data;
        this.mContext = context;
    }

    @NonNull
    @Override
    public MenuRVViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.menu_list_layout, viewGroup, false);
        return new MenuRVAdapter.MenuRVViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuRVViewHolder MenuRVViewHolder, int position) {
        final CookMenu cookMenu = cookMenus.get(position);

        MenuRVViewHolder.setDishName(cookMenu.getDishName());
        MenuRVViewHolder.setPrice(String.valueOf(cookMenu.getPrice()));
        MenuRVViewHolder.setDescription(cookMenu.getDescription());
    }

    @Override
    public int getItemCount() {
        return cookMenus == null? 0: cookMenus.size();
    }

    public class MenuRVViewHolder extends RecyclerView.ViewHolder{

        private TextView dishName;
        private TextView price;
        private TextView description;

        public MenuRVViewHolder(@NonNull View itemView) {
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
