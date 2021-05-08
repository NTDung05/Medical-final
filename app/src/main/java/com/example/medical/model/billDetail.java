package com.example.medical.model;

public class billDetail {

    private int maHD;
    private int soLuong;
    private int maThuoc;

    public billDetail() {
    }

    public billDetail(int maHD, int soLuong, int maThuoc) {
        this.maHD = maHD;
        this.soLuong = soLuong;
        this.maThuoc = maThuoc;
    }

    public billDetail(int soLuong, int maThuoc) {
        this.soLuong = soLuong;
        this.maThuoc = maThuoc;
    }

    public billDetail(int maHD) {
        this.maHD = maHD;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public int getMaThuoc() {
        return maThuoc;
    }

    public void setMaThuoc(int maThuoc) {
        this.maThuoc = maThuoc;
    }
}
