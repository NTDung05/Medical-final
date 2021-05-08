package com.example.medical.Custom;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.medical.Chart_activity;
import com.example.medical.NhaThuoc_activity;
import com.example.medical.R;
import com.example.medical.model.ChartThuoc;
import com.example.medical.model.NhaThuoc;
import com.example.medical.model.Thuoc;

import java.util.List;

public class ChartAdapter extends ArrayAdapter<ChartThuoc> {
    private Context context;
    private int resoure;
    private List<ChartThuoc> listThuoc;

    public ChartAdapter(@NonNull Context context, int resource, @NonNull List<ChartThuoc> listThuoc) {
        super(context, resource, listThuoc);
        this.context = context;
        this.resoure = resource;
        this.listThuoc = listThuoc;
    }

    public class ViewHolder {
        TextView tvTenthuoc, tvDVT, tvDongia, tvSoluong;
        ImageView imgThuoc;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        LayoutInflater inflater = LayoutInflater.from(context);
        if (convertView == null) {
            convertView = inflater.inflate(resoure, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.tvTenthuoc = (TextView) convertView.findViewById(R.id.tvTenthuoc);
            viewHolder.tvDongia = (TextView) convertView.findViewById(R.id.tvDongia);
            viewHolder.tvDVT = (TextView) convertView.findViewById(R.id.tvDVT);
            viewHolder.imgThuoc = (ImageView) convertView.findViewById(R.id.imgThuoc);
            viewHolder.tvSoluong = (TextView) convertView.findViewById(R.id.tvCount);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        ChartThuoc thuoc = listThuoc.get(position);

        viewHolder.tvTenthuoc.setText(String.valueOf(thuoc.getMaThuoc())+ " | " + thuoc.getTenThuoc());
        viewHolder.tvDVT.setText(thuoc.getDVT());
        viewHolder.tvDongia.setText(thuoc.getDonGia());
        Bitmap bitmap = BitmapFactory.decodeByteArray(thuoc.getImg(), 0, thuoc.getImg().length);
        viewHolder.imgThuoc.setImageBitmap(bitmap);
        viewHolder.tvSoluong.setText(String.valueOf(thuoc.getSoLuong()));

        return convertView;
    }
}
