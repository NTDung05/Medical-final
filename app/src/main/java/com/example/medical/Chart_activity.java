package com.example.medical;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.medical.Custom.ChartAdapter;
import com.example.medical.Custom.NhaThuocAdapter;
import com.example.medical.Custom.customListview_Thuoc;
import com.example.medical.Data.Database;
import com.example.medical.Data.dataBase_Thuoc;
import com.example.medical.model.ChartThuoc;
import com.example.medical.model.NhaThuoc;
import com.example.medical.model.Thuoc;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class Chart_activity extends AppCompatActivity {
    ImageView edImg;
    ListView lvThuoc;
    private ChartAdapter customAdapter;
    private List<Thuoc> listThuoc;
    private List<ChartThuoc> listChartThuoc;
    dataBase_Thuoc db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Thông tin Thuốc");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setContentView(R.layout.chart_thuoc);
        db = new dataBase_Thuoc(this);
        listThuoc = db.getAllThuoc();
        listChartThuoc = new ArrayList<>();
        Random generator = new Random();
        for (Thuoc i : listThuoc){
            listChartThuoc.add(new ChartThuoc(i.getMaThuoc(),i.getTenThuoc(),i.getDVT(),i.getDonGia(),i.getImg(),generator.nextInt(100)));
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

        setControl();
        setAdapter();
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

    @SuppressLint("WrongViewCast")
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
}
