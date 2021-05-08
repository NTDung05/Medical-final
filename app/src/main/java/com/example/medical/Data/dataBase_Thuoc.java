package com.example.medical.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.medical.model.Thuoc;

import java.util.ArrayList;
import java.util.List;

public class dataBase_Thuoc extends SQLiteOpenHelper {
    private final String TAG = "DB";
    private static final String DATA_NAME = "Medical";
    private static final int DATA_VERSION = 1;
    private static final String DATA_TABLE = "Thuoc";
    private static final String KEY_ID = "maThuoc";
    private static final String TEN_THUOC = "tenThuoc";
    private static final String DVT = "DVT";
    private static final String DON_GIA = "donGia";
    private static final String IMG_SRC = "Img";
    private Context context;

    public dataBase_Thuoc(Context context) {
        super(context, DATA_NAME, null, DATA_VERSION);
        this.context = context;
        Log.d(TAG, "DBFF");
    }

    private String SQLQuery = "CREATE TABLE " + DATA_TABLE + " ( " +
            KEY_ID + " integer primary key, " +
            TEN_THUOC + " TEXT, " +
            DVT + " TEXT, " +
            DON_GIA + " TEXT, " +
            IMG_SRC + " BLOB )";


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQLQuery);
        Log.d(TAG, "onCreate");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d(TAG, " onUpgrade");
    }

    public void addThuoc(Thuoc thuoc) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TEN_THUOC, thuoc.getTenThuoc());
        values.put(DVT, thuoc.getDVT());
        values.put(DON_GIA, thuoc.getDonGia());
        values.put(IMG_SRC, thuoc.getImg());
        db.insert(DATA_TABLE, null, values);
        db.close();
        Log.d(TAG, "Successful");

    }

    public void deleteThuoc(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String delete = "DELETE FROM " + DATA_TABLE + " Where " + KEY_ID + " = ? ";
        db.execSQL(delete, new String[]{String.valueOf(id)});
        db.close();
        Log.d(TAG, " Hoàn thành Xóa");
    }

    public int updateThuoc(Thuoc thuoc) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TEN_THUOC, thuoc.getTenThuoc());
        contentValues.put(DVT, thuoc.getDVT());
        contentValues.put(DON_GIA, thuoc.getDonGia());
        contentValues.put(IMG_SRC, thuoc.getImg());

        return db.update(DATA_TABLE, contentValues, KEY_ID + " = ?", new String[]{String.valueOf(thuoc.getMaThuoc())});
    }

    public List<Thuoc> getAllThuoc() {
        List<Thuoc> listThuoc = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + DATA_TABLE;
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Thuoc thuoc = new Thuoc();
                thuoc.setMaThuoc(cursor.getInt(0));
                thuoc.setTenThuoc(cursor.getString(1));
                thuoc.setDonGia(cursor.getString(3));
                thuoc.setDVT(cursor.getString(2));
                thuoc.setImg(cursor.getBlob(4));
                listThuoc.add(thuoc);
            } while (cursor.moveToNext());
        }
        db.close();
        Log.d(TAG, "Thành công đọc danh sách");
        return listThuoc;
    }

    public Cursor GetData(String sql){
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql,null);
    }
}
