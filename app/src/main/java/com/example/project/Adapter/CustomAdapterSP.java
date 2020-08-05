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

import com.example.project.GiaoDien.ChamCongActivity;
import com.example.project.GiaoDien.ChiTietCCActivity;
import com.example.project.Model.ChamCong;
import com.example.project.Model.SanPham;
import com.example.project.R;

import java.util.ArrayList;
import java.util.Locale;

public class CustomAdapterSP extends ArrayAdapter {
    Context context;
    int resource;
    ArrayList<SanPham> data;
    ArrayList<SanPham> data_DS;

    public CustomAdapterSP(Context context, int resource, ArrayList<SanPham> data) {
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
        TextView tvMaSP;
        TextView tvDonGia;
        ImageView imgAnh;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        CustomAdapterSP.Holder holder = null;
        if (view == null) {
            holder = new CustomAdapterSP.Holder();
            view = LayoutInflater.from(context).inflate(resource, null);
            holder.tvMaSP = view.findViewById(R.id.tvMaSP);
            holder.tvDonGia = view.findViewById(R.id.tvDonGia);
            holder.imgAnh = view.findViewById(R.id.imgAnhSP);
            view.setTag(holder);
        } else
            holder = (CustomAdapterSP.Holder) view.getTag();
        final SanPham sanPham = data.get(position);
        byte[] hinhAnh = sanPham.getAnhSP();
        Bitmap bitmap = BitmapFactory.decodeByteArray(hinhAnh,0,hinhAnh.length);
        holder.imgAnh.setImageBitmap(bitmap);
        holder.tvMaSP.setText(sanPham.getMaSP());
        holder.tvDonGia.setText(sanPham.getDonGia());

        return view;
    }

    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        data.clear();
        if (charText.length() == 0) {
            data.addAll(data_DS);
        } else {
            for (SanPham model : data_DS) {
                if (model.getMaSP().toLowerCase(Locale.getDefault())
                        .contains(charText)) {
                    data.add(model);
                }
            }
        }
        notifyDataSetChanged();
    }
}
