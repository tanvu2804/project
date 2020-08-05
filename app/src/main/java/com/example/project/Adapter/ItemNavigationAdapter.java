package com.example.project.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.project.Model.ItemNavigation;
import com.example.project.R;

import java.util.ArrayList;

public class ItemNavigationAdapter extends ArrayAdapter {
    Context context;
    int resource;
    ArrayList<ItemNavigation> data;

    public ItemNavigationAdapter(Context context, int resource, ArrayList<ItemNavigation> data) {
        super(context, resource);
        this.context = context;
        this.resource = resource;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }



    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(resource, null);
        // ánh xạ
        ImageView imgCo = view.findViewById(R.id.imgNavigation);
        TextView tvTen = view.findViewById(R.id.tieuDeNavigation);
        // code
        imgCo.setImageResource(data.get(position).getIcon());
        tvTen.setText(data.get(position).getTieuDe());

        return view;

    }
}