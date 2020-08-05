package com.example.project.Model;

public class CongNhan {
    String maCN;
    String hoCN;
    String tenCN;
    String phanXuong;

    public String getMaCN() {
        return maCN;
    }

    public void setMaCN(String maCN) {
        this.maCN = maCN;
    }

    public String getHoCN() {
        return hoCN;
    }

    public void setHoCN(String hoCN) {
        this.hoCN = hoCN;
    }

    public String getTenCN() {
        return tenCN;
    }

    public void setTenCN(String tenCN) {
        this.tenCN = tenCN;
    }

    public String getPhanXuong() {
        return phanXuong;
    }

    public void setPhanXuong(String phanXuong) {
        this.phanXuong = phanXuong;
    }

    @Override
    public String toString() {
        return "CongNhan{" +
                "maCN='" + maCN + '\'' +
                ", hoCN='" + hoCN + '\'' +
                ", tenCN='" + tenCN + '\'' +
                ", phanXuong='" + phanXuong + '\'' +
                '}';
    }
}
