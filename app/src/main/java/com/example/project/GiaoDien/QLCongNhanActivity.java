package com.example.project.GiaoDien;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.project.Adapter.CustomAdapterCN;
import com.example.project.DataBase.DBCongNhan;
import com.example.project.Model.CongNhan;
import com.example.project.R;

import java.util.ArrayList;

public class QLCongNhanActivity extends AppCompatActivity {

    EditText txtMaCongNhan, txtHoCongNhan, txtTenCongNhan;
    Spinner spPhanXuong;
    Button btnThem, btnXoa, btnSua, btnClear;
    ListView lvDanhSach;
    DBCongNhan dbCongNhan = new DBCongNhan(this);

    ArrayList<String> data_px = new ArrayList<>();
    ArrayList<CongNhan> data_cn = new ArrayList<>();


    ArrayAdapter adapter_px;
    ArrayAdapter adapter_cn;
    int index = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q_l_cong_nhan);
        setControl();
        setEvent();
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    private void setEvent() {
        KhoiTao();
        try {
            data_cn = dbCongNhan.LayDL();
            setAdapter();
        } catch (Exception ex) {
        }

        adapter_px = new ArrayAdapter(this, android.R.layout.simple_spinner_item, data_px);
        spPhanXuong.setAdapter(adapter_px);

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBCongNhan dbCongNhan = new DBCongNhan(getApplicationContext());
                CongNhan congNhan = getCongNhan();
                dbCongNhan.Them(congNhan);
                updateCongNhan();
                setAdapter();
            }
        });


        lvDanhSach.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CongNhan congNhan = data_cn.get(position);
                txtMaCongNhan.setText(congNhan.getMaCN());
                txtHoCongNhan.setText(congNhan.getHoCN());
                txtTenCongNhan.setText(congNhan.getTenCN());
                spPhanXuong.setSelection(data_px.indexOf(congNhan.getPhanXuong()));
                index = position;
            }
        });

        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    CongNhan congNhan = data_cn.get(index);
                    dbCongNhan.Xoa(congNhan);
                    updateCongNhan();
                    setAdapter();
                }catch (Exception ex){

                }
            }
        });

        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    DBCongNhan dbCongNhan = new DBCongNhan(getApplicationContext());
                    CongNhan congNhan = getCongNhan();
                    dbCongNhan.Sua(congNhan);
                    updateCongNhan();
                    setAdapter();
                }catch (Exception ex){

                }
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtMaCongNhan.setText("");
                txtHoCongNhan.setText("");
                txtTenCongNhan.setText("");
                spPhanXuong.setSelection(0);

            }
        });

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

    public void updateCongNhan() {
        data_cn.clear();
        data_cn.addAll(dbCongNhan.LayDL());
        if (adapter_cn != null) {
            adapter_cn.notifyDataSetChanged();
        }
    }

    private CongNhan getCongNhan() {
        CongNhan congNhan = new CongNhan();
        congNhan.setMaCN(txtMaCongNhan.getText().toString());
        congNhan.setHoCN(txtHoCongNhan.getText().toString());
        congNhan.setTenCN(txtTenCongNhan.getText().toString());
        congNhan.setPhanXuong(spPhanXuong.getSelectedItem().toString());
        return congNhan;
    }


    private void setControl() {
        txtMaCongNhan = findViewById(R.id.txtMaCN);
        txtHoCongNhan = findViewById(R.id.txtHoCN);
        txtTenCongNhan = findViewById(R.id.txtTenCN);
        spPhanXuong = findViewById(R.id.spPhanXuong);
        btnThem = findViewById(R.id.btnThem);
        btnXoa = findViewById(R.id.btnXoa);
        btnSua = findViewById(R.id.btnSua);
        btnClear = findViewById(R.id.btnClear);
        lvDanhSach = findViewById(R.id.lvDanhSach);
    }

    private void KhoiTao() {
        data_px.add("1");
        data_px.add("2");
        data_px.add("3");

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent1 = new Intent((Activity) this, DanhSachCN.class);
                startActivity(intent1);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}