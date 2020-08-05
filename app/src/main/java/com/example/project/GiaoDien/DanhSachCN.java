package com.example.project.GiaoDien;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
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

public class DanhSachCN extends AppCompatActivity {
    Button btnQLCN;
    ListView lvDanhSach;
    ListView listNavigation;
    DBCongNhan dbCongNhan = new DBCongNhan(this);

    ArrayList<CongNhan> data_cn = new ArrayList<>();
    CustomAdapterCN adapter_cn;
    int index = -1;

    ArrayList<ItemNavigation> navigationNames = new ArrayList<>();
    ArrayAdapter adapter_navigation;

    int navigationID = 0;
    DrawerLayout drawerLayout;

    ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.keo_navagation);

        FrameLayout mainLayout = findViewById(R.id.drawContainer);
        getLayoutInflater().inflate(R.layout.activity_danh_sach_c_n, mainLayout);

        setControl();
        setToggleDraw();
        setEvent();
    }

    private void setEvent() {

        try {
            data_cn = dbCongNhan.LayDL();
            setAdapter();
        } catch (Exception ex) {
        }
        dienThongTinNavigation();
        listNavigation.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                navigationID = position;
                if (navigationID == 0) {

                    Intent intent = new Intent(DanhSachCN.this, QLCongNhanActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("ma", "-1");
                    intent.putExtras(bundle);
                    startActivity(intent);
                    drawerLayout.closeDrawer(listNavigation);
                }
                if (navigationID == 1) {

                    Intent intent = new Intent(DanhSachCN.this, ChamCongActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("ma", "-1");
                    intent.putExtras(bundle);
                    startActivity(intent);
                    drawerLayout.closeDrawer(listNavigation);
                }
                if (navigationID == 2) {

                    Intent intent = new Intent(DanhSachCN.this, ChiTietCCActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("ma", "-1");
                    intent.putExtras(bundle);
                    startActivity(intent);

                    drawerLayout.closeDrawer(listNavigation);

                }
                if (navigationID == 3) {

                    Intent intent = new Intent(DanhSachCN.this, SanPhamActivity.class);
                    startActivity(intent);

                    drawerLayout.closeDrawer(listNavigation);

                }
            }
        });
        lvDanhSach.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                index = position;
            }
        });
        btnQLCN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DanhSachCN.this, QLCongNhanActivity.class);
                startActivity(intent);
            }
        });

    }

    private void setControl() {
        lvDanhSach = findViewById(R.id.lvDanhSach);
        btnQLCN = findViewById(R.id.btnQLCN);
        listNavigation = findViewById(R.id.draw);
        drawerLayout = findViewById(R.id.drawLayout);
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

    private void dienThongTinNavigation() {
        navigationNames.add(new ItemNavigation("Quản lý công nhân", R.drawable.nav1));
        navigationNames.add(new ItemNavigation("Chấm công", R.drawable.nav2));
        navigationNames.add(new ItemNavigation("Chi tiết chấm công", R.drawable.nav3));
        navigationNames.add(new ItemNavigation("Sản phẩm", R.drawable.nav4));
        adapter_navigation = new ItemNavigationAdapter(this, R.layout.navigation, navigationNames);
        listNavigation.setAdapter(adapter_navigation);
        registerForContextMenu(listNavigation);
    }

    public void updateCongNhan() {
        data_cn.clear();
        data_cn.addAll(dbCongNhan.LayDL());
        if (adapter_cn != null) {
            adapter_cn.notifyDataSetChanged();
        }
    }

    private void setToggleDraw() {

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().setHomeButtonEnabled(true);

        drawerToggle = new ActionBarDrawerToggle(DanhSachCN.this, drawerLayout, R.string.sua, R.string.them) {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };
        drawerLayout.setDrawerListener(drawerToggle);
        drawerToggle.syncState();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_actionbar_ds, menu);
        MenuItem menuItem = menu.findItem(R.id.mnuSearch);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint("Type here to search");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                adapter_cn.filter(newText);
                return true;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        } else {
            switch (item.getItemId()) {
                case android.R.id.home:
                    onBackPressed();
                    return true;
                case R.id.mnuIn:
                    try {
                        Intent intent = new Intent((Activity) DanhSachCN.this, DanhSachCNIn.class);
                        startActivity(intent);
                    } catch (Exception ex) {

                    }
                    break;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}