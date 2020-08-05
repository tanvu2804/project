package com.example.project.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.project.Model.CardViewModel;
import com.example.project.Model.ChamCong;
import com.example.project.Model.CongNhan;

import java.util.ArrayList;

public class DBChamCong {
    DBHelper dbHelper;

    public DBChamCong(Context context) {
        dbHelper = new DBHelper(context);
    }

    public void Them(ChamCong chamCong) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("machamcong", chamCong.getMaCC());
        values.put("ngaychamcong", chamCong.getNgayCC());
        values.put("macongnhan", chamCong.getMaCN());
        db.insert("ChamCong", null, values);
        db.close();
    }

    public void Sua(ChamCong chamCong) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("machamcong", chamCong.getMaCC());
        values.put("ngaychamcong", chamCong.getNgayCC());
        values.put("macongnhan", chamCong.getMaCN());
        db.update("ChamCong", values, "machamcong ='" + chamCong.getMaCC() + "'", null);
    }


    public void Xoa(ChamCong chamCong) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String sql = "Delete from ChamCong where machamcong= '" + chamCong.getMaCC() + "'";
        db.execSQL(sql);

    }

    public ArrayList<ChamCong> LayDL() {
        ArrayList<ChamCong> data = new ArrayList<>();
        String sql = "select * from ChamCong";
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        do {
            ChamCong chamCong = new ChamCong();
            chamCong.setMaCC(cursor.getString(0));
            chamCong.setNgayCC(cursor.getString(1));
            chamCong.setMaCN(cursor.getString(2));
            data.add(chamCong);
        }
        while (cursor.moveToNext());

        return data;
    }

    public ArrayList<CardViewModel> LayDLCardView() {
        ArrayList<CardViewModel> data = new ArrayList<>();
        String sql = "select * from CongNhan";
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        do {
            CardViewModel cardViewModel = new CardViewModel();
            cardViewModel.setMa(cursor.getString(0));
            cardViewModel.setCardName(cursor.getString(1) + cursor.getString(2));
            data.add(cardViewModel);
        }
        while (cursor.moveToNext());

        return data;
    }

    public ArrayList<ChamCong> LayDL(String ma) {
        ArrayList<ChamCong> data = new ArrayList<>();
        String sql = "select * from ChamCong where macongnhan = '" + ma + "'";
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        try {
            do {
                ChamCong chamCong = new ChamCong();
                chamCong.setMaCC(cursor.getString(0));
                chamCong.setNgayCC(cursor.getString(1));
                chamCong.setMaCN(cursor.getString(2));
                data.add(chamCong);
            } while (cursor.moveToNext());
        } catch (Exception ex) {

        }


        return data;
    }
}

