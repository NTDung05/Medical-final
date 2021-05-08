package com.example.medical.model;

import java.util.HashMap;

public class ChartThuoc {
    private int maThuoc, soLuong;
    private String tenThuoc, DVT, donGia;
    private byte[] img;

    @Override
    public boolean equals(Object o){
        if(o instanceof ChartThuoc){
            ChartThuoc p = (ChartThuoc) o;
            return this.maThuoc==p.getMaThuoc();
        } else
            return false;
    }

    public ChartThuoc(int maThuoc, String tenThuoc, String DVT, String donGia, byte[] img, int soLuong) {
        this.maThuoc = maThuoc;
        this.tenThuoc = tenThuoc;
        this.DVT = DVT;
        this.donGia = donGia;
        this.img = img;
        this.soLuong = soLuong;
    }

    public ChartThuoc() {
    }

    public byte[] getImg() {
        return img;
    }

    public void setImg(byte[] img) {
        this.img = img;
    }

    public int getMaThuoc() {
        return maThuoc;
    }

    public void setMaThuoc(int maThuoc) {
        this.maThuoc = maThuoc;
    }

    public String getTenThuoc() {
        return tenThuoc;
    }

    public void setTenThuoc(String tenThuoc) {
        this.tenThuoc = tenThuoc;
    }

    public String getDVT() {
        return DVT;
    }

    public void setDVT(String DVT) {
        this.DVT = DVT;
    }

    public String getDonGia() {
        return donGia;
    }

    public void setDonGia(String donGia) {
        this.donGia = donGia;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }
}
