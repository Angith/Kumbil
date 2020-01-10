package com.kumbil.neha;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class CooksRVAdapter extends RecyclerView.Adapter<CooksRVAdapter.CooksRVViewHolder> {

    public ArrayList<Cooks> cookList;
    private Context mContext;
    private final ClickListener listener;
    
    public CooksRVAdapter(ArrayList<Cooks> data, Context context, ClickListener listener){
        this.cookList = data;
        this.mContext = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CooksRVViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.cooks_list_layout, viewGroup, false);
        return new CooksRVViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CooksRVViewHolder cooksRVViewHolder, int position) {
        final Cooks cook = cookList.get(position);
        cooksRVViewHolder.setCooksName(cook.getCook());
        cooksRVViewHolder.setCooksPlace(cook.getPlace());
    }

    @Override
    public int getItemCount() {
        return cookList == null? 0: cookList.size();
    }

    public class CooksRVViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView cooksName;
        private TextView cooksPlace;
        private WeakReference<ClickListener> listenerRef;

        public CooksRVViewHolder(View view) {
            super(view);
            cooksName = (TextView) itemView.findViewById(R.id.tvCookName);
            cooksPlace = (TextView) itemView.findViewById(R.id.tvCooksPlace);
            listenerRef = new WeakReference<>(listener);
            itemView.setOnClickListener(this);
        }

        public void setCooksName(String name) {
            cooksName.setText(name);
        }

        public void setCooksPlace(String place) {
            cooksPlace.setText(place);
        }

        @Override
        public void onClick(View v) {
            listenerRef.get().onPositionClicked(getAdapterPosition(), true);
        }
    }
}
