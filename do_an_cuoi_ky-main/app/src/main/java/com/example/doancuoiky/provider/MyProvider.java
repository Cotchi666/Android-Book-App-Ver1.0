package com.example.doancuoiky.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.doancuoiky.databases.CartDatabase;
import com.example.doancuoiky.other.Check;


public class MyProvider extends ContentProvider {
    private SQLiteDatabase sqLiteDatabase;
    public static final String AUTHORITIES = "com.example.doancuoiky.provider.MyProvider";
    public static final String URL = "content://" + AUTHORITIES + "/" + CartDatabase.NAME_DATABASE;
    private UriMatcher uriMatcher;

    {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITIES, CartDatabase.NAME_DATABASE, 1);
    }

    @Override
    public boolean onCreate() {
        CartDatabase cartDatabase = new CartDatabase(getContext());
        sqLiteDatabase = cartDatabase.getWritableDatabase();
        return (sqLiteDatabase == null) ? false : true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        return uriMatcher.match(uri) == 1 ? sqLiteDatabase.query(CartDatabase.TABLE_NAME, null, null, null, null, null, null) : null;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        if (uriMatcher.match(uri) == 1){
            sqLiteDatabase.insert(CartDatabase.TABLE_NAME, null, values);
            Check.creatToat("Thêm vào giỏ hàng thành công",getContext());
            Check.creatToat("Mời bạn tiếp tục mua hàng",getContext());
        }


        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        if (uriMatcher.match(uri) == 1)
            sqLiteDatabase.delete(CartDatabase.TABLE_NAME, selection, selectionArgs);
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        if (uriMatcher.match(uri) == 1)
            sqLiteDatabase.update(CartDatabase.TABLE_NAME, values, selection, selectionArgs);
        return 0;
    }
}
