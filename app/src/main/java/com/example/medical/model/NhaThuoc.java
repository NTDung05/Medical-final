package com.example.medical.model;

import java.io.Serializable;

public class NhaThuoc implements Serializable {
    private int maNT;
    private String tenNT;
    private String diaChi;

    public NhaThuoc(int maNT, String tenNT, String diaChi) {
        this.maNT = maNT;
        this.tenNT = tenNT;
        this.diaChi = diaChi;
    }
    public boolean equals(Object o){
        if(o instanceof NhaThuoc){
            NhaThuoc p = (NhaThuoc) o;
            return this.maNT==p.getMaNT();
        } else
            return false;
    }
    public NhaThuoc() {
    }

    public int getMaNT() {
        return maNT;
    }

    public void setMaNT(int maNT) {
        this.maNT = maNT;
    }

    public String getTenNT() {
        return tenNT;
    }

    public void setTenNT(String tenNT) {
        this.tenNT = tenNT;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }
}
