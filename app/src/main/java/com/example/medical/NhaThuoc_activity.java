package com.example.medical;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.medical.Custom.NhaThuocAdapter;
import com.example.medical.Data.Database;
import com.example.medical.model.NhaThuoc;

import java.util.ArrayList;

public class NhaThuoc_activity extends AppCompatActivity {

    Database database;

    ListView lvNhaThuoc;
    ArrayList<NhaThuoc> listNhaThuoc;
    NhaThuocAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nha_thuoc);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Thông tin Nhà Thuốc");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        lvNhaThuoc = (ListView) findViewById(R.id.lvNhaThuoc);
        listNhaThuoc = new ArrayList<>();

        adapter = new NhaThuocAdapter(this, R.layout.custom_listview_nhathuoc, listNhaThuoc);
        lvNhaThuoc.setAdapter(adapter);

        database = new Database(this,"QLNhaThuoc",null,1);
        getDataNhaThuoc();
    }

    public void diaLogSuaNhaThuoc(int id,String tenNhaThuoc,String diaChi){
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_sua_nha_thuoc);

        EditText edtTenNhaThuoc = (EditText) dialog.findViewById(R.id.edtTenNhaThuoc);
        EditText edtDiaChi = (EditText) dialog.findViewById(R.id.edtDiaChi);
        Button btnCapNhat = (Button) dialog.findViewById(R.id.btnCapNhat);
        Button btnHuy = (Button) dialog.findViewById(R.id.btnHuy1);

        edtTenNhaThuoc.setText(tenNhaThuoc);
        edtDiaChi.setText(diaChi);

        btnCapNhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newTenNhaThuoc = edtTenNhaThuoc.getText().toString().trim();
                String newDiaChi = edtDiaChi.getText().toString().trim();
                database.QueryData("UPDATE NhaThuoc SET TenNT = '"+ newTenNhaThuoc + "',DiaChi = '"+ newDiaChi + "' WHERE Id = '"+ id + "'");
                Toast.makeText(NhaThuoc_activity.this,"Da Cap Nhat",Toast.LENGTH_SHORT).show();
                dialog.dismiss();
                getDataNhaThuoc();
            }
        });
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public void dialogXoaCV(int id){
        AlertDialog.Builder dialogXoa = new AlertDialog.Builder(this);
        dialogXoa.setMessage("Bạn Có Muốn Xóa Hay Không ??? ");
        dialogXoa.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                database.QueryData("DELETE FROM NhaThuoc WHERE  Id = "+ id + "");
                Toast.makeText(NhaThuoc_activity.this,"Da Xoa",Toast.LENGTH_SHORT).show();
                getDataNhaThuoc();
            }
        });
        dialogXoa.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialogXoa.show();
    }

    private void getDataNhaThuoc(){
        listNhaThuoc.clear();
        Cursor dataNhaThuoc = database.GetData("SELECT * FROM NhaThuoc");
        while (dataNhaThuoc.moveToNext()){
            int id =  dataNhaThuoc.getInt(0);
            String ten =  dataNhaThuoc.getString(1);
            String diachi =  dataNhaThuoc.getString(2);
            listNhaThuoc.add(new NhaThuoc(id,ten,diachi));
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_nha_thuoc, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.menuAdd){
            DialogThem();
        }
        if(item.getItemId() == android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    private void DialogThem(){
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_them_nha_thuoc);

        EditText edtTenNhaThuoc = (EditText) dialog.findViewById(R.id.edtTenNhaThuoc);
        EditText edtDiaChi = (EditText) dialog.findViewById(R.id.edtDiaChi);
        Button btnThem = (Button) dialog.findViewById(R.id.btThem);
        Button btnHuy = (Button) dialog.findViewById(R.id.btHuy);

        btnThem.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String tenNhaThuoc = edtTenNhaThuoc.getText().toString();
                String diaChi = edtDiaChi.getText().toString();
                if(tenNhaThuoc.equals("") || diaChi.equals((""))){
                    Toast.makeText(NhaThuoc_activity.this,"Vui Long Nhap Ten Nha Thuoc hoac Dia Chi",Toast.LENGTH_SHORT).show();
                }else {
                    database.QueryData("INSERT INTO NhaThuoc VALUES(null, '" + tenNhaThuoc +"',' "+ diaChi + "')");
                    Toast.makeText(NhaThuoc_activity.this,"Da Them.",Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                    getDataNhaThuoc();
                }
            }
        });

        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}