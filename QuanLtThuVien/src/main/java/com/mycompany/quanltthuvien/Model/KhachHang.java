/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quanltthuvien.Model;

import java.util.regex.Pattern;

/**
 *
 * @author Legion
 */
public class KhachHang {
    private String MaKH;
    private String TenKH;
    private String SdtKH;
    private static final Pattern PHONE_PATTERN = Pattern.compile("^[0-9]{10}$");

    
    
    public KhachHang(String MaKH, String TenKH, String SdtKH) {
        this.MaKH = MaKH;
        this.TenKH = TenKH;
        setSdtKH(SdtKH);
    }

    public KhachHang() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public String getMaKH() {
        return MaKH;
    }

    public void setMaKH(String MaKH) {
        this.MaKH = MaKH;
    }

    public String getTenKH() {
        return TenKH;
    }

    public void setTenKH(String TenKH) {
        this.TenKH = TenKH;
    }

    public String getSdtKH() {
        return SdtKH;
    }

    public void setSdtKH(String SdtKH) throws IllegalArgumentException {
        if (SdtKH == null || SdtKH.trim().isEmpty()) {
            throw new IllegalArgumentException("Số điện thoại không được để trống");
        }
        
        // Loại bỏ khoảng trắng
        SdtKH = SdtKH.trim();
        
        // Kiểm tra format: phải là 10 chữ số từ 0-9
        if (!PHONE_PATTERN.matcher(SdtKH).matches()) {
            throw new IllegalArgumentException(
                "Số điện thoại không hợp lệ. Phải có đúng 10 chữ số (0-9). Ví dụ: 0912345678"
            );
        }
        
        this.SdtKH = SdtKH;
    }

    
}
