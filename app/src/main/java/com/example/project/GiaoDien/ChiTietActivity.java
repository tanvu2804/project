package com.example.project.GiaoDien;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.EditText;

import com.example.project.DataBase.DBCongNhan;
import com.example.project.Model.CongNhan;
import com.example.project.R;

import java.util.ArrayList;

public class ChiTietActivity extends AppCompatActivity {
    EditText txtMa, txtHo, txtTen, txtPhanXuong;
    ArrayList<CongNhan> data_cn = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet);
        setConTrol();
        setEvent();
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

    }

    private void setEvent() {
        String ma = getIntent().getExtras().getString("ma");
        DBCongNhan dbCongNhan = new DBCongNhan(this);
        data_cn = dbCongNhan.LayDL(ma);
        txtMa.setText(data_cn.get(0).getMaCN());
        txtHo.setText(data_cn.get(0).getHoCN());
        txtTen.setText(data_cn.get(0).getTenCN());
        txtPhanXuong.setText(data_cn.get(0).getPhanXuong());
    }


    private void setConTrol() {
        txtMa = findViewById(R.id.txtMa);
        txtHo = findViewById(R.id.txtHo);
        txtTen = findViewById(R.id.txtTen);
        txtPhanXuong = findViewById(R.id.txtPhanXuong);


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