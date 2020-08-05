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

import com.example.project.DataBase.DBCongNhan;
import com.example.project.GiaoDien.ChamCongActivity;
import com.example.project.Model.CongNhan;
import com.example.project.R;

import java.util.ArrayList;
import java.util.Locale;

public class CustomAdapterCN extends ArrayAdapter {
    Context context;
    int resource;
    ArrayList<CongNhan> data;
    ArrayList<CongNhan> data_DS;

    public CustomAdapterCN(Context context, int resource, ArrayList<CongNhan> data) {
        super(context, resource);
        this.context = context;
        this.data = data;
        DBCongNhan dbCongNhan = new DBCongNhan(context);
        this.data_DS = dbCongNhan.LayDL();
        this.resource = resource;

    }

    @Override
    public int getCount() {
        return data.size();
    }

    private static class Holder {
        TextView tvMaCN;
        TextView tvHoTenCN;
        ImageView imgPhanXuong;
        ImageView imgChiTiet;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        Holder holder = null;
        if (view == null) {
            holder = new Holder();
            view = LayoutInflater.from(context).inflate(resource, null);
            holder.tvMaCN = view.findViewById(R.id.tvMaCN);
            holder.tvHoTenCN = view.findViewById(R.id.tvHoTenCN);
            holder.imgPhanXuong = view.findViewById(R.id.imgPhanXuong);
            holder.imgChiTiet = view.findViewById(R.id.imgChiTiet);
            view.setTag(holder);
        } else
            holder = (Holder) view.getTag();

        final CongNhan congNhan = data.get(position);
        if (congNhan.getPhanXuong().equals("1")) {
            holder.imgPhanXuong.setImageResource(R.drawable.anhpx1);
        } else if (congNhan.getPhanXuong().equals("2")) {
            holder.imgPhanXuong.setImageResource(R.drawable.anhpx2);
        } else {
            holder.imgPhanXuong.setImageResource(R.drawable.anhpx3);
        }
        holder.tvMaCN.setText(congNhan.getMaCN());
        holder.tvHoTenCN.setText(congNhan.getHoCN() + congNhan.getTenCN());
        holder.imgChiTiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent((Activity) context, ChamCongActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("ma", congNhan.getMaCN());
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
            for (CongNhan model : data_DS) {
                String abc = model.getHoCN() + model.getTenCN();
                if (model.getMaCN().toLowerCase(Locale.getDefault()).contains(charText)
                        || abc.toLowerCase(Locale.getDefault()).contains(charText)) {
                    data.add(model);
                }
            }
        }
        notifyDataSetChanged();
    }
}
