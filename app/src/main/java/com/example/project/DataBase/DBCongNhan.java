package com.example.project.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.project.Model.CardViewModel;
import com.example.project.Model.CongNhan;

import java.util.ArrayList;

public class DBCongNhan {
    DBHelper dbHelper;

    public DBCongNhan(Context context) {
        dbHelper= new DBHelper(context);
    }

    public void Them(CongNhan congNhan)
    {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("macongnhan",congNhan.getMaCN());
        values.put("hocongnhan",congNhan.getHoCN());
        values.put("tencongnhan",congNhan.getTenCN());
        values.put("phanxuong",congNhan.getPhanXuong());
        db.insert("CongNhan",null,values);
        db.close();
    }

    public  void Sua(CongNhan congNhan)
    {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("macongnhan",congNhan.getMaCN());
        values.put("hocongnhan",congNhan.getHoCN());
        values.put("tencongnhan",congNhan.getTenCN());
        values.put("phanxuong",congNhan.getPhanXuong());
        db.update("CongNhan",values,"macongnhan ='"+congNhan.getMaCN() +"'",null);
    }


    public  void Xoa(CongNhan congNhan)
    {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String sql ="Delete from CongNhan where macongnhan= '"+congNhan.getMaCN()+"'";
        db.execSQL(sql);

    }

    public ArrayList<CongNhan> LayDL()
    {
        ArrayList<CongNhan> data = new ArrayList<>();
        String sql="select * from CongNhan";
        SQLiteDatabase db= dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql,null);
        cursor.moveToFirst();
        do {
            CongNhan congNhan = new CongNhan();
            congNhan.setMaCN(cursor.getString(0));
            congNhan.setHoCN(cursor.getString(1));
            congNhan.setTenCN(cursor.getString(2));
            congNhan.setPhanXuong(cursor.getString(3));
            data.add(congNhan);
        }
        while (cursor.moveToNext());

        return  data;
    }
    public ArrayList<CardViewModel> LayDLCardView()
    {
        ArrayList<CardViewModel> data = new ArrayList<>();
        String sql="select * from CongNhan";
        SQLiteDatabase db= dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql,null);
        cursor.moveToFirst();
        do {
            CardViewModel cardViewModel = new CardViewModel();
            cardViewModel.setMa(cursor.getString(0));
            cardViewModel.setCardName(cursor.getString(1) + cursor.getString(2));
            data.add(cardViewModel);
        }
        while (cursor.moveToNext());

        return  data;
    }
    public ArrayList<CongNhan> LayDL(String ma)
    {
        ArrayList<CongNhan> data = new ArrayList<>();
        String sql="select * from CongNhan where macongnhan = '"+ma+"'";
        SQLiteDatabase db= dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql,null);
        cursor.moveToFirst();
        try {
            do {
                CongNhan congNhan = new CongNhan();
                congNhan.setMaCN(cursor.getString(0));
                congNhan.setHoCN(cursor.getString(1));
                congNhan.setTenCN(cursor.getString(2));
                congNhan.setPhanXuong(cursor.getString(3));
                data.add(congNhan);
            }
            while (cursor.moveToNext());
        }catch (Exception ex)
        {

        }


        return  data;
    }
}
