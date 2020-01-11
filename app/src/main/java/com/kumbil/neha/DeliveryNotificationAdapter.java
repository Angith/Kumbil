package com.kumbil.neha;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kumbil.neha.models.dNotification;

import java.util.ArrayList;

public class DeliveryNotificationAdapter extends RecyclerView.Adapter<DeliveryNotificationAdapter.DeliveryNotificationViewHolder> {

    public ArrayList<dNotification> list;
    private Context mContext;

    public DeliveryNotificationAdapter(ArrayList<dNotification> data, Context context) {
        this.list = data;
        this.mContext = context;
    }

    @NonNull
    @Override
    public DeliveryNotificationAdapter.DeliveryNotificationViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.notification_list_layout, viewGroup, false);
        return new DeliveryNotificationAdapter.DeliveryNotificationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DeliveryNotificationAdapter.DeliveryNotificationViewHolder notificationViewHolder, int position) {
        final dNotification nfn = list.get(position);

        notificationViewHolder.setSourceAddress(nfn.getsAddress());
        notificationViewHolder.setDestinationAddress(nfn.getdAddress());
        notificationViewHolder.setTime(nfn.getdAddress());
        notificationViewHolder.setStatus(nfn.getStatus());
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public class DeliveryNotificationViewHolder extends RecyclerView.ViewHolder {

        private TextView sAddress;
        private TextView dAddress;
        private TextView time;
        private TextView status;


        public DeliveryNotificationViewHolder(@NonNull View itemView) {
            super(itemView);
            sAddress = itemView.findViewById(R.id.etsAddressValue);
            dAddress = itemView.findViewById(R.id.etdAddressValue);
            time = itemView.findViewById(R.id.etTimeValue);
            status = itemView.findViewById(R.id.etStatusValue);

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

    }
}
