package com.example.project.Model;

import java.util.Arrays;

public class SanPham {
    String maSP;
    String tenSP;
    String donGia;
    byte[] anhSP;

    public String getMaSP() {
        return maSP;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public String getDonGia() {
        return donGia;
    }

    public void setDonGia(String donGia) {
        this.donGia = donGia;
    }

    public byte[] getAnhSP() {
        return anhSP;
    }

    public void setAnhSP(byte[] anhSP) {
        this.anhSP = anhSP;
    }

    @Override
    public String toString() {
        return "SanPham{" +
                "maSP='" + maSP + '\'' +
                ", tenSP='" + tenSP + '\'' +
                ", donGia='" + donGia + '\'' +
                ", anhSP=" + Arrays.toString(anhSP) +
                '}';
    }
}
