package com.example.project.GiaoDien;

//import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project.Adapter.MyRecyclerViewAdapter;
import com.example.project.DataBase.DBCongNhan;
import com.example.project.Model.CardViewModel;
import com.example.project.R;

import java.util.ArrayList;
//import android.support.v7.widget.GridLayoutManager;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;

public class MainRecyclerView extends AppCompatActivity {
    ArrayList<CardViewModel> data = new ArrayList<>();
    RecyclerView recyclerView;
    int position = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_view_layout);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);


        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        //Initiation of data
        DBCongNhan dbCongNhan = new DBCongNhan(this);
        data = dbCongNhan.LayDLCardView();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //Setup Recycler View
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        //GridLayoutManager layoutManager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(layoutManager);

        MyRecyclerViewAdapter adapter = new MyRecyclerViewAdapter(R.layout.card_view_layout, data);
        recyclerView.setAdapter(adapter);
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
