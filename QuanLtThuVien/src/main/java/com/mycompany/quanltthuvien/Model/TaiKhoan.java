package com.mycompany.quanltthuvien.Model;

import java.util.regex.Pattern;

public class TaiKhoan {
    private String username;
    private String password;
    private String role;
    private String TenTK;
    private String SdtTK;
    private static final Pattern PHONE_PATTERN = Pattern.compile("^[0-9]{10}$");

    public TaiKhoan(String username, String password, String role,String SdtTK, String TenTK) {
        this.username = username;
        this.password = password;
        this.role = role;
        setSdtTK(SdtTK);
        this.TenTK = TenTK;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    public String getTenTK() {
        return TenTK;
    }
    public void setTenTK(String TenTK) {
        this.TenTK = TenTK;
    }
    public String getSdtTK() {
        return SdtTK;
    }
    public void setSdtTK(String SdtTK) throws IllegalArgumentException {
        if (SdtTK == null || SdtTK.trim().isEmpty()) {
            throw new IllegalArgumentException("Số điện thoại không được để trống");
        }
        
        // Loại bỏ khoảng trắng
        SdtTK = SdtTK.trim();
        
        // Kiểm tra format: phải là 10 chữ số từ 0-9
        if (!PHONE_PATTERN.matcher(SdtTK).matches()) {
            throw new IllegalArgumentException(
                "Số điện thoại không hợp lệ. Phải có đúng 10 chữ số (0-9). Ví dụ: 0912345678"
            );
        }
        
        this.SdtTK = SdtTK;
    }

}
