package com.kumbil.neha;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CartRVAdapter extends RecyclerView.Adapter<CartRVAdapter.CartRVViewHolder> {

    public ArrayList<Cart> cartList;
    private Context mContext;

    public CartRVAdapter(ArrayList<Cart> data, Context context) {
        this.cartList = data;
        this.mContext = context;
    }

    @NonNull
    @Override
    public CartRVViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.cart_list_layout, viewGroup, false);
        return new CartRVViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartRVViewHolder cartRVViewHolder, int position) {
        final Cart cart = cartList.get(position);
        cartRVViewHolder.setPrice(cart.getPrice());
        cartRVViewHolder.setDishName(cart.getDishName());
        cartRVViewHolder.setQty(cart.getQty());
    }

    @Override
    public int getItemCount() {
        return cartList == null? 0: cartList.size();
    }

    public class CartRVViewHolder extends RecyclerView.ViewHolder{

        private TextView price;
        private TextView dishName;
        private TextView qty;

        public CartRVViewHolder(View view) {
            super(view);
            dishName = view.findViewById(R.id.tvDishNameCart);
            price = view.findViewById(R.id.tvPriceCart);
            qty = view.findViewById(R.id.tvQtyCart);
        }

        public void setPrice(String amount) {
            price.setText(amount);
        }

        public void setDishName(String dName) {
            dishName.setText(dName);
        }

        public void setQty(String quantity) {
           qty.setText(quantity);
        }
    }
}
