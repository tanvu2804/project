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
import com.example.project.R;

import java.util.ArrayList;
import java.util.Locale;

public class CustomAdapterCT extends ArrayAdapter {
    Context context;
    int resource;
    ArrayList<ChiTietChamCong> data;
    ArrayList<ChiTietChamCong> data_DS;
    public CustomAdapterCT(Context context, int resource, ArrayList<ChiTietChamCong> data) {
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
        TextView tvMaCc;
        TextView tvStpSpp;
        ImageView imgSanPham;
        ImageView imgChiTiet;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        Holder holder = null;
        if (view == null) {
            holder = new Holder();
            view = LayoutInflater.from(context).inflate(resource, null);
            holder.tvMaCc= view.findViewById(R.id.tvMaCc);
            holder.tvStpSpp= view.findViewById(R.id.tvStpSpp);
            holder.imgSanPham= view.findViewById(R.id.imgSanPham);
            holder.imgChiTiet= view.findViewById(R.id.imgChiTiet);
            view.setTag(holder);
        } else
            holder = (Holder) view.getTag();

        final ChiTietChamCong chiTietChamCong = data.get(position);
        byte[] hinhAnh = chiTietChamCong.getAnhSP();
        Bitmap bitmap = BitmapFactory.decodeByteArray(hinhAnh,0,hinhAnh.length);
        holder.imgSanPham.setImageBitmap(bitmap);
        holder.tvMaCc.setText(chiTietChamCong.getMaCc());
        holder.tvStpSpp.setText("Thành phẩm: " + chiTietChamCong.getStp() + " Phế phẩm: " + chiTietChamCong.getSpp());
        holder.imgChiTiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent((Activity) context, ChiTietActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("ma", chiTietChamCong.getMaCc());
                intent.putExtras(bundle);
                ((Activity) context).startActivity(intent);


            }
        });
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
