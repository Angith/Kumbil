package com.kumbil.neha;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class CustomerOrdersRVAdapter extends RecyclerView.Adapter<CustomerOrdersRVAdapter.CustomerOrdersRVViewHolder> {

    public ArrayList<userOrder> OrderList;
    private Context mContext;

    public CustomerOrdersRVAdapter(ArrayList<userOrder> data, Context context) {
        this.OrderList = data;
        this.mContext = context;
    }

    @NonNull
    @Override
    public CustomerOrdersRVAdapter.CustomerOrdersRVViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.customer_orders_list, viewGroup, false);
        return new CustomerOrdersRVAdapter.CustomerOrdersRVViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomerOrdersRVAdapter.CustomerOrdersRVViewHolder orderRVViewHolder, int position) {
        final userOrder order = OrderList.get(position);

        orderRVViewHolder.setDishName(order.getDishName());
        orderRVViewHolder.setDate(order.getDate());
        orderRVViewHolder.setTime(order.getTime());
        orderRVViewHolder.setQty(String.valueOf(order.getQuantity()));
        orderRVViewHolder.setStatus(order.getStatus());
    }

    @Override
    public int getItemCount() {
        return OrderList == null ? 0 : OrderList.size();
    }

    public class CustomerOrdersRVViewHolder extends RecyclerView.ViewHolder {

        private TextView dishName;
        private TextView tvDate;
        private TextView time;
        private TextView qty;
        private TextView status;


        public CustomerOrdersRVViewHolder(@NonNull View itemView) {
            super(itemView);
            dishName = itemView.findViewById(R.id.etDishnameValue);
            tvDate = itemView.findViewById(R.id.etDateValue);
            time = itemView.findViewById(R.id.etTimeValue);
            qty = itemView.findViewById(R.id.etQtyValue);
            status = itemView.findViewById(R.id.etStatusValue);

        }

        public void setDishName(String dish) {
            dishName.setText(dish);
        }

        public void setDate(String date) {
            tvDate.setText(date);
        }

        public void setTime(String tm) {
            time.setText(tm);
        }

        public void setQty(String qt) {
            qty.setText(qt);
        }

        public void setStatus(String sts) {
            status.setText(sts);
        }
    }
}
