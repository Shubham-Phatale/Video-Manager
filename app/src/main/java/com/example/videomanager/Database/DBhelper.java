package com.example.videomanager.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DBhelper extends SQLiteOpenHelper {
    String Create_table = "create table User(u_name text not null,u_email text primary key not null," +
            "u_mobnumber integer not null,u_pass text not null)";
    String Create_table2 = "create table Category(cat_name text not null)";
    String Create_table3 = "create table Videocode(v_name text not null,v_code text not null,v_cat text not null)";

    public DBhelper(Context context) {
        super(context, "userinfo.db", null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Create_table);
        Log.i("SHUB", "Table User created successfully");
        db.execSQL(Create_table2);
        Log.i("SHUB", "Table Category created successfully");
        db.execSQL(Create_table3);
        Log.i("SHUB", "Table Category created successfully");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop table if exists User");
        db.execSQL("drop table if exists Category");
        db.execSQL("drop table if exists Videocode");

    }

    //Inserting the Values
    public boolean insert(String name, String email, String phone_no, String Pass) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("u_name", name);
        cv.put("u_email", email);
        cv.put("u_mobnumber", phone_no);
        cv.put("u_pass", Pass);
        long ins = db.insert("User", null, cv);
        if (ins == -1) return false;
        else return true;
    }

    //Inserting category
    public boolean insertCategory(String catname) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("cat_name", catname);
        long ins2 = db.insert("Category", null, cv);
        if (ins2 == -1) return false;
        else return true;
    }

    //Inserting Video Code
    public boolean insertvideocode(String VideoName, String Code, String Category) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("v_name", VideoName);
        cv.put("v_code", Code);
        cv.put("v_cat",Category);
        long ins3 = db.insert("Videocode", null, cv);
        if (ins3 == -1) return false;
        else return true;
    }

    //checking if email exists
    public Boolean chkemail(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from User where u_email=? ", new String[]{email});
        if (cursor.getCount() > 0) return false;
        else return true;
    }

    //checking if cat exists
    public Boolean chkcat(String name) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from Category where cat_name=?", new String[]{name});
        if (cursor.getCount() > 0) return false;
        else return true;
    }

    //Checking email and password
    public Boolean emailpass(String email, String pass) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from User where u_email=? and u_pass = ?", new String[]{email, pass});
        if (cursor.getCount() > 0) return true;
        else return false;
    }

    //checking if video exists
    public Boolean chkvdo(String code) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from Videocode where v_code=?", new String[]{code});
        if (cursor.getCount() > 0) return false;
        else return true;
    }

    //fetching data for Category page
    public ArrayList getCategoryName() {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList s = new ArrayList();
        Cursor cursor = db.rawQuery("select * from Category", new String[]{});
        if (cursor.moveToFirst()) {
            do {
                s.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return s;
    }

    //fetching data for Video page
    public ArrayList getVideoName() {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList s = new ArrayList();
        Cursor cursor = db.rawQuery("select v_name from Videocode", new String[]{});
        if (cursor.moveToFirst()) {
            do {
                s.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return s;

    }
    //fecting video code through video name
    public String getvideocode(String name){
        SQLiteDatabase db = this.getReadableDatabase();
        String s ="";
        Cursor cursor = db.rawQuery("select v_code from Videocode where v_name=?",new String[]{name});
        if (cursor.moveToFirst()){
            do {
                s = cursor.getString(0);
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return s;
    }
}
