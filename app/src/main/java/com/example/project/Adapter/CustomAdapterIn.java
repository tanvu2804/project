package com.example.project.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.project.GiaoDien.ChiTietActivity;
import com.example.project.Model.ChiTietChamCong;
import com.example.project.Model.SanPham;
import com.example.project.R;

import java.util.ArrayList;
import java.util.Locale;

public class CustomAdapterIn extends ArrayAdapter {
    Context context;
    int resource;
    ArrayList<ChiTietChamCong> data;
    ArrayList<ChiTietChamCong> data_DS;
    public CustomAdapterIn(Context context, int resource, ArrayList<ChiTietChamCong> data) {
        super(context, resource);
        this.context=context;
        this.data= data;
        this.resource=resource;
    }

    @Override
    public int getCount() {
        return data.size();
    }
    private static class Holder {
        TextView tvSTT;
        TextView tvMaSP;
        TextView tvTenSP;
        TextView tvSTP;
        TextView tvSPP;
        TextView tvTC;
        TextView tvTT;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        Holder holder = null;
        if (view == null) {
            holder = new Holder();
            view = LayoutInflater.from(context).inflate(resource, null);
            holder.tvSTT= view.findViewById(R.id.tvSTT);
            holder.tvMaSP= view.findViewById(R.id.tvMaSP);
            holder.tvTenSP= view.findViewById(R.id.tvTenSP);
            holder.tvSTP= view.findViewById(R.id.tvSTP);
            holder.tvSPP= view.findViewById(R.id.tvSPP);
            holder.tvTC= view.findViewById(R.id.tvTC);
            holder.tvTT= view.findViewById(R.id.tvTT);

            view.setTag(holder);
        } else
            holder = (Holder) view.getTag();

        final ChiTietChamCong chiTietChamCong = data.get(position);
        int tienluong = (Integer.parseInt(chiTietChamCong.getStp()) * Integer.parseInt(chiTietChamCong.getDonGia()))
                - (Integer.parseInt(chiTietChamCong.getSpp()) * (Integer.parseInt(chiTietChamCong.getDonGia())/2));
        holder.tvSTT.setText(String.valueOf(position+1));
        holder.tvMaSP.setText(chiTietChamCong.getMaSP());
        holder.tvTenSP.setText(chiTietChamCong.getTenSP());
        holder.tvSTP.setText(chiTietChamCong.getStp());
        holder.tvSPP.setText(chiTietChamCong.getSpp());
        holder.tvTC.setText(chiTietChamCong.getDonGia());
        holder.tvTT.setText(String.valueOf(tienluong));

        return  view;
    }
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        data.clear();
        if (charText.length() == 0) {
            data.addAll(data_DS);
        } else {
            for (ChiTietChamCong model : data_DS) {
                if (model.getMaCc().toLowerCase(Locale.getDefault())
                        .contains(charText)) {
                    data.add(model);
                }
            }
        }
        notifyDataSetChanged();
    }
}
