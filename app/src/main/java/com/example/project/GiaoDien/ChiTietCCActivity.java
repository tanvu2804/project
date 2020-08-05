package com.example.project.GiaoDien;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;

import com.example.project.Adapter.CustomAdapterCT;
import com.example.project.Adapter.CustomAdapterSP;
import com.example.project.Adapter.CustomSpinnerSP;
import com.example.project.DataBase.DBChiTietChamCong;
import com.example.project.DataBase.DBSanPham;
import com.example.project.Model.ChiTietChamCong;
import com.example.project.Model.SanPham;
import com.example.project.R;

import java.util.ArrayList;

public class ChiTietCCActivity extends AppCompatActivity {

    EditText txtMaCc, txtStp, txtSpp;
    Spinner spSanPham;
    Button btnThem, btnXoa, btnSua, btnClear;
    ListView lvDanhSach;
    DBChiTietChamCong dbChiTietChamCong = new DBChiTietChamCong(this);
    DBSanPham dbSanPham = new DBSanPham(this);
    ArrayList<SanPham> data_sp = new ArrayList<>();
    ArrayList<ChiTietChamCong> data_ct = new ArrayList<>();

    CustomSpinnerSP adapter_sp;
    ArrayAdapter adapter_ct;
    int index = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_cc);
        setControl();
        setEvent();
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    private void setEvent() {

        String ma = getIntent().getExtras().getString("ma");
        try {
            KhoiTao();
            if (ma.equals("-1")) {
                data_ct = dbChiTietChamCong.LayDL();
                setAdapter();
            } else {
                data_ct = dbChiTietChamCong.LayDL(ma);
                setAdapter();
                txtMaCc.setText(ma);
            }


        } catch (Exception ex) {
        }

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBChiTietChamCong dbChiTietChamCong = new DBChiTietChamCong(getApplicationContext());
                ChiTietChamCong chiTietChamCong = getChiTietChamCong();
                dbChiTietChamCong.Them(chiTietChamCong);
                updateChiTietChamCong();
                setAdapter();

            }
        });

        lvDanhSach.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ChiTietChamCong chiTietChamCong = data_ct.get(position);
                txtMaCc.setText(chiTietChamCong.getMaCc());
                txtStp.setText(chiTietChamCong.getStp());
                txtSpp.setText(chiTietChamCong.getSpp());
                spSanPham.setSelection(data_sp.indexOf(chiTietChamCong.getMaSP()));
                index = position;
            }
        });

        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    ChiTietChamCong chiTietChamCong = data_ct.get(index);
                    dbChiTietChamCong.Xoa(chiTietChamCong);
                    updateChiTietChamCong();
                    setAdapter();
                } catch (Exception ex) {

                }
            }
        });

        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    DBChiTietChamCong dbChiTietChamCong = new DBChiTietChamCong(getApplicationContext());
                    ChiTietChamCong chiTietChamCong = getChiTietChamCong();
                    dbChiTietChamCong.Sua(chiTietChamCong);
                    updateChiTietChamCong();
                    setAdapter();
                } catch (Exception ex) {

                }
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtMaCc.setText("");
                txtStp.setText("");
                txtSpp.setText("");
                spSanPham.setSelection(0);

            }
        });

    }

    private void setAdapter() {
        if (adapter_ct == null) {
            adapter_ct = new CustomAdapterCT(this, R.layout.listview_item_ct, data_ct);
            lvDanhSach.setAdapter(adapter_ct);
        } else {
            adapter_ct.notifyDataSetChanged();
            lvDanhSach.setSelection(adapter_ct.getCount() - 1);
        }
    }

    public void updateChiTietChamCong() {
        String ma = getIntent().getExtras().getString("ma");
        data_ct.clear();
        if (ma.equals("-1")) {
            data_ct.addAll(dbChiTietChamCong.LayDL());
        } else {
            data_ct.addAll(dbChiTietChamCong.LayDL(ma));
        }

        if (adapter_ct != null) {
            adapter_ct.notifyDataSetChanged();
        }
    }

    private ChiTietChamCong getChiTietChamCong() {
        ChiTietChamCong chiTietChamCong = new ChiTietChamCong();
        chiTietChamCong.setMaCc(txtMaCc.getText().toString());
        chiTietChamCong.setStp(txtStp.getText().toString());
        chiTietChamCong.setSpp(txtSpp.getText().toString());
        chiTietChamCong.setMaSP(data_sp.get(spSanPham.getSelectedItemPosition()).getMaSP());
        chiTietChamCong.setTenSP(data_sp.get(spSanPham.getSelectedItemPosition()).getTenSP());
        chiTietChamCong.setDonGia(data_sp.get(spSanPham.getSelectedItemPosition()).getDonGia());
        chiTietChamCong.setAnhSP(data_sp.get(spSanPham.getSelectedItemPosition()).getAnhSP());
        return chiTietChamCong;
    }


    private void setControl() {
        txtMaCc = findViewById(R.id.txtMaCc);
        txtStp = findViewById(R.id.txtStp);
        txtSpp = findViewById(R.id.txtSpp);
        spSanPham = findViewById(R.id.spSanPham);
        btnThem = findViewById(R.id.btnThem);
        btnXoa = findViewById(R.id.btnXoa);
        btnSua = findViewById(R.id.btnSua);
        btnClear = findViewById(R.id.btnClear);
        lvDanhSach = findViewById(R.id.lvDanhSach);
    }

    private void KhoiTao() {
        data_sp = dbSanPham.LayDL();
        adapter_sp = new CustomSpinnerSP(this, R.layout.spinner_item, data_sp);
        spSanPham.setAdapter(adapter_sp);
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