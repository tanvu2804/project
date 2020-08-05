package com.example.project.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.project.Model.CardViewModel;
import com.example.project.Model.SanPham;

import java.util.ArrayList;

public class DBSanPham {
    DBHelper dbHelper;

    public DBSanPham(Context context) {
        dbHelper= new DBHelper(context);
    }

    public void Them(SanPham sanPham)
    {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("masanpham",sanPham.getMaSP());
        values.put("tensanpham",sanPham.getTenSP());
        values.put("dongia",sanPham.getDonGia());
        values.put("anhsanpham",sanPham.getAnhSP());
        db.insert("SanPham",null,values);
        db.close();
    }

    public  void Sua(SanPham sanPham)
    {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("masanpham",sanPham.getMaSP());
        values.put("tensanpham",sanPham.getTenSP());
        values.put("dongia",sanPham.getDonGia());
        values.put("anhsanpham",sanPham.getAnhSP());
        db.update("SanPham",values,"masanpham ='"+sanPham.getMaSP() +"'",null);
    }


    public  void Xoa(SanPham sanPham)
    {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String sql ="Delete from SanPham where masanpham= '"+sanPham.getMaSP()+"'";
        db.execSQL(sql);

    }

    public ArrayList<SanPham> LayDL()
    {
        ArrayList<SanPham> data = new ArrayList<>();
        String sql="select * from SanPham";
        SQLiteDatabase db= dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql,null);
        cursor.moveToFirst();
        do {
            SanPham sanPham = new SanPham();
            sanPham.setMaSP(cursor.getString(0));
            sanPham.setTenSP(cursor.getString(1));
            sanPham.setDonGia(cursor.getString(2));
            sanPham.setAnhSP(cursor.getBlob(3));
            data.add(sanPham);
        }
        while (cursor.moveToNext());

        return  data;
    }
    public ArrayList<SanPham> LayDL(String ma)
    {
        ArrayList<SanPham> data = new ArrayList<>();
        String sql="select * from SanPham where masanpham = '"+ma+"'";
        SQLiteDatabase db= dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql,null);
        cursor.moveToFirst();
        try {
            do {
                SanPham sanPham = new SanPham();
                sanPham.setMaSP(cursor.getString(0));
                sanPham.setTenSP(cursor.getString(1));
                sanPham.setDonGia(cursor.getString(2));
                sanPham.setAnhSP(cursor.getBlob(3));
                data.add(sanPham);
            }
            while (cursor.moveToNext());
        }catch (Exception ex)
        {

        }
        return  data;
    }
    public ArrayList<String> LayTenSP()
    {
        ArrayList<String> data = new ArrayList<>();
        String sql="select * from SanPham";
        SQLiteDatabase db= dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql,null);
        cursor.moveToFirst();
        try {
            do {
                data.add(cursor.getString(0));
            }
            while (cursor.moveToNext());
        }catch (Exception ex)
        {

        }
        return  data;
    }
}
