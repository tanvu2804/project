package com.example.project.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.project.Model.CardViewModel;
import com.example.project.Model.ChiTietChamCong;

import java.util.ArrayList;

public class DBChiTietChamCong {
    DBHelper dbHelper;

    public DBChiTietChamCong(Context context) {
        dbHelper= new DBHelper(context);
    }

    public void Them(ChiTietChamCong chiTietChamCong)
    {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("machamcong",chiTietChamCong.getMaCc());
        values.put("sothanhpham",chiTietChamCong.getStp());
        values.put("sophepham",chiTietChamCong.getSpp());
        values.put("masanpham",chiTietChamCong.getMaSP());
        values.put("tensanpham",chiTietChamCong.getTenSP());
        values.put("dongia",chiTietChamCong.getDonGia());
        values.put("anhsanpham",chiTietChamCong.getAnhSP());
        db.insert("ChiTietChamCong",null,values);
        db.close();
    }

    public  void Sua(ChiTietChamCong chiTietChamCong)
    {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("machamcong",chiTietChamCong.getMaCc());
        values.put("sothanhpham",chiTietChamCong.getStp());
        values.put("sophepham",chiTietChamCong.getSpp());
        values.put("masanpham",chiTietChamCong.getMaSP());
        values.put("tensanpham",chiTietChamCong.getTenSP());
        values.put("dongia",chiTietChamCong.getDonGia());
        values.put("anhsanpham",chiTietChamCong.getAnhSP());
        db.update("ChiTietChamCong",values,"machamcong ='"+chiTietChamCong.getMaCc() +"'",null);
    }


    public  void Xoa(ChiTietChamCong chiTietChamCong)
    {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String sql ="Delete from ChiTietChamCong where machamcong= '"+chiTietChamCong.getMaCc()+"'";
        db.execSQL(sql);

    }

    public ArrayList<ChiTietChamCong> LayDL()
    {
        ArrayList<ChiTietChamCong> data = new ArrayList<>();
        String sql="select * from ChiTietChamCong";
        SQLiteDatabase db= dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql,null);
        cursor.moveToFirst();
        do {
            ChiTietChamCong chiTietChamCong = new ChiTietChamCong();
            chiTietChamCong.setMaCc(cursor.getString(0));
            chiTietChamCong.setStp(cursor.getString(1));
            chiTietChamCong.setSpp(cursor.getString(2));
            chiTietChamCong.setMaSP(cursor.getString(3));
            chiTietChamCong.setTenSP(cursor.getString(4));
            chiTietChamCong.setDonGia(cursor.getString(5));
            chiTietChamCong.setAnhSP(cursor.getBlob(6));
            data.add(chiTietChamCong);
        }
        while (cursor.moveToNext());

        return  data;
    }
    public ArrayList<CardViewModel> LayDLCardView()
    {
        ArrayList<CardViewModel> data = new ArrayList<>();
        String sql="select * from ChiTietChamCong";
        SQLiteDatabase db= dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql,null);
        cursor.moveToFirst();
        do {
            CardViewModel cardViewModel = new CardViewModel();
            cardViewModel.setMa(cursor.getString(3));
            cardViewModel.setCardName(cursor.getString(0));
            data.add(cardViewModel);
        }
        while (cursor.moveToNext());

        return  data;
    }
    public ArrayList<ChiTietChamCong> LayDL(String ma)
    {
        ArrayList<ChiTietChamCong> data = new ArrayList<>();
        String sql="select * from ChiTietChamCong where machamcong = '"+ma+"'";
        SQLiteDatabase db= dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql,null);
        cursor.moveToFirst();
        try {
            do {
                ChiTietChamCong chiTietChamCong = new ChiTietChamCong();
                chiTietChamCong.setMaCc(cursor.getString(0));
                chiTietChamCong.setStp(cursor.getString(1));
                chiTietChamCong.setSpp(cursor.getString(2));
                chiTietChamCong.setMaSP(cursor.getString(3));
                chiTietChamCong.setTenSP(cursor.getString(4));
                chiTietChamCong.setDonGia(cursor.getString(5));
                chiTietChamCong.setAnhSP(cursor.getBlob(6));
                data.add(chiTietChamCong);
            }
            while (cursor.moveToNext());
        }catch (Exception ex)
        {

        }
        return  data;
    }
    public ArrayList<String> LayMaSP(String ma)
    {
        ArrayList<String> data = new ArrayList<>();
        String sql="select * from ChiTietChamCong where machamcong = '"+ma+"'";
        SQLiteDatabase db= dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql,null);
        cursor.moveToFirst();
        try {
            do {
                data.add(cursor.getString(3));
            }
            while (cursor.moveToNext());
        }catch (Exception ex)
        {

        }
        return  data;
    }
}
