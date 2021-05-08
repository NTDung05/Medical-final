package com.example.medical.model;

import android.graphics.Bitmap;

public class Thuoc {
    private int maThuoc;
    private String tenThuoc, DVT, donGia;
    private byte[] img;

    public Thuoc(int maThuoc, String tenThuoc, String DVT, String donGia, byte[] img) {
        this.maThuoc = maThuoc;
        this.tenThuoc = tenThuoc;
        this.DVT = DVT;
        this.donGia = donGia;
        this.img = img;
    }

    public Thuoc(String tenThuoc, String DVT, String donGia, byte[] img) {
        super();
        this.img = img;
        this.tenThuoc = tenThuoc;
        this.DVT = DVT;
        this.donGia = donGia;
    }

    public byte[] getImg() {
        return img;
    }

    public void setImg(byte[] img) {
        this.img = img;
    }

    public Thuoc() {
        super();
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
}
