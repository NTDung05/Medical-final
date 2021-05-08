package com.example.medical.Custom;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.medical.R;
import com.example.medical.model.*;

import java.util.List;

public class custom_list_detail extends ArrayAdapter<billDetail> {

    private Context context;
    private int resoure;
    private List<billDetail> listDetail;

    // private String nhathuoc;
    public custom_list_detail(@NonNull Context context, int resource, @NonNull List<billDetail> listDetail) {
        super(context, resource, listDetail);
        this.context = context;
        this.resoure = resource;
        this.listDetail = listDetail;

    }

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        custom_list_detail.ViewHolder viewHolder;
        LayoutInflater inflater = LayoutInflater.from(context);
        if (convertView == null) {
            convertView = inflater.inflate(resoure, parent, false);
            viewHolder = new custom_list_detail.ViewHolder();
            viewHolder.tvStt = (TextView) convertView.findViewById(R.id.tvStt);
            viewHolder.tvThuoc = (TextView) convertView.findViewById(R.id.tvThuoc);
            viewHolder.tvSoluong = (TextView) convertView.findViewById(R.id.tvSoluong);
            viewHolder.tvMathuoc = (TextView) convertView.findViewById(R.id.tvMathuoc);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (custom_list_detail.ViewHolder) convertView.getTag();
        }
        billDetail detail = listDetail.get(position);
        viewHolder.tvStt.setText(String.valueOf(position + 1));
        viewHolder.tvMathuoc.setText(String.valueOf(detail.getMaThuoc()));
        viewHolder.tvSoluong.setText(String.valueOf("Số lượng: " + detail.getSoLuong()));
        // viewHolder.tvThuoc.setText(String.valueOf(detail.getMaThuoc()));
        return convertView;
    }

    public class ViewHolder {
        TextView tvStt, tvThuoc, tvSoluong, tvMathuoc;
    }
}