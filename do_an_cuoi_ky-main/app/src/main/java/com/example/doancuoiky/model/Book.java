package com.example.doancuoiky.model;

import java.io.Serializable;

public class Book implements Serializable {
    private int maSach, soLuong;
    private String tenSach, hinhAnhSach, tacGia, nhaXuatBan, moTa, theLoai;
    private double donGia;
    private int tingTrang;
    public Book() {
    }


    public Book(int maSach, String tenSach, String hinhAnhSach, String moTa, double donGia) {
        this.maSach = maSach;
        this.tenSach = tenSach;
        this.hinhAnhSach = hinhAnhSach;
        this.moTa = moTa;
        this.donGia = donGia;
    }

    public Book(int maSach, String tenSach, String hinhAnhSach, String tacGia, String nhaXuatBan, String moTa, String theLoai, double donGia) {
        this.maSach = maSach;
        this.tenSach = tenSach;
        this.hinhAnhSach = hinhAnhSach;
        this.tacGia = tacGia;
        this.nhaXuatBan = nhaXuatBan;
        this.moTa = moTa;
        this.theLoai = theLoai;
        this.donGia = donGia;
    }

    public Book(int maSach, String tenSach, String hinhAnhSach, double donGia, int soluong) {
        this.maSach = maSach;
        this.tenSach = tenSach;
        this.hinhAnhSach = hinhAnhSach;
        this.donGia = donGia;
        this.soLuong = soluong;
    }

    public Book(int soLuong, String tenSach, double donGia, int tingTrang) {
        this.soLuong = soLuong;
        this.tenSach = tenSach;
        this.donGia = donGia;
        this.tingTrang = tingTrang;
    }

    public int getTingTrang() {
        return tingTrang;
    }

    public void setTingTrang(int tingTrang) {
        this.tingTrang = tingTrang;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public int getMaSach() {
        return maSach;
    }

    public void setMaSach(int maSach) {
        this.maSach = maSach;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public String getHinhAnhSach() {
        return hinhAnhSach;
    }

    public void setHinhAnhSach(String hinhAnhSach) {
        this.hinhAnhSach = hinhAnhSach;
    }

    public String getTacGia() {
        return tacGia;
    }

    public void setTacGia(String tacGia) {
        this.tacGia = tacGia;
    }

    public String getNhaXuatBan() {
        return nhaXuatBan;
    }

    public void setNhaXuatBan(String nhaXuatBan) {
        this.nhaXuatBan = nhaXuatBan;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public String getTheLoai() {
        return theLoai;
    }

    public void setTheLoai(String theLoai) {
        this.theLoai = theLoai;
    }

    public double getDonGia() {
        return donGia;
    }

    public void setDonGia(double donGia) {
        this.donGia = donGia;
    }
}
