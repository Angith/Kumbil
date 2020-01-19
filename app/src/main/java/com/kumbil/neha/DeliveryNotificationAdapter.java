package com.kumbil.neha;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.kumbil.neha.models.dNotification;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class DeliveryNotificationAdapter extends RecyclerView.Adapter<DeliveryNotificationAdapter.DeliveryNotificationViewHolder> {

    public ArrayList<dNotification> list;
    private Context mContext;
    private ClickListener listener;

    public DeliveryNotificationAdapter(ArrayList<dNotification> data, Context context, ClickListener listener) {
        this.list = data;
        this.mContext = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public DeliveryNotificationAdapter.DeliveryNotificationViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.notification_list_layout, viewGroup, false);
        return new DeliveryNotificationAdapter.DeliveryNotificationViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull DeliveryNotificationAdapter.DeliveryNotificationViewHolder notificationViewHolder, int position) {
        final dNotification nfn = list.get(position);

        notificationViewHolder.setSourceAddress(nfn.getsAddress());
        notificationViewHolder.setDestinationAddress(nfn.getdAddress());
        notificationViewHolder.setTime(nfn.getDeliveryTime());
        notificationViewHolder.setStatus(nfn.getStatus());
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public class DeliveryNotificationViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView sAddress;
        private TextView dAddress;
        private TextView time;
        private TextView status;
        private Button bDelivered;
        private WeakReference<ClickListener> listenerRef;

        public DeliveryNotificationViewHolder(@NonNull View itemView, ClickListener listener) {
            super(itemView);
            sAddress = itemView.findViewById(R.id.etsAddressValue);
            dAddress = itemView.findViewById(R.id.etdAddressValue);
            time = itemView.findViewById(R.id.etTimeValue);
            status = itemView.findViewById(R.id.etStatusValue);
            listenerRef = new WeakReference<>(listener);

            bDelivered = itemView.findViewById(R.id.bDelivery);
            bDelivered.setOnClickListener(this);
        }

        public void setSourceAddress(String address) {
            sAddress.setText(address);
        }

        public void setDestinationAddress(String dAdd) {
            dAddress.setText(dAdd);
        }

        public void setTime(String tm) {
            time.setText(tm);
        }

        public void setStatus(String sts) {
            status.setText(sts);
        }

        @Override
        public void onClick(View v) {
            listenerRef.get().onPositionClicked(getAdapterPosition(), true);
        }
    }
}
