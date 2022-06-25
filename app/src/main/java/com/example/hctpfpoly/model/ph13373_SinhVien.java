package com.example.hctpfpoly.model;

import java.io.Serializable;

public class ph13373_SinhVien implements Serializable {
    private String maSV;
    private String maLop;
    private String tenSV;
    private String ngaySinh;

    public ph13373_SinhVien() {
    }

    public String getMaSV() {
        return maSV;
    }

    public void setMaSV(String maSV) {
        this.maSV = maSV;
    }

    public String getMaLop() {
        return maLop;
    }

    public void setMaLop(String maLop) {
        this.maLop = maLop;
    }

    public String getTenSV() {
        return tenSV;
    }

    public void setTenSV(String tenSV) {
        this.tenSV = tenSV;
    }

    public String getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public ph13373_SinhVien(String maSV, String maLop, String tenSV, String ngaySinh) {
        this.maSV = maSV;
        this.maLop = maLop;
        this.tenSV = tenSV;
        this.ngaySinh = ngaySinh;
    }
}
