package com.example.project.GiaoDien;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.GridView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.project.Adapter.CustomAdapterCN;
import com.example.project.Model.CongNhan;
import com.example.project.DataBase.DBCongNhan;
import com.example.project.R;

import java.util.ArrayList;


//import android.support.v7.app.AppCompatActivity;

public class MainActivityGridView extends AppCompatActivity {

    GridView gvDanhSachCN;


    CustomAdapterCN apdapter ;
    ArrayList<CongNhan> data_CN = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_gridview);
        setConTrol();
        setEvent();
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);


    }

    private void setEvent() {

        HienThiDL();



    }

    private  void HienThiDL()
    {
        DBCongNhan dbCongNhan = new DBCongNhan(this);
        data_CN = dbCongNhan.LayDL();
        apdapter = new CustomAdapterCN(this,R.layout.listview_item,data_CN);
        gvDanhSachCN.setAdapter(apdapter);
    }



    private void setConTrol() {
        gvDanhSachCN = findViewById(R.id.gvDanhSach);


    }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
