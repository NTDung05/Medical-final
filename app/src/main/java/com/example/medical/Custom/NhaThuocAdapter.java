package com.example.medical.Custom;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.medical.NhaThuoc_activity;
import com.example.medical.R;
import com.example.medical.model.NhaThuoc;

import java.util.List;

public class NhaThuocAdapter extends BaseAdapter {

    private NhaThuoc_activity context;
    private int layout;
    private List<NhaThuoc> listNhaThuoc;

    public NhaThuocAdapter(NhaThuoc_activity context, int layout, List<NhaThuoc> listNhaThuoc) {
        this.context = context;
        this.layout = layout;
        this.listNhaThuoc = listNhaThuoc;
    }

    @Override
    public int getCount() {
        return listNhaThuoc.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private class ViewHolder{
        TextView tvTenNT,tvMaNT,tvDiaChi;
        ImageView imgDelete, imgEdit;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if(convertView == null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, null);
            holder.tvMaNT = (TextView) convertView.findViewById(R.id.tvMaNT);
            holder.tvTenNT = (TextView) convertView.findViewById(R.id.tvTenNT);
            holder.tvDiaChi = (TextView) convertView.findViewById(R.id.tvDiaChi);
            holder.imgDelete = (ImageView) convertView.findViewById(R.id.imgDelete);
            holder.imgEdit = (ImageView) convertView.findViewById(R.id.imgEdit);
            convertView.setTag(holder);
        }else{
            holder= (ViewHolder) convertView.getTag();
        }

        NhaThuoc nhaThuoc = listNhaThuoc.get(position);

        holder.tvMaNT.setText(String.valueOf(nhaThuoc.getMaNT()));
        holder.tvTenNT.setText(nhaThuoc.getTenNT().toString());
        holder.tvDiaChi.setText(nhaThuoc.getDiaChi().toString());

        //bắt sự kiện xóa & sửa
        holder.imgEdit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                context.diaLogSuaNhaThuoc(nhaThuoc.getMaNT(),nhaThuoc.getTenNT(),nhaThuoc.getDiaChi());
            }
        });

        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.dialogXoaCV(nhaThuoc.getMaNT());
            }
        });
        return convertView;
    }
}