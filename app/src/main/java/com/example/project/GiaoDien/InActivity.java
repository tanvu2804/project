package com.example.project.GiaoDien;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.project.Adapter.CustomAdapterCN;
import com.example.project.Adapter.CustomAdapterIn;
import com.example.project.DataBase.DBChiTietChamCong;
import com.example.project.DataBase.DBCongNhan;
import com.example.project.DataBase.DBSanPham;
import com.example.project.Model.ChiTietChamCong;
import com.example.project.Model.CongNhan;
import com.example.project.Model.SanPham;
import com.example.project.R;

import java.util.ArrayList;

public class InActivity extends AppCompatActivity {
    TextView tvSTT, tvMaCN, tvTenCN, tvPX, tvMaCC, tvNgayCC, tvTongCong;
    ListView lvIn;
    DBSanPham dbSanPham = new DBSanPham(this);
    DBChiTietChamCong dbChiTietChamCong = new DBChiTietChamCong(this);
    ArrayList<ChiTietChamCong> data_ct = new ArrayList<>();
    ArrayAdapter adapter_in;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in);
        setControl();
        setEvent();
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }
    private void setEvent() {

        try {
            String ma = getIntent().getExtras().getString("macc");
            data_ct = dbChiTietChamCong.LayDL(ma);
            adapter_in = new CustomAdapterIn(this, R.layout.listview_item_in, data_ct);
            lvIn.setAdapter(adapter_in);
        } catch (Exception ex) {
        }
        tvMaCN.setText(getIntent().getExtras().getString("macn"));
        tvTenCN.setText(getIntent().getExtras().getString("hoten"));
        tvPX.setText(getIntent().getExtras().getString("phanxuong"));
        tvMaCC.setText(getIntent().getExtras().getString("macc"));
        tvNgayCC.setText(getIntent().getExtras().getString("ngaycc"));
        int tong = 0;
        for (int i = 0; i < data_ct.size(); i++){
            tong += (Integer.parseInt(data_ct.get(i).getStp()) * Integer.parseInt(data_ct.get(i).getDonGia()))
                    - (Integer.parseInt(data_ct.get(i).getSpp())*(Integer.parseInt(data_ct.get(i).getDonGia())/2));
        }
        tvTongCong.setText(String.valueOf(tong));
    }

    private void setControl() {
        tvSTT = findViewById(R.id.tvSTT);
        lvIn = findViewById(R.id.lvIn);
        tvMaCN = findViewById(R.id.tvMaCN1);
        tvTenCN = findViewById(R.id.tvTenCN1);
        tvPX = findViewById(R.id.tvPX1);
        tvMaCC = findViewById(R.id.tvMaCC1);
        tvNgayCC = findViewById(R.id.tvNgayCC1);
        tvTongCong = findViewById(R.id.tvTongCong);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}