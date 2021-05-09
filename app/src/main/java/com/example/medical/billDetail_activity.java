package com.example.medical;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.medical.Custom.*;
import com.example.medical.Data.*;
import com.example.medical.model.*;

import java.util.ArrayList;
import java.util.List;

public class billDetail_activity extends AppCompatActivity {
    TextView tvMahd, tvNgay;
    ListView lvBilldetail;
    Bill bill = new Bill();
    ArrayList<billDetail> listDetail;
    custom_list_detail adapter;
    dataBase_Thuoc db;
   // dataBase_HoaDon dbhd;
    private List<Thuoc> listThuoc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bill_detail);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Chi Tiết");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent myintent = getIntent();
        Bundle    bundle =  myintent.getExtras();
        if(bundle != null){
            bill = (Bill) bundle.getSerializable("h1");}

        db = new dataBase_Thuoc(this);
        listThuoc = db.getAllThuoc();
        listDetail = new ArrayList<>();
   //     billDetail d = new billDetail(1, 10, 1);
   //     billDetail d1 = new billDetail(1, 10, 2);
   //    listDetail.add(d);
  //      listDetail.add(d1);

        Database database = new Database(this,"QLNhaThuoc",null,1);

//        database.QueryData("INSERT INTO CTHoaDon VALUES(1, '" + listThuoc.get(1) +"',' "+ 10 + "')");
        Cursor dataHD = database.GetData("SELECT * FROM HoaDon Where MaHD = " + bill.getMaHD());
        Cursor dataCT = database.GetData("SELECT * FROM CTHoaDon Where MaHD = " + bill.getMaHD());
        while (dataHD.moveToNext()) {
            int maHD = dataHD.getInt(0);
            int ngay = dataHD.getInt(1);
            int maNT = dataHD.getInt(2);
        }
        while (dataCT.moveToNext()) {
            int maHD = dataCT.getInt(0);
            int mthuoc = dataCT.getInt(1);
            int soLuong = dataCT.getInt(2);

            billDetail d = new billDetail(maHD,soLuong,mthuoc);

            listDetail.add(d);
        }
        setControl();
        setEvent();
        setAdapter();
    }

    private void setControl() {
        tvMahd = (TextView) findViewById(R.id.edmaHD);
        tvNgay = (TextView) findViewById(R.id.edNgay);
        lvBilldetail = (ListView) findViewById(R.id.lvBilldetail);
    }

    private void setEvent() {
        tvMahd.setText(String.valueOf(bill.getMaHD()));
        tvNgay.setText(bill.getDate());
        lvBilldetail.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
              //  Toast.makeText(getApplication(), "Ten Thuoc là: " + listDetail.get(position).getMaThuoc().getTenThuoc().toString(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void setAdapter() {
        if (adapter == null) {
            adapter = new custom_list_detail(this, R.layout.custom_list_detail, listDetail, listThuoc);
            lvBilldetail.setAdapter(adapter);
        } else {
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}

