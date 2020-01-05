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

import com.kumbil.neha.Network.ApiClient;
import com.kumbil.neha.Network.ApiInterface;
import com.kumbil.neha.models.Resp;
import com.kumbil.neha.models.UpdateOrder;
import com.kumbil.neha.models.ordersResp;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import retrofit2.Call;

public class OrderRVAdapter extends RecyclerView.Adapter<OrderRVAdapter.OrderRVViewHolder> {

    public ArrayList<Orders> OrderList;
    private Context mContext;
    private final ClickListener listener;

    public OrderRVAdapter(ArrayList<Orders> data, Context context, ClickListener listener) {
        this.listener = listener;
        this.OrderList = data;
        this.mContext = context;
    }

    @NonNull
    @Override
    public OrderRVViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.order_list_layout, viewGroup, false);
        return new OrderRVViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderRVViewHolder orderRVViewHolder, int position) {
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

    public class OrderRVViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView userName;
        private TextView dishName;
        private TextView tvDate;
        private TextView time;
        private TextView qty;
        private TextView deliveryAddress;
        private ConstraintLayout orders;
        private Button reject, accept;
        private WeakReference<ClickListener> listenerRef;
        public OrderRVViewHolder(@NonNull View itemView, ClickListener listener) {
            super(itemView);
            userName = itemView.findViewById(R.id.etUsernameValue);
            dishName = itemView.findViewById(R.id.etDishnameValue);
            tvDate = itemView.findViewById(R.id.etDateValue);
            time = itemView.findViewById(R.id.etTimeValue);
            qty = itemView.findViewById(R.id.etQtyValue);
            deliveryAddress = itemView.findViewById(R.id.etDeliveryAddressValue);
            orders = itemView.findViewById(R.id.ordersConstraintlayout);

            listenerRef = new WeakReference<>(listener);
            reject = (Button) itemView.findViewById(R.id.bReject);
            accept = (Button) itemView.findViewById(R.id.bAccept);

            reject.setOnClickListener(this);
            accept.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            boolean doAccept = v.getId() == accept.getId();
            listenerRef.get().onPositionClicked(getAdapterPosition(), doAccept);
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
