package com.example.medical;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.medical.Data.Database;

public class main_layout extends AppCompatActivity {

    Database database;

    ImageButton btNhathuoc,btHoadon, btThuoc, btThongke;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);

        database = new Database(this,"QLNhaThuoc",null,1);
        database.QueryData("CREATE TABLE IF NOT EXISTS NhaThuoc(Id INTEGER PRIMARY KEY AUTOINCREMENT,TenNT VARCHAR(200),DiaChi VARCHAR(200))");
        database.QueryData("CREATE TABLE IF NOT EXISTS HoaDon(MaHD INTEGER PRIMARY KEY AUTOINCREMENT,Ngay VARCHAR(200),MaNT INTEGER)");
        database.QueryData("CREATE TABLE IF NOT EXISTS CTHoaDon(MaHD INTEGER,MaThuoc INTEGER, SoLuong INTEGER,PRIMARY KEY (MaHD, MaThuoc))");
        setControl();
        setEvent();
    }
    private void setControl(){
        btNhathuoc = (ImageButton)findViewById(R.id.btNhathuoc);
        btHoadon = (ImageButton)findViewById(R.id.btHoadon);
        btThuoc = (ImageButton)findViewById(R.id.btThuoc);
        btThongke = (ImageButton)findViewById(R.id.btThongke);
    }

    private  void setEvent(){
        btNhathuoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), NhaThuoc_activity.class);
                startActivity(intent);
            }

        });
        btThuoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Thuoc_activity.class);
                startActivity(intent);
            }
        });
        btHoadon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), all_list_bill.class);
                startActivity(intent);
            }
        });
        btThongke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Chart_activity.class);
                startActivity(intent);
            }
        });
    }
}
