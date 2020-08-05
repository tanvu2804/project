package com.example.project.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project.Model.CardViewModel;
import com.example.project.R;

import java.util.ArrayList;


//import android.support.annotation.NonNull;
//import android.support.v7.widget.CardView;
//import android.support.v7.widget.RecyclerView;


public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder> {
    private int layoutID;
    private ArrayList<CardViewModel> data;

    public MyRecyclerViewAdapter(int layoutID, ArrayList<CardViewModel> data) {
        this.layoutID = layoutID;
        this.data = data;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView txtView;
        private TextView txtText;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtView = (TextView) itemView.findViewById(R.id.txtView);
            txtText = (TextView) itemView.findViewById(R.id.txtText);
        }
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        CardView viewItem = (CardView) inflater.inflate(layoutID, viewGroup, false);
        return new MyViewHolder(viewItem);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder viewHolder, int i) {
        CardViewModel cardViewModel = data.get(i);
        viewHolder.txtView.setText(cardViewModel.getMa());
        viewHolder.txtText.setText(cardViewModel.getCardName());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
