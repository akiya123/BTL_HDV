/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quanltthuvien.Model;

import java.sql.Date;

/**
 *
 * @author Legion
 */
public class Muon {
    private String MaMuon;
    private String MaKH;
    private String MaSach;
    private int SoLuong;
    private Date NgayMuon;
    private Date NgayTra;

    public Muon(String MaMuon, String MaKH, String MaSach, int SoLuong, Date NgayMuon, Date NgayTra) {
        this.MaMuon = MaMuon;
        this.MaKH = MaKH;
        this.MaSach = MaSach;
        this.SoLuong = SoLuong;
        this.NgayMuon = NgayMuon;
        this.NgayTra = NgayTra;
    }

    public String getMaMuon() {
        return MaMuon;
    }

    public void setMaMuon(String MaMuon) {
        this.MaMuon = MaMuon;
    }

    public String getMaKH() {
        return MaKH;
    }

    public void setMaKH(String MaKH) {
        this.MaKH = MaKH;
    }

    public String getMaSach() {
        return MaSach;
    }

    public void setMaSach(String MaSach) {
        this.MaSach = MaSach;
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int SoLuong) {
        this.SoLuong = SoLuong;
    }

    public Date getNgayMuon() {
        return NgayMuon;
    }

    public void setNgayMuon(Date NgayMuon) {
        this.NgayMuon = NgayMuon;
    }

    public Date getNgayTra() {
        return NgayTra;
    }

    public void setNgayTra(Date NgayTra) {
        this.NgayTra = NgayTra;
    }
    
}
