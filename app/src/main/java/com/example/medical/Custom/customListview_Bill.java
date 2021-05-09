package com.example.medical.Custom;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.medical.R;
import com.example.medical.billDetail_activity;
import com.example.medical.model.Bill;
import com.example.medical.model.NhaThuoc;
import com.example.medical.model.Thuoc;

import java.util.List;

public class customListview_Bill extends ArrayAdapter<Bill> {
    private Context context;
    private int resoure;
    private List<Bill> listBill;
    private String nhathuoc;

    public customListview_Bill(@NonNull Context context, int resource, @NonNull List<Bill> listBill, String nhathuoc) {
        super(context, resource, listBill);
        this.context = context;
        this.resoure = resource;
        this.listBill = listBill;
        this.nhathuoc = nhathuoc;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        customListview_Bill.ViewHolder viewHolder;
        LayoutInflater inflater = LayoutInflater.from(context);
        if (convertView == null) {
            convertView = inflater.inflate(resoure, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.tvMaHD = (TextView) convertView.findViewById(R.id.tvMaHD);
            viewHolder.tvTenNT = (TextView) convertView.findViewById(R.id.tvTenNT);
            viewHolder.tvNgay = (TextView) convertView.findViewById(R.id.tvNgay);
            viewHolder.btInfo = (ImageView) convertView.findViewById(R.id.imgInfo);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (customListview_Bill.ViewHolder) convertView.getTag();
        }
        Bill bill = listBill.get(position);
        viewHolder.tvMaHD.setText("Mã HĐ");
        viewHolder.tvTenNT.setText(String.valueOf(bill.getMaHD()));
        viewHolder.tvNgay.setText("Ngày Lập"+bill.getDate());
//        viewHolder.tvDongia.setText(thuoc.getDonGia());
//        viewHolder.imgThuoc.setImageResource(Integer.parseInt(thuoc.getImg()));
//        Bitmap bitmap = BitmapFactory.decodeByteArray(thuoc.getImg(),0,thuoc.getImg().length);
//        viewHolder.imgThuoc.setImageBitmap(bitmap);

        viewHolder.btInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, billDetail_activity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("h1", bill);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
        return convertView;
    }

    public class ViewHolder {
        TextView tvMaHD, tvTenNT, tvNgay;
        ImageView btInfo;
    }
}
