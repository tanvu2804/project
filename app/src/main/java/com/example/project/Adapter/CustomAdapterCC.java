package com.example.project.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.project.GiaoDien.ChiTietCCActivity;
import com.example.project.Model.ChamCong;
import com.example.project.GiaoDien.ChiTietActivity;
import com.example.project.Model.CongNhan;
import com.example.project.R;

import java.util.ArrayList;
import java.util.Locale;

public class CustomAdapterCC extends ArrayAdapter {
    Context context;
    int resource;
    ArrayList<ChamCong> data;
    ArrayList<ChamCong> data_DS;

    public CustomAdapterCC(Context context, int resource, ArrayList<ChamCong> data) {
        super(context, resource);
        this.context = context;
        this.data = data;
        this.resource = resource;

    }

    @Override
    public int getCount() {
        return data.size();
    }

    private static class Holder {
        TextView tvMaCC;
        TextView tvMaCN;
        TextView tvNgayCC;
        ImageView imgChiTiet;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        Holder holder = null;
        if (view == null) {
            holder = new Holder();
            view = LayoutInflater.from(context).inflate(resource, null);
            holder.tvMaCC = view.findViewById(R.id.tvMaCC);
            holder.tvMaCN = view.findViewById(R.id.tvMaCN);
            holder.tvNgayCC = view.findViewById(R.id.tvNgayCC);
            holder.imgChiTiet = view.findViewById(R.id.imgChiTiet);
            view.setTag(holder);
        } else
            holder = (Holder) view.getTag();
        final ChamCong chamCong = data.get(position);
        holder.tvMaCC.setText(chamCong.getMaCC());
        holder.tvMaCN.setText(chamCong.getMaCN());
        holder.tvNgayCC.setText(chamCong.getNgayCC());
        holder.imgChiTiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent((Activity) context, ChiTietCCActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("ma", chamCong.getMaCC());
                intent.putExtras(bundle);
                ((Activity) context).startActivity(intent);
            }
        });
        return view;
    }

    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        data.clear();
        if (charText.length() == 0) {
            data.addAll(data_DS);
        } else {
            for (ChamCong model : data_DS) {
                if (model.getMaCC().toLowerCase(Locale.getDefault())
                        .contains(charText)) {
                    data.add(model);
                }
            }
        }
        notifyDataSetChanged();
    }
}
