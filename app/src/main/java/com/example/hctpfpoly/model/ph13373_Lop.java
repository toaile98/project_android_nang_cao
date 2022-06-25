package com.example.hctpfpoly.model;

import java.io.Serializable;

public class ph13373_Lop implements Serializable {
    private String maLop;
    private String tenLop;




    public ph13373_Lop(String maLop, String tenLop) {
        this.maLop = maLop;
        this.tenLop = tenLop;
    }


    public ph13373_Lop() {
    }

    public String getMaLop() {
        return maLop;
    }

    public void setMaLop(String maLop) {
        this.maLop = maLop;
    }

    public String getTenLop() {
        return tenLop;
    }

    public void setTenLop(String tenLop) {
        this.tenLop = tenLop;
    }
}
