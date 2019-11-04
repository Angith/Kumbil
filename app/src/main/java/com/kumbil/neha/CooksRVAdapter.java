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

public class CooksRVAdapter extends RecyclerView.Adapter<CooksRVAdapter.CooksRVViewHolder> {

    public ArrayList<Cooks> cookList;
    private Context mContext;
    
    public CooksRVAdapter(ArrayList<Cooks> data, Context context){
        this.cookList = data;
        this.mContext = context;
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

    public class CooksRVViewHolder extends RecyclerView.ViewHolder{

        private TextView cooksName;
        private TextView cooksPlace;

        public CooksRVViewHolder(View view) {
            super(view);
            cooksName = (TextView) itemView.findViewById(R.id.tvCookName);
            cooksPlace = (TextView) itemView.findViewById(R.id.tvCooksPlace);
        }

        public void setCooksName(String name) {
            cooksName.setText(name);
        }

        public void setCooksPlace(String place) {
            cooksPlace.setText(place);
        }
    }
}
