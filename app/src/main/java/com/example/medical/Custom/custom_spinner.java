package com.example.medical.Custom;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.medical.R;
import com.example.medical.add_bill_activity;
import com.example.medical.model.ChartThuoc;
import com.example.medical.model.NhaThuoc;
import com.example.medical.model.Thuoc;

import java.util.ArrayList;
import java.util.List;

public class custom_spinner extends BaseAdapter {

    Context context;
   List<Thuoc> thuocArrayList;
    LayoutInflater inflter;
    //int layoutID;
    public custom_spinner(Context context,  List thuocArrayList){
//this.layoutID= layoutID;
        this.context= context;
        this.thuocArrayList = thuocArrayList;
        inflter = (LayoutInflater.from(context));


    }



    @Override
    public int getCount() {
        return thuocArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Thuoc thuoc = thuocArrayList.get(position);
        convertView = inflter.inflate(R.layout.custom_spinner,null);
        ImageView img = (ImageView)convertView.findViewById(R.id.imgThuoc);
        TextView tvmanhathuoc = (TextView)convertView.findViewById(R.id.mthuoc);
        TextView tvtennhathuoc = (TextView)convertView.findViewById(R.id.tthuoc);
        tvmanhathuoc.setText(String.valueOf(thuocArrayList.get(position).getMaThuoc()));
        tvtennhathuoc.setText(thuocArrayList.get(position).getTenThuoc());
        Bitmap bitmap = BitmapFactory.decodeByteArray(thuoc.getImg(), 0, thuoc.getImg().length);
        img.setImageBitmap(bitmap);

        return convertView;
    }
}
