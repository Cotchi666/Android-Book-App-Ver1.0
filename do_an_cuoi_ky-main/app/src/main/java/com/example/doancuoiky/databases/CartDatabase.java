package com.example.doancuoiky.databases;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class CartDatabase extends SQLiteOpenHelper {
    public static final String NAME_DATABASE = "CartBook";
    public static final String TABLE_NAME = "table_book";

    public CartDatabase(@Nullable Context context) {
        super(context, NAME_DATABASE, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table if not exists " + TABLE_NAME +
                "(maSach INTEGER PRIMARY KEY," +
                "tenSach TEXT NOT NULL," +
                "donGia FLOAT NOT NULL," +
                "hinhAnh text not null," +
                "soLuong INTEGER not null)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE_NAME);
        onCreate(db);
    }
}
