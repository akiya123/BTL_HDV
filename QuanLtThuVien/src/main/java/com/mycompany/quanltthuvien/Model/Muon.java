/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quanltthuvien.Model;

import java.sql.Date;
import java.text.SimpleDateFormat;

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

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public Muon(String MaMuon, String MaKH, String MaSach, int SoLuong, String NgayMuon, String NgayTra) {
        this.MaMuon = MaMuon;
        this.MaKH = MaKH;
        this.MaSach = MaSach;
        this.SoLuong = SoLuong;
        setNgayTra(NgayTra);
        setNgayMuon(NgayMuon);
    }

    public Muon() {
        
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

    public void setNgayMuon(String NgayMuon) {
        try {
            java.util.Date utilDate = dateFormat.parse(NgayMuon);
            this.NgayMuon = new java.sql.Date(utilDate.getTime());
        } catch (Exception e) {
        }
    }

    public Date getNgayTra() {
        return NgayTra;
    }

    public void setNgayTra(String NgayTra) {
        try {
            java.util.Date utilDate = dateFormat.parse(NgayTra);
            this.NgayTra = new java.sql.Date(utilDate.getTime());
        } catch (Exception e) {
        }
    }
    
}
