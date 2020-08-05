package com.example.project.GiaoDien;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project.Adapter.CustomAdapterCT;
import com.example.project.Adapter.CustomAdapterSP;
import com.example.project.DataBase.DBChiTietChamCong;
import com.example.project.DataBase.DBSanPham;
import com.example.project.Model.ChiTietChamCong;
import com.example.project.Model.SanPham;
import com.example.project.R;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;


public class SanPhamActivity extends AppCompatActivity {

    EditText txtMaSP, txtTenSP, txtDonGia;
    ImageView imgAnhSP;
    Button btnThem, btnXoa, btnSua, btnClear;
    ListView lvDanhSach;
    DBSanPham dbSanPham= new DBSanPham(this);
    final int REQUEST_CODE = 999;
    ArrayList<SanPham> data_sp = new ArrayList<>();


    ArrayAdapter adapter_sp;
    int index = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_san_pham);
        setControl();
        setEvent();
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    private void setEvent() {
        try {
            data_sp = dbSanPham.LayDL();
            setAdapter();
        } catch (Exception ex) {
        }

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBSanPham dbSanPham = new DBSanPham(getApplicationContext());
                SanPham sanPham = getSanPham();
                dbSanPham.Them(sanPham);
                updateChiTietChamCong();
                setAdapter();

            }
        });

        lvDanhSach.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SanPham sanPham = data_sp.get(position);
                txtMaSP.setText(sanPham.getMaSP());
                txtTenSP.setText(sanPham.getTenSP());
                txtDonGia.setText(sanPham.getDonGia());
                byte[] hinhAnh = sanPham.getAnhSP();
                Bitmap bitmap = BitmapFactory.decodeByteArray(hinhAnh, 0, hinhAnh.length);
                imgAnhSP.setImageBitmap(bitmap);
                index = position;
            }
        });

        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    SanPham sanPham = data_sp.get(index);
                    dbSanPham.Xoa(sanPham);
                    updateChiTietChamCong();
                    setAdapter();
                }catch (Exception ex){

                }
            }
        });

        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    DBSanPham dbSanPham = new DBSanPham(getApplicationContext());
                    SanPham sanPham = getSanPham();
                    dbSanPham.Sua(sanPham);
                    updateChiTietChamCong();
                    setAdapter();
                }catch (Exception ex){

                }
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtMaSP.setText("");
                txtTenSP.setText("");
                txtDonGia.setText("");
                imgAnhSP.setImageResource(R.drawable.anhsp1);

            }
        });
        imgAnhSP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityCompat.requestPermissions(SanPhamActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_CODE);

            }
        });
    }

    private void setAdapter() {
        if (adapter_sp == null) {
            adapter_sp = new CustomAdapterSP(this, R.layout.listview_item_sp, data_sp);
            lvDanhSach.setAdapter(adapter_sp);
        } else {
            adapter_sp.notifyDataSetChanged();
            lvDanhSach.setSelection(adapter_sp.getCount() - 1);
        }
    }

    public void updateChiTietChamCong() {
        data_sp.clear();
        data_sp.addAll(dbSanPham.LayDL());
        if (adapter_sp != null) {
            adapter_sp.notifyDataSetChanged();
        }
    }

    private SanPham getSanPham() {
        SanPham sanPham = new SanPham();
        sanPham.setMaSP(txtMaSP.getText().toString());
        sanPham.setTenSP(txtTenSP.getText().toString());
        sanPham.setDonGia(txtDonGia.getText().toString());
        sanPham.setAnhSP(imageViewToByte(imgAnhSP));
        return sanPham;
    }
    private byte[] imageViewToByte(ImageView imageView) {
        Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] bytes = stream.toByteArray();
        return bytes;
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CODE) {
            if (grantResults.length >= 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_CODE);
            } else {
                Toast.makeText(this, "Chưa cấp quyền truy cập!", Toast.LENGTH_SHORT).show();
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            Uri uri = data.getData();

            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);

                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imgAnhSP.setImageBitmap(bitmap);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    private void setControl() {
        txtMaSP = findViewById(R.id.txtMaSP);
        txtTenSP = findViewById(R.id.txtTenSP);
        txtDonGia = findViewById(R.id.txtDonGia);
        imgAnhSP = findViewById(R.id.imgSP);
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