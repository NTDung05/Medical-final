package com.example.medical.model;

import java.io.Serializable;
import java.util.Date;

public class Bill implements Serializable {
private int maHD;
private String date;
private String maNT;

    public Bill(int maHD, String date, String maNT) {
        this.maHD = maHD;
        this.date = date;
        this.maNT = maNT;
    }

    public Bill(String date, String maNT) {
        this.date = date;
        this.maNT = maNT;
    }

    public Bill() {
    }

    public int getMaHD() {
        return maHD;
    }

    public void setMaHD(int maHD) {
        this.maHD = maHD;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMaNT() {
        return maNT;
    }

    public void setMaNT(String maNT) {
        this.maNT = maNT;
    }
}
