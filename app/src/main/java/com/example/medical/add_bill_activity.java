package com.example.medical;

import android.app.Application;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.medical.Custom.ChartAdapter;
import com.example.medical.Custom.customListview_Thuoc;
import com.example.medical.Custom.custom_spinner;
import com.example.medical.Data.*;
import com.example.medical.model.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class add_bill_activity extends AppCompatActivity {
    EditText edNgay, edSoluong;
    Spinner spinner_thuoc;
    Button btXacnhan, btHuy, btLuu, btXoa;
    ListView lvchitiet;
    private List<Thuoc> listThuoc;
    dataBase_Thuoc db;
    int t1;
    //dataBase_HoaDon db1;
    private custom_spinner custom_spinner;
    private ChartAdapter customAdapter;
    private List<billDetail> details = new ArrayList<>();
    private List<ChartThuoc> chartThuoc = new ArrayList<>();
    Thuoc thuoc;
    Database database;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_bill);
        Intent myintent = getIntent();
        Bundle bundle = myintent.getExtras();
        if (bundle != null) {
            t1 = bundle.getInt("n1");
            Log.d("Lỗi", "Bmm");
        }

         database = new Database(this, "QLNhaThuoc", null, 1);
    //    database.QueryData("INSERT INTO HoaDon VALUES(null, '" + 1 + "',' " + t1 + "')");

        db = new dataBase_Thuoc(this);
        listThuoc = db.getAllThuoc();

        System.out.println("lelele" + listThuoc.get(0).getTenThuoc());



        setControl();
        setAdapter();
        setEvent();

    }

    private void setControl() {
        edNgay = (EditText) findViewById(R.id.edNgay);
        edSoluong = (EditText) findViewById(R.id.edsoluong);
        spinner_thuoc = (Spinner) findViewById(R.id.spinner_thuoc);
        btXacnhan = (Button) findViewById(R.id.btXacnhan);
        btHuy = (Button) findViewById(R.id.btHuy);
        btLuu = (Button) findViewById(R.id.btLuu);
        btXoa = (Button) findViewById(R.id.btXoa);
        lvchitiet = (ListView) findViewById(R.id.lvchitiet);
    }

    private void setEvent() {

        spinner_thuoc.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                thuoc = listThuoc.get(position);
                System.out.println("2 " + listThuoc.get(position).getTenThuoc());
                System.out.println("1 " + thuoc.getTenThuoc());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        lvchitiet.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                System.out.println(chartThuoc.get(position).getSoLuong());
                edSoluong.setText(String.valueOf(chartThuoc.get(position).getSoLuong()));

               for(int i =0 ; i< listThuoc.size(); i++){
                  if(listThuoc.get(i).getMaThuoc() == chartThuoc.get(position).getMaThuoc()){
                      spinner_thuoc.setSelection(i);
                   }
                }
                ChartThuoc ch = chartThuoc.get(position);

                btXoa.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        chartThuoc.remove(ch);
                        setAdapterlv();
                    }
                });
            }
        });

        btXacnhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("3 " + thuoc.getTenThuoc());


                ChartThuoc chart = new ChartThuoc();
                chart.setTenThuoc(thuoc.getTenThuoc());
                chart.setImg(thuoc.getImg());
                chart.setDonGia(thuoc.getDonGia());
                chart.setDVT(thuoc.getDVT());
                chart.setMaThuoc(thuoc.getMaThuoc());
                chart.setSoLuong(Integer.parseInt(String.valueOf(edSoluong.getText())));

                if (chartThuoc.contains(chart)) {
                    System.out.println("ditme co roi");
                    chartThuoc.remove(chart);
                    chartThuoc.add(chart);

                } else {
                    chartThuoc.add(chart);
                }

                setAdapterlv();


            }
        });

        btHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database.QueryData("");
                Intent intent = new Intent(getApplicationContext(), all_list_bill.class);
                startActivity(intent);
            }
        });
        btLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // add();
                database.QueryData("INSERT INTO HoaDon VALUES(null, '" + edNgay.getText().toString() + "',' " + t1 + "')");

                Cursor c = database.GetData("select Max(MaHD) from HoaDon");
                c.moveToNext();
                System.out.println(c.getString(0));
                int m = c.getInt(0);
                for(int i =0;i < chartThuoc.size();i++){
                    String uery = String.format("INSERT INTO CTHoaDon VALUES(%d,%d,%d)",m,chartThuoc.get(i).getMaThuoc(),chartThuoc.get(i).getSoLuong());
                    database.QueryData(uery);
                }
                Toast.makeText(getApplicationContext(), "Lưu Thành Công", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(), Bill_activity.class);
                startActivity(intent);
            }
        });
    }

    private void setAdapter() {
        if (custom_spinner == null) {
            custom_spinner = new custom_spinner(this, listThuoc);
            spinner_thuoc.setAdapter(custom_spinner);
        }
    }

//    private void add() {
//
//
//        for (int i = 0; i < chartThuoc.size(); i++) {
//            database.QueryData("INSERT INTO CTHoaDon VALUES(null, '" + + "',' " + chartThuoc.get(i).getSoLuong() + "')");
//
//        }
//    }

    private void setAdapterlv() {
        if (customAdapter == null) {
            customAdapter = new ChartAdapter(this, R.layout.custom_listview_chart_thuoc, chartThuoc);
            lvchitiet.setAdapter(customAdapter);
        } else {
            customAdapter.notifyDataSetChanged();
            lvchitiet.setSelection(customAdapter.getCount() - 1);
        }
    }
}
