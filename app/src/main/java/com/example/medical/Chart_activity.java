package com.example.medical;

import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.medical.Custom.ChartAdapter;
import com.example.medical.Data.Database;
import com.example.medical.Data.dataBase_Thuoc;
import com.example.medical.model.ChartThuoc;
import com.example.medical.model.Thuoc;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Chart_activity extends AppCompatActivity {
    ListView lvThuoc;
    private ChartAdapter customAdapter;
    private List<Thuoc> listThuoc;
    private List<ChartThuoc> listChartThuoc;
    dataBase_Thuoc db;
    Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Thông tin Thuốc");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setContentView(R.layout.pie_chart);
        db = new dataBase_Thuoc(this);
        listThuoc = db.getAllThuoc();
        listChartThuoc = new ArrayList<>();
        database = new Database(this, "QLNhaThuoc", null, 1);

        for (Thuoc i : listThuoc) {
            Cursor data = database.GetData("SELECT SUM(SoLuong) FROM CTHoaDon WHERE MaThuoc = " + i.getMaThuoc() + " GROUP BY MaThuoc");
            int soLuong = 0;
            while (data.moveToNext()) {
                soLuong = data.getInt(0);
                System.out.println(soLuong);
            }
            listChartThuoc.add(new ChartThuoc(i.getMaThuoc(), i.getTenThuoc(), i.getDVT(), i.getDonGia(), i.getImg(), soLuong));
        }

        Collections.sort(listChartThuoc, new Comparator<ChartThuoc>() {
            @Override
            public int compare(ChartThuoc sv1, ChartThuoc sv2) {
                if (sv1.getSoLuong() < sv2.getSoLuong()) {
                    return 1;
                } else {
                    if (sv1.getSoLuong() == sv2.getSoLuong()) {
                        return 0;
                    } else {
                        return -1;
                    }
                }
            }
        });

        PieChart pieChart = findViewById(R.id.pieChart);
        ArrayList<PieEntry> dsThuoc = new ArrayList<>();
        for (ChartThuoc i : listChartThuoc) {
            dsThuoc.add(new PieEntry(i.getSoLuong(), i.getTenThuoc()));
        }

        PieDataSet pieDataSet = new PieDataSet(dsThuoc, "THUOC");
        pieDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        pieDataSet.setValueTextColor(Color.BLACK);
        pieDataSet.setValueTextSize(16f);

        PieData pieData = new PieData(pieDataSet);

        pieChart.setData(pieData);
        pieChart.getDescription().setEnabled(false);
        pieChart.setCenterText("THUOC");
        pieChart.animate();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menuAdd) {
            thongKe();
        }
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    private void thongKe() {
        setContentView(R.layout.chart_thuoc);
        setControl();
        setAdapter();
    }

    private void setControl() {
        lvThuoc = (ListView) findViewById(R.id.lvThuoc);
    }

    private void setAdapter() {
        if (customAdapter == null) {
            customAdapter = new ChartAdapter(this, R.layout.custom_listview_chart_thuoc, listChartThuoc);
            lvThuoc.setAdapter(customAdapter);
        } else {
            customAdapter.notifyDataSetChanged();
            lvThuoc.setSelection(customAdapter.getCount() - 1);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_nha_thuoc, menu);
        return super.onCreateOptionsMenu(menu);
    }
}