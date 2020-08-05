package com.example.project.Model;

public class ChamCong {
    String maCC, ngayCC, maCN;

    public String getMaCC() {
        return maCC;
    }

    public void setMaCC(String maCC) {
        this.maCC = maCC;
    }

    public String getNgayCC() {
        return ngayCC;
    }

    public void setNgayCC(String ngayCC) {
        this.ngayCC = ngayCC;
    }

    public String getMaCN() {
        return maCN;
    }

    public void setMaCN(String maCN) {
        this.maCN = maCN;
    }

    @Override
    public String toString() {
        return "ChamCong{" +
                "maCC='" + maCC + '\'' +
                ", ngayCC='" + ngayCC + '\'' +
                ", maCN='" + maCN + '\'' +
                '}';
    }
}
