/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quanltthuvien.Model;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Legion
 */
public class LiSuGiaoDich {
    private String MaGD;
    private String Username;
    private Date NgayGD;
    private String MaSach;
    private int SoLuong;
    private String TrangThai;

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public LiSuGiaoDich(String MaGD, String Username, String NgayGD, String MaSach, int SoLuong, String TrangThai) {
        this.MaGD = MaGD;
        this.Username = Username;
        setNgayGD(NgayGD);
        this.MaSach = MaSach;
        this.SoLuong = SoLuong;
        this.TrangThai = TrangThai;
    }

    public String getMaGD() {
        return MaGD;
    }

    public void setMaGD(String MaGD) {
        this.MaGD = MaGD;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String Username) {
        this.Username = Username;
    }

    public Date getNgayGD() {
        return NgayGD;
    }

    public void setNgayGD(String NgayGD) {
        try {
            java.util.Date utilDate = dateFormat.parse(NgayGD);
            this.NgayGD = new java.sql.Date(utilDate.getTime());
        } catch (Exception e) {
        }
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

    public String getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(String TrangThai) {
        this.TrangThai = TrangThai;
    }
    
}
