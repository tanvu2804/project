package com.example.project.Model;

import java.util.Arrays;

public class ChiTietChamCong {
    String maCc;
    String Stp;
    String Spp;
    String maSP;
    String tenSP;
    String donGia;
    byte[] anhSP;

    public String getMaCc() {
        return maCc;
    }

    public void setMaCc(String maCc) {
        this.maCc = maCc;
    }

    public String getStp() {
        return Stp;
    }

    public void setStp(String stp) {
        Stp = stp;
    }

    public String getSpp() {
        return Spp;
    }

    public void setSpp(String spp) {
        Spp = spp;
    }

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
        return "ChiTietChamCong{" +
                "maCc='" + maCc + '\'' +
                ", Stp='" + Stp + '\'' +
                ", Spp='" + Spp + '\'' +
                ", maSP='" + maSP + '\'' +
                ", tenSP='" + tenSP + '\'' +
                ", donGia='" + donGia + '\'' +
                ", anhSP=" + Arrays.toString(anhSP) +
                '}';
    }
}
