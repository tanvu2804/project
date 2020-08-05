package com.example.project.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.project.Model.SanPham;
import com.example.project.R;

import java.util.ArrayList;

public class CustomSpinnerSP extends BaseAdapter {
    Context context;
    int resource;
    ArrayList<SanPham> data;
    ArrayList<SanPham> data_DS;

    public CustomSpinnerSP(Context context, int resource, ArrayList<SanPham> data) {
        this.context = context;
        this.data = data;
        this.resource = resource;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private static class Holder {
        TextView tvDonGia;
        TextView tvMaSP;
        TextView tvTenSP;
        ImageView imgSanPham;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = convertView;
        CustomSpinnerSP.Holder holder = null;
        if (view == null) {
            holder = new CustomSpinnerSP.Holder();
            view = LayoutInflater.from(context).inflate(resource, null);
            holder.tvTenSP = view.findViewById(R.id.tvTenSP);
            holder.tvMaSP = view.findViewById(R.id.tvMaSP);
            holder.tvDonGia = view.findViewById(R.id.tvDonGia);
            holder.imgSanPham = view.findViewById(R.id.imgAnhSP);
            view.setTag(holder);
        } else
            holder = (CustomSpinnerSP.Holder) view.getTag();

        final SanPham sanPham = data.get(position);
        holder.tvMaSP.setText(sanPham.getMaSP());
        holder.tvTenSP.setText(sanPham.getTenSP());
        holder.tvDonGia.setText(sanPham.getDonGia());
        byte[] hinhAnh = sanPham.getAnhSP();
        Bitmap bitmap = BitmapFactory.decodeByteArray(hinhAnh, 0, hinhAnh.length);
        holder.imgSanPham.setImageBitmap(bitmap);
        return view;
    }
}
