package com.example.medical;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.medical.Custom.*;
import com.example.medical.Data.Database;
import com.example.medical.Data.dataBase_Thuoc;
import com.example.medical.model.Bill;
import com.example.medical.model.NhaThuoc;
import com.example.medical.model.Thuoc;

import java.util.ArrayList;
import java.util.List;

public class Bill_activity extends AppCompatActivity {
    Button btSetdate;
    TextView edmanhathuoc, edtennhathuoc, eddiachi;
    ListView lvBill;
    EditText edMa;
    NhaThuoc t1 = new NhaThuoc();
    private customListview_Bill customAdapter;
    private List<Bill> listBill;
  //  dataBase_HoaDon db;
    ArrayList<NhaThuoc> nhathuocArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Hóa Đơn");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.bill);
        Intent myintent = getIntent();
        Bundle bundle = myintent.getExtras();
        if (bundle != null) {
            int ma = bundle.getInt("1");
            t1 = (NhaThuoc) bundle.getSerializable("n1");
            nhathuocArrayList.add(t1);

        }
      //  Bill bill = new Bill("05", "1");
       // db = new dataBase_HoaDon(this);
       // db.addBill(bill);

      //  listBill = db.getAllBill();
        Database database = new Database(this,"QLNhaThuoc",null,1);

        database = new Database(this, "QLNhaThuoc", null, 1);
        database.QueryData("INSERT INTO HoaDon VALUES(null, '" + 1 + "',' " + t1.getMaNT() + "')");
        //database.QueryData("INSERT INTO HoaDon VALUES(null, '" + 13 + "',' " + t1.getMaNT() + "')");

        listBill = new ArrayList<>();

        Cursor dataHoaDon = database.GetData("SELECT * FROM HoaDon WHERE MaNT = '" + String.valueOf(t1.getMaNT())+ " ' ");

        while (dataHoaDon.moveToNext()) {
            int maHD = dataHoaDon.getInt(0);
            String ngayLap = dataHoaDon.getString(1);
            String maNT = dataHoaDon.getString(2);
            Bill bill = new Bill(maHD,ngayLap,maNT);
            listBill.add(bill);
        }
        setControl();
        setEvent();
        setAdapter();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menuAdd) {
            addBill();
        }
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    private void setControl() {
        lvBill = (ListView) findViewById(R.id.lvBill);
        edmanhathuoc = (TextView) findViewById(R.id.edmanhathuoc);
        edtennhathuoc = (TextView) findViewById(R.id.edtennhathuoc);
        eddiachi = (TextView) findViewById(R.id.eddiachi);

    }

    private void setEvent() {
        edmanhathuoc.setText(String.valueOf(t1.getMaNT()));
        edtennhathuoc.setText(t1.getTenNT());
        eddiachi.setText(t1.getDiaChi());
    }

    private void setAdapter() {
        if (customAdapter == null) {
            customAdapter = new customListview_Bill(this, R.layout.custom_listview_bill, listBill, t1.getTenNT());
            lvBill.setAdapter(customAdapter);
        } else {
            customAdapter.notifyDataSetChanged();
            lvBill.setSelection(customAdapter.getCount() - 1);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_nha_thuoc, menu);
        return super.onCreateOptionsMenu(menu);
    }


    private void addBill() {
        Intent intent = new Intent(getApplicationContext(), add_bill_activity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("n1", t1.getMaNT());
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
