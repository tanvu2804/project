package com.example.project.GiaoDien;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ListView;

import com.example.project.Adapter.CustomAdapterCC;
import com.example.project.Adapter.CustomAdapterCN;
import com.example.project.Adapter.ItemNavigationAdapter;
import com.example.project.DataBase.DBChamCong;
import com.example.project.DataBase.DBCongNhan;
import com.example.project.Model.ChamCong;
import com.example.project.Model.CongNhan;
import com.example.project.Model.ItemNavigation;
import com.example.project.Model.SanPham;
import com.example.project.R;

import java.util.ArrayList;

public class DanhSachCC extends AppCompatActivity {
    ListView lvDanhSach;
    DBChamCong dbChamCong = new DBChamCong(this);

    ArrayList<ChamCong> data_cc = new ArrayList<>();
    ArrayAdapter adapter_cc;
    int index = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_c_c);

        setControl();
        setEvent();
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    private void setEvent() {
        String ma = getIntent().getExtras().getString("ma");
        try {
            data_cc = dbChamCong.LayDL(ma);
            setAdapter();
        } catch (Exception ex) {
        }
        lvDanhSach.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(DanhSachCC.this, InActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("macc", data_cc.get(position).getMaCC());
                bundle.putString("ngaycc", data_cc.get(position).getNgayCC());
                bundle.putString("macn", data_cc.get(position).getMaCN());
                bundle.putString("hoten", getIntent().getExtras().getString("hoten"));
                bundle.putString("phanxuong", getIntent().getExtras().getString("phanxuong"));
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

    }
    private void setControl() {
        lvDanhSach = findViewById(R.id.lvDanhSachCC);
    }
    private void setAdapter() {
        if (adapter_cc == null) {
            adapter_cc = new CustomAdapterCC(this, R.layout.listview_item_cc, data_cc);
            lvDanhSach.setAdapter(adapter_cc);
        } else {
            adapter_cc.notifyDataSetChanged();
            lvDanhSach.setSelection(adapter_cc.getCount() - 1);
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