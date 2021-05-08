package com.example.medical.Custom;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.medical.R;
import com.example.medical.model.NhaThuoc;

import java.util.List;


public class custom_list_nhathuoc_bill extends BaseAdapter {

    private Context context;
    private int layout;
    private List<NhaThuoc> listNhaThuoc;

    public custom_list_nhathuoc_bill(Context context, int layout, List<NhaThuoc> listNhaThuoc) {
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
            convertView.setTag(holder);
        }else{
            holder= (ViewHolder) convertView.getTag();
        }

        NhaThuoc nhaThuoc = listNhaThuoc.get(position);

        holder.tvMaNT.setText(String.valueOf(nhaThuoc.getMaNT()));
        holder.tvTenNT.setText(nhaThuoc.getTenNT().toString());
        holder.tvDiaChi.setText(nhaThuoc.getDiaChi().toString());

        return convertView;
    }
}