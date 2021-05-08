//package com.example.medical.Data;
//
//import android.content.ContentValues;
//import android.content.Context;
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteOpenHelper;
//import android.util.Log;
//
//import com.example.medical.model.*;
//import com.example.medical.model.Thuoc;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class dataBase_HoaDon extends SQLiteOpenHelper {
//    private final String TAG = "DB";
//    private static final String DATA_NAME = "Medical1";
//    private static final int DATA_VERSION = 1;
//    private static final String DATA_TABLE = "HoaDon";
//    private static final String KEY_ID = "maHD";
//    private static final String NGAY_LAP = "ngayLap";
//    private static final String MA_NT = "maNT";
//
//    private Context context;
//
//    public dataBase_HoaDon(Context context) {
//        super(context, DATA_NAME, null, DATA_VERSION);
//        this.context = context;
//        Log.d(TAG, "DatabassHOaDOn");
//
//    }
//
//    private String SQLQuery = "CREATE TABLE " + DATA_TABLE + " ( " +
//            KEY_ID + " integer primary key, " +
//            NGAY_LAP + " TEXT, " +
//            MA_NT + " TEXT )";
//
////database.QueryData("CREATE TABLE IF NOT EXISTS NhaThuoc(Id INTEGER PRIMARY KEY AUTOINCREMENT,TenNT VARCHAR(200),DiaChi VARCHAR(200))");
//
//    @Override
//    public void onCreate(SQLiteDatabase db) {
//        db.execSQL(SQLQuery);
//        Log.d(TAG, "onCreate");
//    }
//
//    @Override
//    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        Log.d(TAG, " onUpgrade");
//    }
//
//    public void QueryData(String sql) {
//        SQLiteDatabase database = getWritableDatabase();
//        database.execSQL(sql);
//    }
//
//    public void addBill(Bill bill) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put(NGAY_LAP, bill.getDate().toString());
//        values.put(MA_NT, bill.getMaNT());
////        values.put(DON_GIA,thuoc.getDonGia());
////        values.put(IMG_SRC, thuoc.getImg());
//        db.insert(DATA_TABLE, null, values);
//        db.close();
//        Log.d(TAG, "Successful");
//
//    }
//    public void adddetail(billDetail detail1) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put("soluong", detail1.getSoLuong());
//        values.put("mathuoc",detail1.getMaThuoc());
//        db.insert("detail", null, values);
//        db.close();
//        Log.d(TAG, "Successful chi tiet");
//
//    }
//
//    public List<Bill> getAllBill() {
//        List<Bill> listBill = new ArrayList<>();
//        String selectQuery = "SELECT * FROM " + DATA_TABLE;
//        SQLiteDatabase db = this.getWritableDatabase();
//
//        Cursor cursor = db.rawQuery(selectQuery, null);
//
//        if (cursor.moveToFirst()) {
//            do {
//                Bill bill = new Bill();
//                bill.setMaHD(cursor.getInt(0));
//                bill.setDate(String.valueOf(cursor.getString(1)));
//                bill.setMaNT(cursor.getString(2));
//                listBill.add(bill);
//            } while (cursor.moveToNext());
//        }
//        db.close();
//        Log.d(TAG, "Thành công đọc danh sách");
//        return listBill;
//    }
//}
