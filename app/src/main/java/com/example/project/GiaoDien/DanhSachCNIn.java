package com.example.project.GiaoDien;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ListView;

import com.example.project.Adapter.CustomAdapterCN;
import com.example.project.Adapter.ItemNavigationAdapter;
import com.example.project.DataBase.DBCongNhan;
import com.example.project.Model.CongNhan;
import com.example.project.Model.ItemNavigation;
import com.example.project.Model.SanPham;
import com.example.project.R;

import java.util.ArrayList;

public class DanhSachCNIn extends AppCompatActivity {
    ListView lvDanhSach;
    DBCongNhan dbCongNhan = new DBCongNhan(this);

    ArrayList<CongNhan> data_cn = new ArrayList<>();
    ArrayAdapter adapter_cn;
    int index = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_c_n_in);

        setControl();
        setEvent();
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    private void setEvent() {

        try {
            data_cn = dbCongNhan.LayDL();
            setAdapter();
        } catch (Exception ex) {
        }
        lvDanhSach.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(DanhSachCNIn.this, DanhSachCC.class);
                Bundle bundle = new Bundle();
                bundle.putString("ma", data_cn.get(position).getMaCN());
                bundle.putString("hoten", data_cn.get(position).getHoCN() + data_cn.get(position).getTenCN());
                bundle.putString("phanxuong", data_cn.get(position).getPhanXuong());
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

    }
    private void setControl() {
        lvDanhSach = findViewById(R.id.lvDanhSachCNIn);
    }
    private void setAdapter() {
        if (adapter_cn == null) {
            adapter_cn = new CustomAdapterCN(this, R.layout.listview_item, data_cn);
            lvDanhSach.setAdapter(adapter_cn);
        } else {
            adapter_cn.notifyDataSetChanged();
            lvDanhSach.setSelection(adapter_cn.getCount() - 1);
        }
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