package com.example.medical;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.medical.Custom.NhaThuocAdapter;
import com.example.medical.Custom.custom_list_nhathuoc_bill;
import com.example.medical.Data.Database;
import com.example.medical.model.NhaThuoc;

import java.util.ArrayList;

public class all_list_bill extends AppCompatActivity {
    Database database;

    ListView lv_nhathuoc_bill;
    ArrayList<NhaThuoc> listNhaThuoc;
    custom_list_nhathuoc_bill adapter;

    protected void onCreate(Bundle savedInstaceState) {
        super.onCreate(savedInstaceState);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Thông tin Nhà Thuốc");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setContentView(R.layout.all_list_bill);
        setControl();
        listNhaThuoc = new ArrayList<>();
        adapter = new custom_list_nhathuoc_bill(getApplicationContext(), R.layout.custom_list_nhathuoc_bill, listNhaThuoc);
        lv_nhathuoc_bill.setAdapter(adapter);
        database = new Database(this, "QLNhaThuoc", null, 1);
        database.QueryData("CREATE TABLE IF NOT EXISTS NhaThuoc(Id INTEGER PRIMARY KEY AUTOINCREMENT,TenNT VARCHAR(200),DiaChi VARCHAR(200))");
        database.QueryData("CREATE TABLE IF NOT EXISTS HoaDon(soHD INTEGER PRIMARY KEY AUTOINCREMENT,ngayHD VARCHAR(200),maNT VARCHAR(200))");
        getDataNhaThuoc();
        setEvent();

    }

    private void setControl() {
        lv_nhathuoc_bill = (ListView) findViewById(R.id.lvNhaThuoc_bill);
    }

    private void setEvent() {
        lv_nhathuoc_bill.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                NhaThuoc nhathuoc = new NhaThuoc();
                nhathuoc = listNhaThuoc.get(position);
                Toast.makeText(getApplicationContext(), "Bạn đã chọn nhà thuốc " + nhathuoc.getTenNT(), Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(), Bill_activity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("n1", nhathuoc);
                intent.putExtras(bundle);
                startActivity(intent);

            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    private void getDataNhaThuoc() {
        listNhaThuoc.clear();
        Cursor dataNhaThuoc = database.GetData("SELECT * FROM NhaThuoc");
        while (dataNhaThuoc.moveToNext()) {
            int id = dataNhaThuoc.getInt(0);
            String ten = dataNhaThuoc.getString(1);
            String diachi = dataNhaThuoc.getString(2);
            listNhaThuoc.add(new NhaThuoc(id, ten, diachi));
        }
        adapter.notifyDataSetChanged();
    }
}
