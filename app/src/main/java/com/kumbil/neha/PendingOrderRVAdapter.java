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

public class PendingOrderRVAdapter extends RecyclerView.Adapter<PendingOrderRVAdapter.PendingOrderRVViewHolder> {

    public ArrayList<Orders> OrderList;
    private Context mContext;
    private ClickListener listener;
    public PendingOrderRVAdapter(ArrayList<Orders> data, Context context, ClickListener listener) {
        this.OrderList = data;
        this.mContext = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public PendingOrderRVViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.pending_orders_list, viewGroup, false);
        return new PendingOrderRVViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull PendingOrderRVViewHolder orderRVViewHolder, int position) {
        final Orders order = OrderList.get(position);

        orderRVViewHolder.setUserName(order.getUserName());
        orderRVViewHolder.setDishName(order.getDishName());
        orderRVViewHolder.setDate(order.getDate());
        orderRVViewHolder.setTime(order.getTime());
        orderRVViewHolder.setQty(order.getQuantity());
        orderRVViewHolder.setDeliveryAddress(order.getdAddress());
    }

    @Override
    public int getItemCount() {
        return OrderList == null? 0: OrderList.size();
    }

    public class PendingOrderRVViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView userName;
        private TextView dishName;
        private TextView tvDate;
        private TextView time;
        private TextView qty;
        private TextView deliveryAddress;
        private ConstraintLayout orders;
        private Button notify;
        private WeakReference<ClickListener> listenerRef;
        public PendingOrderRVViewHolder(@NonNull View itemView, ClickListener listener) {
            super(itemView);
            userName = itemView.findViewById(R.id.etUsernameValue);
            dishName = itemView.findViewById(R.id.etDishnameValue);
            tvDate = itemView.findViewById(R.id.etDateValue);
            time = itemView.findViewById(R.id.etTimeValue);
            qty = itemView.findViewById(R.id.etQtyValue);
            deliveryAddress = itemView.findViewById(R.id.etDeliveryAddressValue);
            orders = itemView.findViewById(R.id.pendingOrdersConstraintlayout);

            listenerRef = new WeakReference<>(listener);
            notify = itemView.findViewById(R.id.bNotify);

            notify.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listenerRef.get().onPositionClicked(getAdapterPosition(), true);
        }

        public void setUserName(String name) {
            userName.setText(name);
        }

        public void setDishName(String dName) {
            dishName.setText(dName);
        }

        public void setDate(String date) {
            tvDate.setText(date);
        }


        public void setTime(String dTime) {
            time.setText(dTime);
        }

        public void setQty(int Qty) {
            qty.setText(Integer.toString(Qty));
        }

        public void setDeliveryAddress(String dAddress) {
            deliveryAddress.setText(dAddress);
        }

    }
}
