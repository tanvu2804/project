package com.example.project.DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper( Context context) {
        super(context, "QLCongNhan", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql="create table CongNhan(macongnhan text, hocongnhan Text , tencongnhan text ,phanxuong Text)";
        db.execSQL(sql);
        String sql1="create table ChamCong(machamcong text, ngaychamcong Text , macongnhan text)";
        db.execSQL(sql1);
        String sql3="create table ChiTietChamCong(machamcong text, sothanhpham Text , sophepham text ,masanpham text, tensanpham Text , dongia text ,anhsanpham Text)";
        db.execSQL(sql3);
        String sql4="create table SanPham(masanpham text, tensanpham Text , dongia text ,anhsanpham Text)";
        db.execSQL(sql4);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

