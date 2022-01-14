package com.example.doancuoiky.model;

public class Account {
    private String email, hoten, diachi;
    private String sdt;

    public Account(String email, String hoten, String diachi, String sdt) {
        this.email = email;
        this.hoten = hoten;
        this.diachi = diachi;
        this.sdt = sdt;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }
}
