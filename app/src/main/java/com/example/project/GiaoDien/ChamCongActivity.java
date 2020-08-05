package com.example.project.GiaoDien;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

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

import com.example.project.Adapter.CustomAdapterCC;
import com.example.project.DataBase.DBChamCong;
import com.example.project.DataBase.DBCongNhan;
import com.example.project.Model.ChamCong;
import com.example.project.Model.CongNhan;
import com.example.project.R;

import java.util.ArrayList;

public class ChamCongActivity extends AppCompatActivity {

    EditText txtMaChamCong, txtNgayChamCong, txtMaCongNhan;
    Button btnThem, btnXoa, btnSua, btnClear;
    ListView lvDanhSach;
    DBChamCong dbChamCong = new DBChamCong(ChamCongActivity.this);

    ArrayList<ChamCong> data_cc = new ArrayList<>();

    ArrayAdapter adapter_cc;
    int index = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cham_cong);
        setControl();
        setEvent();
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    private void setEvent() {
        String ma = getIntent().getExtras().getString("ma");

        try {
            if (ma.equals("-1"))
            {
                data_cc = dbChamCong.LayDL();
                setAdapter();
            }else
            {
                data_cc = dbChamCong.LayDL(ma);
                setAdapter();
                txtMaCongNhan.setText(ma);
            }


        } catch (Exception ex) {
        }



        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBChamCong dbChamCong = new DBChamCong(getApplicationContext());
                ChamCong chamCong = getCC();
                dbChamCong.Them(chamCong);
                update();
                setAdapter();

            }
        });


        lvDanhSach.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ChamCong chamCong = data_cc.get(position);
                txtMaChamCong.setText(chamCong.getMaCC());
                txtNgayChamCong.setText(chamCong.getNgayCC());
                txtMaCongNhan.setText(chamCong.getMaCN());
                index = position;
            }
        });

        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    ChamCong chamCong = data_cc.get(index);
                    dbChamCong.Xoa(chamCong);
                    update();
                    setAdapter();
                }catch (Exception ex){

                }
            }
        });

        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    DBChamCong dbChamCong = new DBChamCong(getApplicationContext());
                    ChamCong chamCong = getCC();
                    dbChamCong.Sua(chamCong);
                    update();
                    setAdapter();
                }catch (Exception ex){

                }
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtMaChamCong.setText("");
                txtNgayChamCong.setText("");
                txtMaCongNhan.setText("");

            }
        });

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

    public void update() {
        String ma = getIntent().getExtras().getString("ma");

        data_cc.clear();
        if (ma.equals("-1"))
        {
            data_cc.addAll(dbChamCong.LayDL());
        }
        else
        {
            data_cc.addAll(dbChamCong.LayDL(ma));
        }
        if (adapter_cc != null) {
            adapter_cc.notifyDataSetChanged();
        }
    }

    private ChamCong getCC() {
        ChamCong chamCong = new ChamCong();
        chamCong.setMaCC(txtMaChamCong.getText().toString());
        chamCong.setNgayCC(txtNgayChamCong.getText().toString());
        chamCong.setMaCN(txtMaCongNhan.getText().toString());
        return chamCong;
    }


    private void setControl() {
        txtMaCongNhan = findViewById(R.id.txtMaCN);
        txtMaChamCong = findViewById(R.id.txtMaCC);
        txtNgayChamCong = findViewById(R.id.txtNgayCC);
        btnThem = findViewById(R.id.btnThem);
        btnXoa = findViewById(R.id.btnXoa);
        btnSua = findViewById(R.id.btnSua);
        btnClear = findViewById(R.id.btnClear);
        lvDanhSach = findViewById(R.id.lvDanhSach);
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